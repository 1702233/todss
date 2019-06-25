package webservices;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import model.Card;
import model.services.CardService;
import model.services.CardServiceProvider;

@Path("/card")
public class CardResource {
	
	@GET
	@Path("{ID}")
	@Produces("application/json")
	@RolesAllowed({"admin","docent"})
	public Card getCardByID(@PathParam("ID") int ID) {
		CardService service = CardServiceProvider.getCardService();
		return service.getCardByID(ID);
	}
	
	@GET
	@Produces("application/json")
	@RolesAllowed({"admin","docent"})
	public List<Card> getAllCards(){
		CardService service = CardServiceProvider.getCardService();
		return service.getAllCards();
	}
}
