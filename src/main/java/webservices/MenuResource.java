package webservices;

import model.Arrangement;
import model.RandomString;
import model.Session;
import model.Student;
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

	ArrangementDao aDao = new ArrangementPostgresDaoImpl();

	@GET
	@Produces("application/json")
	public ArrayList<Session> getMenu() {
		return null;
	}
}
