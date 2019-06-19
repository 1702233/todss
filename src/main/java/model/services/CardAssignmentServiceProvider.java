package model.services;

public class CardAssignmentServiceProvider {
	private static CardAssignmentService service = new CardAssignmentService();
	
	public static CardAssignmentService getCardAssignmentService() {
		return service;
	}
}
