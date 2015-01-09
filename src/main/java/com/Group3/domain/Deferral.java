package com.Group3.domain;

import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class Deferral {

	private int defId;
	private int studentAutoID;
	@Size(min=2, max=10, message="A student ID cannot have less than 2 characters or greater than 10")
	private String lectId;
	@Size(min=2, max=20)
	private int programmeAutoID;
	@Size(min=2, max=10)
	private int moduleAutoID;
	private String approval;
	private MultipartFile file;

	public Deferral() {
		// Blank Constructor
	}

	public Deferral(int defId, int studentAutoID, String lectId,
			int programmeAutoID, int moduleAutoID) {
		super();
		this.defId = defId;
		this.studentAutoID = studentAutoID;
		this.lectId = lectId;
		this.programmeAutoID = programmeAutoID;
		this.moduleAutoID = moduleAutoID;
	}

	public int getDefId() {
		return defId;
	}

	public void setDefId(int defId) {
		this.defId = defId;
	}

	public int getStudentAutoID() {
		return studentAutoID;
	}

	public void setStudentAutoID(int studentAutoID) {
		this.studentAutoID = studentAutoID;
	}

	public String getLectId() {
		return lectId;
	}

	public void setLectId(String lectId) {
		this.lectId = lectId;
	}

	public int getProgrammeAutoID() {
		return programmeAutoID;
	}

	public void setProgrammeAutoID(int programmeAutoID) {
		this.programmeAutoID = programmeAutoID;
	}

	public int getModuleAutoID() {
		return moduleAutoID;
	}

	public void setModuleAutoID(int moduleAutoID) {
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
