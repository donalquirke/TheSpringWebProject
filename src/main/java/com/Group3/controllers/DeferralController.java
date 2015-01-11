package com.Group3.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
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
import org.springframework.web.servlet.ModelAndView;

import com.Group3.Form.DeferralForm;
import com.Group3.domain.Deferral;
import com.Group3.domain.Lecturer;
import com.Group3.domain.Module;
import com.Group3.domain.Programme;
import com.Group3.domain.Student;
import com.Group3.exceptions.ImageUploadException;
import com.Group3.service.DeferralDAO;
import com.Group3.service.LecturerDAO;
import com.Group3.service.ModuleDAO;
import com.Group3.service.ProgrammeDAO;
import com.Group3.service.StudentDAO;

@Controller
@RequestMapping("/deferral")
public class DeferralController {

	Logger logger = Logger.getLogger(DeferralController.class);
	
	@Autowired
	DeferralDAO deferralDAO;
	
	@Autowired
	ProgrammeDAO programmeDAO;
	
	@Autowired
	ModuleDAO moduleDAO;
	
	@Autowired
	LecturerDAO lecturerDAO;
	
	@Autowired
	StudentDAO studentDAO;
	
	@Autowired
    private ServletContext servletContext;
	
	@RequestMapping(value="/listall", method = RequestMethod.GET) 
	public String listAll(ModelMap model) {			
			Date date = new java.util.Date();
			List<Deferral> listDeferrals  =deferralDAO.listDeferrals();
			
			List<Student> listStudents    =studentDAO.listStudentsWithdeferrals();
			List<Lecturer> listLecturers   =lecturerDAO.listLecturersWithdeferrals();
			List<Programme> listProgrammes=programmeDAO.listProgrammesWithdeferrals();
			List<Module> listModules      =moduleDAO.listModulesWithdeferrals();
			
			HashMap<Integer, Student> studentMap     = new HashMap<Integer, Student>();
			HashMap<Integer, Lecturer> lecturerMap   = new HashMap<Integer, Lecturer>();
			HashMap<Integer, Programme> programmeMap = new HashMap<Integer, Programme>();
			HashMap<Integer, Module> moduleMap       = new HashMap<Integer, Module>();
			
			for(Student student : listStudents){
				studentMap.put(student.getStudentAutoId(), student);
			}
			for(Lecturer lecturer : listLecturers){
				lecturerMap.put(lecturer.getLecturerAutoId(), lecturer);
			}
			for(Programme programme : listProgrammes){
				programmeMap.put(programme.getProgrammeAutoID(), programme);
			}
			for(Module module : listModules){
				moduleMap.put(module.getModuleAutoID(), module);
			}
			
			
			model.addAttribute("deferrals", listDeferrals);
			
			model.addAttribute("studentMap", studentMap);
			model.addAttribute("lecturerMap", lecturerMap);
			model.addAttribute("programmeMap", programmeMap);
			model.addAttribute("moduleMap", moduleMap);
			
			model.addAttribute("now", date);
		    return "displayDeferrals";			
	}  //WORKING  
		
	@RequestMapping(value="/programme/{programmeAutoID}", method = RequestMethod.GET) 
	public String listDeferralsByProgramme(@PathVariable("programmeAutoID") int programmeAutoID, ModelMap model){
		Date date = new java.util.Date();	
		List <Deferral> deferrals=deferralDAO.listDeferralsByProgramme(programmeAutoID);
		model.addAttribute("deferrals", deferrals);
		model.addAttribute("now", date);
	    return "displayDeferrals";	
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String SearchDeferrals(ModelMap model) {
		logger.debug("search form");
		model.addAttribute("deferral", new Deferral());
	    return "searchDeferral";
	}
	
	@RequestMapping(value="/search/studentAutoID/{studentAutoID}", method = RequestMethod.GET) 
	public String listDeferralsByStudent(@PathVariable("studentAutoID") int studentAutoID, ModelMap model){
		logger.debug("search by Id");
		Date date = new java.util.Date();	
		List <Deferral> deferrals=deferralDAO.listDeferralsByStudent(studentAutoID);
		model.addAttribute("deferrals", deferrals);
		model.addAttribute("now", date);
	    return "displayDeferrals";	
	}
	
	@RequestMapping(value="list/{approval}", method = RequestMethod.GET) 
	public String listUnapprovedDeferrals(@PathVariable("approval") String unapproved, ModelMap model){
		Date date = new java.util.Date();	
		List <Deferral> deferrals=deferralDAO.listUnapprovedDeferrals(unapproved);
		model.addAttribute("deferrals", deferrals);
		model.addAttribute("now", date);
	    return "displayDeferrals";	
	}
	
	@RequestMapping(value="list/{approval}/{programmeAutoID}", method = RequestMethod.GET) 
	public String listUnapprovedDeferralsByProgramme(@PathVariable("approval") String unapproved, @PathVariable("programmeAutoID") int programmeAutoID, ModelMap model){
		Date date = new java.util.Date();	
		List <Deferral> deferrals=deferralDAO.listUnapprovedDeferralsByProgramme(unapproved, programmeAutoID);
		model.addAttribute("deferrals", deferrals);
		model.addAttribute("now", date);
	    return "displayDeferrals";	
	}
	
	@RequestMapping(value="list/coord/{approval}/{firstname}", method = RequestMethod.GET) 
	public String listUnapprovedDeferralsByProgrammeCoordinatorName(@PathVariable("approval") String unapproved, @PathVariable("firstname") String firstname, ModelMap model){
		Date date = new java.util.Date();	
		List <Deferral> deferrals=deferralDAO.listUnapprovedDeferralsByProgrammeCoordinatorName(unapproved, firstname);
		model.addAttribute("deferrals", deferrals);
		model.addAttribute("now", date);
	    return "displayDeferrals";	
	}
	

	@RequestMapping(value = "/doDeferModule", method = RequestMethod.GET) 
	public ModelAndView doDeferModule() {
		logger.info("doDeferModule()");
		ModelAndView modelAndView = new ModelAndView();
		
		DeferralForm deferralForm = new DeferralForm();
//		deferralForm.setLectList(lecturerDAO.listLecturers());
//		deferralForm.setProgrammeList(programmeDAO.listProgrammes());
//		deferralForm.setModuleList(moduleDAO.listModules());
		
		modelAndView.addObject("deferral", new Deferral());  //sending a blank deferral		
		modelAndView.addObject("deferralForm", deferralForm);  //sending a blank deferral form
		modelAndView.setViewName("doDeferModule");
		
		return modelAndView;
	} //
	
	@RequestMapping(value = "/addNew", method = RequestMethod.GET) 
	public String addNewDeferral(ModelMap model) {
		logger.debug("addNew");
		model.addAttribute("deferral", new Deferral());
		return "newDeferral";
	} //WORKING

	@RequestMapping(value = "/addNew", method = RequestMethod.POST)
	public ModelAndView displayDeferral(@ModelAttribute("deferral") @Valid Deferral deferral,
			BindingResult result, ModelMap model) {
		logger.debug("addNew Submit");
		ModelAndView returnModel = new ModelAndView();
		MultipartFile file = deferral.getFile();
		
		if(result.hasErrors()){
			logger.debug("form errors found : redirect back to : newDeferral");
			returnModel.setViewName("newDeferral");
			return returnModel;
		}
		
		try {
			 if (!file.isEmpty()) {
				 logger.trace("file not empty");
				 validateImage(file);
				 
				  
		            try {
		            		
		            	//deferral.setStudentAutoID(studentDAO.getStudentById(studentId).getStudentAutoID());
			        	int defId = deferralDAO.createDeferralGetId(deferral.getStudentAutoID(), deferral.getLectId(),
		            			deferral.getProgrammeAutoID(), deferral.getModuleAutoID(), deferral.getApproval());
		            	logger.debug("new Deferral created with Id:" + defId);
		            	deferral.setDefId(defId); //update deferral with new ID
		            	returnModel.addObject("deferral", deferral);  //add the deferral to the new model
		            	returnModel.addObject("message", "The following deferral has been added to the system");

		
		            	byte[] bytes = file.getBytes(); 
		            	File dir = new File(servletContext.getRealPath("/")+"/resources/uploads");
		            	System.out.println(dir.getAbsolutePath());
        
		            	if (!dir.exists())
		            		dir.mkdirs();		                
       		 
		            	// Create the file on server
		            	String fileLocation=dir.getAbsolutePath()
		            			+ File.separator + Integer.toString(defId)+".jpg";;
        
		            			File serverFile = new File(fileLocation);
        
		            			BufferedOutputStream stream = new BufferedOutputStream(
		            					new FileOutputStream(serverFile));
        
		            			stream.write(bytes);
		            			stream.close();		  
		
		            } catch (Exception e) {
		            	logger.error("Creation of deferral failed, "+e.getMessage());
		            	returnModel.addObject("message", "Creation of deferral failed, "+e.getLocalizedMessage());
		            	returnModel.setViewName("displayError");
		        		return returnModel;
		            }
			 }else{
				 logger.error("You failed to upload " + (file!=null?file.getOriginalFilename():"no File found") + " because the file was empty.");
				 returnModel.addObject("message", "You failed to upload " + file.getOriginalFilename() + " because the file was empty.");
				 returnModel.setViewName("displayError");
				 return returnModel;
			 }
		} catch(ImageUploadException e){
			logger.error("Creation of deferral failed. The system only supports JPEGs. : " + e.getMessage());
			returnModel.addObject("message", "Creation of deferral failed. The system only supports JPEGs.");
			returnModel.setViewName("displayError");
			return returnModel;

		}
		returnModel.setViewName("displayDeferral");
		return returnModel;
	} //WORKING
	
	@RequestMapping(value = "/downloadImage/{defId}", method = RequestMethod.GET) 
	public @ResponseBody void downloadImageDeferral(@PathVariable int defId, HttpServletRequest request, HttpServletResponse response) {   
		try {
	 
		File fullPath = new File(servletContext.getRealPath("/")+"/resources/uploads");
        System.out.println(fullPath.getAbsolutePath());
        
        File downloadFile = new File(fullPath+"/"+defId+".jpg");
        FileInputStream inputStream = new FileInputStream(downloadFile);		     
       
        // get MIME type of the file
        String mimeType = "image/jpeg";         
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[1024];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }		   
 
        inputStream.close();
        outStream.close();
        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private void validateImage(MultipartFile image){
		if(!image.getContentType().equals("image/jpeg")){
			throw new ImageUploadException("OnlyJPGimagesaccepted");
		}
	}
	
	
	
	
	
	
	
	@RequestMapping(value = "/downloadForm", method = RequestMethod.GET) 
	public @ResponseBody void downloadApplicationForm(HttpServletRequest request, HttpServletResponse response) {   
		try {
	 
		File fullPath = new File(servletContext.getRealPath("/")+"/resources/images");
        System.out.println(fullPath.getAbsolutePath());
        
        File downloadFile = new File(fullPath+"/deferralApplication.jpg");
        FileInputStream inputStream = new FileInputStream(downloadFile);		     
       
        // get MIME type of the file
        String mimeType = "image/jpeg";         
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[1024];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }		   
 
        inputStream.close();
        outStream.close();
        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET) 
	public String deleteDeferral(ModelMap model) {   
		List<Deferral> listDeferrals=deferralDAO.listDeferrals();
		model.addAttribute("deferrals", listDeferrals);		
		return "delete";
	} //WORKING
	
	@RequestMapping(value = "/delete/defId/{defId}", method = RequestMethod.GET) 
	public String deleteDeferralById(@PathVariable int defId, ModelMap model) { 
		Deferral deferralDelete=deferralDAO.getDeferral(defId);
		deferralDAO.deleteDeferralById(defId);
		model.addAttribute("message", "Deferral with deferral id "+ defId +" and details "
				+ "below have been deleted from the system");
		model.addAttribute("studentAutoID", deferralDelete.getStudentAutoID());
		model.addAttribute("lectId", deferralDelete.getLectId());
		model.addAttribute("programmeAutoID", deferralDelete.getProgrammeAutoID());
		model.addAttribute("moduleAutoID", deferralDelete.getModuleAutoID());
		model.addAttribute("approval", deferralDelete.getApproval());
		return "displayDeferral";
	} //WORKING
	
	@RequestMapping(value="/modify", method = RequestMethod.GET) 
	public String modify(ModelMap model) {			
		List<Deferral> listDeferrals=deferralDAO.listDeferrals();
		Date date = new java.util.Date();	
		model.addAttribute("deferrals", listDeferrals);
		model.addAttribute("now", date);
		return "modify";			
	} //WORKING
	
	@RequestMapping(value = "/modify/defId/{defId}", method = RequestMethod.GET) 
	public String modifyDeferral(@PathVariable int defId, ModelMap model) { 
		logger.debug("Load Deferral Modify Display");
		Deferral deferralModify=deferralDAO.getDeferral(defId);
		model.addAttribute("message", "Deferral with id "+ defId +" can now be modified");
		model.addAttribute("deferral", deferralModify);
		return "modifyForm";	
	} //WORKING
	
	@RequestMapping(value="/modify/defId/{defId}/approval/{approval}", method = RequestMethod.GET) 
	public ModelAndView modifyDeferral(@PathVariable int defId, @PathVariable String approval,  ModelMap model) {			
		logger.debug("update request");
		ModelAndView modelAndView = new ModelAndView();
		deferralDAO.updateDeferral(defId, approval);
		Deferral deferralModify=deferralDAO.getDeferral(defId);
		model.addAttribute("message", "Deferral with deferral id "+ defId +" has been modified");
		modelAndView.addObject("deferral", deferralModify);
		modelAndView.setViewName("displayDeferral");
		return modelAndView;		
	} //WORKING

	@RequestMapping(value={"/getStudentProgrammeList"})
	@ResponseBody
	public DeferralForm getStudentProgrammeList(@RequestParam String studentId) { 
		logger.info("getStudentProgrammeList");
		DeferralForm deferralForm = new DeferralForm();

		List<Programme> programmeList = programmeDAO.listProgrammeByStudentID(studentId);
		deferralForm.setProgrammeList(programmeList);
		deferralForm.setStudentAutoID(studentDAO.getStudentByStudentID(studentId));
		return deferralForm;
	
	}
	
	@RequestMapping(value={"/getProgrammeModuleList"})
	@ResponseBody
	public DeferralForm getProgrammeModuleList(@RequestParam int programmeAutoID) { 
		logger.info("getProgrammeModuleList");
		DeferralForm deferralForm = new DeferralForm();
		
		List<Module> moduleList = moduleDAO.listModuleByProgrammeAutoID(programmeAutoID);
		deferralForm.setModuleList(moduleList);
		return deferralForm;
	
	}
	
	@RequestMapping(value={"/getModuleDetails"})
	@ResponseBody
	public DeferralForm getModuleDetails(@RequestParam Integer ModuleAutoID) { 
		logger.info("getModuleDetails");
		
		Module module = moduleDAO.getModule(ModuleAutoID);
		Lecturer lecturer = lecturerDAO.getLecturer(module.getLectId());
		List<Lecturer> lectList = new ArrayList<>();
		lectList.add(lecturer);
		DeferralForm deferralForm = new DeferralForm();
		deferralForm.setLectList(lectList);
		return deferralForm;
	
	}
}