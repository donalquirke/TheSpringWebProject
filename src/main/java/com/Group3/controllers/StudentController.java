package com.Group3.controllers;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.Group3.domain.Student;
import com.Group3.service.StudentDAO;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentDAO studentDAO;
	
	@RequestMapping(value="/listStudents", method = RequestMethod.GET) 
	public String listAll(ModelMap model) {			
			Date date = new java.util.Date();
			List<Student> listStudents=studentDAO.listStudents();
			model.addAttribute("students", listStudents);
			model.addAttribute("now", date);
		    return "displayStudents";			
		}  //working
	
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
	}  //working
	
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
	}  //working
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET) 
	public String deleteStudent(ModelMap model) {   
		List<Student> listStudents=studentDAO.listStudents();
		model.addAttribute("students", listStudents);		
		return "deleteStudent";
	}
	
	@RequestMapping(value = "/delete/studentId/{studentId}", method = RequestMethod.GET) 
	public String deleteStudentById(@PathVariable String studentId, ModelMap model) { 
		Student studentDelete=studentDAO.getStudent(studentId);
		studentDAO.deleteStudent(studentId);
		model.addAttribute("message", "Student with Student id "+ studentId +" and details "
				+ "below have been deleted from the system");
		model.addAttribute("studentId", studentDelete.getStudentId());
		model.addAttribute("firstName", studentDelete.getFirstName());
		model.addAttribute("lastName", studentDelete.getLastName());
		model.addAttribute("email", studentDelete.getEmail());
		return "displayStudent";
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.GET) 
	public String modify(ModelMap model) {			
		List<Student> listStudents=studentDAO.listStudents();
		Date date = new java.util.Date();	
		model.addAttribute("students", listStudents);
		model.addAttribute("now", date);
		return "modifyStudent";			
	}
	
	@RequestMapping(value = "/modify/studentId/{studentId}", method = RequestMethod.GET) 
	public String modifyStudent(@PathVariable String studentId, ModelMap model) { 
		Student studentModify=studentDAO.getStudent(studentId);
		model.addAttribute("message", "Student with id "+ studentId +" can now be modified");
		model.addAttribute("student", studentModify);
		return "modifyStudentForm";	
	}
	
	@RequestMapping(value="/modify/studentId/{studentId}/email/{email}", method = RequestMethod.GET) 
	public String modifyStudent(@PathVariable String studentId, @PathVariable String firstname, 
			@PathVariable String lastname, @PathVariable String email, ModelMap model) {			
		studentDAO.updateStudent(studentId, firstname, lastname, email);
		Student studentModify=studentDAO.getStudent(studentId);
		model.addAttribute("message", "Student with student id "+ studentId +" has been modified");
		model.addAttribute("studentId", studentModify.getFirstName());
		model.addAttribute("lectId", studentModify.getLastName());
		model.addAttribute("programmeId", studentModify.getEmail());
		return "displayStudent";		
	}
	
}