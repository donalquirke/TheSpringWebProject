package com.Group3.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.Group3.domain.Module;

@Service
public interface ModuleDAO {
	/**
	 * This is the method to be used to initialize database resources ie.
	 * connection.
	 */
	public void setDataSource(DataSource ds);

	/**
	 * This is the method to be used to create a record in the Module table.
	 */

	public void createModule(String moduleId, int crnNumber, String name,
			int lecturerAutoId, int semesterAutoId);

	/**
	 * This is the method to be used to update a record in the Module table.
	 */

	public void updateModuleName(int moduleAutoID, String name);

	/**
	 * This is the method to be used to delete a record from the Module table
	 * corresponding to a passed Module ID and CRN.
	 */
	public void deleteModule(int moduleAutoID);

	/**
	 * This is the method to be used to create multiple Modules.
	 */
	public void createMultipleModules(final List<Module> modules);

	/**
	 * This is the method to be used to list down a record from the Module table
	 * corresponding to a passed Module ID and CRN.
	 */
	public Module getModule(Integer ModuleAutoID);

	/**
	 * This is the method to be used to list all the modules corresponding to a
	 * Student ID.
	 */
	public List<Module> listStudentModules(String studId);

	/**
	 * This is the method to be used to list all the modules corresponding to a
	 * Lecturer ID.
	 */
	public List<Module> listModulesByLecturer(String lectId);

	/**
	 * This is the method to be used to list down all the records from the
	 * Module table.
	 */
	public List<Module> listModules();

	/**
	 * This is the method to be used to register a student for a specfic module.
	 */
	
	public int countRows();

	public List<Module> listModuleByProgrammeAutoID(int programmeAutoID);

	public List<Module> listModulesWithdeferrals();

	public int createModuleGetId(String moduleId, int crnNumber, String name,
			Integer lecturerAutoID, int semesterAutoId);

	public void updateModule(int moduleAutoID, int lecturerAutoID);
}
