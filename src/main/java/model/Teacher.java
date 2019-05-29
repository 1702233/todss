package model;

import java.util.ArrayList;

public class Teacher {
	private String username;
	private String password;

	private ArrayList<Picture> allPictures;
	private ArrayList<Minigame> allMinigames;
	private ArrayList<Cardset> allCardsets;
	private ArrayList<Arrangement> allArrangementen;

	public Teacher(String username, String password, ArrayList<Picture> allPictures, ArrayList<Minigame> allMinigames,
			ArrayList<Cardset> allCardsets, ArrayList<Arrangement> allArrangementen) {
		super();
		this.username = username;
		this.password = password;
		this.allPictures = allPictures;
		this.allMinigames = allMinigames;
		this.allCardsets = allCardsets;
		this.allArrangementen = allArrangementen;
	}
	
	

	public Teacher(String username, String password, ArrayList<Picture> allPictures, ArrayList<Minigame> allMinigames,
			ArrayList<Cardset> allCardsets) {
		super();
		this.username = username;
		this.password = password;
		this.allPictures = allPictures;
		this.allMinigames = allMinigames;
		this.allCardsets = allCardsets;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Picture> getAllPictures() {
		return allPictures;
	}

	public void setAllPictures(ArrayList<Picture> allPictures) {
		this.allPictures = allPictures;
	}

	public ArrayList<Minigame> getAllMinigames() {
		return allMinigames;
	}

	public void setAllMinigames(ArrayList<Minigame> allMinigames) {
		this.allMinigames = allMinigames;
	}

	public ArrayList<Cardset> getAllCardsets() {
		return allCardsets;
	}

	public void setAllCardsets(ArrayList<Cardset> allCardsets) {
		this.allCardsets = allCardsets;
	}

	public ArrayList<Arrangement> getAllArrangementen() {
		return allArrangementen;
	}

	public void setAllArrangementen(ArrayList<Arrangement> allArrangementen) {
		this.allArrangementen = allArrangementen;
	}

}
