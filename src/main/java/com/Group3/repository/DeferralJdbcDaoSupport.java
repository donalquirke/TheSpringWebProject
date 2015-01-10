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

import com.Group3.domain.Deferral;
import com.Group3.domain.Module;
import com.Group3.domain.Programme;
import com.Group3.domain.mappers.DeferralMapper;
import com.Group3.domain.mappers.ModuleMapper;
import com.Group3.domain.mappers.ProgrammeMapper;
import com.Group3.service.DeferralDAO;

@Repository
public class DeferralJdbcDaoSupport extends JdbcDaoSupport implements
		DeferralDAO {

	@Autowired
	DeferralJdbcDaoSupport(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void createModuleDeferral(int studentAutoID, String moduleAutoID,
			int crnNumber) {

		/*
		 * The module must be registered to the student and 
		 * correspond to the student's registered Programme 
		 * Get this Programme and coordinator ID
		 */
		List<Programme> programmeList;
		String SQL2 = "SELECT * from programme"
				+ " JOIN registration on registration.ProgrammeAutoID=programme.Programme_ID  "
				+ " AND registration.StudentAutoID= ?"
				+ " AND registration.CRN= ?";
		programmeList = getJdbcTemplate().query(SQL2,new Object[] { studentAutoID, crnNumber }, new ProgrammeMapper());
		int programmeAutoID = programmeList.get(0).getProgrammeAutoID();
		int lecturerId= programmeList.get(0).getLecturerAutoID();

		/* Execute Insert Statement */
		String SQL3 = "insert into deferrals (Student_ID, Lect_ID, Programme_ID, Module_ID) values (?, ?, ?,?)";

		Object[] params = new Object[] { studentAutoID, lecturerId, programmeAutoID,moduleAutoID };
		PreparedStatementCreatorFactory psc = new PreparedStatementCreatorFactory(SQL3);
		psc.addParameter(new SqlParameter("Student_ID", Types.VARCHAR));
		psc.addParameter(new SqlParameter("Lect_ID", Types.VARCHAR));
		psc.addParameter(new SqlParameter("Programme_ID", Types.VARCHAR));
		psc.addParameter(new SqlParameter("Module_ID", Types.VARCHAR));

		KeyHolder holder = new GeneratedKeyHolder();
		getJdbcTemplate().update(psc.newPreparedStatementCreator(params),holder);

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void createProgrammeDeferral(final int studentAutoID, final int programmeAutoID) {

		/*
		 * Get all the modules registered to the studentAutoID that 
		 * correspond to the programme Id
		 */
		final List<Module> studentModules;
		String SQL = "SELECT * from modules"
				+ " JOIN registration on registration.CRN=modules.CRN"
				+ " AND registration.studentAutoID=?"
				+ " AND registration.Programme_ID=?";
		studentModules = getJdbcTemplate().query(SQL,new Object[] { studentAutoID, programmeAutoID }, new ModuleMapper());

		/*
		 * Get the Coordinator Id for the Programme
		 */
		List<Programme> programmeList;
		String SQL2 = "SELECT * from programme "
				+ " WHERE Programme_ID=?";
		programmeList = getJdbcTemplate().query(SQL2,new Object[] { programmeAutoID}, new ProgrammeMapper());
		final int lecturerID = programmeList.get(0).getLecturerAutoID();
		
		/*
		 * Add all the deferrals that correspond to the student ID 
		 * and programme ID
		 */
		String SQL3 = "insert into deferrals (Student_ID, Lect_ID,Programme_ID,Module_ID) values (?, ?, ?,?)";
		getJdbcTemplate().batchUpdate(SQL3, new BatchPreparedStatementSetter() {

			public int getBatchSize() {
				return studentModules.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				Module modules = studentModules.get(i);
				ps.setInt(1, studentAutoID);
				ps.setInt(2, lecturerID);
				ps.setInt(3, programmeAutoID);
				ps.setInt(4, modules.getModuleAutoID());
			}

		});

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<Deferral> listDeferrals() {
		String SQL = "select * from deferrals";
		List<Deferral> deferralList = getJdbcTemplate().query(SQL,new DeferralMapper());
		return deferralList;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<Deferral> listDeferralsByStudent(int studentAutoID) {
		String SQL = "select * from deferrals where Student_ID = ?";
		List<Deferral> deferralsList = getJdbcTemplate().query(SQL,  new Object[]{studentAutoID}, new DeferralMapper());
		return deferralsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void approveModuleDeferral(String studId, int moduleAutoID) {
		
		// Update approval
		String SQL = "UPDATE deferrals SET Approved=True"
				+ " WHERE Student_ID=? AND ModuleAutoID=?";
		getJdbcTemplate().update(SQL, new Object[] { studId, moduleAutoID });

		/*
		 * The module must correspond to a specific CRN Get this CRN
		 */
		List<Module> moduleList;
		String SQL2 = "SELECT * from modules" + " WHERE ModuleAutoID=?";
		moduleList = getJdbcTemplate().query(SQL2, new Object[] { moduleAutoID },new ModuleMapper());
		int crn = moduleList.get(0).getCrnNumber();

		/*
		 * Delete the module corresponding to the student from the 
		 * registration table
		 */
		String SQL3 = "DELETE FROM registration"
				+ " WHERE Student_ID=? AND CRN=?";
		getJdbcTemplate().update(SQL3, new Object[] { studId, crn });

		return;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void approveProgrammeDeferral(String studId, int programmeAutoID) {
		
		// Update approval
		String SQL = "UPDATE deferrals SET Approved=True"
				+ " WHERE Student_ID=? AND Programme_ID=?";
		getJdbcTemplate().update(SQL, new Object[] { studId, programmeAutoID });

		/*
		 * Delete the modules corresponding to the student and programme from
		 * the registration table
		 */
		String SQL2 = "DELETE FROM registration"
				+ " WHERE Student_ID=? AND Programme_ID=?";
		getJdbcTemplate().update(SQL2, new Object[] { studId, programmeAutoID });

		return;

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateDeferral(int defId, String approval) {
		
		// Update approval
		String SQL = "UPDATE deferrals SET Approved=? WHERE Def_ID = ?";
		getJdbcTemplate().update(SQL, new Object[] { approval, defId });

		return;

	}
	
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int countRows() {
		String SQL = "select count(*) from deferrals";
		int rows = getJdbcTemplate().queryForObject(SQL, Integer.class);
		return rows;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int createDeferralGetId(int studentAutoID, String lectId,
			int programmeAutoID, int moduleAutoID, String approval) {
		String SQL = "INSERT into deferrals (StudentAutoID, Lect_ID, ProgrammeAutoID,  ModuleAutoID, Approved) values(?, ?, ?, ?, ?) ";
		
		Object[] params=new Object[]{studentAutoID, lectId, programmeAutoID, moduleAutoID, approval};
		PreparedStatementCreatorFactory psc=new PreparedStatementCreatorFactory(SQL);
		psc.addParameter(new SqlParameter("StudentAutoID", Types.VARCHAR));
		psc.addParameter(new SqlParameter("Lect_ID", Types.VARCHAR));
		psc.addParameter(new SqlParameter("ProgrammeAutoID", Types.VARCHAR));
		psc.addParameter(new SqlParameter("ModuleAutoID", Types.VARCHAR));
		psc.addParameter(new SqlParameter("Approved", Types.VARCHAR));
		
		KeyHolder holder = new GeneratedKeyHolder();
		getJdbcTemplate().update(psc.newPreparedStatementCreator(params), holder);
		System.out.println("holder: " + holder.getKey().toString());
		
		String key = holder.getKey().toString();
		return Integer.parseInt(key);
	}
	

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteDeferralById(int id) {
		String SQL = "delete from deferrals where Def_ID = ?";
		getJdbcTemplate().update(SQL, new Object[]{id});
		System.out.println("deleted record id: "+ id);
		return;
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteDeferralsByStudentAutoID(String studentAutoID){
		String SQL = "delete from deferrals where StudentAutoID = ?";
		getJdbcTemplate().update(SQL, new Object[]{studentAutoID});
		return;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteDeferralsByModuleAutoID(int moduleAutoID){
		String SQL = "delete from deferrals where Module_ID = ?";
		getJdbcTemplate().update(SQL, new Object[]{moduleAutoID});
		return;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Deferral getDeferral(int id) {
		String SQL = "select * from deferrals where Def_ID = ?";
		Deferral deferral = (Deferral) getJdbcTemplate().queryForObject(SQL, new Object[]{id}, new DeferralMapper());
		return deferral;
	}

	

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<Deferral> listDeferralsByProgramme(int programmeAutoID) {
		String SQL = "select * from deferrals where ProgrammeAutoID = ?";
		List<Deferral> deferralsList = getJdbcTemplate().query(SQL,  new Object[]{programmeAutoID}, new DeferralMapper());
		return deferralsList;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<Deferral> listUnapprovedDeferrals(String unapproved) {
		String SQL = "select * from deferrals where Approved = ?";
		List<Deferral> deferralsList = getJdbcTemplate().query(SQL,  new Object[]{unapproved}, new DeferralMapper());
		return deferralsList;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<Deferral> listUnapprovedDeferralsByProgramme(String unapproved, int programmeAutoID) {
		String SQL = "select * from deferrals where Approved = ? and Programme_ID = ?";
		List<Deferral> deferralsList = getJdbcTemplate().query(SQL,  new Object[]{unapproved, programmeAutoID}, new DeferralMapper());
		return deferralsList;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<Deferral> listUnapprovedDeferralsByProgrammeCoordinatorName(
			String unapproved, String firstname) {
		String SQL = "select * from deferrals "
				+ "join lecturer on lecturer.Lect_ID=deferrals.Lect_ID "
				+ "where deferrals.Approved = ? "
				+ "and lecturer.Firstname=?";
		List<Deferral> deferralsList = getJdbcTemplate().query(SQL,  new Object[]{unapproved, firstname}, new DeferralMapper());
		return deferralsList;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<Deferral> listApprovedDeferralsByStudent(String approved, int studentAutoID){
		String SQL = "select * from deferrals where Approved = ? and StudentAutoID = ?";
		List<Deferral> deferralsList = getJdbcTemplate().query(SQL,  new Object[]{approved, studentAutoID}, new DeferralMapper());
		return deferralsList;
	}

	@Override
	public void deleteDeferralsByStudentAutoID(int studentAutoID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createModuleDeferral(int studentAutoID, int moduleAutoID,
			int crnNumber) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}