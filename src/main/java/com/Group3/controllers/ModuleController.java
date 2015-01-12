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
import com.Group3.domain.Module;
import com.Group3.domain.Programme;
import com.Group3.domain.Semester;
import com.Group3.service.LecturerDAO;
import com.Group3.service.ModuleDAO;
import com.Group3.service.ProgrammeDAO;

@Controller
@RequestMapping("/module")
public class ModuleController {
	
	Logger logger = Logger.getLogger(ModuleController.class);
	
	@Autowired
	ModuleDAO moduleDAO;
	
	@Autowired
	LecturerDAO lecturerDAO;
	
	@RequestMapping(value="/listModules", method = RequestMethod.GET) 
	public String listAll(ModelMap model) {			
			Date date = new java.util.Date();
			List<Module> listModules=moduleDAO.listModules();
			
			List<Lecturer> listLecturers   =lecturerDAO.listLecturers();
			
			HashMap<Integer, Lecturer> lecturerMap   = new HashMap<Integer, Lecturer>();
			
			for(Lecturer lecturer : listLecturers){
				lecturerMap.put(lecturer.getLecturerAutoId(), lecturer);
			}
			
			model.addAttribute("modules", listModules);
			model.addAttribute("lecturerMap", lecturerMap);
			model.addAttribute("now", date);
		    return "displayModules";			
		}  //working
	
	@RequestMapping(value="/listById/{moduleId}", method = RequestMethod.GET) 
	public String getModule(ModelMap model) {			
			Date date = new java.util.Date();
			List<Module> listModules=moduleDAO.listModules();
			model.addAttribute("modules", listModules);
			model.addAttribute("now", date);
		    return "displayModules";			
		}
	
	@RequestMapping(value = "/addNew", method = RequestMethod.GET) 
	public ModelAndView createModule(ModelMap model) {
		logger.debug("add New Module");
		ModelAndView modelAndView = new ModelAndView();
		List<Lecturer> lecturerList=lecturerDAO.listLecturers();
		System.out.println("list: " +lecturerList);
		model.addAttribute("module", new Module());
		//modelAndView.addObject("lecturerList", lecturerList);
		modelAndView.setViewName("newModule");
		return modelAndView;
	}  //
	
	@RequestMapping(value = "/addNew", method = RequestMethod.POST)
	public ModelAndView displayModule(@ModelAttribute("module") Module module, ModelMap model) {
		logger.debug("module add form");
		ModelAndView modelAndView = new ModelAndView();
		List<Lecturer> lecturerList=lecturerDAO.listLecturers();
		System.out.println("list: " +lecturerList);
		model.addAttribute("moduleId", module.getModuleId());
		model.addAttribute("crnNumber", module.getCrnNumber());
		model.addAttribute("name", module.getName());	
		model.addAttribute("lecturerAutoID", module.getLecturerAutoID());
		modelAndView.addObject("lecturerList", lecturerList);
		model.addAttribute("semesterAutoID", module.getSemesterAutoID());
		model.addAttribute("message", "The following module has been added to the system");

		try {
			int moduleId=moduleDAO.createModuleGetId(module.getModuleId(), 
					module.getCrnNumber(), module.getName(), module.getLecturerAutoID(), module.getSemesterAutoID());
			model.addAttribute("moduleId", Integer.toString(moduleId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("displayModule");
		return modelAndView;
	} 
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET) 
	public String deleteModule(ModelMap model) {   
		List<Module> listModules=moduleDAO.listModules();
		model.addAttribute("modules", listModules);	
		System.out.println(listModules);
		return "deleteModule";
	} 
	
	@RequestMapping(value = "/delete/moduleAutoID/{moduleAutoID}", method = RequestMethod.GET) 
	public String deleteProgrammeById(@PathVariable int moduleAutoID, ModelMap model) { 
		Module moduleDelete=moduleDAO.getModule(moduleAutoID);
		moduleDAO.deleteModule(moduleAutoID);
		model.addAttribute("message", "Module with Module id "+ moduleAutoID +" and details "
				+ "below have been deleted from the system");
		model.addAttribute("moduleId", moduleDelete.getModuleId());
		model.addAttribute("crnNumber", moduleDelete.getCrnNumber());
		model.addAttribute("name", moduleDelete.getName());
		model.addAttribute("lecturerAutoID", moduleDelete.getLecturerAutoID());
		model.addAttribute("semesterAutoID", moduleDelete.getSemesterAutoID());
		return "displayModule";
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.GET) 
	public String modify(ModelMap model) {		
		logger.debug("modify module");
		List<Module> listModules=moduleDAO.listModules();
		
		List<Lecturer> listLecturers   =lecturerDAO.listLecturers();
		
		HashMap<Integer, Lecturer> lecturerMap   = new HashMap<Integer, Lecturer>();
		
		for(Lecturer lecturer : listLecturers){
			lecturerMap.put(lecturer.getLecturerAutoId(), lecturer);
		}
		
		
		
		Date date = new java.util.Date();
		model.addAttribute("lecturerMap", lecturerMap);
		model.addAttribute("modules", listModules);
		model.addAttribute("now", date);
		System.out.println(" " + listModules);
		return "modifyModule";			
	}  
	
	@RequestMapping(value = "/modify/moduleAutoID/{moduleAutoID}", method = RequestMethod.GET) 
	public String modifyProgramme(@PathVariable int moduleAutoID, ModelMap model) { 
		logger.debug("Load Module Modify Display");
		Module moduleModify=moduleDAO.getModule(moduleAutoID);
		model.addAttribute("message", "Module with id "+ moduleAutoID +" can now be modified");
		model.addAttribute("programme", moduleModify);
		return "modifyModuleForm";	
	}  
	
	@RequestMapping(value="/modify/moduleAutoID/{moduleAutoID}/lecturerAutoID/{lecturerAutoID}", method = RequestMethod.GET) 
	public ModelAndView modifyModule(@PathVariable int moduleAutoID, @PathVariable int lecturerAutoID, ModelMap model) {			
		logger.debug("update request");
		ModelAndView modelAndView = new ModelAndView();
		moduleDAO.updateModule(moduleAutoID, lecturerAutoID);
		Module moduleModify=moduleDAO.getModule(moduleAutoID);
		model.addAttribute("message", "Module with moduleAutoID id "+ moduleAutoID +" has been modified");
		modelAndView.addObject("module", moduleModify);
		modelAndView.setViewName("displayModule");
		return modelAndView;	
	}  
	
}
	
