package webservices;

import java.util.List;

import javax.ws.rs.*;

import model.Cardset;
import model.services.CardSetService;
import model.services.CardSetServiceProvider;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Path("/cardset")
public class CardsetResource {
	@GET
	@Produces("application/json")
	public List<Cardset> findAllCardsets(){
		System.out.println("test");
		CardSetService service = CardSetServiceProvider.getCardSetService();
		return service.findAllCardsets();
	}
	
	@GET
	@Path("{teacher}")
	@Produces("application/json")
	public List<Cardset> findByTeacher(@PathParam("teacher") String teacher){
		System.out.println("test");
		CardSetService service = CardSetServiceProvider.getCardSetService();
		return service.findByTeacher(teacher);
	}

	@POST
	@Produces("application/json")
	public Boolean saveCardset(String json) {
		try {
			JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
			System.out.println(obj);

			JsonObject backside = obj.get("backside").getAsJsonObject();
			String backsideText = backside.get("text").getAsString();
			String backsideImage = backside.get("image").getAsString();

			JsonObject frontside = obj.get("frontside").getAsJsonObject();

		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		return true;
	}
}
