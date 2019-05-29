package persistance;

import java.util.ArrayList;


import model.CardRule;

public interface CardRuleDao {

	public ArrayList<CardRule> findAllCardRules();
	public ArrayList<CardRule> findByMinigame(int minigameID);
	public CardRule findByID(int ID);
	
	public boolean saveCardRules(CardRule cardRule);
	public boolean updateCardRule(CardRule cardRule);
	public boolean deleteCardRule(int ID);
}
