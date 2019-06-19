package model.services;

import java.util.List;

import model.CardRule;
import model.Minigame;
import persistance.CardRuleDao;
import persistance.CardRulePostgresDaoImpl;


public class CardRuleService {
	private CardRuleDao cdao = new CardRulePostgresDaoImpl();

	public boolean saveCardRule(CardRule cr, int minigameid) {
		return cdao.saveCardRules(cr, minigameid);
		
	}

	public List<CardRule> getAllCardRules() {
		return cdao.findAllCardRules();
	}

}
