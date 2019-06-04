package model.services;

public class CardServiceProvider {
	private static CardService service = new CardService();
	
	public static CardService getCardService() {
		return service;
	}
}
