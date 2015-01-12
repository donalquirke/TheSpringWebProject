package com.Group3.domain;

import javax.validation.constraints.Size;

public class Module {

	private int moduleAutoID;
	private String moduleId;
	@Size(min=2, max=15, message="A Module ID cannot have less than 2 characters or greater than 15")
	private int crnNumber;
	private String name;
	private int lecturerAutoID;
	private int semesterAutoID;
	
	public Module() {
		// Blank Constructor
	}


	public String  getCombindedKey() {
		return moduleAutoID + "-" +crnNumber;
	}

	public Module(int moduleAutoID, String moduleId, int crnNumber, String name, Integer lecturerAutoID,
			int semesterAutoID) {
		super();
		this.moduleId = moduleId;
		this.crnNumber = crnNumber;
		this.name = name;
		this.lecturerAutoID = lecturerAutoID;
		this.semesterAutoID = semesterAutoID;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public int getCrnNumber() {
		return crnNumber;
	}

	public void setCrnNumber(int crnNumber) {
		this.crnNumber = crnNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLecturerAutoID() {
		return lecturerAutoID;
	}

	public void setLecturerAutoId(Integer lecturerAutoID) {
		this.lecturerAutoID = lecturerAutoID;
	}

	public int getSemesterAutoID() {
		return semesterAutoID;
	}

	public int getModuleAutoID() {
		return moduleAutoID;
	}


	public void setModuleAutoID(int moduleAutoID) {
		this.moduleAutoID = moduleAutoID;
	}


	public void setSemesterAutoID(int semesterAutoID) {
		this.semesterAutoID = semesterAutoID;
	}
	

}
