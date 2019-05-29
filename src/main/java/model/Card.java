package model;

public class Card {

	private int ID;
	private Cardside voorkant;
	private Cardside achterkant;
	private Cardset cardset;
	private CardAssignment cardAssignment;

	public Card(Cardside voorkant, Cardside achterkant, Cardset cardset, CardAssignment cardAssignment, int iD) {
		super();
		this.voorkant = voorkant;
		this.achterkant = achterkant;
		this.cardset = cardset;
		this.cardAssignment = cardAssignment;
		ID = iD;
	}

	public Card(Cardside voorkant, Cardside achterkant, Cardset cardset, CardAssignment cardAssignment) {
		super();
		this.voorkant = voorkant;
		this.achterkant = achterkant;
		this.cardset = cardset;
		this.cardAssignment = cardAssignment;
	}

	

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Cardside getVoorkant() {
		return voorkant;
	}

	public void setVoorkant(Cardside voorkant) {
		this.voorkant = voorkant;
	}

	public Cardside getAchterkant() {
		return achterkant;
	}

	public void setAchterkant(Cardside achterkant) {
		this.achterkant = achterkant;
	}

	public Cardset getCardset() {
		return cardset;
	}

	public void setCardset(Cardset cardset) {
		this.cardset = cardset;
	}

	public CardAssignment getCardAssignment() {
		return cardAssignment;
	}

	public void setCardAssignment(CardAssignment cardAssignment) {
		this.cardAssignment = cardAssignment;
	}

}
