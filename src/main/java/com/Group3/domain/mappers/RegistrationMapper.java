package com.Group3.domain.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Group3.domain.Registration;

@SuppressWarnings("rawtypes")
public class RegistrationMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Registration registration = new Registration();
		registration.setRegistrationAutoId(rs.getInt("RegistrationAutoID"));
		registration.setStudentAutoID(rs.getInt("StudentAutoID"));
		registration.setModuleAutoID(rs.getInt("ModuleAutoID"));
		registration.setProgrammeAutoID(rs.getInt("ProgrammeAutoID"));
		return registration;

	}

}