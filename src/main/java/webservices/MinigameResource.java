package webservices;

import java.util.ArrayList;
import java.util.Comparator;
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

import io.jsonwebtoken.lang.Collections;
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
	@Produces("application/json")
	@Path("/arrangement/{id}")
	public List<Minigame> getMinigamesByArrangementID(@PathParam("id") int ID){
		System.out.println("test");
		return service.getMinigamesByArrangementID(ID);
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
				@FormParam("cardopened") String cardsopened,
				@FormParam("omschrijving") String omschrijving,
				@FormParam("teachernaam") String teachernaam,
			  	@FormParam("cardsetid") int cardsetid){

		MinigameService service = MinigameServiceProvider.getMinigameService();
		TeacherDao tDao = new TeacherPostgresDaoImpl();
		CardsetDao cDao = new CardsetPostgresDaoImpl();
		
		Teacher t1 = tDao.findByUsername(teachernaam);
		Cardset c1 = cDao.findByID(cardsetid);
		ArrayList<CardRule> cr1 = new ArrayList<CardRule>();
		
		boolean cardstart;
		if (cardsopened == "dicht") {
			cardstart = false;
		} else{
			cardstart = true;
		} 

		Minigame newMinigame = new Minigame(1, titel, type, cardstart, omschrijving, t1, c1, cr1);
		service.saveMinigame(newMinigame);
		List<Minigame> allminigames = service.getAllMinigames();
		Minigame maxint = allminigames.stream().max(Comparator.comparingInt(Minigame::getId)).get();

		return Response.ok(maxint).build();
	}

	@GET
	@Path("/teacher/{username}")
	@Produces("application/json")
	public List<Minigame> getMinigameByTeacher(@PathParam("username") String username) {
		return service.getMinigameByTeacher(username);

	}
}
