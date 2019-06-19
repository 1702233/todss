package webservices;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.CardRule;

@Path("/cardrule")
public class CardRuleResource {
	@POST
	@Produces("application/json")
	public boolean SaveCardRules(String json) {
		try {
			JsonArray obj = new JsonParser().parse(json).getAsJsonArray();
			System.out.println(obj);
			int index = 0;
			for (JsonElement o: obj) {
				System.out.println(o);
				JsonObject  jobject = o.getAsJsonObject();
				System.out.println(jobject.get("set"+index).toString());
				
				index++;
			}
			
			return true;
		} catch(Exception e) {
			System.out.println(e);
			return false;
		}
		
	}

}
