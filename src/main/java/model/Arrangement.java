package model;

import java.util.ArrayList;

public class Arrangement {

	private String name;
	private String omschrijving;

	private ArrayList<Minigame> allMinigames;
	private Teacher teacher;
	private ArrayList<Session> allSessions;
	private ArrayList<Tag> allTags;

	public Arrangement(String name, String omschrijving, ArrayList<Minigame> allMinigames, Teacher teacher,
			ArrayList<Session> allSessions, ArrayList<Tag> allTags) {
		super();
		this.name = name;
		this.omschrijving = omschrijving;
		this.allMinigames = allMinigames;
		this.teacher = teacher;
		this.allSessions = allSessions;
		this.allTags = allTags;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	public ArrayList<Minigame> getAllMinigames() {
		return allMinigames;
	}

	public void setAllMinigames(ArrayList<Minigame> allMinigames) {
		this.allMinigames = allMinigames;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public ArrayList<Session> getAllSessions() {
		return allSessions;
	}

	public void setAllSessions(ArrayList<Session> allSessions) {
		this.allSessions = allSessions;
	}

	public ArrayList<Tag> getAllTags() {
		return allTags;
	}

	public void setAllTags(ArrayList<Tag> allTags) {
		this.allTags = allTags;
	}

}
