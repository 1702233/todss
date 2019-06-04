package persistance;

import java.util.ArrayList;


import model.CardRule;

public interface CardRuleDao {

	public ArrayList<CardRule> findAllCardRules();
	public ArrayList<CardRule> findByMinigame(int minigameID);
	public CardRule findByID(int ID);
	
	public boolean saveCardRules(CardRule cardRule, int minigameID);
	public boolean updateCardRule(CardRule cardRule, int minigameID);
	public boolean deleteCardRule(int ID);
}
