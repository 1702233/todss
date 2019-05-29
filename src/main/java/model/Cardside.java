package model;

public class Cardside {

	private String tekst;

	private Picture picture;
	private Card card;

	public Cardside(String tekst, Picture picture, Card card) {
		super();
		this.tekst = tekst;
		this.picture = picture;
		this.card = card;
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
