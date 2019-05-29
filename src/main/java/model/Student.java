package model;

import java.util.ArrayList;

public class Student {

	private String name;
	private ArrayList<Result> allResults;
	private Session session;
	
	public Student(String name, ArrayList<Result> allResults, Session session) {
		super();
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
	
	
	
	
}
