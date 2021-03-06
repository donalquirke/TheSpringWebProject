package com.Group3.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.Group3.domain.Registration;
import com.Group3.domain.mappers.RegistrationMapper;
import com.Group3.service.RegistrationDAO;

@Repository
public class RegistrationJdbcDaoSupport extends JdbcDaoSupport implements
		RegistrationDAO {

	@Autowired
	RegistrationJdbcDaoSupport(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<Registration> listRegistrations() {
		String SQL = "select * from registration";
		List<Registration> registrationList = getJdbcTemplate().query(SQL,new RegistrationMapper());
		return registrationList;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int countRows() {
		String SQL = "select count(*) from registration";
		int rows = getJdbcTemplate().queryForObject(SQL, Integer.class);
		return rows;
	}

}
