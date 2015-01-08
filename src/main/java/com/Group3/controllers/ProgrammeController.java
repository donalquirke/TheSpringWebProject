package com.Group3.controllers;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.Group3.domain.Programme;
import com.Group3.domain.Student;
import com.Group3.service.ProgrammeDAO;
import com.Group3.service.StudentDAO;

@Controller
@RequestMapping("/programme")
public class ProgrammeController {
	
	Logger logger = Logger.getLogger(ProgrammeController.class);
	
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
	}  //NOT WORKING - FOREIGN KEY RESTRAINT
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET) 
	public String deleteProgramme(ModelMap model) {   
		List<Programme> listProgrammes=programmeDAO.listProgrammes();
		model.addAttribute("programmes", listProgrammes);		
		return "deleteProgramme";
	} //Working - displaying all programmes with delete buttons
	
	@RequestMapping(value = "/delete/programmeAutoId/{programmeAutoId}", method = RequestMethod.GET) 
	public String deleteProgrammeById(@PathVariable int programmeAutoId, ModelMap model) { 
		Programme programmeDelete=programmeDAO.getProgramme(programmeAutoId);
		programmeDAO.deleteProgramme(programmeAutoId);
		model.addAttribute("message", "Programme with Programme id "+ programmeAutoId +" and details "
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
	}  // WORKING
	
	@RequestMapping(value = "/modify/programmeAutoId/{programmeAutoId}", method = RequestMethod.GET) 
	public String modifyProgramme(@PathVariable int programmeAutoId, ModelMap model) { 
		logger.debug("Load Programme Modify Display");
		Programme programmeModify=programmeDAO.getProgramme(programmeAutoId);
		model.addAttribute("message", "Programme with id "+ programmeAutoId +" can now be modified");
		model.addAttribute("programme", programmeModify);
		return "modifyProgrammeForm";	
	}  // WORKING
	
	@RequestMapping(value="/modify/programmeAutoId/{programmeAutoId}/coordinatorId/{coordinatorId}", method = RequestMethod.GET) 
	public ModelAndView modifyProgramme(@PathVariable int programmeAutoId, @PathVariable String coordinatorId, ModelMap model) {			
		logger.debug("update request");
		ModelAndView modelAndView = new ModelAndView();
		programmeDAO.updateProgramme(programmeAutoId, coordinatorId);
		Programme programmeModify=programmeDAO.getProgramme(programmeAutoId);
		model.addAttribute("message", "Programme with programme id "+ programmeAutoId +" has been modified");
		modelAndView.addObject("programme", programmeModify);
		modelAndView.setViewName("displayProgramme");
		return modelAndView;	
	}  //NOT WORKING - nothing happening, not updating database
	
}
	

