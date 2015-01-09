package com.Group3.repository;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.Group3.domain.Deferral;
import com.Group3.domain.Programme;
import com.Group3.domain.mappers.DeferralMapper;
import com.Group3.domain.mappers.ProgrammeMapper;
import com.Group3.service.ProgrammeDAO;
@Repository
public class ProgrammeJdbcDaoSupport extends JdbcDaoSupport implements ProgrammeDAO {

	@Autowired
	ProgrammeJdbcDaoSupport(DataSource dataSource) {
		   setDataSource(dataSource);
	} 

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<Programme> listProgrammeByStudentID(String studentID){
		String SQL = "select p.* "
				+ "from programme p "
				+ "left join registration r on r.programme_id = p.programme_id "
				+ "where r.student_id = ?";
		List<Programme> programmeList = getJdbcTemplate().query(SQL,  new Object[]{studentID}, new ProgrammeMapper());
		return programmeList;
	}
	
	/**
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void createProgramme(String programmeId, int numYears, int lecturerAutoID, String startMonth) {
		
		for (int x=0;x<numYears; x++){
		int progYear=x+1;
		//We want the Programme ID as format "****_Y*"
		String programmeIdFormatted=programmeId + "_Y" + progYear;
		
		//We need to define the semesters for the given Programme"
		String semesterId1=programmeIdFormatted + "_S1";
		String semesterId2=programmeIdFormatted + "_S2";
		
		//Create Programme 
		String SQL = "insert into programme (Programme_ID, Num_Years, Coord_ID, Prog_Year) values (?, ?, ?, ?)";
		getJdbcTemplate().update(SQL,new Object[] { programmeIdFormatted, numYears, lecturerAutoID, progYear  });
		
		//Need to create entries in Semester Table
		String SQL2 = "insert into semester (Semester_ID, Programme_ID, StartDate, FinishDate) values (?, ?, ?, ?)";
		if (startMonth=="Sept"){
		getJdbcTemplate().update(SQL2,new Object[] { semesterId1, programmeIdFormatted,  "Sept", "Dec"  });
		getJdbcTemplate().update(SQL2,new Object[] { semesterId2, programmeIdFormatted, "Jan", "May"  });
		
		}
		else if(startMonth=="Jan"){
			getJdbcTemplate().update(SQL2,new Object[] { semesterId1, programmeIdFormatted, "Jan", "May"  });
		getJdbcTemplate().update(SQL2,new Object[] { semesterId2, programmeIdFormatted, "Sept", "Dec"  });
		
		}
		
		}
			
	}  **/
		
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteProgramme(int programmeAutoId) {
		String SQL = "delete from programme where ProgrammeAutoID = ?";
		getJdbcTemplate().update(SQL, new Object[] { programmeAutoId});
		System.out.println("deleted record id: "+ programmeAutoId);
		return;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public String getStudentProgrammeId(String studId) {
		String SQL = "select DISTINCT Programme_ID from programme"
			+ " WHERE Programme_ID in (SELECT Programme_ID FROM registration WHERE Student_ID = ?)";
		String programmeId = getJdbcTemplate().queryForObject(SQL,new Object[]{studId}, String.class);
		return programmeId;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<Programme> listProgrammes() {
		String SQL = "select * from programme";
		List<Programme> programmeList = getJdbcTemplate().query(SQL, 
						new ProgrammeMapper());
		return programmeList;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int countRows() {
		String SQL = "select count(*) from programme";
		int rows=getJdbcTemplate().queryForObject(SQL, Integer.class);
		return rows;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Programme getProgramme(int programmeAutoId) {
		String SQL = "select * from programme where ProgrammeAutoID = ?";
		Programme programme = (Programme) getJdbcTemplate().queryForObject(SQL, new Object[]{programmeAutoId}, new ProgrammeMapper());
		return programme;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateProgramme(int programmeAutoId, int lecturerAutoID) {
		String SQL = "update Programme set LecturerAutoID = ? where ProgrammeAutoID = ?";
		getJdbcTemplate().update(SQL, new Object[] {lecturerAutoID, programmeAutoId});
		System.out.println("Updated record with Programme ID: "+ programmeAutoId);
		return;		
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int createProgrammeGetId(String programmeId, int numYears,
			int lecturerAutoID, int progYear) {
		String SQL = "insert into programme (Programme_ID, Num_Years, LecturerAutoID, Prog_Year) values (?, ?, ?,?)";
		
		Object[] params=new Object[]{programmeId, numYears, lecturerAutoID, progYear};
		PreparedStatementCreatorFactory psc=new PreparedStatementCreatorFactory(SQL);
		psc.addParameter(new SqlParameter("Programme_ID", Types.VARCHAR));
		psc.addParameter(new SqlParameter("Num_Years", Types.VARCHAR));
		psc.addParameter(new SqlParameter("LecturerAutoID", Types.VARCHAR));
		psc.addParameter(new SqlParameter("Prog_Year", Types.VARCHAR));
		
		KeyHolder holder = new GeneratedKeyHolder();
		getJdbcTemplate().update(psc.newPreparedStatementCreator(params), holder);
		System.out.println("holder: " + holder.getKey().toString());
		
		String key = holder.getKey().toString();
		return Integer.parseInt(key);
	}

	@Override
	public void createProgramme(String programmeId, int numYears,
			String coordinatorId, String startMonth) {
		// TODO Auto-generated method stub
		
	}

}
