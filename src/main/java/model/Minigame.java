package model;

import java.util.ArrayList;

public class Minigame {
	
	private String name;
	private int id;
	private boolean cardsOpened;
	private String omschrijving;

	private ArrayList<Arrangement> allArangementen;
	private ArrayList<Beoordeling> allBeoordelingen;
	private Teacher teacher;
	private Cardset cardset;
	private CardRule cardRule;
	private ArrayList<Tag> allTags;
	private ArrayList<Result> allResults;
	
	public Minigame(String name, int id, boolean cardsOpened, String omschrijving,
			ArrayList<Arrangement> allArangementen, ArrayList<Beoordeling> allBeoordelingen, Teacher teacher,
			Cardset cardset, CardRule cardRule, ArrayList<Tag> allTags, ArrayList<Result> allResults) {
		super();
		this.name = name;
		this.id = id;
		this.cardsOpened = cardsOpened;
		this.omschrijving = omschrijving;
		this.allArangementen = allArangementen;
		this.allBeoordelingen = allBeoordelingen;
		this.teacher = teacher;
		this.cardset = cardset;
		this.cardRule = cardRule;
		this.allTags = allTags;
		this.allResults = allResults;
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
	public ArrayList<Arrangement> getAllArangementen() {
		return allArangementen;
	}
	public void setAllArangementen(ArrayList<Arrangement> allArangementen) {
		this.allArangementen = allArangementen;
	}
	public ArrayList<Beoordeling> getAllBeoordelingen() {
		return allBeoordelingen;
	}
	public void setAllBeoordelingen(ArrayList<Beoordeling> allBeoordelingen) {
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
	public CardRule getCardRule() {
		return cardRule;
	}
	public void setCardRule(CardRule cardRule) {
		this.cardRule = cardRule;
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
	
	
	
}
