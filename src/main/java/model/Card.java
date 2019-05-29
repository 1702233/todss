package model;

import java.util.ArrayList;

public class Card {

	private Cardside voorkant;
	private Cardside achterkant;
	private Cardset cardset;
	private ArrayList<CardAssignment> allCardAssignments;

	public Card(Cardside voorkant, Cardside achterkant, Cardset cardset, ArrayList<CardAssignment> allCardAssignments) {
		super();
		this.voorkant = voorkant;
		this.achterkant = achterkant;
		this.cardset = cardset;
		this.allCardAssignments = allCardAssignments;
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

	public ArrayList<CardAssignment> getCardAssignment() {
		return allCardAssignments;
	}

	public void setCardAssignment(ArrayList<CardAssignment> allCardAssignments) {
		this.allCardAssignments = allCardAssignments;
	}

}
