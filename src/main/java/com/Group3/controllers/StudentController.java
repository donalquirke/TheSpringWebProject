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

import com.Group3.domain.Student;
import com.Group3.service.StudentDAO;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	Logger logger = Logger.getLogger(StudentController.class);
	
	@Autowired
	StudentDAO studentDAO;
	
	@RequestMapping(value="/listStudents", method = RequestMethod.GET) 
	public String listAll(ModelMap model) {			
			Date date = new java.util.Date();
			List<Student> listStudents=studentDAO.listStudents();
			model.addAttribute("students", listStudents);
			model.addAttribute("now", date);
		    return "displayStudents";			
		}  //WORKING
	
	@RequestMapping(value="/listById/{studentId}", method = RequestMethod.GET) 
	public String getStudent(ModelMap model) {			
			Date date = new java.util.Date();
			List<Student> listStudents=studentDAO.listStudents();
			model.addAttribute("students", listStudents);
			model.addAttribute("now", date);
		    return "displayStudents";			
		}
	
	@RequestMapping(value = "/addNew", method = RequestMethod.GET) 
	public String createStudent(ModelMap model) {
		model.addAttribute("student", new Student());
		return "newStudent";
	}  //WORKING
	
	@RequestMapping(value = "/addNew", method = RequestMethod.POST)
	public String displayStudent(@ModelAttribute("student") Student student, ModelMap model) {

		model.addAttribute("firstName", student.getFirstName());
		model.addAttribute("lastName", student.getLastName());	
		model.addAttribute("email", student.getEmail());
		model.addAttribute("message", "The following student has been added to the system");

		try {
			int id=studentDAO.createStudentGetId(student.getStudentId(), 
					student.getFirstName(), student.getLastName(), student.getEmail());
			model.addAttribute("id", Integer.toString(id));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "displayStudent";
	}  //WORKING
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET) 
	public String deleteStudent(ModelMap model) {   
		List<Student> listStudents=studentDAO.listStudents();
		model.addAttribute("students", listStudents);		
		return "deleteStudent";
	} //WORKING
	
	@RequestMapping(value = "/delete/studentAutoId/{studentAutoId}", method = RequestMethod.GET) 
	public String deleteStudentById(@PathVariable int studentAutoId, ModelMap model) { 
		Student studentDelete=studentDAO.getStudent(studentAutoId);
		studentDAO.deleteStudent(studentAutoId);
		model.addAttribute("message", "Student with Student id "+ studentAutoId +" and details "
				+ "below have been deleted from the system");
		model.addAttribute("studentId", studentDelete.getStudentId());
		model.addAttribute("firstName", studentDelete.getFirstName());
		model.addAttribute("lastName", studentDelete.getLastName());
		model.addAttribute("email", studentDelete.getEmail());
		return "displayStudent";
	} // WORKING
	
	@RequestMapping(value="/modify", method = RequestMethod.GET) 
	public String modify(ModelMap model) {			
		List<Student> listStudents=studentDAO.listStudents();
		Date date = new java.util.Date();	
		model.addAttribute("students", listStudents);
		model.addAttribute("now", date);
		return "modifyStudent";			
	} //Working - displaying all students with modify buttons
	
	@RequestMapping(value = "/modify/studentAutoId/{studentAutoId}", method = RequestMethod.GET) 
	public String modifyStudent(@PathVariable int studentAutoId, ModelMap model) { 
		logger.info("modify");
		Student studentModify=studentDAO.getStudent(studentAutoId);
		model.addAttribute("message", "Student with id "+ studentAutoId +" can now be modified");
		model.addAttribute("student", studentModify);
		return "modifyStudentForm";	
	} //Working - displaying student modify form
	
	@RequestMapping(value="/modify/studentAutoId/{studentAutoId}/email/{email}", method = RequestMethod.GET) 
	public ModelAndView modifyStudent(@PathVariable int studentAutoId, @PathVariable String email, ModelMap model) {
		logger.debug("update request");
		ModelAndView modelAndView = new ModelAndView();
		studentDAO.updateStudent(studentAutoId, email);
		Student studentModify=studentDAO.getStudent(studentAutoId);
		model.addAttribute("message", "Student with student id "+ studentAutoId +" has been modified");
		modelAndView.addObject("student", studentModify);
		modelAndView.setViewName("displayStudent");
		return modelAndView;		
	} //NOT WORKING - not updating database
	
}