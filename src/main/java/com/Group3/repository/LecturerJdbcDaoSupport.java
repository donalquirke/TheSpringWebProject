package com.Group3.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
	public void deleteLecturer(int lecturerAutoId) {
		String SQL = "delete from lecturer where LecturerAutoID = ?";
		getJdbcTemplate().update(SQL, new Object[] { lecturerAutoId });
		return;
	}
	
	@Override // LOSE IT
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
	public Lecturer getLecturer(int lecturerAutoId) {
		String SQL = "select * from lecturer where LecturerAutoID = ?";
		Lecturer lecturer= (Lecturer) getJdbcTemplate().queryForObject(SQL, 
						new Object[]{lecturerAutoId}, new LecturerMapper());
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

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateLecturer(int lecturerAutoId, String email) {
		String SQL = "update Lecturer set Lecturer_Email=? where LecturerAutoID = ?";
		getJdbcTemplate().update(SQL, new Object[] {email, lecturerAutoId});
		System.out.println("Updated record with Lecturer ID: "+ lecturerAutoId);
		return;		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int createLecturerGetId(String lectId, String firstName,
			String lastName, String email) {
		String SQL = "insert into lecturer (Lect_ID, Firstname, Surname, Lecturer_Email) values (?, ?, ?,?)";
		
		Object[] params=new Object[]{lectId, firstName, lastName, email};
		PreparedStatementCreatorFactory psc=new PreparedStatementCreatorFactory(SQL);
		psc.addParameter(new SqlParameter("Lecturer_ID", Types.VARCHAR));
		psc.addParameter(new SqlParameter("Firstname", Types.VARCHAR));
		psc.addParameter(new SqlParameter("Surname", Types.VARCHAR));
		psc.addParameter(new SqlParameter("Lecturer_Email", Types.VARCHAR));
		
		KeyHolder holder = new GeneratedKeyHolder();
		getJdbcTemplate().update(psc.newPreparedStatementCreator(params), holder);
		System.out.println("holder: " + holder.getKey().toString());
		
		String key = holder.getKey().toString();
		return Integer.parseInt(key);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateLecturer(String lectId, String email) {
		
		// Update email
		String SQL = "UPDATE lecturer SET Lecturer_Email=? WHERE Lect_ID = ?";
		getJdbcTemplate().update(SQL, new Object[] { email, lectId });

		return;

	}
}
