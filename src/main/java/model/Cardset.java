package model;

import java.util.ArrayList;

public class Cardset {

	private String name;

	private Teacher teacher;
	private ArrayList<Minigame> allMinigames;
	private ArrayList<Card> allCards;

	public Cardset(String name, Teacher teacher, ArrayList<Minigame> allMinigames, ArrayList<Card> allCards) {
		super();
		this.name = name;
		this.teacher = teacher;
		this.allMinigames = allMinigames;
		this.allCards = allCards;
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

}
