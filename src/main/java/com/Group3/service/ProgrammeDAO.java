package com.Group3.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.Group3.domain.Programme;

@Service
public interface ProgrammeDAO {
	/**
	 * This is the method to be used to initialize database resources ie.
	 * connection.
	 */
	public void setDataSource(DataSource ds);

	/**
	 * This is the method to be used to create a record in the Programme table.
	 */
	public void createProgramme(String programmeId, int numYears,
			String coordinatorId, String startMonth);

	/**
	 * This is the method to be used to delete a record from the Programme table
	 * corresponding to a passed Programme ID.
	 */
	public void deleteProgramme(int programmeAutoID);

	/**
	 * This is the method to be used to return a student's registered Programme
	 * ID.
	 */
	public String getStudentProgrammeId(String studId);

	/**
	 * This is the method to be used to list down all the records from the
	 * Programme table.
	 */
	public List<Programme> listProgrammes();

	/**
	 * This is the method used to count the rows in the table.
	 */
	public int countRows();

	public Programme getProgramme(int programmeAutoID);

	public void updateProgramme(int programmeAutoID, int lecturerAutoID);

	public int createProgrammeGetId(String programmeId, int numYears,
			int lecturerAutoID, int progYear);

	public List<Programme> listProgrammeByStudentID(String studentID);

	public List<Programme> listProgrammesWithdeferrals();
}
