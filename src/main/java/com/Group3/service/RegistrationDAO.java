package com.Group3.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.Group3.domain.Registration;

@Service
public interface RegistrationDAO {
	/**
	 * This is the method to be used to initialize database resources ie.
	 * connection.
	 */
	public void setDataSource(DataSource ds);

	/**
	 * This is the method to be used to list down all the records from the
	 * Registration table.
	 */
	public List<Registration> listRegistrations();

	/**
	 * This is the method used to count the rows in the table.
	 */
	public int countRows();
}