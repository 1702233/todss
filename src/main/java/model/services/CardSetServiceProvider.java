package model.services;

public class CardSetServiceProvider {
	
	private static CardSetService service = new CardSetService();
	
	public static CardSetService getCardSetService() {
		return service;

	}
}
