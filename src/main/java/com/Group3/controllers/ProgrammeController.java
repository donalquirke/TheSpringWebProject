package com.Group3.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Group3.domain.Programme;
import com.Group3.domain.Student;
import com.Group3.service.ProgrammeDAO;
import com.Group3.service.StudentDAO;

@Controller
@RequestMapping("/programme")
public class ProgrammeController {
	
	@Autowired
	ProgrammeDAO programmeDAO;
	
	@RequestMapping(value="/listProgrammes", method = RequestMethod.GET) 
	public String listAll(ModelMap model) {			
			Date date = new java.util.Date();
			List<Programme> listProgrammes=programmeDAO.listProgrammes();
			model.addAttribute("programmes", listProgrammes);
			model.addAttribute("now", date);
		    return "displayProgrammes";			
		}  //working
	
	@RequestMapping(value="/listById/{programmeId}", method = RequestMethod.GET) 
	public String getProgramme(ModelMap model) {			
			Date date = new java.util.Date();
			List<Programme> listProgrammes=programmeDAO.listProgrammes();
			model.addAttribute("students", listProgrammes);
			model.addAttribute("now", date);
		    return "displayProgrammes";			
		}
	
	@RequestMapping(value = "/addNew", method = RequestMethod.GET) 
	public String createProgramme(ModelMap model) {
		model.addAttribute("programme", new Programme());
		return "newProgramme";
	}  //working
	
	@RequestMapping(value = "/addNew", method = RequestMethod.POST)
	public String displayProgramme(@ModelAttribute("programme") Programme programme, ModelMap model) {

		model.addAttribute("programmeId", programme.getProgrammeId());
		model.addAttribute("numYears", programme.getNumYears());	
		model.addAttribute("coordinatorId", programme.getCoordinatorId());
		model.addAttribute("progYear", programme.getProgYear());
		model.addAttribute("message", "The following programme has been added to the system");

		try {
			int programmeId=programmeDAO.createProgrammeGetId(programme.getProgrammeId(), 
					programme.getNumYears(), programme.getCoordinatorId(), programme.getProgYear());
			model.addAttribute("programmeId", Integer.toString(programmeId));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "displayProgramme";
	}  //working
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET) 
	public String deleteProgramme(ModelMap model) {   
		List<Programme> listProgrammes=programmeDAO.listProgrammes();
		model.addAttribute("students", listProgrammes);		
		return "deleteProgramme";
	}
	
	@RequestMapping(value = "/delete/programmeId/{programmeId}", method = RequestMethod.GET) 
	public String deleteProgrammeById(@PathVariable String programmeId, ModelMap model) { 
		Programme programmeDelete=programmeDAO.getProgramme(programmeId);
		programmeDAO.deleteProgramme(programmeId);
		model.addAttribute("message", "Programme with Programme id "+ programmeId +" and details "
				+ "below have been deleted from the system");
		model.addAttribute("programmeId", programmeDelete.getProgrammeId());
		model.addAttribute("numYears", programmeDelete.getNumYears());
		model.addAttribute("coordinatorId", programmeDelete.getCoordinatorId());
		model.addAttribute("progYear", programmeDelete.getProgYear());
		return "displayProgramme";
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.GET) 
	public String modify(ModelMap model) {			
		List<Programme> listProgrammes=programmeDAO.listProgrammes();
		Date date = new java.util.Date();	
		model.addAttribute("programmes", listProgrammes);
		model.addAttribute("now", date);
		return "modifyProgramme";			
	}
	
	@RequestMapping(value = "/modify/programmeId/{programmeId}", method = RequestMethod.GET) 
	public String modifyProgramme(@PathVariable String programmeId, ModelMap model) { 
		Programme programmeModify=programmeDAO.getProgramme(programmeId);
		model.addAttribute("message", "Programme with id "+ programmeId +" can now be modified");
		model.addAttribute("programme", programmeModify);
		return "modifyProgrammeForm";	
	}
	
	@RequestMapping(value="/modify/programmeId/{programmeId}/coordinatorId/{coordinatorId}", method = RequestMethod.GET) 
	public String modifyProgramme(@PathVariable String programmeId, @PathVariable int numYears, 
			@PathVariable String coordinatorId, @PathVariable int progYear, ModelMap model) {			
		programmeDAO.updateProgramme(programmeId, numYears, coordinatorId, progYear);
		Programme programmeModify=programmeDAO.getProgramme(programmeId);
		model.addAttribute("message", "Programme with programme id "+ programmeId +" has been modified");
		model.addAttribute("numYears", programmeModify.getNumYears());
		model.addAttribute("coordinatorId", programmeModify.getCoordinatorId());
		model.addAttribute("progYear", programmeModify.getProgYear());
		return "displayProgramme";		
	}
	
}
	

