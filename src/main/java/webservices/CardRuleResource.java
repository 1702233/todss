package webservices;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
	@POST
	@Produces("application/json")
	public boolean SaveCardRules(String json) {
		MinigameService minigameService = MinigameServiceProvider.getMinigameService();
		CardRuleService cardruleService = CardRuleServiceProvider.getCardRuleService();
		CardAssignmentService cardassignmentService = CardAssignmentServiceProvider.getCardAssignmentService();
		CardService cardService = CardServiceProvider.getCardService();
		try {
			System.out.println(json);
			JsonArray sets = (JsonArray) new JsonParser().parse(json);
			JsonObject jsonObjectmi = (JsonObject) sets.get(sets.size()-1);
			int minigameid = jsonObjectmi.get("minigameid").getAsInt();
			System.out.println(minigameid);
			for (int i = 0; i < (sets.size()); i++) {
				int group = i + 1;
				System.out.println("1    " + sets.get(i));
				JsonObject jsonObject = (JsonObject) sets.get(i);
				String value = jsonObject.get("set"+i).getAsString();
				String[] setvalues = value.split(" ");
				i =1;
				for (String s : setvalues) {
					System.out.println(s);
					CardRule cr = new CardRule(1, "set", false, group, new ArrayList<CardAssignment>());
					cardruleService.saveCardRule(cr, minigameid);
					List<CardRule> allcardrule = cardruleService.getAllCardRules();
					CardRule maxint = allcardrule.stream().max(Comparator.comparingInt(CardRule::getID)).get();
					Card c = cardService.getCardByID(Integer.parseInt(s));
					CardAssignment ca = new CardAssignment(1, i, c);
					cardassignmentService.saveCardAssignment(ca, maxint.getID());
					i++;
				}
			}
			
			return true;
		} catch(Exception e) {
			System.out.println(e);
			return false;
		}
		
	}

}
