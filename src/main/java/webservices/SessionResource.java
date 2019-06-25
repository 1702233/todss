package webservices;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Arrangement;
import model.RandomString;
import model.Session;
import model.Student;
import model.services.ArrangementService;
import model.services.ArrangementServiceProvider;
import model.services.SessionService;
import model.services.SessionServiceProvider;
import persistance.ArrangementDao;
import persistance.ArrangementPostgresDaoImpl;

@Path("/session")
public class SessionResource {

	ArrangementDao aDao = new ArrangementPostgresDaoImpl();

	@GET
	@Path("/{teacherName}")
	@Produces("application/json")
	public ArrayList<Session> getSessionsByTeacher(@PathParam("teacherName") String teacherName) {
		SessionService sessionService = SessionServiceProvider.getSessionService();
		ArrangementService arrangementService = ArrangementServiceProvider.getArrangementService();
		
		ArrayList<Session> sessions = sessionService.getSessionsByTeacher(teacherName);
		for(Session session : sessions) {
			session.setArrangement(arrangementService.getArrangementByID(session.getArrangementID()));
		}
		return sessions;
	}

	@POST
	public Response addSession(@FormParam("opmerking") String opmerking, @FormParam("dropdown") int dropdown) {
		SessionService service = SessionServiceProvider.getSessionService();
		ArrayList<Student> allStudents = new ArrayList<Student>();
		Arrangement arrangement = aDao.findById(dropdown);

		String randomSessionString = RandomString.getAlphaNumericString();
		boolean sessionExists = service.checkSessionExists(randomSessionString);
		
		while(sessionExists) {
			randomSessionString = RandomString.getAlphaNumericString();
			sessionExists = service.checkSessionExists(randomSessionString);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		Session session = new Session(randomSessionString, opmerking, arrangement, allStudents);
		
		if (!sessionExists) {
			if (service.saveSession(session)) {
				return Response.ok(randomSessionString).build();
			} else {
				return Response.status(405).build();
			}
		} else {
			return Response.status(405).build();
		}

	}
	
	@POST
	@Path("/check")
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkSession(@FormParam("code") String code) {
		SessionService service = SessionServiceProvider.getSessionService();
		Session session = service.getSessionByCode(code);
		
		try {
			if (session == null) {
	            throw new IllegalArgumentException("No session found!");
			}
			return Response.ok(session.getArrangementID()).build();
	            
        }catch(IllegalArgumentException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
	}
	
	@DELETE
	@Path("/{pin}")
	public Response deleteAfgerondeTaak(@PathParam("pin") String pin) {
		SessionService service = SessionServiceProvider.getSessionService();
		if (!service.closeSession(pin)) {
			return Response.status(404).build();
		}

		return Response.ok().build();
	}
}
