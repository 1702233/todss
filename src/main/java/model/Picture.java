package model;

import java.util.ArrayList;

public class Picture {
	
	private int ID;
	private String url;
	private Teacher teacher;

//	public Picture(int iD2, String url2) {
//		super();
//		this.url = iD2;
//		this.teacher = url2;
//	}

	public Picture(int iD, String url) {
		super();
		ID = iD;
		this.url = url;
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

	@Override
	public String toString() {
		return "Picture{" +
				"ID=" + ID +
				", url='" + url + '\'' +
				", teacher=" + teacher +
				'}';
	}
}
