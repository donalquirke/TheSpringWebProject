package com.Group3.controllers;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.Group3.domain.Registration;
import com.Group3.service.RegistrationDAO;


@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	RegistrationDAO registrationDAO;
	
	@RequestMapping(value="/listRegistrations", method = RequestMethod.GET) 
	public String listAll(ModelMap model) {			
			Date date = new java.util.Date();
			List<Registration> listRegistrations=registrationDAO.listRegistrations();
			model.addAttribute("registrations", listRegistrations);
			model.addAttribute("now", date);
		    return "displayRegistrations";			
		}  //Working - displaying all registrations in database
}
