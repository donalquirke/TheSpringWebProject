package com.Group3.domain.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Group3.domain.Module;

@SuppressWarnings("rawtypes")
public class ModuleMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Module module = new Module();
		module.setModuleAutoID(rs.getInt("ModuleAutoID"));
		module.setModuleId(rs.getString("Module_ID"));
		module.setCrnNumber(rs.getInt("CRN"));
		module.setName(rs.getString("Name"));
		module.setLectId(rs.getInt("LecturerAutoID"));
		module.setSemesterId(rs.getString("SemesterAutoID"));
		return module;

	}

}