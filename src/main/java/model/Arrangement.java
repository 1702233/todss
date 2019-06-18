package model;

import java.util.ArrayList;

public class Arrangement {

    private int ID;
    private String name;
    private String description;
    private ArrayList<Minigame> allMinigames;
    private Teacher teacher;

    public Arrangement(int iD, String name, String description, ArrayList<Minigame> allMinigames, Teacher teacher) {
        super();
        ID = iD;
        this.name = name;
        this.description = description;
        this.allMinigames = allMinigames;
        this.teacher = teacher;
    }
    
    

    public Arrangement(String name, String description, ArrayList<Minigame> allMinigames, Teacher teacher) {
		super();
		this.name = name;
		this.description = description;
		this.allMinigames = allMinigames;
		this.teacher = teacher;
	}



	public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

}
