package webservices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import model.Result;
import model.Session;
import model.Student;
import model.services.ResultService;
import model.services.ResultServiceProvider;
import model.services.SessionService;
import model.services.SessionServiceProvider;
import model.services.StudentService;
import model.services.StudentServiceProvider;

@Path("/student")
public class StudentResource {

	StudentService service = StudentServiceProvider.getStudentService();
	SessionService sessionService = SessionServiceProvider.getSessionService();
	ResultService resultService = ResultServiceProvider.getResultService();
	
	@POST
	public Response postStudent(@FormParam("naam") String name, @FormParam("code") String sessionCode) {
		
		int id = service.getMaxID() +1;
		Session session = sessionService.getSessionByCode(sessionCode);
		Student newStudent = new Student(id,name,null,session);
		service.saveStudent(newStudent);
		
		return Response.ok(id).build();
	}
	
	@GET
	@Path("/results/{id}")
	@Produces("application/json")
	public String getResultsByStudent(@PathParam("id") int studentID){
		JsonArray results = new JsonArray();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		
		for(Result result : resultService.getResultsForStudent(studentID)) {
			
			JsonObject resultObject = new JsonObject();
			resultObject.addProperty("starttime", sdf.format(result.getStart()));
			resultObject.addProperty("endtime", sdf.format(result.getEnd()));
			resultObject.addProperty("minigamename", result.getMinigame().getName());
			results.add(resultObject);
		}
		
		return results.toString();
	}
}
