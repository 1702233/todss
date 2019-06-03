package model;

import java.util.ArrayList;

public class Student {

    private int ID;
    private String name;
    private ArrayList<Result> allResults;
    private Session session;

    public Student(int iD, String name, ArrayList<Result> allResults, Session session) {
        super();
        ID = iD;
        this.name = name;
        this.allResults = allResults;
        this.session = session;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Result> getAllResults() {
        return allResults;
    }

    public void setAllResults(ArrayList<Result> allResults) {
        this.allResults = allResults;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

}
