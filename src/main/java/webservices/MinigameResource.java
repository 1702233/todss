package webservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import model.Minigame;
import model.MinigameService;
import model.MinigameServiceProvider;

@Path("/minigames")
public class MinigameResource {
	
	@GET
	@Produces("application/json")
	public List<Minigame> getAllMinigames(){
		System.out.println("test");
		MinigameService service = MinigameServiceProvider.getMinigameService();
		return service.getAllMinigames();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Minigame getMinigameByID(@PathParam("id") int ID) {
		MinigameService service = MinigameServiceProvider.getMinigameService();
		return service.getMinigameByID(ID);
	}
}
