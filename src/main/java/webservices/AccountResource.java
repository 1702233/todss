package webservices;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Teacher;
import model.services.AccountService;
import model.services.AccountServiceProvider;

@Path("/account")
public class AccountResource {

	
	private AccountService service = AccountServiceProvider.getAccountService();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Teacher> getAllTeachers() {
		return service.getAllTeachers();
	}
	
	
	@POST
	public Response addTeacher(@FormParam("username") String username, @FormParam("password") String password) {

		Teacher teacher = new Teacher(username, password);
		
		if (service.saveAccount(teacher)) {

			return Response.ok().build();
		} else {
			return Response.status(404).build();
		}
	}
}
