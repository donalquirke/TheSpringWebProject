package com.Group3.domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

public class Programme {

	private int programmeAutoID;
	private String programmeId;
	@Size(min=2, max=15, message="A Programme ID cannot have less than 2 characters or greater than 15")
	private int numYears;
	private int lecturerAutoID;
	private int progYear;
	
	private List<Lecturer> lecturerList = new ArrayList<>();

	public Programme() {
		// Blank Contructor
	}

	public Programme(int programmeAutoID, String programmeId, int numYears, int lecturerAutoID, int progYear) {
		super();
		this.programmeAutoID = programmeAutoID;
		this.programmeId = programmeId;
		this.numYears = numYears;
		this.lecturerAutoID = lecturerAutoID;
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

	public int getLecturerAutoID() {
		return lecturerAutoID;
	}

	public void setLecturerAutoID(int lecturerAutoID) {
		this.lecturerAutoID = lecturerAutoID;
	}


	public int getProgYear() {
		return progYear;
	}

	public void setProgYear(int progYear) {
		this.progYear = progYear;
	}

	public int getProgrammeAutoID() {
		return programmeAutoID;
	}

	public void setProgrammeAutoId(int programmeAutoID) {
		this.programmeAutoID = programmeAutoID;
	}

	public List<Lecturer> getLecturerList() {
		return lecturerList;
	}

	public void setLecturerList(List<Lecturer> lecturerList) {
		this.lecturerList = lecturerList;
	}

}
