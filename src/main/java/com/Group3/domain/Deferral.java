package com.Group3.domain;

import org.springframework.web.multipart.MultipartFile;

public class Deferral {

	private Integer defId;
	private Integer studentAutoID;
	//@Size(min=2, max=10, message="A student ID cannot have less than 2 characters or greater than 10")
	private Integer lectId;
	//@Size(min=2, max=20)
	private Integer programmeAutoID;
	//@Size(min=2, max=10)
	private Integer moduleAutoID;
	private String approval;
	private MultipartFile file;

	public Deferral() {
		// Blank Constructor
	}

	public Deferral(Integer defId, Integer studentAutoID, Integer lectId,
			Integer programmeAutoID, Integer moduleAutoID) {
		super();
		this.defId = defId;
		this.studentAutoID = studentAutoID;
		this.lectId = lectId;
		this.programmeAutoID = programmeAutoID;
		this.moduleAutoID = moduleAutoID;
	}

	public Integer getDefId() {
		return defId;
	}

	public void setDefId(Integer defId) {
		this.defId = defId;
	}

	public Integer getStudentAutoID() {
		return studentAutoID;
	}

	public void setStudentAutoID(Integer studentAutoID) {
		this.studentAutoID = studentAutoID;
	}

	public Integer getLectId() {
		return lectId;
	}

	public void setLectId(Integer lectId) {
		this.lectId = lectId;
	}

	public Integer getProgrammeAutoID() {
		return programmeAutoID;
	}

	public void setProgrammeAutoID(Integer programmeAutoID) {
		this.programmeAutoID = programmeAutoID;
	}

	public Integer getModuleAutoID() {
		return moduleAutoID;
	}

	public void setModuleAutoID(Integer moduleAutoID) {
		this.moduleAutoID = moduleAutoID;
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
