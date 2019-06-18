package model;

import java.util.ArrayList;

public class Minigame {
	private int id;
	private String name;
	private String type;
	private boolean cardsOpened;
	private String omschrijving;

	private ArrayList<Grade> allBeoordelingen;
	private Teacher teacher;
	private Cardset cardset;
	private ArrayList<CardRule> cardRules;
	private ArrayList<Tag> allTags;
	private ArrayList<Result> allResults;

	public Minigame(int id, String name, String type, boolean cardsOpened, String omschrijving,
			ArrayList<Grade> allBeoordelingen, Teacher teacher, Cardset cardset, ArrayList<CardRule> cardRules,
			ArrayList<Tag> allTags, ArrayList<Result> allResults) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.cardsOpened = cardsOpened;
		this.omschrijving = omschrijving;
		this.allBeoordelingen = allBeoordelingen;
		this.teacher = teacher;
		this.cardset = cardset;
		this.cardRules = cardRules;
		this.allTags = allTags;
		this.allResults = allResults;
	}

	public Minigame(int id, String name, String type, boolean cardsOpened, String omschrijving, Teacher teacher, Cardset cardset,
			ArrayList<CardRule> cardRules) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;

		this.cardsOpened = cardsOpened;
		this.omschrijving = omschrijving;
		this.teacher = teacher;
		this.cardset = cardset;
		this.cardRules = cardRules;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isCardsOpened() {
		return cardsOpened;
	}

	public void setCardsOpened(boolean cardsOpened) {
		this.cardsOpened = cardsOpened;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	public ArrayList<Grade> getAllBeoordelingen() {
		return allBeoordelingen;
	}

	public void setAllBeoordelingen(ArrayList<Grade> allBeoordelingen) {
		this.allBeoordelingen = allBeoordelingen;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Cardset getCardset() {
		return cardset;
	}

	public void setCardset(Cardset cardset) {
		this.cardset = cardset;
	}

	public ArrayList<CardRule> getCardRules() {
		return cardRules;
	}

	public void setCardRules(ArrayList<CardRule> cardRules) {
		this.cardRules = cardRules;
	}

	public ArrayList<Tag> getAllTags() {
		return allTags;
	}

	public void setAllTags(ArrayList<Tag> allTags) {
		this.allTags = allTags;
	}

	public ArrayList<Result> getAllResults() {
		return allResults;
	}

	public void setAllResults(ArrayList<Result> allResults) {
		this.allResults = allResults;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Minigame [id=" + id + ", name=" + name + ", type=" + type + ", cardsOpened=" + cardsOpened
				+ ", omschrijving=" + omschrijving + ", allBeoordelingen=" + allBeoordelingen + ", teacher=" + teacher
				+ ", cardset=" + cardset + ", cardRules=" + cardRules + ", allTags=" + allTags + ", allResults="
				+ allResults + "]";
	}
	
	

}
