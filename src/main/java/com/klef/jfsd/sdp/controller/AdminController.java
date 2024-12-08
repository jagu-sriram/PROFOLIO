package com.klef.jfsd.sdp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.sdp.model.Admin;
import com.klef.jfsd.sdp.model.Media;
import com.klef.jfsd.sdp.model.Mentor;
import com.klef.jfsd.sdp.model.MentorStudentMapping;
import com.klef.jfsd.sdp.model.Project;
import com.klef.jfsd.sdp.model.ProjectFeedback;
import com.klef.jfsd.sdp.model.Student;
import com.klef.jfsd.sdp.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController 
{
	@Autowired
	private AdminService adminService;
	
	@GetMapping("adminhome")
	  public ModelAndView adminhome(HttpServletRequest request)
	  {
		HttpSession session = request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		if(admin==null) {
			mv.setViewName("redirect:/login");
			return mv;
		}
	    
	    int studentcount = adminService.viewallstudents().size();
	    int mentorcount = adminService.viewAllMentors().size();
	    int projectscount=adminService.viewallprojects().size();
	    mv.setViewName("adminhome");
	    mv.addObject("studentcount", studentcount);
	    mv.addObject("mentorcount", mentorcount);
	    mv.addObject("projectscount", projectscount);
	    return mv;
	  }
	
	
//	@PostMapping("checkadminlogin")
//	@ResponseBody
//	public ModelAndView checkadminlogin(HttpServletRequest request)
//	{
//		String uname = request.getParameter("auname");
//		String apwd = request.getParameter("apwd");
//		
//		Admin admin =  adminService.checkadminlogin(uname, apwd);
//		ModelAndView mv = new ModelAndView();
//		
//		if(admin!=null)
//		{
//			 mv.setViewName("adminhome");
//			 return mv;
//		}
//		else
//		{
//			mv.setViewName("login");
//			return mv;
//		}
//		
//	}
	
	
	@GetMapping("addstudent")
	  public ModelAndView addstudent(HttpSession session)
	  {
		Admin admin=(Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		if(admin==null) {
			mv.setViewName("redirect:/login");
			return mv;
		}
		
	    mv.setViewName("addstudent");
	    return mv;          
	  }
	
	@PostMapping("insertstudent")
	  public ModelAndView insertstudent(HttpServletRequest request) 
	  {
		
		 
		 String fname = request.getParameter("fname");
		 String lname = request.getParameter("lname");
		 float age = Float.parseFloat(request.getParameter("sage"));
		 String dob = request.getParameter("sdob");
		 
//		 SimpleDateFormat input = new SimpleDateFormat("dd-MM-yyyy");
//		 
//		 Date dateValue = input.parse(dob);
//		 SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy");
//		 String dateofbirth = output.format(dateValue);
		 
		 String gender = request.getParameter("sgender");
		 String dept = request.getParameter("sdept");
		 String email = request.getParameter("semail");
		 String contact = request.getParameter("scontact");
		 String password =dob;
		 
		 
		 
		 Student s = new Student();
		 s.setFirstname(fname);
		 s.setLastname(lname);
		 s.setAge(age);
		 s.setDateofBirth(dob);
		 s.setGender(gender);
		 s.setDepartment(dept);
		 s.setEmail(email);
		 s.setContact(contact);
		 s.setPassword(password);
		 
		 
		 String msg = adminService.addstudent(s);
		 ModelAndView mv = new ModelAndView("addstudent");
	        mv.addObject("message", msg);
	    
	      return mv;
	  }
	
	@GetMapping("viewallstudents")
	public ModelAndView viewallstudents()
	{
		ModelAndView mv = new ModelAndView();
		List<Student> studentlist = adminService.viewallstudents();
		mv.setViewName("viewallstudents");
		mv.addObject("studentlist", studentlist);
		
		return mv;
	}
	
	
	
	@GetMapping("deletestudent")
	public ModelAndView deletestudent()
	{
		ModelAndView mv = new ModelAndView();
		List<Student> studentlist = adminService.viewallstudents();
		mv.setViewName("deletestudent");
		mv.addObject("studentlist", studentlist);
		
		return mv;
	}
	
	@GetMapping("delete")
	  public String deleteoperation(@RequestParam("id") int sid)
	  {
	    adminService.deletestudent(sid);
	    return "redirect:/deletestudent"; // redirect to specific page
	  }
	
	
	@GetMapping("updatestudent")
	public ModelAndView updatestudent()
	{
		ModelAndView mv = new ModelAndView();
		List<Student> studentlist = adminService.viewallstudents();
		mv.setViewName("updatestudent");
		mv.addObject("studentlist", studentlist);
		
		return mv;
	}
	
	@GetMapping("update")
	public ModelAndView updateOperation(@RequestParam("id") int sid) {
		ModelAndView mv=new ModelAndView("updatestudentform");
	    // Fetch the student details by ID and add it to the model
	    Student student = adminService.findStudentbyID(sid);
        mv.addObject("student", student);
	    return mv; // redirect to the form view for updating
	}
	
	
	@PostMapping("saveStudent")
	public String saveStudent(HttpServletRequest request) 
	{
		
	    
	    
	    
	    	int id=Integer.parseInt(request.getParameter("sid"));
	    	String fname = request.getParameter("fname");
			 String lname = request.getParameter("lname");
			 float age = Float.parseFloat(request.getParameter("sage"));
			 String dob = request.getParameter("sdob");
			 String gender = request.getParameter("sgender");
			 String dept = request.getParameter("sdept");
			 String email = request.getParameter("semail");
			 String contact = request.getParameter("scontact");
			 String password = dob;
			 
			 
			 Student s = adminService.findStudentbyID(id);
			 s.setFirstname(fname);
			 s.setLastname(lname);
			 s.setAge(age);
			 s.setDateofBirth(dob);
			 s.setGender(gender);
			 s.setDepartment(dept);
			 s.setEmail(email);
			 s.setContact(contact);
			 s.setPassword(password);
			 
			 String msg = adminService.updatestudent(s);
			 
//			 Student st = adminService.findStudentbyID(student.getId());
//			HttpServletRequest request = null;
//			 String msg = adminService.updatestudent(student);
//			 HttpSession session = request.getSession();
//			 session.setAttribute("student", st);
			 
//			 ModelAndView mv = new ModelAndView("updatestudentform");
//		        mv.addObject("message", msg);
//		    
//		      return mv;
			 
			 return "redirect:/updatestudent"; 
	    	
	    			
	    
	    
	}

	@GetMapping("adminsessionexpiry")
	  public ModelAndView adminsessionexpiry()
	  {
	    
	    ModelAndView mv=new ModelAndView();
	    mv.setViewName("adminsessionexpiry");
	    return mv;
	  }
	
	
	@GetMapping("viewallprojectsbyAdmin")
	public ModelAndView viewallprojects()
	{
		ModelAndView mv = new ModelAndView();
		List<Project> projectslist = adminService.viewallprojects();
		mv.setViewName("viewallprojectsbyAdmin");
		mv.addObject("projectslist", projectslist);
		
		return mv;
	}
	
	@GetMapping("viewprojectdetailsbyAdmin")
	  public ModelAndView viewprojectdetails(@RequestParam("id") int pid)
	  {
		  ModelAndView mv=new ModelAndView("viewprojectdetailsbyAdmin");
		  Project project=adminService.viewProjectWithMedia(pid);
		  mv.addObject("project", project);
		  return mv;
	  }
	
	@GetMapping("displayprojectmedia")
	  public ResponseEntity<byte[]> displaymedia(@RequestParam("id") int id) throws Exception {
	      Media media = adminService.viewMediaByID(id);
	      byte[] fileBytes = media.getMedia().getBytes(1, (int) media.getMedia().length());

	      // Set the appropriate content type and Content-Disposition header based on media type
	      if ("Image".equalsIgnoreCase(media.getMediatype())) {
	          return ResponseEntity.ok()
	                  .contentType(MediaType.IMAGE_JPEG)
	                  .body(fileBytes);
	      } else if ("Document".equalsIgnoreCase(media.getMediatype())) {
	          return ResponseEntity.ok()
	                  .contentType(MediaType.APPLICATION_PDF)
	                  .header("Content-Disposition", "inline; filename=\"" + media.getMediafilename() + "\"")
	                  .body(fileBytes);
	      } else {
	          return ResponseEntity.ok()
	                  .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                  .header("Content-Disposition", "attachment; filename=\"" + media.getMediafilename() + "\"")
	                  .body(fileBytes);
	      }
	  }
	
	  
	
	  
	  @GetMapping("addmentor")
	  public ModelAndView addmentor()
	  {
		ModelAndView mv=new ModelAndView();
	    mv.setViewName("addmentor");
	    return mv;          
	  }
	  
	  @PostMapping("insertmentor")
	  public ModelAndView insertmentor(HttpServletRequest request) 
	  {
		 
		 int id =Integer.parseInt(request.getParameter("mid")) ;
		 String fname = request.getParameter("fname");
		 String lname = request.getParameter("lname");
		 String dob = request.getParameter("mdob");
		 String gender = request.getParameter("mgender");
		 String dept = request.getParameter("mdept");
		 String qualification = request.getParameter("qualification");
		 String designation=request.getParameter("mdesg");
		 float expereince=Float.parseFloat(request.getParameter("mexp")) ;
		 String email = request.getParameter("memail");
		 String contact = request.getParameter("mcontact");
		 String password =dob;
		 
		 
		 
		 Mentor m = new Mentor();
		 m.setId(id);
		 m.setFirstname(fname);
		 m.setLastname(lname);
		 m.setDateofBirth(dob);
		 m.setGender(gender);
		 m.setDepartment(dept);
		 m.setQualification(qualification);
		 m.setDesignation(designation);
		 m.setExperience(expereince);
		 m.setEmail(email);
		 m.setContact(contact);
		 m.setPassword(password);
		 
		 
		 String msg = adminService.addMentor(m);
		 ModelAndView mv = new ModelAndView("addmentor");
	        mv.addObject("message", msg);
	    
	      return mv;
	  }
	  
	  @GetMapping("addstudentcsv")
	  public ModelAndView addstudentcsv()
	  {
	  	ModelAndView mv=new ModelAndView();
	    mv.setViewName("addstudentcsv");
	    return mv;          
	  }

//	  @PostMapping("insertstudentcsv")
//	  public ModelAndView uploadstudentcsvFile(HttpServletRequest request,@RequestParam("file") MultipartFile file) {
//	  	
//	      ModelAndView mv=new ModelAndView("addstudentcsv");
//	      HttpSession session = request.getSession(false); 
//	      
//	    
//	       String msg = adminService.saveStudentcsv(file);
//	       mv.addObject("message", msg);
//	   
//	     return mv;
//	    
//	  }

	  @GetMapping("addmentorcsv")
	  public ModelAndView addmentorcsv()
	  {
	  	ModelAndView mv=new ModelAndView();
	    mv.setViewName("addmentorcsv");
	    return mv;          
	  }

//	  @PostMapping("insertmentorcsv")
//	  public ModelAndView uploadmentorcsvFile(HttpServletRequest request,@RequestParam("csvfile") MultipartFile file) {
//	  	
//	      ModelAndView mv=new ModelAndView("addmentorcsv");
//	      HttpSession session = request.getSession(false); 
//	      
//	    
//	       String msg = adminService.saveMentorcsv(file);
//	       mv.addObject("message", msg);
//	   
//	     return mv;
//	    
//	  }
	  
	  @GetMapping("viewallmentors")
		public ModelAndView viewallmentors()
		{
			ModelAndView mv = new ModelAndView();
			List<Mentor> mentorlist = adminService.viewAllMentors();
			mv.setViewName("viewallmentors");
			mv.addObject("mentorlist", mentorlist);
			
			return mv;
		}
	  
	  @GetMapping("deletementor")
		public ModelAndView deletementor()
		{
			ModelAndView mv = new ModelAndView();
			List<Mentor> mentorlist = adminService.viewAllMentors();
			mv.setViewName("deletementor");
			mv.addObject("mentorlist", mentorlist);
			
			return mv;
		}
		
		@GetMapping("deleteM")
		  public String deleteM(@RequestParam("id") int mid)
		  {
		    adminService.deleteMentor(mid);
		    return "redirect:/deletementor"; // redirect to specific page
		  }
		
		
		
		@GetMapping("updatementor")
		public ModelAndView updatementor()
		{
			ModelAndView mv = new ModelAndView();
			List<Mentor> mentorlist = adminService.viewAllMentors();
			mv.setViewName("updatementor");
			mv.addObject("mentorlist", mentorlist);
			
			return mv;
		}
		
		@GetMapping("updateM")
		public ModelAndView updateM(@RequestParam("id") int mid) {
			ModelAndView mv=new ModelAndView("updatementorform");
		    // Fetch the student details by ID and add it to the model
		    Mentor mentor = adminService.findMentorByID(mid);
	        mv.addObject("mentor", mentor);
		    return mv; // redirect to the form view for updating
		}
		
		
		@PostMapping("saveMentor")
		public String saveMentor(HttpServletRequest request) 
		{
			
		    
		    
		    
			int id =Integer.parseInt(request.getParameter("mid")) ;
			 String fname = request.getParameter("fname");
			 String lname = request.getParameter("lname");
			 String dob = request.getParameter("mdob");
			 String gender = request.getParameter("mgender");
			 String dept = request.getParameter("mdept");
			 String qualification = request.getParameter("qualification");
			 String designation=request.getParameter("mdesg");
			 float expereince=Float.parseFloat(request.getParameter("mexp")) ;
			 String email = request.getParameter("memail");
			 String contact = request.getParameter("mcontact");
			 String password =dob;
			 
			 
			 
			 Mentor m = new Mentor();
			 m.setId(id);
			 m.setFirstname(fname);
			 m.setLastname(lname);
			 m.setDateofBirth(dob);
			 m.setGender(gender);
			 m.setDepartment(dept);
			 m.setQualification(qualification);
			 m.setDesignation(designation);
			 m.setExperience(expereince);
			 m.setEmail(email);
			 m.setContact(contact);
			 m.setPassword(password);
			 
				 
				 String msg = adminService.updateMentor(m);
				 
		    
				 
				 return "redirect:/updatementor"; 
		    	
		    			
		    
		    
		}
		
		
		@GetMapping("mstudentmapping")
		  public ModelAndView mentorstudentmapping()
		  {
			  ModelAndView mv = new ModelAndView("mentorstudentmapping");
		  	  
		      List<Student> studentlist = adminService.viewallstudents();
		      mv.addObject("studentdata", studentlist);
			  
			  List<Mentor> mentorlist = adminService.viewAllMentors();
			  mv.addObject("mentordata", mentorlist);
			  
			  return mv;
		  }
		
		@PostMapping("mstudentmappinginsert")
		  public ModelAndView mstudentmappinginsert(HttpServletRequest request)
		  {
			  String msg=null;
			  
			  ModelAndView mv = new ModelAndView("mentorstudentmapping");
			  
			  int mid = Integer.parseInt(request.getParameter("mid"));
			  int sid = Integer.parseInt(request.getParameter("sid"));
			  
			  
			  long n = adminService.checkMentorStudentMapping(adminService.findMentorByID(mid),adminService.findStudentbyID(sid));
			  long m = adminService.checkstudent(adminService.findStudentbyID(sid));
			  if(n>0 || m>0)
			  {
				  msg = "Mapping Already Done";
			  }
			  else
			  {
				  MentorStudentMapping mcm = new MentorStudentMapping();
				  
				  mcm.setStudent(adminService.findStudentbyID(sid));
				  mcm.setMentor(adminService.findMentorByID(mid));
				  
				  
				  msg = adminService.mentorStudentMapping(mcm);
				  
			  }
			  
			  mv.addObject("message", msg);
			  
			  
			  
			  return mv;
		  }

	  


		@GetMapping("viewmstudentmapping")
		public ModelAndView viewfcoursemapping()
		{
			  ModelAndView mv = new ModelAndView("viewmstudentmapping");
			  
			  List<MentorStudentMapping> mcmlist = adminService.displayMentorStudentMapping();
			  mv.addObject("mcmdata", mcmlist);
			  
			  return mv;
		}
		
		@GetMapping("adminlogout")
		  public ModelAndView logout(HttpServletRequest request)
		  {
			HttpSession session = request.getSession();
			session.removeAttribute("admin");
			
		    ModelAndView mv=new ModelAndView();
		    mv.setViewName("login");
		    return mv;
		  }
}