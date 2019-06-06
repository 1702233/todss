package webservices;

import model.Arrangement;
import model.Minigame;
import model.services.ArrangementService;
import model.services.ArrangementServiceProvider;
import model.services.MinigameService;
import model.services.MinigameServiceProvider;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/arrangements")
public class ArrangementResource {
	@GET
	@Produces("application/json")
	public List<Arrangement> getAllArrangements(){
		System.out.println("test");
		ArrangementService service = ArrangementServiceProvider.getArrangementService();
		return service.getAllArrangements();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Arrangement getArrangementByID(@PathParam("id") int ID) {
		ArrangementService service = ArrangementServiceProvider.getArrangementService();
		return service.getArrangementByID(ID);
	}

	@GET
	@Path("teacher/{teacherName}")
	@Produces("application/json")
	public List<Arrangement> getArrangementsByTeacher(@PathParam("teacherName") String teacherName) {
		ArrangementService service = ArrangementServiceProvider.getArrangementService();
		return service.getArrangementsByTeacher(teacherName);
	}
}
