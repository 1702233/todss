package webservices;

import java.util.List;

import javax.ws.rs.*;

import model.Cardset;
import model.services.CardSetService;
import model.services.CardSetServiceProvider;

import org.json.JSONArray;
import org.json.JSONObject;


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
		System.out.println("Json: " + json);
		try {
			System.out.println("joehoe");
			JSONObject obj = new JSONObject(json);
			System.out.println(obj);
			String backsideText = obj.getJSONObject("backside").getString("text");
			System.out.println(backsideText);
			System.out.println("hoi");
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		System.out.println("end");
		return true;
	}
}
