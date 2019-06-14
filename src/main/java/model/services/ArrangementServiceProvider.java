package model.services;

public class ArrangementServiceProvider {
	private static ArrangementService service = new ArrangementService();
	
	public static ArrangementService getArrangementService() {
		return service;
	}
}
