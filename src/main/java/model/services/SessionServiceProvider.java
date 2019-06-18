package model.services;

public class SessionServiceProvider {
private static SessionService service = new SessionService();
	
	public static SessionService getSessionService() {
		return service;
	}
}
