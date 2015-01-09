package com.Group3.domain.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Group3.domain.Deferral;

@SuppressWarnings("rawtypes")
public class DeferralMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Deferral deferral = new Deferral();
		deferral.setDefId(rs.getInt("Def_ID"));
		deferral.setStudentAutoID(rs.getInt("StudentAutoID"));
		deferral.setLectId(rs.getString("Lect_ID"));
		deferral.setProgrammeAutoID(rs.getInt("ProgrammeAutoID"));
		deferral.setModuleAutoID(rs.getInt("ModuleAutoID"));
		deferral.setApproval(rs.getString("Approved"));
		return deferral;

	}

}