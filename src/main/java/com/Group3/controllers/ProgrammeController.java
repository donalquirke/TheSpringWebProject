package com.Group3.controllers;

import java.util.Date;
import java.util.HashMap;
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

import com.Group3.domain.Lecturer;
import com.Group3.domain.Programme;
import com.Group3.service.LecturerDAO;
import com.Group3.service.ProgrammeDAO;

@Controller
@RequestMapping("/programme")
public class ProgrammeController {
	
	Logger logger = Logger.getLogger(ProgrammeController.class);
	
	@Autowired
	ProgrammeDAO programmeDAO;
	
	@Autowired
	LecturerDAO lecturerDAO;
	
	@RequestMapping(value="/listProgrammes", method = RequestMethod.GET) 
	public String listAll(ModelMap model) {			
			Date date = new java.util.Date();
			List<Programme> listProgrammes=programmeDAO.listProgrammes();
			
			List<Lecturer> listLecturers   =lecturerDAO.listLecturers();
			
			HashMap<Integer, Lecturer> lecturerMap   = new HashMap<Integer, Lecturer>();
			
			for(Lecturer lecturer : listLecturers){
				lecturerMap.put(lecturer.getLecturerAutoId(), lecturer);
			}
			
			model.addAttribute("programmes", listProgrammes);
			model.addAttribute("lecturerMap", lecturerMap);
			model.addAttribute("now", date);
		    return "displayProgrammes";			
		}  
	
	@RequestMapping(value="/listById/{programmeId}", method = RequestMethod.GET) 
	public String getProgramme(ModelMap model) {			
			Date date = new java.util.Date();
			List<Programme> listProgrammes=programmeDAO.listProgrammes();
			model.addAttribute("students", listProgrammes);
			model.addAttribute("now", date);
		    return "displayProgrammes";			
		}
	
	@RequestMapping(value = "/addNew", method = RequestMethod.GET) 
	public ModelAndView createProgramme(ModelMap model) {
		logger.debug("addNew Programme");
		ModelAndView modelAndView = new ModelAndView();
		List<Lecturer> lecturerList=lecturerDAO.listLecturers();
		System.out.println("list: " +lecturerList);
		model.addAttribute("programme", new Programme());
		//modelAndView.addObject("lecturerList", lecturerList);
		modelAndView.setViewName("newProgramme");
		return modelAndView;
	}  
	
	@RequestMapping(value = "/addNew", method = RequestMethod.POST)
	public ModelAndView displayProgramme(@ModelAttribute("programme") Programme programme, ModelMap model) {
		logger.debug("programme add form");
		ModelAndView modelAndView = new ModelAndView();
		List<Lecturer> lecturerList=lecturerDAO.listLecturers();
		System.out.println("list: " +lecturerList);
		model.addAttribute("programmeId", programme.getProgrammeId());
		model.addAttribute("numYears", programme.getNumYears());	
		model.addAttribute("lecturerAutoID", programme.getLecturerAutoID());
		modelAndView.addObject("lecturerList", lecturerList);
		model.addAttribute("progYear", programme.getProgYear());
		model.addAttribute("message", "The following programme has been added to the system");

		try {
			int programmeId=programmeDAO.createProgrammeGetId(programme.getProgrammeId(), 
					programme.getNumYears(), programme.getLecturerAutoID(), programme.getProgYear());
			model.addAttribute("programmeId", Integer.toString(programmeId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("displayProgramme");
		return modelAndView;
	}  
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET) 
	public String deleteProgramme(ModelMap model) {   
		List<Programme> listProgrammes=programmeDAO.listProgrammes();
		model.addAttribute("programmes", listProgrammes);		
		return "deleteProgramme";
	} 
	
	@RequestMapping(value = "/delete/programmeAutoID/{programmeAutoID}", method = RequestMethod.GET) 
	public String deleteProgrammeById(@PathVariable int programmeAutoID, ModelMap model) { 
		Programme programmeDelete=programmeDAO.getProgramme(programmeAutoID);
		programmeDAO.deleteProgramme(programmeAutoID);
		model.addAttribute("message", "Programme with Programme id "+ programmeAutoID +" and details "
				+ "below have been deleted from the system");
		model.addAttribute("programmeId", programmeDelete.getProgrammeId());
		model.addAttribute("numYears", programmeDelete.getNumYears());
		model.addAttribute("lecturerAutoID", programmeDelete.getLecturerAutoID());
		model.addAttribute("progYear", programmeDelete.getProgYear());
		return "displayProgramme";
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.GET) 
	public String modify(ModelMap model) {		
		logger.debug("modify programme");
		List<Programme> listProgrammes=programmeDAO.listProgrammes();
		
		List<Lecturer> listLecturers   =lecturerDAO.listLecturers();
		
		HashMap<Integer, Lecturer> lecturerMap   = new HashMap<Integer, Lecturer>();
		
		for(Lecturer lecturer : listLecturers){
			lecturerMap.put(lecturer.getLecturerAutoId(), lecturer);
		}
		
		Date date = new java.util.Date();
		model.addAttribute("lecturerMap", lecturerMap);	
		model.addAttribute("programmes", listProgrammes);
		model.addAttribute("now", date);
		System.out.println(" " + listProgrammes);
		return "modifyProgramme";			
	}  
	
	@RequestMapping(value = "/modify/programmeAutoID/{programmeAutoID}", method = RequestMethod.GET) 
	public String modifyProgramme(@PathVariable int programmeAutoID, ModelMap model) { 
		logger.debug("Load Programme Modify Display");
		Programme programmeModify=programmeDAO.getProgramme(programmeAutoID);
		model.addAttribute("message", "Programme with id "+ programmeAutoID +" can now be modified");
		model.addAttribute("programme", programmeModify);
		return "modifyProgrammeForm";	
	}  
	
	@RequestMapping(value="/modify/programmeAutoID/{programmeAutoID}/lecturerAutoID/{lecturerAutoID}", method = RequestMethod.GET) 
	public ModelAndView modifyProgramme(@PathVariable int programmeAutoID, @PathVariable int lecturerAutoID, ModelMap model) {			
		logger.debug("update request");
		ModelAndView modelAndView = new ModelAndView();
		programmeDAO.updateProgramme(programmeAutoID, lecturerAutoID);
		Programme programmeModify=programmeDAO.getProgramme(programmeAutoID);
		model.addAttribute("message", "Programme with programme id "+ programmeAutoID +" has been modified");
		modelAndView.addObject("programme", programmeModify);
		modelAndView.setViewName("displayProgramme");
		return modelAndView;	
	}  
	
}
	
