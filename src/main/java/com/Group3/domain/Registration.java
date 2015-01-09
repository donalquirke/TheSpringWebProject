package com.Group3.domain;

import javax.validation.constraints.Size;

public class Registration {

	private int registrationAutoId;
	private int studentAutoID;
	@Size(min=2, max=10, message="A student ID cannot have less than 2 characters or greater than 10")
	private int moduleAutoID;
	private int programmeAutoID;
	@Size(min=2, max=15, message="A Programme ID cannot have less than 2 characters or greater than 15")
	
	public Registration(int studentAutoID, int moduleAutoID, int programmeAutoID) {
		super();
		this.studentAutoID = studentAutoID;
		this.moduleAutoID = moduleAutoID;
		this.programmeAutoID = programmeAutoID;
	}

	public Registration() {
		// TODO Auto-generated constructor stub
	}

	public int getStudentAutoID() {
		return studentAutoID;
	}

	public void setStudentAutoID(int studentAutoID) {
		this.studentAutoID = studentAutoID;
	}

	public int getModuleAutoID() {
		return moduleAutoID;
	}

	public void setModuleAutoID(int moduleAutoID) {
		this.moduleAutoID = moduleAutoID;
	}

	public int getProgrammeAutoID() {
		return programmeAutoID;
	}

	public void setProgrammeAutoID(int programmeAutoID) {
		this.programmeAutoID = programmeAutoID;
	}

	public int getRegistrationAutoId() {
		return registrationAutoId;
	}

	public void setRegistrationAutoId(int registrationAutoId) {
		this.registrationAutoId = registrationAutoId;
	}
	
}
