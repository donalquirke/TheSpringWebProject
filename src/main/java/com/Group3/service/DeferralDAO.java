package com.Group3.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.Group3.domain.Deferral;
import com.Group3.domain.Programme;

@Service
public interface DeferralDAO {
	/**
	 * This is the method to be used to initialize database resources ie.
	 * connection.
	 */
	public void setDataSource(DataSource ds);

	/**
	 * This is the method to be used to create a Deferral entry and return the
	 * id generated.
	 */
	public int createDeferralGetId(int studentAutoID, String lectID,
			int programmeAutoID, int moduleAutoID, String approval);

	/**
	 * This is the method to be used to create a Module Deferral.
	 */
	
	public void deleteDeferralById(int id);

	/**
	 * This is the method to be used to delete a Deferral based on a student ID.
	 */
	public void deleteDeferralsByStudentAutoID(int studentAutoID);

	/**
	 * This is the method to be used to delete a Deferral based on a module ID.
	 */
	public void deleteDeferralsByModuleAutoID(int moduleAutoID);

	/**
	 * This is the method to be retrieve a Deferral based on the ID.
	 */
	public Deferral getDeferral(int id);

	/**
	 * This is the method to be used to list down all the records from the
	 * deferral table.
	 */
	public List<Deferral> listDeferrals();

	/**
	 * This is the method to be used to list all deferrals of a particular
	 * student.
	 */
	public List<Deferral> listDeferralsByStudent(int studentAutoID);

	/**
	 * This is the method to be used to list all deferrals of a particular
	 * programme.
	 */
	public List<Deferral> listDeferralsByProgramme(int programmeAutoID);

	/**
	 * This is the method to be used to list all unapproved deferrals.
	 */
	public List<Deferral> listUnapprovedDeferrals(String unapproved);

	/**
	 * This is the method to be used to list all unapproved deferrals of a
	 * particular programme.
	 */
	public List<Deferral> listUnapprovedDeferralsByProgramme(String unapproved,
			int programmeAutoID);

	/**
	 * This is the method to be used to list all unapproved deferrals by
	 * coodinator.
	 */
	public List<Deferral> listUnapprovedDeferralsByProgrammeCoordinatorName(
			String unapproved, String firstname);

	/**
	 * This is the method to be used to list all unapproved deferrals of a
	 * particular programme.
	 */
	public List<Deferral> listApprovedDeferralsByStudent(String approved,
			int studentAutoID);

	/**
	 * This is the method used to approve a module deferral.
	 */
	public void approveModuleDeferral(String studId, int moduleAutoID);

	/**
	 * This is the method used to approve a programme deferral.
	 */
	public void approveProgrammeDeferral(String studId, int programmeAutoID);

	/**
	 * This is the method used to count the rows in the table.
	 */
	public void updateDeferral(int defId, String approval);

	/**
	 * This is the method used to count the rows in the table.
	 */	
	public int countRows();

	void createModuleDeferral(int studentAutoID, int moduleAutoID, int crnNumber);

	void createProgrammeDeferral(int studentAutoID, int programmeAutoID);

	void deleteDeferralsByStudentAutoID(String studentAutoID);

	void createModuleDeferral(int studentAutoID, String moduleAutoID,
			int crnNumber);

	
}
