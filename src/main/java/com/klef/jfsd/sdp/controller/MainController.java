package com.klef.jfsd.sdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.sdp.model.Admin;
import com.klef.jfsd.sdp.model.Mentor;
import com.klef.jfsd.sdp.model.Student;
import com.klef.jfsd.sdp.service.AdminService;
import com.klef.jfsd.sdp.service.MentorService;
import com.klef.jfsd.sdp.service.StudentService;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController 
{
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private MentorService mentorService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private JavaMailSender mailSender;
	
  @GetMapping("/")
  public ModelAndView home()
  {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("index");
    return mv;
  }
  
  @GetMapping("login")
  public ModelAndView login()
  {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("login");
    return mv;
  }
  
  @PostMapping("checklogin")
  public ModelAndView checklogin(HttpServletRequest request)
  {
	  ModelAndView mv=new ModelAndView();
	  
	  String identifier=request.getParameter("identifier");
	  String password=request.getParameter("password");
	  
	  Admin admin=adminService.checkadminlogin(identifier, password);
	  if(admin!=null) 
	  {
		  HttpSession session = request.getSession();
	      session.setAttribute("admin", admin); 
	      
	      mv.setViewName("redirect:/adminhome");
		  return mv;
	  }
	  
	  Mentor mentor=mentorService.checkmentorlogin(identifier, password);
	  if(mentor!=null) 
		{
			
			HttpSession session = request.getSession();
			session.setAttribute("mentor", mentor); // mentor is session variable
			
			mv.setViewName("redirect:/mentorhome");
			return mv;
		}
	  
	  Student student=studentService.checkStudentLogin(identifier, password);
	  if(student!=null) 
		{
			// session
			HttpSession session = request.getSession();
			session.setAttribute("student", student); 
			
			mv.setViewName("redirect:/studenthome");
			return mv;
		}
	  mv.setViewName("login");
	  mv.addObject("message", "Login Failed");
	  return mv;
	  
  }
  
  @GetMapping("mentorlogin")
	public ModelAndView mentorlogin()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("mentorlogin");
		return mv;
	}
  
  @PostMapping("sendemail")
  public ModelAndView sendEmail(HttpServletRequest request) throws Exception
  {
  String name = request.getParameter("name");
  String fromEmail = request.getParameter("email");
  
  String msg = request.getParameter("message");
  MimeMessage mimeMessage = mailSender.createMimeMessage();
  MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

  int otp = (int)(Math.random() * 99999); // random number generation
  helper.setTo("shaiknusratraheel@gmail.com");
  helper.setFrom(fromEmail);
  String htmlContent =
  "<h3>Contact Form Details</h3>" +
  "<p><strong>Name:</strong> " + name + "</p>" +
  "<p><strong>Email:</strong> " + fromEmail + "</p>" +
  "<p><strong>Message:</strong> " + msg + "</p>" +
  "<p><strong>OTP:</strong> " + otp + "</p>";
  helper.setText(htmlContent, true);
  mailSender.send(mimeMessage);

  ModelAndView mv = new ModelAndView("index");
  mv.addObject("message", "Email Sent Successfully");
  return mv;
  }
}