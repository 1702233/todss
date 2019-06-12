package webservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import model.Minigame;
import model.services.MinigameService;
import model.services.MinigameServiceProvider;

@Path("/minigames")
public class MinigameResource {
	
	MinigameService service = MinigameServiceProvider.getMinigameService();
	
	@GET
	@Produces("application/json")
	public List<Minigame> getAllMinigames(){	
		return service.getAllMinigames();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Minigame getMinigameByID(@PathParam("id") int ID) {
		return service.getMinigameByID(ID);
	}
	
	@GET
	@Path("/teacher/{id}")
	@Produces("application/json")
	public List<Minigame> getMinigameByTeacher(@PathParam("id") String username) {
		return service.getMinigameByTeacher(username);
	}
}
