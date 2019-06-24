package webservices;

import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;

import model.services.ResultService;
import model.services.ResultServiceProvider;

@Path("/result")
public class ResultResource {

	@GET
	@Path("/{code}")
	@Produces("application/json")
	public String getResultsForSession(@PathParam("code") String code) {
		ResultService resultService = ResultServiceProvider.getResultService();
		return resultService.getResultsForSession(code).toString();
	}
}
