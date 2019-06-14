package webservices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.CardRule;
import model.Cardset;
import model.Minigame;
import model.Teacher;
import model.services.MinigameService;
import model.services.MinigameServiceProvider;
import persistance.CardsetDao;
import persistance.CardsetPostgresDaoImpl;
import persistance.TeacherDao;
import persistance.TeacherPostgresDaoImpl;

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
	
	@POST
	//@RolesAllowed("user")
	@Produces("application/json")
	public Response postMinigame(@FormParam("titel") String titel,
				@FormParam("speltype") String type,
				@FormParam("cardopened") boolean cardsopened,
				@FormParam("omschrijving") String omschrijving,
				@FormParam("teachernaam") String teachernaam,
			  	@FormParam("cardsetid") int cardsetid){

		MinigameService service = MinigameServiceProvider.getMinigameService();
		TeacherDao tDao = new TeacherPostgresDaoImpl();
		CardsetDao cDao = new CardsetPostgresDaoImpl();
		
		
		Teacher t1 = tDao.findByUsername(teachernaam);
		Cardset c1 = cDao.findByID(cardsetid);
		ArrayList<CardRule> cr1 = new ArrayList<CardRule>();
		
		Minigame newMinigame = new Minigame(1, titel, type, cardsopened, omschrijving, t1, c1, cr1);
	

		service.saveMinigame(newMinigame);

		return Response.ok(newMinigame).build();
	}

	@GET
	@Path("/teacher/{id}")
	@Produces("application/json")
	public List<Minigame> getMinigameByTeacher(@PathParam("id") String username) {
		return service.getMinigameByTeacher(username);

	}
}
