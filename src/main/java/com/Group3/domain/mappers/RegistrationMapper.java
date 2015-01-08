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
		registration.setStudentId(rs.getString("Student_ID"));
		registration.setCrnNumber(rs.getInt("CRN"));
		registration.setProgrammeId(rs.getString("Programme_ID"));
		return registration;

	}

}