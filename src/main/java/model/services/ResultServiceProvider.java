package model.services;

public class ResultServiceProvider {

	private static ResultService service = new ResultService();

	public static ResultService getResultService() {
		return service;
	}
}
