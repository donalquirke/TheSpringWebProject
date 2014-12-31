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

import com.Group3.domain.Module;
import com.Group3.domain.Programme;
import com.Group3.domain.Semester;
import com.Group3.domain.mappers.ModuleMapper;
import com.Group3.domain.mappers.ProgrammeMapper;
import com.Group3.domain.mappers.SemesterMapper;
import com.Group3.service.ModuleDAO;
@Repository
public class ModuleJdbcDaoSupport extends JdbcDaoSupport implements ModuleDAO {

	@Autowired
	ModuleJdbcDaoSupport(DataSource dataSource) {
		   setDataSource(dataSource);
	} 
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<Module> listModuleByProgrammeID(String programmeID){
		String SQL = "select m.* "
				+ "from modules m "
				+ "left join semester s on m.semester_id = s.semester_id "
				+ "where s.programme_id = ?";
		System.out.println("SQL : " + SQL);
		List<Module> moduleList = getJdbcTemplate().query(SQL,  new Object[]{programmeID}, new ModuleMapper());
		return moduleList;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void createModule(String moduleId, int crnNumber, String name, String lectId, String semesterId) {
		String SQL = "insert into modules (Module_ID, CRN, Name, Lect_ID, Semester_ID) values (?, ?, ?,?,?)";
		getJdbcTemplate().update(SQL,new Object[] { moduleId, crnNumber, name, lectId, semesterId  });
		return;
		}
	

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteModule(String moduleId,int crnNumber) {
		String SQL = "delete from modules where Module_ID = ? and CRN= ?";
		getJdbcTemplate().update(SQL, new Object[] { moduleId, crnNumber });
		return;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void createMultipleModules(final List<Module> modules) {
		String SQL = "insert into modules (Module_ID, CRN, Name, Lect_ID, Semester_ID) values (?, ?, ?,?,?)";
		getJdbcTemplate().batchUpdate(SQL, new BatchPreparedStatementSetter() {

			public int getBatchSize() {
				return modules.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Module module = modules.get(i);
				ps.setString(1, module.getModuleId());
				ps.setInt(2, module.getCrnNumber());
				ps.setString(3, module.getName());
				ps.setString(4, module.getLectId());
				ps.setString(5, module.getSemesterId());
			}

						
		});
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Module getModule(String moduleId, int crnNumber) {
		String SQL = "select * from modules where Module_ID = ? and CRN= ?";
		Module module= (Module) getJdbcTemplate().queryForObject(SQL, 
						new Object[]{moduleId,crnNumber}, new ModuleMapper());
		return module;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<Module> listStudentModules(String studId) {
		List<Module> modules;
		String SQL="SELECT * from modules"
				+ " JOIN registration on registration.CRN=modules.CRN"
				+ " AND registration.Student_ID=?";
		modules = getJdbcTemplate().query(SQL, new Object[] {studId}, new ModuleMapper());
		
		return modules;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<Module> listModules() {
		String SQL = "select * from modules";
		List<Module> moduleList = getJdbcTemplate().query(SQL, 
						new ModuleMapper());
		return moduleList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void registerStudentForModule(String studId,int crnNumber) {
		
		/*The module must correspond to a specific programme ID
		Get this programme ID*/
		List<Module> moduleList;
		String SQL="SELECT * from modules"
				+ " WHERE CRN=?";
		moduleList = getJdbcTemplate().query(SQL, new Object[] {crnNumber}, new ModuleMapper());
		String semesterId=moduleList.get(0).getSemesterId();
		
		List<Semester> semesterList;
		String SQL2="SELECT * from semester"
				+ " WHERE Semester_ID=?";
		semesterList = getJdbcTemplate().query(SQL2, new Object[] {semesterId}, new SemesterMapper());
		String moduleProgramme=semesterList.get(0).getProgrammeId();
		
		/*Now that we have the correct programme ID.
		Excecute insert statement*/ 
		String SQL3 = "insert into registration (Student_ID, CRN,Programme_ID) values (?, ?,?)";
		getJdbcTemplate().update(SQL3, new Object[] { studId, crnNumber, moduleProgramme });
		return;
	}

	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int countRows() {
		String SQL = "select count(*) from modules";
		int rows=getJdbcTemplate().queryForObject(SQL, Integer.class);
		return rows;
	}


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateModuleName(String id, int crn, String name) {
		String SQL = "update modules set Name =? where Module_ID = ? and CRN = ?";
		getJdbcTemplate().update(SQL, new Object[] {name, id, crn});
		System.out.println("Updated record with id: "+ id);
		return;	
		
	}


	@Override
	public List<Module> listModulesByLecturer(String lectId) {
		String SQL = "select * from modules "
				+ " WHERE Lect_ID=?";
		List<Module> moduleList = getJdbcTemplate().query(SQL, new Object[]{lectId}, new ModuleMapper());
		return moduleList;
	}

	
}

	
