package webservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import model.Card;
import model.CardService;
import model.CardServiceProvider;

@Path("/card")
public class CardResource {
	
	@GET
	@Path("/cardset")
	@Produces("application/json")
	public List<Card> getCardsByCardset(@PathParam("cardset") int cardset){
		CardService service = CardServiceProvider.getCardService();
		return service.getCardsByCardset(cardset);
		
	}
}
