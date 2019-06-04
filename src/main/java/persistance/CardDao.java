package persistance;

import java.util.ArrayList;


import model.Card;

public interface CardDao {

	public ArrayList<Card> findAllCards();
	public ArrayList<Card> findCardsOfCardset(int cardsetID);
	public Card findById(int ID);
	public boolean saveCard(Card card, int cardsetID);
	public boolean updateCard(Card card, int cardsetID);
	public boolean deleteCard(int ID);
	
}
