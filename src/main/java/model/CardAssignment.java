package model;

public class CardAssignment {
	private int rank;

	private Card card;
	private CardRule cardRule;

	public CardAssignment(int rank, Card card, CardRule cardRule) {
		super();
		this.rank = rank;
		this.card = card;
		this.cardRule = cardRule;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public CardRule getCardRule() {
		return cardRule;
	}

	public void setCardRule(CardRule cardRule) {
		this.cardRule = cardRule;
	}

}
