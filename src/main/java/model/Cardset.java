package model;

import java.util.ArrayList;

public class Cardset {

	private int id;
	private String name;

	private Teacher teacher;
	private ArrayList<Minigame> allMinigames;
	private ArrayList<Card> allCards;

	public Cardset(int id, String name, Teacher teacher, ArrayList<Minigame> allMinigames, ArrayList<Card> allCards) {
		super();
		this.id = id;
		this.name = name;
		this.teacher = teacher;
		this.allMinigames = allMinigames;
		this.allCards = allCards;
	}

	public Cardset(int id, String name, Teacher teacher, ArrayList<Card> allCards) {
		super();
		this.id = id;
		this.name = name;
		this.teacher = teacher;
		this.allCards = allCards;
	}

	public Cardset(String name, Teacher teacher, ArrayList<Card> allCards) {
		super();
		this.name = name;
		this.teacher = teacher;
		this.allCards = allCards;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public ArrayList<Minigame> getAllMinigames() {
		return allMinigames;
	}

	public void setAllMinigames(ArrayList<Minigame> allMinigames) {
		this.allMinigames = allMinigames;
	}

	public ArrayList<Card> getAllCards() {
		return allCards;
	}

	public void setAllCards(ArrayList<Card> allCards) {
		this.allCards = allCards;
	}

	public void addCard(Card card) {
		this.allCards.add(card);
	}

	@Override
	public String toString() {
		return "Cardset{" +
				"id=" + id +
				", name='" + name + '\'' +
				", teacher=" + teacher +
				", allMinigames=" + allMinigames +
				", allCards=" + allCards +
				'}';
	}
}
