package webservices;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.Arrangement;
import model.Session;
import model.Student;
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
		SessionService service = SessionServiceProvider.getSessionService();
		return service.getSessionsByTeacher(teacherName);
	}
	
	@POST
	public Response addSession(@FormParam("opmerking") String opmerking, @FormParam("dropdown") int dropdown) {
		SessionService service = SessionServiceProvider.getSessionService();
		ArrayList<Student> allStudents = new ArrayList<Student>();
		Arrangement arrangement = aDao.findById(dropdown);
		
		Session session = new Session("ABC1234", opmerking, arrangement, allStudents);
		
		if(service.saveSession(session)) {
			return Response.ok().build();
		} else {
			return Response.status(404).build();
		}
	
	}
}
