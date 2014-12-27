package com.Group3.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.Group3.domain.Student;
import com.Group3.repository.StudentJdbcDaoSupport;
import com.Group3.service.StudentDAO;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentDAO studentDAO;
	
	@RequestMapping(value="/listall", method = RequestMethod.GET) 
	public String listAll(ModelMap model) {			
			Date date = new java.util.Date();
			List<Student> listStudents=studentDAO.listStudents();
			model.addAttribute("deferrals", listStudents);
			model.addAttribute("now", date);
		    return "displayStudents";			
		}  
	
}