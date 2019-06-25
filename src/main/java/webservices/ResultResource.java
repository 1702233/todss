package webservices;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Minigame;
import model.Result;
import model.Student;
import model.services.MinigameService;
import model.services.MinigameServiceProvider;
import model.services.ResultService;
import model.services.ResultServiceProvider;
import model.services.StudentService;
import model.services.StudentServiceProvider;

@Path("/result")
public class ResultResource {
	
	ResultService service = ResultServiceProvider.getResultService();

	@RolesAllowed({"admin", "docent"})
	@GET
	@Path("/{code}")
	@Produces("application/json")
	public String getResultsForSession(@PathParam("code") String code) {
		ResultService resultService = ResultServiceProvider.getResultService();
		return resultService.getResultsForSession(code).toString();
	}
	
	
	@POST
	public Response postResult(String json) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
		
		int studentID = jsonObject.get("student").getAsInt();
		int minigameID = jsonObject.get("minigame").getAsInt();
		String timeStartedString = jsonObject.get("timeStarted").getAsString();
		String timeEndedString = jsonObject.get("timeDone").getAsString();
		
		timeStartedString = timeStartedString.replace("T", " ");
		timeEndedString = timeEndedString.replace("T", " ");
		
		String[] splittedStartString = timeStartedString.split("Z");
		String[] splittedEndString = timeEndedString.split("Z");
		
		
		Timestamp start = Timestamp.valueOf(splittedStartString[0]);
		Timestamp end = Timestamp.valueOf(splittedEndString[0]);		
		
		StudentService studentService = StudentServiceProvider.getStudentService();
		MinigameService minigameService = MinigameServiceProvider.getMinigameService();
		
		Student student = studentService.getStudentByID(studentID);
		Minigame minigame = minigameService.getMinigameByID(minigameID);
		
		
		Result newResult = new Result(start,end,student,minigame);
		if(service.saveResult(newResult)) {
			return Response.ok().build();
		}
		else {
			return Response.status(405).build(); 
		}
		
		
	}
}
