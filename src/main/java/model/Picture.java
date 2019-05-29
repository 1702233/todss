package model;

import java.util.ArrayList;

public class Picture {
	
	private int ID;
	private String url;

	private Teacher teacher;
	private ArrayList<Cardside> cardside;

	public Picture(String url, Teacher teacher, ArrayList<Cardside> cardside) {
		super();
		this.url = url;
		this.teacher = teacher;
		this.cardside = cardside;
	}
	
	

	public Picture(int iD, String url, Teacher teacher) {
		super();
		ID = iD;
		this.url = url;
		this.teacher = teacher;
	}



	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public ArrayList<Cardside> getCardside() {
		return cardside;
	}

	public void setCardside(ArrayList<Cardside> cardside) {
		this.cardside = cardside;
	}

}
