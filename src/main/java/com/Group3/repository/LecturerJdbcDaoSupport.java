package com.Group3.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.Group3.domain.Lecturer;
import com.Group3.domain.mappers.LecturerMapper;
import com.Group3.service.LecturerDAO;

@Repository
public class LecturerJdbcDaoSupport extends JdbcDaoSupport implements LecturerDAO {
	
	@Autowired
	LecturerJdbcDaoSupport(DataSource dataSource) {
		   setDataSource(dataSource);
	} 
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void createLecturer(String lectId, String firstName,String lastName, String email) {
		String SQL = "insert into lecturer (Lect_ID, Firstname, Surname, Lecturer_Email) values (?, ?, ?,?)";
		getJdbcTemplate().update(SQL,new Object[] { lectId, firstName, lastName, email });
		return;
		}
	

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteLecturer(String lectId) {
		String SQL = "delete from lecturer where Lect_ID = ?";
		getJdbcTemplate().update(SQL, new Object[] { lectId });
		return;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void createMultipleLecturers(final List<Lecturer> lecturers) {
		String SQL = "insert into lecturer (Lect_ID, Firstname, Surname, Lecturer_Email) values (?, ?, ?, ?)";
		getJdbcTemplate().batchUpdate(SQL, new BatchPreparedStatementSetter() {

			public int getBatchSize() {
				return lecturers.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Lecturer lecturer = lecturers.get(i);
				ps.setString(1, lecturer.getLectId());
				ps.setString(2, lecturer.getFirstName());
				ps.setString(3, lecturer.getLastName() );
				ps.setString(4, lecturer.getEmail() );
			}

						
		});
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Lecturer getLecturer(String id) {
		String SQL = "select * from lecturer where Lect_ID = ?";
		Lecturer lecturer= (Lecturer) getJdbcTemplate().queryForObject(SQL, 
						new Object[]{id}, new LecturerMapper());
		return lecturer;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<Lecturer> listLecturers() {
		String SQL = "select * from lecturer";
		List<Lecturer> lecturerList = getJdbcTemplate().query(SQL, 
						new LecturerMapper());
		return lecturerList;
	}

	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int countRows() {
		String SQL = "select count(*) from lecturer";
		int rows=getJdbcTemplate().queryForObject(SQL, Integer.class);
		return rows;
	}

	
}
