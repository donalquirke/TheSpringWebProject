package com.Group3.controllers;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class HomeController {
	Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping(value={"/","/home"})
	public String showHomePage(ModelMap model) { 
		logger.info("showHomePage");
		Date date = new java.util.Date();		
		model.addAttribute("message", "To apply for deferral, "
				+ "download PDF and complete online application form "
				+ "attaching scanned copy of completed form: ");
		model.addAttribute("now", date);
		return "home";
	
	}  
}