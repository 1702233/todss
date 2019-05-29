package model;

public class Cardside {

	private int ID;
	private String tekst;
	private Picture picture;
	private Card card;

	public Cardside(int iD, String tekst, Picture picture, Card card) {
		super();
		ID = iD;
		this.tekst = tekst;
		this.picture = picture;
		this.card = card;
	}

	public Cardside(int iD, String tekst, Picture picture) {
		super();
		ID = iD;
		this.tekst = tekst;
		this.picture = picture;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

}
