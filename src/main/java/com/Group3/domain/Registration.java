package com.Group3.domain;

import javax.validation.constraints.Size;

public class Registration {

	private int registrationAutoId;
	private String studentId;
	@Size(min=2, max=10, message="A student ID cannot have less than 2 characters or greater than 10")
	private int crnNumber;
	private String programmeId;
	@Size(min=2, max=15, message="A Programme ID cannot have less than 2 characters or greater than 15")
	
	public Registration(String studentId, int crnNumber, String programmeId) {
		super();
		this.studentId = studentId;
		this.crnNumber = crnNumber;
		this.programmeId = programmeId;
	}

	public Registration() {
		// TODO Auto-generated constructor stub
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public int getCrnNumber() {
		return crnNumber;
	}

	public void setCrnNumber(int crnNumber) {
		this.crnNumber = crnNumber;
	}

	public String getProgrammeId() {
		return programmeId;
	}

	public void setProgrammeId(String programmeId) {
		this.programmeId = programmeId;
	}

	public int getRegistrationAutoId() {
		return registrationAutoId;
	}

	public void setRegistrationAutoId(int registrationAutoId) {
		this.registrationAutoId = registrationAutoId;
	}
	
}
