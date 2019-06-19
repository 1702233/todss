package model.services;

public class CardRuleServiceProvider {
	private static CardRuleService service = new CardRuleService();
	
	public static CardRuleService getCardRuleService() {
		return service;
	}
}
