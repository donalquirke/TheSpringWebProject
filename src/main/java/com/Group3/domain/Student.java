package com.Group3.domain;

import javax.validation.constraints.Size;

public class Student extends Person {

	private int studentAutoId;
	private String studentId;
	@Size(min=2, max=10, message="A student ID cannot have less than 2 characters or greater than 10")

	public Student() {
		// Blank Constructor
	}

	public Student(String studentId, String firstName, String lastName,
			String email) {
		super(firstName, lastName, email);
		this.studentId = studentId;

	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public int getStudentAutoId() {
		return studentAutoId;
	}

	public void setStudentAutoId(int studentAutoId) {
		this.studentAutoId = studentAutoId;
	}

	@Override
	public String toString() {
		return "Student [StudId=" + getStudentId() + ", FirstName="
				+ getFirstName() + ", LastName=" + getLastName() + ", Email="
				+ getEmail() + "]";
	}

}
