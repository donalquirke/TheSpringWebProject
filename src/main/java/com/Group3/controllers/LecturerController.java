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

import com.Group3.domain.Lecturer;
import com.Group3.service.LecturerDAO;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {
	
	Logger logger = Logger.getLogger(LecturerController.class);
	
	@Autowired
	LecturerDAO lecturerDAO;
	
	@RequestMapping(value="/listLecturers", method = RequestMethod.GET) 
	public String listAll(ModelMap model) {			
			Date date = new java.util.Date();
			List<Lecturer> listLecturers=lecturerDAO.listLecturers();
			model.addAttribute("lecturers", listLecturers);
			model.addAttribute("now", date);
		    return "displayLecturers";			
		}  //WORKING
	
	@RequestMapping(value="/listById/{lectId}", method = RequestMethod.GET) 
	public String getLecturer(ModelMap model) {			
			Date date = new java.util.Date();
			List<Lecturer> listLecturers=lecturerDAO.listLecturers();
			model.addAttribute("lecturers", listLecturers);
			model.addAttribute("now", date);
		    return "displayLecturers";			
		}
	
	@RequestMapping(value = "/addNew", method = RequestMethod.GET) 
	public String createLecturer(ModelMap model) {
		model.addAttribute("lecturer", new Lecturer());
		return "newLecturer";
	}  //Working - displaying Add Form
	
	@RequestMapping(value = "/addNew", method = RequestMethod.POST)
	public String displayLecturer(@ModelAttribute("lecturer") Lecturer lecturer, ModelMap model) {

		model.addAttribute("firstName", lecturer.getFirstName());
		model.addAttribute("lastName", lecturer.getLastName());	
		model.addAttribute("email", lecturer.getEmail());
		model.addAttribute("message", "The following lecturer has been added to the system");

		try {
			int id=lecturerDAO.createLecturerGetId(lecturer.getLectId(), 
					lecturer.getFirstName(), lecturer.getLastName(), lecturer.getEmail());
			model.addAttribute("id", Integer.toString(id));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "displayLecturer";
	}  //working - Adding new lecturer
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET) 
	public String deleteLecturer(ModelMap model) {   
		List<Lecturer> listLecturers=lecturerDAO.listLecturers();
		model.addAttribute("lecturers", listLecturers);		
		return "deleteLecturer";
	} //Working - displaying all lecturers with delete buttons
	
	@RequestMapping(value = "/delete/lectId/{lectId}", method = RequestMethod.GET) 
	public String deleteLecturerById(@PathVariable String lecturerId, ModelMap model) {
		logger.debug("delete Submit");
		Lecturer lecturerDelete=lecturerDAO.getLecturer(lecturerId);
		lecturerDAO.deleteLecturer(lecturerId);
		model.addAttribute("message", "Lecturer with Lecturer id "+ lecturerId +" and details "
				+ "below have been deleted from the system");
		model.addAttribute("lecturerId", lecturerDelete.getLectId());
		model.addAttribute("firstName", lecturerDelete.getFirstName());
		model.addAttribute("lastName", lecturerDelete.getLastName());
		model.addAttribute("email", lecturerDelete.getEmail());
		return "displayLecturer";
	} // NOT WORKING
	
	@RequestMapping(value="/modify", method = RequestMethod.GET) 
	public String modify(ModelMap model) {			
		List<Lecturer> listLecturers=lecturerDAO.listLecturers();
		Date date = new java.util.Date();	
		model.addAttribute("lecturers", listLecturers);
		model.addAttribute("now", date);
		return "modifyLecturer";			
	} //Working - displaying all lecturers with modify buttons
	
	@RequestMapping(value = "/modify/lectId/{lectId}", method = RequestMethod.GET) 
	public String modifyLecturer(@PathVariable String lectId, ModelMap model) { 
		logger.debug("Load Lecturer Modify Display");
		Lecturer lecturerModify=lecturerDAO.getLecturer(lectId);
		model.addAttribute("message", "Lecturer with id "+ lectId +" can now be modified");
		model.addAttribute("lecturer", lecturerModify);
		return "modifyLecturerForm";	
	} //Working - displaying lecturer modify form
	
	@RequestMapping(value="/modify/lectId/{lectId}/email/{email}", method = RequestMethod.GET) 
	public ModelAndView modifyLecturer(@PathVariable String lectId, @PathVariable String email, ModelMap model) {
		logger.debug("update request");
		ModelAndView modelAndView = new ModelAndView();
		lecturerDAO.updateLecturer(lectId, email);
		Lecturer lecturerModify=lecturerDAO.getLecturer(lectId);
		model.addAttribute("message", "Lecturer with lecturer id "+ lectId +" has been modified");
		
		/**model.addAttribute("lectId", lecturerModify.getFirstName());
		model.addAttribute("lectId", lecturerModify.getLastName());
		model.addAttribute("programmeId", lecturerModify.getEmail());
		return "displayLecturer";	**/
		
		modelAndView.addObject("lecturer", lecturerModify);
		modelAndView.setViewName("displayLecturer");
		return modelAndView;
	} //NOT WORKING - not updating database
	
}


/**
public void createLecturer(String lectId, String firstName, String lastName, String email);

public void deleteLecturer(String lectId);

public void createMultipleLecturers(final List<Lecturer> lecturers);

public Lecturer getLecturer(String id);

public List<Lecturer> listLecturers();

public int countRows();
**/