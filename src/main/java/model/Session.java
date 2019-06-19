package model;


import java.util.ArrayList;

public class Session {

	private String code;
	private String opmerking;
	private Arrangement arrangement;
	private ArrayList<Student> allStudents;

	public Session(String code, String opmerking, Arrangement arrangement, ArrayList<Student> allStudents) {
		super();
		this.code = code;
		this.opmerking = opmerking;
		this.arrangement = arrangement;
		this.allStudents = allStudents;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOpmerking() {
		return opmerking;
	}

	public void setOpmerking(String opmerking) {
		this.opmerking = opmerking;
	}

	public Arrangement getArrangement() {
		return arrangement;
	}
	
	public int getArrangementID() {
		return arrangement.getID();
	}

	public void setArrangement(Arrangement arrangement) {
		this.arrangement = arrangement;
	}

	public ArrayList<Student> getAllStudents() {
		return allStudents;
	}

	public void setAllStudents(ArrayList<Student> allStudents) {
		this.allStudents = allStudents;
	}

}
