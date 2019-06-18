package model;

public class Cardside {

	private int ID;
	private String tekst;
	private Picture picture;

	public Cardside(int iD, String tekst) {
		super();
		ID = iD;
		this.tekst = tekst;
	}

	public Cardside(int iD, Picture picture) {
		super();
		ID = iD;
		this.picture = picture;
	}

	public Cardside(String tekst) {
		super();
		this.tekst = tekst;
	}

	public Cardside(Picture picture) {
		super();
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

	@Override
	public String toString() {
		return "Cardside{" +
				"ID=" + ID +
				", tekst='" + tekst + '\'' +
				", picture=" + picture +
				'}';
	}
}
