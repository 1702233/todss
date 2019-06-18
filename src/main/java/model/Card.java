package model;

public class Card {

	private int ID;
	private Cardside frontside;
	private Cardside backside;

	public Card(Cardside voorkant, Cardside achterkant, int iD) {
		super();
		this.frontside = voorkant;
		this.backside = achterkant;
		ID = iD;
	}

	public Card(Cardside voorkant, Cardside achterkant) {
		super();
		this.frontside = voorkant;
		this.backside = achterkant;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Cardside getFrontside() {
		return frontside;
	}

	public void setFrontside(Cardside voorkant) {
		this.frontside = voorkant;
	}

	public Cardside getBackside() {
		return backside;
	}

	public void setBackside(Cardside achterkant) {
		this.backside = achterkant;
	}

	@Override
	public String toString() {
		return "Card{" +
				"ID=" + ID +
				", frontside=" + frontside +
				", backside=" + backside +
				'}';
	}
}
