package com.Group3.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.Group3.domain.Module;
import com.Group3.exceptions.ImageUploadException;
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