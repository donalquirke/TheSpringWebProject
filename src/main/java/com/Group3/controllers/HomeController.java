package com.Group3.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class HomeController {

	@RequestMapping(value={"/","/home"})
	public String showHomePage(ModelMap model) { 
		Date date = new java.util.Date();		
		model.addAttribute("message", "This our Spring Project home page.");
		model.addAttribute("now", date);
		return "home";
	
	} 
}