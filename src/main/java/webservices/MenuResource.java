package webservices;

import model.*;
import model.services.MenuService;
import model.services.MenuServiceProvider;
import model.services.SessionService;
import model.services.SessionServiceProvider;
import persistance.ArrangementDao;
import persistance.ArrangementPostgresDaoImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Path("/menu")
public class MenuResource {

	@GET
	@Produces("application/json")
	public Menu getMenu() {
		MenuService service = MenuServiceProvider.getMenuService();

		Teacher teacher = new Teacher("leraar1", "wachtwoord1");
		return service.getMenu(teacher);
	}
}
