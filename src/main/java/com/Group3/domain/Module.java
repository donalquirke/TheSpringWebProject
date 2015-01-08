package com.Group3.domain;

import javax.validation.constraints.Size;

public class Module {

	private int ModuleAutoId;
	private String moduleId;
	@Size(min=2, max=15, message="A Module ID cannot have less than 2 characters or greater than 15")
	private int crnNumber;
	private String name;
	private String lectId;
	private String semesterId;
	
	public Module() {
		// Blank Constructor
	}


	public String  getCombindedKey() {
		return moduleId + "-" +crnNumber;
	}

	public Module(String moduleId, int crnNumber, String name, String lectId,
			String semesterId) {
		super();
		this.moduleId = moduleId;
		this.crnNumber = crnNumber;
		this.name = name;
		this.lectId = lectId;
		this.semesterId = semesterId;
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

	public String getLectId() {
		return lectId;
	}

	public void setLectId(String lectId) {
		this.lectId = lectId;
	}

	public String getSemesterId() {
		return semesterId;
	}

	public int getModuleAutoId() {
		return ModuleAutoId;
	}


	public void setModuleAutoId(int moduleAutoId) {
		ModuleAutoId = moduleAutoId;
	}


	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}
	

}
