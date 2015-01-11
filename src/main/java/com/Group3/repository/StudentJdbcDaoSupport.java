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

import com.Group3.domain.Student;
import com.Group3.domain.mappers.StudentMapper;
import com.Group3.service.StudentDAO;
@Repository
public class StudentJdbcDaoSupport extends JdbcDaoSupport implements StudentDAO {

	@Autowired
	StudentJdbcDaoSupport(DataSource dataSource) {
		   setDataSource(dataSource);
	} 
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void createStudent(String studId, String firstName,String lastName, String email) {
		String SQL = "insert into student (Student_ID, Firstname, Surname, Student_Email) values (?, ?, ?,?)";
		getJdbcTemplate().update(SQL,new Object[] { studId, firstName, lastName, email });
		return;
		}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int createStudentGetId(String studId, String firstName, String lastName, String email) {
		String SQL = "insert into student (Student_ID, Firstname, Surname, Student_Email) values (?, ?, ?,?)";
		
		Object[] params=new Object[]{studId, firstName, lastName, email};
		PreparedStatementCreatorFactory psc=new PreparedStatementCreatorFactory(SQL);
		psc.addParameter(new SqlParameter("Student_ID", Types.VARCHAR));
		psc.addParameter(new SqlParameter("Firstname", Types.VARCHAR));
		psc.addParameter(new SqlParameter("Surname", Types.VARCHAR));
		psc.addParameter(new SqlParameter("Student_Email", Types.VARCHAR));
		
		KeyHolder holder = new GeneratedKeyHolder();
		getJdbcTemplate().update(psc.newPreparedStatementCreator(params), holder);
		System.out.println("holder: " + holder.getKey().toString());
		
		String key = holder.getKey().toString();
		return Integer.parseInt(key);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteStudent(int studentAutoId) {
		String SQL = "delete from student where StudentAutoID = ?";
		getJdbcTemplate().update(SQL, new Object[] { studentAutoId });
		return;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void createMultipleStudents(final List<Student> students) {
		String SQL = "insert into student (Student_ID, Firstname, Surname, Student_Email) values (?, ?, ?, ?)";
		getJdbcTemplate().batchUpdate(SQL, new BatchPreparedStatementSetter() {

			public int getBatchSize() {
				return students.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Student student = students.get(i);
				ps.setString(1, student.getStudentId());
				ps.setString(2, student.getFirstName());
				ps.setString(3, student.getLastName() );
				ps.setString(4, student.getEmail() );
			}

						
		});
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Student getStudent(int studentAutoId) {
		String SQL = "select * from student where StudentAutoID = ?";
		Student student= (Student) getJdbcTemplate().queryForObject(SQL, 
						new Object[]{studentAutoId}, new StudentMapper());
		return student;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<Student> listStudents() {
		String SQL = "select * from student";
		List<Student> studentList = getJdbcTemplate().query(SQL, 
						new StudentMapper());
		return studentList;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int countRows() {
		String SQL = "select count(*) from student";
		int rows=getJdbcTemplate().queryForObject(SQL, Integer.class);
		return rows;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateStudent(int studentAutoId, String email) {
		String SQL = "update Student set Student_Email=? where StudentAutoID = ?";
		getJdbcTemplate().update(SQL, new Object[] {email, studentAutoId});
		System.out.println("Updated record with Student ID: "+ studentAutoId);
		return;		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteMultipleStudents(final List<Student> students) {
		String SQL = "delete from student where StudentAutoID = ?";
		getJdbcTemplate().batchUpdate(SQL, new BatchPreparedStatementSetter() {
			
			@Override
			public int getBatchSize(){
				return students.size();
			}
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Student student = students.get(i);
				ps.setString(1, student.getStudentId());
				
			}
		
		});
	}

	@Override
	public List<Student> listStudentsWithdeferrals() {
		String SQL = "select DISTINCT s.* from student as s"
				+ " join deferrals as d on s.StudentAutoID = d.StudentAutoID";
		@SuppressWarnings("unchecked")
		List<Student> studentList = getJdbcTemplate().query(SQL, 
						new StudentMapper());
		return studentList;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int getStudentByStudentID(String studentId) {
		String SQL = "select * from student where Student_ID = ?";
		Student student= (Student) getJdbcTemplate().queryForObject(SQL, 
						new Object[]{studentId}, new StudentMapper());
		return student.getStudentAutoId();
	}
	
}