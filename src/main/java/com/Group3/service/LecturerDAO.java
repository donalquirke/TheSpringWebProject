package com.Group3.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.Group3.domain.Lecturer;

@Service
public interface LecturerDAO {
	/**
	 * This is the method to be used to initialize database resources ie.
	 * connection.
	 */
	public void setDataSource(DataSource ds);

	/**
	 * This is the method to be used to create a record in the Lecturer table.
	 */

	public void createLecturer(String lectId, String firstName,
			String lastName, String email);

	/**
	 * This is the method to be used to delete a record from the Lecturer table.
	 */
	public void deleteLecturer(int lecturerAutoId);

	/**
	 * This is the method to be used to create multiple lecturers.
	 */
	public void createMultipleLecturers(final List<Lecturer> lecturers);

	/**
	 * This is the method to be used to get a specific Lecturer.
	 */
	public Lecturer getLecturer(int lecturerAutoId);

	/**
	 * This is the method to be used to list down all the records from the
	 * Lecturer table.
	 */
	public List<Lecturer> listLecturers();

	/**
	 * This is the method used to count the rows in the table.
	 */
	public int countRows();

	public void updateLecturer(int lecturerAutoId, String email);

	public int createLecturerGetId(String lectId, String firstName,
			String lastName, String email);

	public void updateLecturer(String lectId, String email);
}
