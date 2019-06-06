package webservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import model.Cardset;
import model.services.CardSetService;
import model.services.CardSetServiceProvider;


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
	
}
