package webservices;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Card;
import model.CardAssignment;
import model.CardRule;
import model.Minigame;
import model.services.CardAssignmentService;
import model.services.CardAssignmentServiceProvider;
import model.services.CardRuleService;
import model.services.CardRuleServiceProvider;
import model.services.CardService;
import model.services.CardServiceProvider;
import model.services.MinigameService;
import model.services.MinigameServiceProvider;

@Path("/cardrule")
public class CardRuleResource {
	@RolesAllowed({"admin", "docent"})
	@POST
	@Produces("application/json")
	public boolean SaveCardRules(String json) {
		MinigameService minigameService = MinigameServiceProvider.getMinigameService();
		CardRuleService cardruleService = CardRuleServiceProvider.getCardRuleService();
		CardAssignmentService cardassignmentService = CardAssignmentServiceProvider.getCardAssignmentService();
		CardService cardService = CardServiceProvider.getCardService();

		try {
			JsonArray sets = (JsonArray) new JsonParser().parse(json);
			JsonObject jsonObjectmi = (JsonObject) sets.get(sets.size()-1);
			int minigameid = jsonObjectmi.get("minigameid").getAsInt();
			// for loop voor elke item in de jsonArray, behalve op de laatste index want daar staat alleen minigameID in.
			for (int i = 0; i < (sets.size()-1); i++) {
				int group = i + 1;
				//maak een jsonobject aan van de item op de index van de loop.
				JsonObject jsonObject = (JsonObject) sets.get(i);
				//haalt de inhoudt van het jsonobject op.
				String value = jsonObject.get("set"+i).getAsString();
				//split de inhoudt op alle spaties en wordt dus een list van alle cardids als strings.
				String[] setvalues = value.split(" ");
				//maak cardrule object aan.
				CardRule cr = new CardRule(1, "set", false, group, new ArrayList<CardAssignment>());
				//slaat de cardrule op in de database.
				cardruleService.saveCardRule(cr, minigameid);
				//haalt de cardrule die net is aangemaakt op uit de database als maxint.
				List<CardRule> allcardrule = cardruleService.getAllCardRules();
				CardRule maxint = allcardrule.stream().max(Comparator.comparingInt(CardRule::getID)).get();
				//tweede index int variable "in"
				int in =1;
				//voor elke kaartid die meegegeven in 1 groep wordt een forloop uitgevoert.
				for (String s : setvalues) {
					//haalt een cardobject op uit de database die overeenkomt met de meegegeven cardid.
					Card c = cardService.getCardByID(Integer.parseInt(s));
					//maakt een cardassignment object aan die je koppelt aan de net opgehaalde Card.
					CardAssignment ca = new CardAssignment(1, in, c);
					//sla de cardassignment op in de database.
					cardassignmentService.saveCardAssignment(ca, maxint.getID());
					in++;
				}
			}
			//return true als alles opgeslagen is.
			return true;
		} catch(Exception e) {
			System.out.println(e);
			//return false als er iets mis is gegaan.
			return false;
		}
		
	}

}
