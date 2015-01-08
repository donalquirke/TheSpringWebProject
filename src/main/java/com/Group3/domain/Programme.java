package com.Group3.domain;

import javax.validation.constraints.Size;

public class Programme {

	private int programmeAutoId;
	private String programmeId;
	@Size(min=2, max=15, message="A Programme ID cannot have less than 2 characters or greater than 15")
	private int numYears;
	private String coordinatorId;
	private int progYear;

	public Programme() {
		// Blank Contructor
	}

	public Programme(String programmeId, int numYears, String coordinatorId, int progYear) {
		super();
		this.programmeId = programmeId;
		this.numYears = numYears;
		this.coordinatorId = coordinatorId;
		this.progYear = progYear;
	}

	public String getProgrammeId() {
		return programmeId;
	}

	public void setProgrammeId(String programmeId) {
		this.programmeId = programmeId;
	}

	public int getNumYears() {
		return numYears;
	}

	public void setNumYears(int numYears) {
		this.numYears = numYears;
	}

	public String getCoordinatorId() {
		return coordinatorId;
	}

	public void setCoordinatorId(String coordinatorId) {
		this.coordinatorId = coordinatorId;
	}


	public int getProgYear() {
		return progYear;
	}

	public void setProgYear(int progYear) {
		this.progYear = progYear;
	}

	public int getProgrammeAutoId() {
		return programmeAutoId;
	}

	public void setProgrammeAutoId(int programmeAutoId) {
		this.programmeAutoId = programmeAutoId;
	}

}
