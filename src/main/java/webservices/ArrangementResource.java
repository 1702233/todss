package webservices;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Arrangement;
import model.Minigame;
import model.Teacher;
import model.services.ArrangementService;
import model.services.ArrangementServiceProvider;
import model.services.MinigameService;
import model.services.MinigameServiceProvider;

@Path("/arrangement")
public class ArrangementResource {
	
	@RolesAllowed({"admin", "docent"})
	@GET
	@Produces("application/json")
	public List<Arrangement> getAllArrangements() {
		ArrangementService service = ArrangementServiceProvider.getArrangementService();
		return service.getAllArrangements();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Arrangement getArrangementByID(@PathParam("id") int ID) {
		ArrangementService service = ArrangementServiceProvider.getArrangementService();
		return service.getArrangementByID(ID);
	}

	@RolesAllowed({"admin","docent"})
	@GET
	@Path("teacher/{teacherName}")
	@Produces("application/json")
	public List<Arrangement> getArrangementsByTeacher(@PathParam("teacherName") String teacherName) {
		ArrangementService service = ArrangementServiceProvider.getArrangementService();
		return service.getArrangementsByTeacher(teacherName);
	}

	@RolesAllowed({"admin","docent"})
	@POST
	public Response addArrangement(String json) {
		ArrangementService arrangementService = ArrangementServiceProvider.getArrangementService();
		MinigameService minigameService = MinigameServiceProvider.getMinigameService();
		ArrayList<Minigame> allMinigames = new ArrayList<Minigame>();

		JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

		String name = jsonObject.get("name").getAsString();
		String description = jsonObject.get("description").getAsString();
		String teacher = jsonObject.get("teacher").getAsString();

		JsonArray jsonArray = jsonObject.getAsJsonArray("minigames");

		for (JsonElement minigameID : jsonArray) {
			int ID = minigameID.getAsInt();
			Minigame newMinigame = minigameService.getMinigameByID(ID);
			allMinigames.add(newMinigame);
		}

		Teacher newTeacher = new Teacher(teacher);

		Arrangement newArrangement = new Arrangement(name, description, allMinigames, newTeacher);

		if (arrangementService.saveArrangement(newArrangement)) {

			return Response.ok().build();

		} else {
			return Response.status(666).build();
		}

	}

	@RolesAllowed({"admin", "docent"})
	@DELETE
	@Path("/delete/{id}")
	public boolean deleteArrangementById(@PathParam("id") int id) {
		System.out.println("hoi");
		ArrangementService service = ArrangementServiceProvider.getArrangementService();
		return service.deleteArrangementById(id);
	}
}
