package webservices;

import model.Arrangement;
import model.Minigame;
import model.Teacher;
import model.services.ArrangementService;
import model.services.ArrangementServiceProvider;
import model.services.MinigameService;
import model.services.MinigameServiceProvider;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

@Path("/arrangement")
public class ArrangementResource {
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

	@GET
	@Path("teacher/{teacherName}")
	@Produces("application/json")
	public List<Arrangement> getArrangementsByTeacher(@PathParam("teacherName") String teacherName) {
		ArrangementService service = ArrangementServiceProvider.getArrangementService();
		return service.getArrangementsByTeacher(teacherName);
	}

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

	@DELETE
	@Path("/delete/{id}")
	public boolean deleteArrangementById(@PathParam("id") int id) {
		System.out.println("hoi");
		ArrangementService service = ArrangementServiceProvider.getArrangementService();
		return service.deleteArrangementById(id);
	}
}
