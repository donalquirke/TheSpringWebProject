package com.Group3.domain;

import javax.validation.constraints.Size;

public class Lecturer extends Person {

	private int lecturerAutoId;
	private String lectId;
	@Size(min=2, max=10, message="A Lecturer ID cannot have less than 2 characters or greater than 10")

	public Lecturer() {
		// blank constructor
	}

	public Lecturer(String lectId, String firstName, String lastName,
			String email) {
		super(firstName, lastName, email);
		this.lectId = lectId;

	}

	public String getLectId() {
		return lectId;
	}

	public void setLectId(String lectId) {
		this.lectId = lectId;
	}

	public int getLecturerAutoId() {
		return lecturerAutoId;
	}

	public void setLecturerAutoId(int lecturerAutoId) {
		this.lecturerAutoId = lecturerAutoId;
	}

	@Override
	public String toString() {
		return "Lecturer [LectId=" + getLectId() + ", FirstName="
				+ getFirstName() + ", LastName=" + getLastName() + ", Email="
				+ getEmail() + "]";
	}

}