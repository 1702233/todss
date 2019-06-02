package model;

import java.util.List;

import persistance.CardDao;
import persistance.CardPostgresDaoImpl;

public class CardService {
	private CardDao dao = new CardPostgresDaoImpl();
	
	public Card getCardByID(int ID) {
		return dao.findById(ID);
	}
	
	public List<Card> getCardsByCardset(int cardSet){
		return dao.findCardsOfCardset(cardSet);
	}
}
