package webservices;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;

import model.*;
import model.services.CardSetService;
import model.services.CardSetServiceProvider;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import persistance.CardsetPostgresDaoImpl;
import persistance.TeacherPostgresDaoImpl;


@Path("/cardset")
public class CardsetResource {
	
	@RolesAllowed({"admin", "docent"})
	@GET
	@Produces("application/json")
	public List<Cardset> findAllCardsets(){
		CardSetService service = CardSetServiceProvider.getCardSetService();
		return service.findAllCardsets();
	}
	
	@RolesAllowed({"admin", "docent"})
	@GET
	@Path("{teacher}")
	@Produces("application/json")
	public List<Cardset> findByTeacher(@PathParam("teacher") String teacher){
		CardSetService service = CardSetServiceProvider.getCardSetService();
		return service.findByTeacher(teacher);
	}

	@RolesAllowed({"admin", "docent"})
	@POST
	@Produces("application/json")
	public boolean saveCardset(String json) {
		try {
			JsonObject obj = new JsonParser().parse(json).getAsJsonObject();

			String cardsetName = obj.get("title").getAsString();

			TeacherPostgresDaoImpl teacherdao = new TeacherPostgresDaoImpl();
			Teacher teacher = teacherdao.findByUsername(obj.get("teacherName").getAsString());

			Cardset cardset = new Cardset(cardsetName, teacher, new ArrayList<Card>());

			JsonObject backside = obj.get("backside").getAsJsonObject();
			String backsideText = backside.get("text").getAsString();
			String backsideImage = backside.get("image").getAsString();

			Cardside back = createCardside(backsideText, backsideImage, teacher);

			JsonArray frontside = obj.get("frontside").getAsJsonArray();

			for (JsonElement element : frontside) {
				JsonObject cardObject = element.getAsJsonObject();
				String frontsideText = cardObject.get("text").getAsString();
				String frontsideImage = cardObject.get("image").getAsString();

				Cardside front = createCardside(frontsideText, frontsideImage, teacher);

				Card card = new Card(front, back);

				cardset.addCard(card);
			}

			CardSetService service = CardSetServiceProvider.getCardSetService();
			return service.saveCardset(cardset);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@RolesAllowed({"admin", "docent"})
	@DELETE
	@Path("/delete/{id}")
	public boolean deleteArrangementById(@PathParam("id") int id) {
		CardSetService service = CardSetServiceProvider.getCardSetService();
		return service.deleteCardsetById(id);
	}

	private Cardside createCardside(String text, String image, Teacher teacher) {
		if (text.equals("") || text.isEmpty() || text == null) {
			Picture picture = new Picture(image, teacher);
			return new Cardside(picture);
		} else {
			return new Cardside(text);
		}
	}
}
