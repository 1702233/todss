package model.services;

import model.CardAssignment;
import model.CardRule;

import persistance.CardAssignmentDao;
import persistance.CardAssignmentPostgresDaoImpl;

public class CardAssignmentService {
	private CardAssignmentDao cadao = new CardAssignmentPostgresDaoImpl();

	public boolean saveCardAssignment(CardAssignment ca, int maxint) {
		return cadao.saveCardAssignment(ca, maxint);
		
	}

}
