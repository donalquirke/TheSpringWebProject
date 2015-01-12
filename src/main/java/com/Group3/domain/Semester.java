package com.Group3.domain;

import javax.validation.constraints.Size;

public class Semester {

	private int semesterAutoID;
	private String semesterId;
	private int programmeAutoID;
	@Size(min=2, max=15, message="A Programme ID cannot have less than 2 characters or greater than 15")
	private String startMonth;
	private String endMonth;

	public Semester() {
		// Blank Constructor
	}

	public Semester(String semesterId, int programmeAutoID, String startMonth,
			String endMonth) {
		super();
		this.semesterId = semesterId;
		this.programmeAutoID = programmeAutoID;
		this.startMonth = startMonth;
		this.endMonth = endMonth;
	}

	public String getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}

	public int getProgrammeAutoID() {
		return programmeAutoID;
	}

	public void setProgrammeAutoID(int programmeAutoID) {
		this.programmeAutoID = programmeAutoID;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public int getSemesterAutoID() {
		return semesterAutoID;
	}

	public void setSemesterAutoID(int semesterAutoID) {
		this.semesterAutoID = semesterAutoID;
	}

}
