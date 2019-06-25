package webservices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import model.Session;
import model.Student;
import model.services.SessionService;
import model.services.SessionServiceProvider;
import model.services.StudentService;
import model.services.StudentServiceProvider;

@Path("/student")
public class StudentResource {

	StudentService service = StudentServiceProvider.getStudentService();
	SessionService sessionService = SessionServiceProvider.getSessionService();
	
	@POST
	public Response postStudent(@FormParam("naam") String name, @FormParam("code") String sessionCode) {
		
		System.out.println(name);
		System.out.println(sessionCode);
		
		int id = service.getMaxID() +1;
		Session session = sessionService.getSessionByCode(sessionCode);
		Student newStudent = new Student(id,name,null,session);
		service.saveStudent(newStudent);
		
		return Response.ok(id).build();
	}
}
