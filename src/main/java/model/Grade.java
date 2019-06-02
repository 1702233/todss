package model;

public class Grade {

	private int ID;
	private int grade;
	private Minigame minigame;

	public Grade(int iD, int grade, Minigame minigame) {
		super();
		ID = iD;
		this.grade = grade;
		this.minigame = minigame;
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

	public Minigame getMinigame() {
		return minigame;
	}

	public void setMinigame(Minigame minigame) {
		this.minigame = minigame;
	}

}
