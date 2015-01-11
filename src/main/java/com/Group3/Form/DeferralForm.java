package com.Group3.Form;

import java.util.ArrayList;
import java.util.List;

import com.Group3.domain.Lecturer;
import com.Group3.domain.Module;
import com.Group3.domain.Programme;

public class DeferralForm {

	private int defId;
	private int studentAutoID;
	private List<Programme> programmeList = new ArrayList<>();
	private List<Module> moduleList = new ArrayList<>();
	private List<Lecturer> lectList = new ArrayList<>();
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
	public List<Programme> getProgrammeList() {
		return programmeList;
	}
	public void setProgrammeList(List<Programme> programmeList) {
		this.programmeList = programmeList;
	}
	public List<Module> getModuleList() {
		return moduleList;
	}
	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}
	public List<Lecturer> getLectList() {
		return lectList;
	}
	public void setLectList(List<Lecturer> lectList) {
		this.lectList = lectList;
	}
	
	
}
