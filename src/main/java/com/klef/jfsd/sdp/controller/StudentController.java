package com.klef.jfsd.sdp.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.BorderRadius;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.klef.jfsd.sdp.model.CustomMilestone;
import com.klef.jfsd.sdp.model.CustomMilestone.Status;
import com.klef.jfsd.sdp.model.Media;
import com.klef.jfsd.sdp.model.MileStone;
import com.klef.jfsd.sdp.model.MileStoneProgress;
import com.klef.jfsd.sdp.model.Portfolio;
import com.klef.jfsd.sdp.model.PortfolioDTO;
import com.klef.jfsd.sdp.model.Project;
import com.klef.jfsd.sdp.model.Project.ProjectStatus;
import com.klef.jfsd.sdp.model.ProjectFeedback;
import com.klef.jfsd.sdp.model.Student;
import com.klef.jfsd.sdp.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController 
{
	@Autowired
	private StudentService studentService;
	
//	<------------------------------------Home and Authentication-------------------------------------------------------------------------->
	
	  @GetMapping("studenthome")
	  public ModelAndView studenthome(HttpServletRequest request)
	  {
		HttpSession session = request.getSession(); 
		Student student = (Student) session.getAttribute("student");
		
		int totalprojectcount=studentService.getTotalStudentProjectsCount(student);
		int completedprojectcount=studentService.getCompletedStudentProjectsCount(student);
		int pendingprojectcount=studentService.getPendingStudentProjectsCount(student);
		
		List<Project> recentprojects=studentService.getRecentTop3ProjectsByStudent(student);
		List<MileStone> pendingmilestones=studentService.getPendingMilstonesByStudent(student);
		
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("studenthome");
	    mv.addObject("tpc", totalprojectcount);
	    mv.addObject("cpc", completedprojectcount);
	    mv.addObject("ppc", pendingprojectcount);
	    mv.addObject("recentprojects", recentprojects);
	    mv.addObject("pendingmilestones", pendingmilestones);
	    for(MileStone m:pendingmilestones) {
	    	System.out.println("Title -"+m.getTitle());
	    }
	    return mv;
	  } 
	  
	  @GetMapping("studentlogout")
		public ModelAndView emplogout(HttpServletRequest request)
		{
			HttpSession session = request.getSession();
			
			session.removeAttribute("student");
			//session.invalidate(); - to remove all the session attributes
			
			ModelAndView mv=new ModelAndView();
			mv.setViewName("login");
			return mv;
		}
//	  <------------------------------Student Updation Section-------------------------------------------------------->
	  @GetMapping("studentprofile")
	  public ModelAndView studentprofile()
	  {
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("studentprofile");
	    return mv;
	  } 
	  
	  @GetMapping("updateprofile")
	  public ModelAndView updateprofile()
	  {
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("updateprofile");
	    return mv;
	  } 
	  
	  @PostMapping("updatestudentprofile")
	  public ModelAndView updatestudentprofile(HttpServletRequest request,@RequestParam("profileimage") MultipartFile file) throws Exception
	  {
		  
		  ModelAndView mv = new ModelAndView();
		  
		  try {
			  int id = Integer.parseInt(request.getParameter("sid"));
			  String firstname = request.getParameter("fname");
			  String lastname = request.getParameter("lname");
			  String gender = request.getParameter("sgender");
			  float age = Float.parseFloat(request.getParameter("sage"));
			  String dob = request.getParameter("sdob");
			  String dept = request.getParameter("sdept");
			  String email = request.getParameter("semail");
			  String password = request.getParameter("spwd");
			  String contact = request.getParameter("scontact");
			  
			  Student s = new Student();
			  s.setId(id);
			  s.setFirstname(firstname);
			  s.setLastname(lastname);
			  s.setGender(gender);
			  s.setAge(age);
			  s.setDateofBirth(dob);
			  s.setDepartment(dept);
			  s.setEmail(email);
			  s.setPassword(password);
			  s.setContact(contact);
			  
			  if(!file.isEmpty()) {
				  byte[] bytes = file.getBytes();
				  Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
				  s.setImage(blob);
			  }
			  
			  
			  String msg = studentService.updatestudentprofile(s);
			  
			  Student st = studentService.displayStudentbyID(id);
			  
			  HttpSession session = request.getSession();
			  session.setAttribute("student",st);
			  
			  mv.setViewName("updateprofile");
			  mv.addObject("message",msg);
			  
		  }
		  catch(Exception e) {
			  mv.setViewName("updateprofile");
			  mv.addObject("message",e);
		  }
		return mv;
	  }

	  
	  @GetMapping("displayprofileimage")
	  public ResponseEntity<byte[]> displayprofileimage(@RequestParam("id") int id) throws Exception
	{
		Student student = studentService.displayStudentbyID(id);
		byte[] imageBytes = null;
		imageBytes = student.getImage().getBytes(1, (int) student.getImage().length());
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}
	  
//	  <----------------------------------------------Project Section---------------------------------------------------------------------------->
	  
	  @GetMapping("uploadproject")
	  public ModelAndView uploadproject()
	  {
		  ModelAndView mv=new ModelAndView("uploadproject");
		  return mv;
	  }
	  
	  @PostMapping("addproject")
	  public ModelAndView addproject(HttpServletRequest request)
	  {
		  ModelAndView mv = new ModelAndView();
		  HttpSession session = request.getSession(); 
		  Student student = (Student) session.getAttribute("student"); 
		    if (student == null) {
		        mv.setViewName("login");
		        return mv; 
		    }
		  String title = request.getParameter("pname");
		  String description = request.getParameter("pdescription");
		  String link = request.getParameter("plink");
		    if (link != null && link.trim().isEmpty()) {
		        link = null;
		    }
		  Project project = new Project();
		  project.setTitle(title);
		  project.setDescription(description);
		  project.setStatus(ProjectStatus.IN_DEVELOPMENT);
		  project.setLink(link);
		  project.setProgress(0);
		  project.setStudent(student);
		  
		  mv.setViewName("uploadproject");
		  String msg = studentService.uploadProject(project);
	      mv.addObject("message", msg);
	      return mv;
	  }
	  
	  @GetMapping("deleteproject")
	  public String deleteproject(@RequestParam("id") int pid)
	  {
		  studentService.deleteProject(pid);
		  return "redirect:/viewprojects";
	  }
	  
	  @GetMapping("updateproject")
	  public ModelAndView updateproject(@RequestParam("id") int pid) {
		  ModelAndView mv=new ModelAndView("updateproject");
		  Project project=studentService.viewProjectById(pid);
		  mv.addObject("project", project);
		  return mv;
	  }
	  
	  
	  @PostMapping("saveupdatedproject")
	  public ModelAndView saveupdatedproject(HttpServletRequest request,@RequestParam("profileimage") MultipartFile file) throws IOException, SerialException, SQLException
	  {
		  ModelAndView mv = new ModelAndView();
		  HttpSession session = request.getSession(); 
		  Student student = (Student) session.getAttribute("student"); 
		    if (student == null) {
		        mv.setViewName("login");
		        return mv; 
		    }
		  int pid=Integer.parseInt(request.getParameter("pid"));
		  String title = request.getParameter("pname");
		  String description = request.getParameter("pdescription");
		  String link = request.getParameter("plink");
		    if (link != null && link.trim().isEmpty()) {
		        link = null;
		    }
		  boolean sendForReview = request.getParameter("sendForReview") != null;
		  
		    
		  Project project = studentService.viewProjectById(pid);
		  project.setId(pid);
		  project.setTitle(title);
		  project.setDescription(description);
		  project.setStatus(ProjectStatus.IN_DEVELOPMENT);
		  project.setLink(link);
		  project.setStudent(student);
		  project.setSubmitForReview(sendForReview);
		  
		  if(!file.isEmpty()) {
			  byte[] bytes = file.getBytes();
			  Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
			  project.setProjecticon(blob);
		  }
		  
		  mv.setViewName("updateproject");
		  String msg = studentService.updateProject(project);
	      mv.addObject("message", msg);
	      return mv;
	  }
	  
	  @GetMapping("displayprojectimage")
	  public ResponseEntity<byte[]> displayprojectimage(@RequestParam("id") int id) throws Exception
	{
		Project project=studentService.viewProjectById(id);
		byte[] imageBytes = null;
		imageBytes = project.getProjecticon().getBytes(1, (int) project.getProjecticon().length());
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}
	  
	  @GetMapping("viewprojectdetails")
	  public ModelAndView viewprojectdetails(@RequestParam("id") int pid,HttpServletRequest request)
	  {
		  HttpSession session = request.getSession();
		  ModelAndView mv=new ModelAndView("viewprojectdetails");
		  Student student = (Student) session.getAttribute("student");
		  
		  Project project=studentService.viewProjectWithMedia(pid);
		  List<ProjectFeedback> feedbacklist = studentService.viewProjectFeedback(student);
		  mv.addObject("feedbacklist", feedbacklist);
		  mv.addObject("project", project);
		  
		  return mv;
	  }
	  
	  @GetMapping("viewprojects")
	  public ModelAndView viewprojects(HttpServletRequest request) {
	      ModelAndView mv = new ModelAndView();
	      HttpSession session = request.getSession(); 
	      Student student = (Student) session.getAttribute("student"); 
	      mv.setViewName("viewprojects");
	      mv.addObject("projectslist", studentService.viewProjectsByStudent(student));
	      return mv;
	  }

//	 <---------------------------------------- Project Media Section------------------------------------------------------------------------->
	  
	  @GetMapping("uploadmedia")
	  public ModelAndView uploadprojectmedia(@RequestParam("id") int pid)
	  {
		  ModelAndView mv=new ModelAndView("uploadmedia");
		  Project project=studentService.viewProjectById(pid);
		  System.out.println("Project ID:"+project.getId());
		  mv.addObject("project", project);
		  return mv;
	  }
	  
	  @PostMapping("saveuploadedmedia")
	  public ModelAndView saveuploadedmedia(HttpServletRequest request,@RequestParam("mediaFile") MultipartFile file) throws Exception
	  {
		  String msg = null;
		  ModelAndView mv=new ModelAndView();
		  HttpSession session = request.getSession(); 
		  Student student = (Student) session.getAttribute("student"); 
		    if (student == null) {
		        mv.setViewName("login");
		        return mv; 
		    }
		  try {
			  System.out.println("Received project ID: " + request.getParameter("projectid"));
			  System.out.println("Received media type: " + request.getParameter("mediaType"));
			  System.out.println("Received caption: " + request.getParameter("caption"));

			  int pid=Integer.parseInt(request.getParameter("projectid"));
			  String mediatype=request.getParameter("mediaType");
			  String caption=request.getParameter("caption");
			  String filename=file.getOriginalFilename();
			  Project project=studentService.viewProjectById(pid);
			  byte[] bytes = file.getBytes();
			  Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
			  
			  Media media=new Media();
			  media.setMediatype(mediatype);
			  media.setCaption(caption);
			  media.setMedia(blob);
			  media.setProject(project);
			  media.setMediafilename(filename);
			  msg=studentService.uploadMedia(media);
			  mv.setViewName("uploadmedia");
			  mv.addObject("message", msg);
		  }
		  catch(Exception e) {
			  msg = e.getMessage();
			   System.out.println(msg.toString());
			   mv.setViewName("uploadmedia");
			   mv.addObject("message",msg);
		  }
		  return mv;
		  
	  }
	  
	  @GetMapping("displaymedia")
	  public ResponseEntity<byte[]> displaymedia(@RequestParam("id") int id) throws Exception {
	      Media media = studentService.viewMediaByID(id);
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

//	  <-------------------------------------PortFolio Section ------------------------------------------------------------------------>
	  
	  @GetMapping("updateportfolio")
	  public ModelAndView updateportfolio(HttpServletRequest request)
	  {
		  HttpSession session = request.getSession(); 
		  Student student = (Student) session.getAttribute("student"); 
		  ModelAndView mv=new ModelAndView("updateportfolio");
		  Portfolio portfolio=studentService.findPortfolioByStudent(student);
		  mv.addObject("skillTypes", studentService.displayAllSkillTypes());
		  mv.addObject("achievementTypes", studentService.displayAllAchievementTypes());
		  mv.addObject("projects", studentService.viewProjectsByStudent(student));
		  mv.addObject("portfolio", portfolio);
		  return mv;
	  }
	  
	  @PostMapping("submitPortfolio")
	  public String saveportfolio(HttpServletRequest request,@RequestParam("portfolioData") String portfolioData) throws JsonMappingException, JsonProcessingException
	  {
		  HttpSession session = request.getSession(); 
		  Student student = (Student) session.getAttribute("student"); 
		  ObjectMapper objectMapper = new ObjectMapper();
		  PortfolioDTO portfolioDTO = objectMapper.readValue(portfolioData, PortfolioDTO.class);
		  String msg=studentService.savePortfolio(student,portfolioDTO);
		  return "redirect:/viewportfolio";
	  }
	  
	  @GetMapping("viewportfolio")
	  public ModelAndView viewportfolio(HttpServletRequest request)
	  {
		  HttpSession session = request.getSession(); 
		  Student student = (Student) session.getAttribute("student");
		  ModelAndView mv=new ModelAndView("viewportfolio");
		  Portfolio portfolio=studentService.findPortfolioByStudent(student);
		  mv.addObject("portfolio", portfolio);
		  return mv;
	  }
	  
	  @GetMapping("viewportfoliofeedback")
	  public ModelAndView viewprojectfeedback(HttpServletRequest request) {
	      ModelAndView mv = new ModelAndView();
	      HttpSession session = request.getSession(); 
	      Student student = (Student) session.getAttribute("student"); 
	      mv.setViewName("viewportfoliofeedback");
	      mv.addObject("feedbacklist", studentService.viewPortfolioFeedback(student));
	      return mv;
	  }
	  
	  @GetMapping("portfolio-pdf")
	  public void generatePortfolioPdf(HttpServletRequest request, HttpServletResponse response) {
	      HttpSession session = request.getSession();
	      Student student = (Student) session.getAttribute("student");

	      try {
	          Portfolio portfolio = studentService.findPortfolioByStudent(student);
	          response.setContentType("application/pdf");
	          response.setHeader("Content-Disposition", "attachment; filename=" + student.getLastname() + "_portfolio.pdf");

	          OutputStream out = response.getOutputStream();
	          PdfWriter writer = new PdfWriter(out);
	          Document document = new Document(new com.itextpdf.kernel.pdf.PdfDocument(writer));

	          // Fonts
	          PdfFont titleFont = PdfFontFactory.createFont("Helvetica-Bold");
	          PdfFont sectionFont = PdfFontFactory.createFont("Helvetica");
	          PdfFont textFont = PdfFontFactory.createFont("Helvetica");
	          PdfFont italicFont = PdfFontFactory.createFont("Helvetica-Oblique");

	          // Colors
	          DeviceRgb darkBlue = new DeviceRgb(54, 79, 107);
	          DeviceRgb softGray = new DeviceRgb(100, 100, 100);
	          DeviceRgb lightGray = new DeviceRgb(200, 200, 200);

	          // Add Student Image (if exists)
	          if (student.getImage() != null) {
	              Blob imageBlob = student.getImage();
	              InputStream imageStream = imageBlob.getBinaryStream();
	              byte[] imageBytes = imageStream.readAllBytes(); // Convert Blob to byte array
	              ImageData imageData = ImageDataFactory.create(imageBytes); // Create ImageData
	              Image studentImage = new Image(imageData);
	              studentImage.setFixedPosition(450, 720) // Top-right corner
	                          .setWidth(100)
	                          .setHeight(100);
	              document.add(studentImage);
	          }

	          // Title
	          document.add(new Paragraph(student.getFirstname() + " " + student.getLastname())
	                  .setFont(titleFont)
	                  .setFontSize(28)
	                  .setBold()
	                  .setTextAlignment(TextAlignment.LEFT)
	                  .setFontColor(darkBlue)
	                  .setMarginBottom(20));

	          // Tagline
	          document.add(new Paragraph(portfolio.getTagline())
	                  .setFont(titleFont)
	                  .setFontSize(20)
	                  .setTextAlignment(TextAlignment.LEFT)
	                  .setFontColor(softGray)
	                  .setItalic()
	                  .setMarginBottom(15));

	          // URL
	          if (portfolio.getOnlineurl() != null) {
	              document.add(new Paragraph("Portfolio Link: " + portfolio.getOnlineurl())
	                      .setFont(textFont)
	                      .setFontSize(12)
	                      .setFontColor(softGray)
	                      .setTextAlignment(TextAlignment.LEFT)
	                      .setMarginBottom(20));
	          }

	          // Divider
	          addDivider(document, lightGray);

	          // Personal Info
	          document.add(createSectionHeader("Personal Info", sectionFont, darkBlue));
	          document.add(new Paragraph(portfolio.getInfo())
	                  .setFont(textFont)
	                  .setFontSize(12)
	                  .setFontColor(softGray)
	                  .setMarginBottom(20));

	          addDivider(document, lightGray);

	          // Skills
	          document.add(createSectionHeader("Skills", sectionFont, darkBlue));
	          if (portfolio.getSkills() != null && !portfolio.getSkills().isEmpty()) {
	              portfolio.getSkills().forEach(skill -> {
	                  document.add(new Paragraph(skill.getSkillType().getName() + " - " + skill.getPercentage() + "%")
	                          .setFont(textFont)
	                          .setFontSize(12)
	                          .setFontColor(softGray)
	                          .setMarginBottom(5));
	              });
	          } else {
	              document.add(new Paragraph("No skills listed.")
	                      .setFont(italicFont)
	                      .setFontSize(12)
	                      .setFontColor(softGray));
	          }

	          addDivider(document, lightGray);

	          // Achievements
	          document.add(createSectionHeader("Achievements", sectionFont, darkBlue));
	          addSubsectionHeader("Awards", document, sectionFont, softGray);
	          portfolio.getAchievements().stream()
	                  .filter(achievement -> "Award".equals(achievement.getAchievementType().getName()))
	                  .forEach(achievement -> {
	                      document.add(new Paragraph("• " + achievement.getTitle())
	                              .setFont(sectionFont)
	                              .setFontSize(14)
	                              .setFontColor(darkBlue));
	                      document.add(new Paragraph(achievement.getDescription())
	                              .setFont(textFont)
	                              .setFontSize(12)
	                              .setFontColor(softGray)
	                              .setMarginLeft(20));
	                  });

	          addSubsectionHeader("Certifications", document, sectionFont, softGray);
	          portfolio.getAchievements().stream()
	                  .filter(achievement -> "Certification".equals(achievement.getAchievementType().getName()))
	                  .forEach(achievement -> {
	                      document.add(new Paragraph("• " + achievement.getTitle())
	                              .setFont(sectionFont)
	                              .setFontSize(14)
	                              .setFontColor(darkBlue));
	                      document.add(new Paragraph(achievement.getDescription())
	                              .setFont(textFont)
	                              .setFontSize(12)
	                              .setFontColor(softGray)
	                              .setMarginLeft(20));
	                  });

	          addDivider(document, lightGray);

	          // Projects
	          document.add(createSectionHeader("Projects", sectionFont, darkBlue));
	          if (portfolio.getSelectedprojects() != null && !portfolio.getSelectedprojects().isEmpty()) {
	              portfolio.getSelectedprojects().forEach(project -> {
	                  document.add(new Paragraph("• " + project.getProject().getTitle())
	                          .setFont(sectionFont)
	                          .setFontSize(14)
	                          .setFontColor(darkBlue));
	                  document.add(new Paragraph(project.getProject().getDescription())
	                          .setFont(textFont)
	                          .setFontSize(12)
	                          .setFontColor(softGray)
	                          .setMarginLeft(20));
	              });
	          } else {
	              document.add(new Paragraph("No projects listed.")
	                      .setFont(italicFont)
	                      .setFontSize(12)
	                      .setFontColor(softGray));
	          }

	          // Close document
	          document.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }
	  private void addDivider(Document document, DeviceRgb color) {
	      LineSeparator line = new LineSeparator(new SolidLine());
	      line.setBackgroundColor(color);
	      document.add(line.setMarginTop(10).setMarginBottom(10));
	  }

	  private Paragraph createSectionHeader(String title, PdfFont font, DeviceRgb color) {
	      return new Paragraph(title)
	              .setFont(font)
	              .setFontSize(18)
	              .setBold()
	              .setFontColor(color)
	              .setMarginBottom(10)
	              .setMarginTop(20);
	  }

	  private void addSubsectionHeader(String title, Document document, PdfFont font, DeviceRgb color) {
	      document.add(new Paragraph(title)
	              .setFont(font)
	              .setFontSize(16)
	              .setBold()
	              .setFontColor(color)
	              .setMarginTop(10)
	              .setMarginBottom(5));
	  }


	  
//	<--------------------------------------- MileStones Section ------------------------------------------------------------------------------------->
	  
	  @GetMapping("proposemilestone")
	  public ModelAndView addcustommilestone(@RequestParam("id") int pid)
	  {
		  ModelAndView mv=new ModelAndView("proposemilestone");
		  Project project=studentService.viewProjectById(pid);
		  mv.addObject("project",project);
		  return mv;
	  }
	  
	  @PostMapping("addcustommilestone")
	  public ModelAndView addCustomMileStone(HttpServletRequest request)
	  {
		  String title=request.getParameter("title");
		  String description = request.getParameter("description");
		  Project project=studentService.viewProjectById(Integer.parseInt(request.getParameter("projectid")));
		  
		  CustomMilestone customMilestone=new CustomMilestone();
		  customMilestone.setTitle(title);
		  customMilestone.setDescription(description);
		  customMilestone.setStatus(Status.PENDING);
		  customMilestone.setProject(project);
		  
		  ModelAndView mv=new ModelAndView("proposemilestone");
		  String msg=studentService.addCustomMileStone(customMilestone);
		  mv.addObject("message", msg);
		  return mv;
	  }
	  
	  @GetMapping("viewmilestones")
	  public ModelAndView viewmilestones(HttpServletRequest request) {
	      ModelAndView mv = new ModelAndView();
	      HttpSession session = request.getSession(); 
	      Student student = (Student) session.getAttribute("student"); 
	      mv.setViewName("viewmilestonesByStudent");
	      mv.addObject("projectslist", studentService.viewProjectsByStudent(student));
	      return mv;
	  }
	  
	  @GetMapping("viewprojectwithmilestones")
	  public ModelAndView viewprojectwithmilestones(@RequestParam("id") int pid)
	  {
		  ModelAndView mv=new ModelAndView("viewprojectwithmilestones");
		  Project project=studentService.viewProjectWithMedia(pid);
		  List<MileStone> milestone = studentService.viewallmilestones(pid);
		  mv.addObject("project", project);
		  mv.addObject("milestone", milestone);
		  return mv;
	  }
	  
	  @GetMapping("viewmedia")
	  public ModelAndView viewmedia(@RequestParam("id") int pid)
	  {
		  ModelAndView mv = new ModelAndView("viewmedia");
		  Project project=studentService.viewProjectWithMedia(pid);
		  mv.addObject("project", project);
		  return mv;
	  }

	  @GetMapping("updatemilestone")
	  public ModelAndView updatemilestone(@RequestParam("id") int mid) {
		  ModelAndView mv=new ModelAndView("updatemilestone");
		  MileStone milestone = studentService.viewMilestoneById(mid);
		  mv.addObject("milestone", milestone);
		  return mv;
	  }
	  
	  @PostMapping("saveupdatedmilestone")
	    public ModelAndView saveupdatedmilestone(HttpServletRequest request)
	    {
	      ModelAndView mv = new ModelAndView();
	      HttpSession session = request.getSession(); 
	      Student student = (Student) session.getAttribute("student"); 
	        if (student == null) {
	            mv.setViewName("login");
	            return mv; 
	        }
	      int mid=Integer.parseInt(request.getParameter("mid"));
	      String title = request.getParameter("mtitle");
	      String description = request.getParameter("mdescription");
	      float progress = Float.parseFloat(request.getParameter("mprogress"));
	        
	      MileStone milestone = studentService.viewMilestoneById(mid);
	      milestone.setId(mid);
	      milestone.setTitle(title);
	      milestone.setDescription(description);
	      milestone.setStatus(MileStone.Status.IN_PROGRESS);
	      milestone.setReviewed(false);
	      
	      MileStoneProgress mp=new MileStoneProgress();
	      mp.setStudentpercentage(progress);
	      mp.setMilestone(milestone);
	      
	      studentService.setMilestoneProgress(mp);
	      
	      mv.setViewName("updatemilestone");
	      String msg = studentService.updateMilestone(milestone);
	        mv.addObject("message", msg);
	        return mv;
	    }
}