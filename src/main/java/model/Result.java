package model;

import java.sql.Date;

public class Result {

	private Date start;
	private Date end;
	private Student student;
	private Minigame minigame;
	
	public Result(Date start, Date end, Student student, Minigame minigame) {
		super();
		this.start = start;
		this.end = end;
		this.student = student;
		this.minigame = minigame;
	}
	
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
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
