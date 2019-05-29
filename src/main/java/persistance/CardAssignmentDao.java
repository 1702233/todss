package persistance;

import java.util.ArrayList;


import model.CardAssignment;

public interface CardAssignmentDao {

	public ArrayList<CardAssignment> findAllCardAssignments();
	public CardAssignment findByCardID(int ID);
	public ArrayList<CardAssignment> findByCardRuleID(int ID);
	public boolean saveCardAssignment(CardAssignment cardAssignment);
	public boolean updateCardAssignment(CardAssignment cardAssignment);
	public boolean deleteCardAssignment(int ID);
	
}
