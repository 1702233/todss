package model;

public class Grade {

	private int ID;
	private int grade;

	public Grade(int iD, int grade) {
		super();
		ID = iD;
		this.grade = grade;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
}
