package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Result {

	private Timestamp start;
	private Timestamp end;
	private Student student;
	private Minigame minigame;

	public Result(Timestamp start, Timestamp end, Minigame minigame) {
		super();
		this.start = start;
		this.end = end;
		this.minigame = minigame;

	}

	public Result(Timestamp start, Timestamp end, Student student, Minigame minigame) {
		super();
		this.start = start;
		this.end = end;
		this.minigame = minigame;
		this.student = student;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Minigame getMinigame() {
		return minigame;
	}

	public void setMinigame(Minigame minigame) {
		this.minigame = minigame;
	}

}
