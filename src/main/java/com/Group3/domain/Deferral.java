package com.Group3.domain;

import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class Deferral {

	private int defId;
	private String studentId;
	@Size(min=2, max=10, message="A student ID cannot have less than 2 characters or greater than 10")
	private String lectId;
	@Size(min=2, max=20)
	private String programmeId;
	@Size(min=2, max=10)
	private String moduleId;
	private String approval;
	private MultipartFile file;

	public Deferral() {
		// Blank Constructor
	}

	public Deferral(int defId, String studentId, String lectId,
			String programmeId, String moduleId) {
		super();
		this.defId = defId;
		this.studentId = studentId;
		this.lectId = lectId;
		this.programmeId = programmeId;
		this.moduleId = moduleId;
	}

	public int getDefId() {
		return defId;
	}

	public void setDefId(int defId) {
		this.defId = defId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getLectId() {
		return lectId;
	}

	public void setLectId(String lectId) {
		this.lectId = lectId;
	}

	public String getProgrammeId() {
		return programmeId;
	}

	public void setProgrammeId(String programmeId) {
		this.programmeId = programmeId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
