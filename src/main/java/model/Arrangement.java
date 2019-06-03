package model;

import java.util.ArrayList;

public class Arrangement {

    private int ID;
    private String name;
    private String omschrijving;
    private ArrayList<Minigame> allMinigames;
    private Teacher teacher;
    private ArrayList<Tag> allTags;

    public Arrangement(int iD, String name, String omschrijving, ArrayList<Minigame> allMinigames, Teacher teacher, ArrayList<Tag> allTags) {
        super();
        ID = iD;
        this.name = name;
        this.omschrijving = omschrijving;
        this.allMinigames = allMinigames;
        this.teacher = teacher;
        this.allTags = allTags;
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

    public ArrayList<Tag> getAllTags() {
        return allTags;
    }

    public void setAllTags(ArrayList<Tag> allTags) {
        this.allTags = allTags;
    }

}
