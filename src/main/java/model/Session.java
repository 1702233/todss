package model;

import java.sql.Date;
import java.util.ArrayList;

public class Session {
	
	private String code;
	private Date startDate;
	private Date endDate;
	private Arrangement arrangement;
	
	public Session(String code, Date startDate, Date endDate, ArrayList<Student> allStudents, Arrangement arrangement) {
		super();
		this.code = code;
		this.startDate = startDate;
		this.endDate = endDate;
		this.arrangement = arrangement;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Arrangement getArrangement() {
		return arrangement;
	}

	public void setArrangement(Arrangement arrangement) {
		this.arrangement = arrangement;
	}

	
}
