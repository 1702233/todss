package model;

public class CardServiceProvider {
	private static CardService service = new CardService();
	
	public static CardService getCardService() {
		return service;
	}
}
