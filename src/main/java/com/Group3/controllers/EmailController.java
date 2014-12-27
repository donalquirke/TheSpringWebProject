package com.Group3.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.Group3.domain.EmailForm;
import com.Group3.service.Mail;

@Controller
public class EmailController {
	
	@Autowired
	private Mail mail;	
     
	@RequestMapping(value = "/createEmail", method = RequestMethod.GET) 
	public ModelAndView createEmail() {                                
		System.out.println("Create an email.");
		ModelMap map=new ModelMap();
		map.addAttribute("email", new EmailForm());
		return new ModelAndView("email", map);
	} 
	
	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST) 
	public String sendEmailRequest(@ModelAttribute("email") @Valid EmailForm email, BindingResult result, ModelMap model) {	

		if(result.hasErrors())		
			return "sendEmail";			

		model.addAttribute("to", email.getTo());
		model.addAttribute("from", email.getFrom());	
		model.addAttribute("subject", email.getSubject());
		model.addAttribute("message", email.getMessage());
		
		mail.sendMail( email.getFrom(), email.getTo(), email.getSubject(), email.getMessage());
		mail.sendPreconfiguredMail();		
				
		return "displayEmail";
	}
}