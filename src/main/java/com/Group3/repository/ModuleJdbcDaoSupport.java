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

import com.Group3.domain.Module;
import com.Group3.domain.mappers.ModuleMapper;
import com.Group3.service.ModuleDAO;
@Repository
public class ModuleJdbcDaoSupport extends JdbcDaoSupport implements ModuleDAO {

	@Autowired
	ModuleJdbcDaoSupport(DataSource dataSource) {
		   setDataSource(dataSource);
	} 
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<Module> listModuleByProgrammeAutoID(int programmeAutoID){
		String SQL = "select m.* "
				+ "from modules m "
				+ "left join semester s on m.SemesterAutoID = s.SemesterAutoID "
				+ "where s.programmeAutoID = ?";
		System.out.println("SQL : " + SQL);
		List<Module> moduleList = getJdbcTemplate().query(SQL,  new Object[]{programmeAutoID}, new ModuleMapper());
		return moduleList;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void createModule(String moduleId, int crnNumber, String name, int lecturerAutoId, int semesterAutoId) {
		String SQL = "insert into modules (Module_ID, CRN, Name, LecturerAutoID, SemesterAutoID) values (?, ?, ?,?,?)";
		getJdbcTemplate().update(SQL,new Object[] { moduleId, crnNumber, name, lecturerAutoId, semesterAutoId  });
		return;
		}
	

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteModule(int moduleAutoID) {
		String SQL = "delete from modules where ModuleAutoID = ?";
		getJdbcTemplate().update(SQL, new Object[] { moduleAutoID });
		return;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void createMultipleModules(final List<Module> modules) {
		String SQL = "insert into modules (Module_ID, CRN, Name, LecturerAutoID, SemesterAutoID) values (?, ?, ?,?,?)";
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
				ps.setInt(4, module.getLecturerAutoID());
				ps.setInt(5, module.getSemesterAutoID());
			}

						
		});
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Module getModule(Integer ModuleAutoID) {
		String SQL = "select * from modules where ModuleAutoID = ?";
		logger.debug(SQL);
		logger.debug("moduleId " + ModuleAutoID);
		Module module= (Module) getJdbcTemplate().queryForObject(SQL, 
						new Object[]{ModuleAutoID}, new ModuleMapper());
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
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int countRows() {
		String SQL = "select count(*) from modules";
		int rows=getJdbcTemplate().queryForObject(SQL, Integer.class);
		return rows;
	}


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateModuleName(int moduleAutoID, String name) {
		String SQL = "update modules set Name =? where ModuleAutoID = ?";
		getJdbcTemplate().update(SQL, new Object[] {name, moduleAutoID});
		System.out.println("Updated record with id: "+ moduleAutoID);
		return;	
		
	}


	@Override
	public List<Module> listModulesByLecturer(String lectId) {
		String SQL = "select * from modules "
				+ " WHERE Lect_ID=?";
		List<Module> moduleList = getJdbcTemplate().query(SQL, new Object[]{lectId}, new ModuleMapper());
		return moduleList;
	}

	@Override
	public List<Module> listModulesWithdeferrals() {
		String SQL = "select DISTINCT m.* from modules as m"
				+ " join deferrals as d on m.ModuleAutoID = d.ModuleAutoID";
		@SuppressWarnings("unchecked")
		List<Module> moduleList = getJdbcTemplate().query(SQL, 
						new ModuleMapper());
		return moduleList;
	}

	@Override
	public int createModuleGetId(String moduleId, int crnNumber, String name,
			Integer lecturerAutoID, int semesterAutoId) {
		String SQL = "insert into modules (Module_ID, CRN, Name, LecturerAutoID, SemesterAutoID) values (?, ?, ?, ?, ?)";
		
		Object[] params=new Object[]{moduleId, crnNumber, name, lecturerAutoID, semesterAutoId};
		PreparedStatementCreatorFactory psc=new PreparedStatementCreatorFactory(SQL);
		psc.addParameter(new SqlParameter("Module_ID", Types.VARCHAR));
		psc.addParameter(new SqlParameter("CRN", Types.INTEGER));
		psc.addParameter(new SqlParameter("Name", Types.VARCHAR));
		psc.addParameter(new SqlParameter("LecturerAutoID", Types.INTEGER));
		psc.addParameter(new SqlParameter("SemesterAutoID", Types.INTEGER));
		
		KeyHolder holder = new GeneratedKeyHolder();
		getJdbcTemplate().update(psc.newPreparedStatementCreator(params), holder);
		System.out.println("holder: " + holder.getKey().toString());
		
		String key = holder.getKey().toString();
		return Integer.parseInt(key);
	}

	@Override
	public void updateModule(int moduleAutoID, int lecturerAutoID) {
		String SQL = "update modules set LecturerAutoID = ? where ModuleAutoID = ?";
		getJdbcTemplate().update(SQL, new Object[] {lecturerAutoID, moduleAutoID});
		System.out.println("Updated record with Module ID: "+ moduleAutoID);
		return;	
		
	}

	
}

	
