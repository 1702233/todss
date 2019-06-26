package webservices;

import model.*;
import model.services.*;
import persistance.ArrangementDao;
import persistance.ArrangementPostgresDaoImpl;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Path("/menu")
public class MenuResource {

	@RolesAllowed({"admin", "docent"})
	@GET
	@Produces("application/json")
	public Menu getMenu(@Context SecurityContext securityContext) {
		String name = securityContext.getUserPrincipal().getName();

		AccountService accountService = AccountServiceProvider.getAccountService();

		String role = accountService.findRoleForUserByUsername(name);

		MenuService service = MenuServiceProvider.getMenuService();

		return service.getMenu(role);
	}
}
