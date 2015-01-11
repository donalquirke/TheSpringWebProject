package com.Group3.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Group3.domain.Module;
import com.Group3.service.ModuleDAO;

@Controller
@RequestMapping("/module")
public class ModuleController {
	
	@Autowired
	ModuleDAO moduleDAO;
	@Autowired
    private ServletContext servletContext;
	
	@RequestMapping(value="/listModules", method = RequestMethod.GET) 
	public String listAll(ModelMap model) {			
			Date date = new java.util.Date();
			List<Module> listModules=moduleDAO.listModules();
			model.addAttribute("modules", listModules);
			model.addAttribute("now", date);
		    return "displayModules";			
		}    
		

}