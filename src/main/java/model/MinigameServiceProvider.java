package model;

public class MinigameServiceProvider {
	private static MinigameService service = new MinigameService();
	
	public static MinigameService getMinigameService() {
		return service;
	}
}
