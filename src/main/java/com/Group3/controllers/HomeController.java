package com.Group3.controllers;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
public class HomeController {
	Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping(value={"/","/home"})
	public String showHomePage(ModelMap model) { 
		logger.info("showHomePage");
		Date date = new java.util.Date();		
		model.addAttribute("message", "Please choose from one of the following: ");
		model.addAttribute("now", date);
		return "home";
	
	} 
	@RequestMapping(value={"/ajaxCall"})
	@ResponseBody
	public String ajaxCall() { 
		logger.info("ajaxCall");
		String 	message = "well done";
		return message;
	
	} 
}