package com.klef.jfsd.sdp.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.klef.jfsd.sdp.model.CustomMilestone;
import com.klef.jfsd.sdp.model.CustomMilestone.Status;
import com.klef.jfsd.sdp.model.Media;
import com.klef.jfsd.sdp.model.Mentor;
import com.klef.jfsd.sdp.model.MentorStudentMapping;
import com.klef.jfsd.sdp.model.MileStone;
import com.klef.jfsd.sdp.model.MileStoneFeedback;
import com.klef.jfsd.sdp.model.MileStoneProgress;
import com.klef.jfsd.sdp.model.Portfolio;
import com.klef.jfsd.sdp.model.PortfolioFeedback;
import com.klef.jfsd.sdp.model.Project;
import com.klef.jfsd.sdp.model.Project.ProjectStatus;
import com.klef.jfsd.sdp.model.ProjectFeedback;
import com.klef.jfsd.sdp.model.Student;
import com.klef.jfsd.sdp.service.MentorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MentorController 
{
	@Autowired
	private MentorService mentorService;
	
//	@PostMapping("checkmentorlogin")
//	@ResponseBody
//	public ModelAndView checkmentorlogin(HttpServletRequest request)
//	{
//		ModelAndView mv=new ModelAndView();
//		String email = request.getParameter("memail");
//		String password = request.getParameter("mpwd");
//		Mentor m = mentorService.checkmentorlogin(email, password);
//		if(m!=null) 
//		{
//			// session
//			HttpSession session = request.getSession();
//			session.setAttribute("mentor", m); // mentor is session variable
//			
//			//session.setMaxInactiveInterval(5);
//			
//			mv.setViewName("mentorhome");
//		}
//		else
//		{
//			mv.setViewName("login");
//			mv.addObject("message", "Login Failed");
//		}
//		return mv;
//		
//	}
	
	@GetMapping("mentorhome")
	public ModelAndView mentorhome(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Mentor mentor=(Mentor) session.getAttribute("mentor");
		ModelAndView mv = new ModelAndView();
		if(mentor==null) {
			mv.setViewName("redirect:/login");
			return mv;
		}
		int totalstudents=mentorService.viewstudents(mentor.getId()).size();
		int totalcompletedprojects=mentorService.getCompletedProjectsCountMentor(mentor);
		int totalPendingProjects=mentorService.getPendingProjectsCountUnderMentor(mentor);
		List<Project> recentprojects=mentorService.getRecentTop3ProjectsByMentor(mentor);
		List<MileStone> unreviewedmilestones=mentorService.getUnreviewedMileStonesByMentor(mentor);
		
		mv.setViewName("mentorhome");
		mv.addObject("ts", totalstudents);
		mv.addObject("tcp", totalcompletedprojects);
		mv.addObject("tpp", totalPendingProjects);
		mv.addObject("recentprojects", recentprojects);
		mv.addObject("unreviewedmilestones", unreviewedmilestones);
		return mv;
	}
	
	@GetMapping("viewstudents")
	public ModelAndView viewstudents(HttpSession session)
	{
		Mentor mentor =  (Mentor) session.getAttribute("mentor"); // Retrieve mentor ID from session
	    List<Student> studentlist = mentorService.viewstudents(mentor.getId());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("viewstudentsbymentor");
		mv.addObject("studentlist", studentlist);
		
		return mv;
	}
	
	@GetMapping("viewallprojects")
	public ModelAndView viewallprojects(HttpSession session)
	{
		ModelAndView mv = new ModelAndView();
		Mentor mentor =  (Mentor) session.getAttribute("mentor");
		if(mentor==null) {
			mv.setViewName("login");
			return mv;
		}
		List<Project> projectslist = mentorService.viewprojects(mentor.getId());
		mv.setViewName("viewprojectsbymentor");
		mv.addObject("projectslist", projectslist);
		
		return mv;
	}
	
	@GetMapping("viewprojectdetailsbymentor")
	  public ModelAndView viewprojectdetailsbymentor(@RequestParam("id") int pid)
	  {
		  ModelAndView mv=new ModelAndView("viewprojectdetailsbymentor");
		  Project project=mentorService.viewProjectWithMedia(pid);
		  mv.addObject("project", project);
		  return mv;
	  }
	
	@GetMapping("displayprojectmediabymentor")
	  public ResponseEntity<byte[]> displayprojectmediabymentor(@RequestParam("id") int id) throws Exception {
	      Media media = mentorService.viewMediaByID(id);
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
	  
	  @GetMapping("viewmediabymentor")
	  public ModelAndView viewmedia(@RequestParam("id") int pid)
	  {
		  ModelAndView mv = new ModelAndView("viewprojectmediabymentor");
		  Project project=mentorService.viewProjectWithMedia(pid);
		  mv.addObject("project", project);
		  return mv;
	  }
	  
	  @GetMapping("projectfeedbackform")
	  public ModelAndView projectfeedbackform(@RequestParam("id") int pid)
	  {
		  
		  ModelAndView mv=new ModelAndView("addprojectfeedback");
		  Project project=mentorService.viewProjectById(pid);
		  System.out.println("Project ID:"+project.getId());
		  mv.addObject("project", project);
		  return mv;
	  }
	  
	  @PostMapping("addprojectfeedback")
	  public ModelAndView addprojectfeedback(HttpServletRequest request)
	  {
		  ModelAndView mv = new ModelAndView("addprojectfeedback");
		  HttpSession session=request.getSession();
		  Mentor mentor =  (Mentor) session.getAttribute("mentor");
			if(mentor==null) {
				mv.setViewName("login");
				return mv;
			}
		  
		  int pid=Integer.parseInt(request.getParameter("projectid"));
		  String rating = request.getParameter("prating");
		  String comments=request.getParameter("pcomments");
		  Project project=mentorService.viewProjectById(pid);
		  
		  if(project.getProgress()==100) {
			  project.setStatus(ProjectStatus.COMPLETED);
		  }
		  
		  Project p=mentorService.saveProject(project);
		  
		  ProjectFeedback pf = new ProjectFeedback();
		  pf.setProject(p);
		  pf.setRating(rating);
		  pf.setComments(comments);
		  pf.setMentor(mentor);
		  
		  String msg = mentorService.addProjectFeedback(pf);
		        mv.addObject("message", msg);
		    
		      return mv;
		  
		  
		  
	  }
	  
	  @GetMapping("viewallportfolios")
		public ModelAndView viewallportfolios(HttpSession session)
		{
			ModelAndView mv = new ModelAndView();
			Mentor mentor =  (Mentor) session.getAttribute("mentor");
			if(mentor==null) {
				mv.setViewName("mentorlogin");
			}
			List<Portfolio> portfoliolist = mentorService.viewportfolio(mentor.getId());
			mv.setViewName("viewportfoliosbymentor");
			mv.addObject("portfoliolist", portfoliolist);
			
			return mv;
		}
	  
	  @GetMapping("viewportfolioByID")
	  public ModelAndView viewportfolio(@RequestParam("id") int pid,HttpSession session)
	  {
		  
		  ModelAndView mv=new ModelAndView();
		  Mentor mentor =  (Mentor) session.getAttribute("mentor");
		  MentorStudentMapping msm = (MentorStudentMapping) session.getAttribute("msm");
		  if(mentor==null) {
				mv.setViewName("mentorlogin");
			}
		  
		  Portfolio portfolio=mentorService.viewPortfolioById(pid);
		  mv.setViewName("viewportfolioByIDByMentor");
		  mv.addObject("portfolio", portfolio);
		  return mv;
	  }
	  
	  @GetMapping("portfoliofeedbackform")
	  public ModelAndView portfoliofeedbackform(@RequestParam("id") int pid)
	  {
		  
		  ModelAndView mv=new ModelAndView("addportfoliofeedback");
		  Portfolio portfolio=mentorService.viewPortfolioById(pid);
		  System.out.println("Portfolio ID:"+portfolio.getId());
		  mv.addObject("portfolio", portfolio);
		  return mv;
	  }
	  
	  @PostMapping("addportfoliofeedback")
	  public ModelAndView addportfoliofeedback(HttpServletRequest request)
	  {
		 
		  HttpSession session = request.getSession(); 
		  int pid=Integer.parseInt(request.getParameter("portfolioid"));
		  String rating = request.getParameter("prating");
		  String message=request.getParameter("pmessage");
		  Portfolio portfolio = mentorService.viewPortfolioById(pid);
		  
		  Mentor mentor = (Mentor) session.getAttribute("mentor"); 
		  PortfolioFeedback pf = new PortfolioFeedback();
		  
		  pf.setRating(rating);
		  pf.setMessage(message);
		  pf.setPortfolio(portfolio);
		  pf.setMentor(mentor);
		  
		  String msg = mentorService.addPortfolioFeedback(pf);
			 ModelAndView mv = new ModelAndView("addportfoliofeedback");
		        mv.addObject("message", msg);
		    
		      return mv;
		  
		  
		  
	  }
	  
	  @GetMapping("reviewmilestones")
	  public ModelAndView viewprojectsformilestonereview(HttpSession session)
	  {
		  ModelAndView mv=new ModelAndView("viewprojectsformilestonereview");
		  Mentor mentor =  (Mentor) session.getAttribute("mentor");
			if(mentor==null) {
				mv.setViewName("login");
			}
		  mv.addObject("projectslist", mentorService.viewProjectsithMilestoneStatus(mentor));
		  return mv;
		  
	  }
	  
	  @GetMapping("viewprojectwithmilestonesbymentor")
	  public ModelAndView viewprojectwithmilestones(@RequestParam("id") int pid)
	  {
		  ModelAndView mv=new ModelAndView("viewprojectwithmilestonesbymentor");
		  Project project=mentorService.viewProjectById(pid);
		  System.out.println("Project ID:"+project.getId());
		  mv.addObject("project", project);
		  return mv;
	  }
	  
	  @GetMapping("reviewmilestone")
	  public ModelAndView reviewMilestone(@RequestParam("id") int mid)
	  {
		  ModelAndView mv=new ModelAndView("reviewmilestoneform");
		  MileStone milestone=mentorService.viewMilestoneById(mid);
		  mv.addObject("milestone", milestone);
		  return mv;
	  }
	  
	  @PostMapping("savemilestonereview")
	  public ModelAndView savemilestonereview(HttpServletRequest request)
	  {
		  int mid=Integer.parseInt(request.getParameter("mid"));
		  String description = request.getParameter("mdescription");
		  float progress = Float.parseFloat(request.getParameter("mprogress"));
		  String feedback=request.getParameter("mfeedback");
		  
		  MileStone milestone=mentorService.viewMilestoneById(mid);
		  milestone.setDescription(description);
		  milestone.setProgressPercentage(progress);
		  milestone.setReviewed(true);
		  
		  MileStoneProgress mp=new MileStoneProgress();
		  mp.setMentorpercentage(progress);
		  mp.setMilestone(milestone);
		  
		  mentorService.setMilestoneProgress(mp);
		  
		  if(progress==100) {
			  milestone.setStatus(MileStone.Status.COMPLETED);
		  }
		  
		  MileStone savedmilestone=mentorService.updatemilestone(milestone);
		  
		  MileStoneFeedback mileStoneFeedback=new MileStoneFeedback();
		  mileStoneFeedback.setComment(feedback);
		  mileStoneFeedback.setMileStone(savedmilestone);
		  String msg=mentorService.addMilestoneFeedback(mileStoneFeedback);
		  ModelAndView mv=new ModelAndView("reviewmilestoneform");
		  mv.addObject("message",msg);
		  return mv;
	  }
	  
	  @GetMapping("viewallproposedmilestones")
	  public ModelAndView viewproposedmilestones(HttpSession session)
	  {
		  ModelAndView mv=new ModelAndView("viewallproposedmilestones");
		  Mentor mentor =  (Mentor) session.getAttribute("mentor");
			if(mentor==null) {
				mv.setViewName("login");
			}
			
		  List<CustomMilestone> pendingmilestones=mentorService.getPendingCustomMilestones(mentor);
		  mv.addObject("pendingmilestones", pendingmilestones);
		  return mv;
	  }
	  
	  @GetMapping("acceptmilestone")
	  public ModelAndView acceptmilestone(@RequestParam("id") int mid)
	  {
		  ModelAndView mv=new ModelAndView("acceptmilestone");
		  CustomMilestone milestone=mentorService.getCustomMilestoneById(mid);
		  List<MileStone> milestones=mentorService.getallMilestonesByProject(milestone.getProject().getId());
		  mv.addObject("milestone", milestone);
		  mv.addObject("milestoneList", milestones);
		  return mv;
	  }
	  
	  @GetMapping("rejectmilestone")
	  public ModelAndView rejectmilestone(@RequestParam("id") int mid)
	  {
		  ModelAndView mv=new ModelAndView("rejectmilestone");
		  CustomMilestone milestone=mentorService.getCustomMilestoneById(mid);
		  mv.addObject("milestone", milestone);
		  return mv;
	  }
	  
	  @PostMapping("approvemilestone")
	  public String addaccepetedmilestone(HttpServletRequest request)
	  {
		  int mid=Integer.parseInt(request.getParameter("milestoneid"));
		  int order=Integer.parseInt(request.getParameter("orderIndex"));
		  String feedback=request.getParameter("feedback");
		  
		  CustomMilestone customMilestone=mentorService.getCustomMilestoneById(mid);
		  mentorService.approveCustomMilestone(customMilestone, order);
		  
		  MileStoneFeedback mileStoneFeedback=new MileStoneFeedback();
		  mileStoneFeedback.setComment(feedback);
		  mileStoneFeedback.setCustomMilestone(customMilestone);
		  
		  mentorService.addMilestoneFeedback(mileStoneFeedback);
		  
		  return "redirect:/viewprojectwithmilestonesbymentor?id="+customMilestone.getProject().getId(); 
	  }
	  
	  @PostMapping("disapprovemilestone")
	  public String disapprovemilestone(HttpServletRequest request)
	  {
		  int mid=Integer.parseInt(request.getParameter("milestoneid"));
		  CustomMilestone customMilestone=mentorService.getCustomMilestoneById(mid);
		  customMilestone.setStatus(Status.REJECTED);
		  mentorService.saveCustomMilestone(customMilestone);
		  
		  String feedback=request.getParameter("feedback");
		  
		  MileStoneFeedback mileStoneFeedback=new MileStoneFeedback();
		  mileStoneFeedback.setComment(feedback);
		  mileStoneFeedback.setCustomMilestone(customMilestone);
		  
		  mentorService.addMilestoneFeedback(mileStoneFeedback);
		  
		  return "redirect:/viewprojectwithmilestonesbymentor?id="+customMilestone.getProject().getId(); 
	  }
	  
	  @GetMapping("mentorprofile")
	  public ModelAndView mentorprofile()
	  {
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("mentorprofile");
	    return mv;
	  } 
	  
	  @GetMapping("mentorlogout")
	  public ModelAndView logout(HttpServletRequest request)
	  {
		HttpSession session = request.getSession();
		session.removeAttribute("mentor");
		
	    ModelAndView mv=new ModelAndView();
	    mv.setViewName("login");
	    return mv;
	  }
	  
	  
	  @GetMapping("portfolio-pdf-dowload")
	  public void generatePortfolioPdf(@RequestParam("id") int pid,HttpServletRequest request, HttpServletResponse response) {
	      HttpSession session = request.getSession();
	      Mentor mentor =  (Mentor) session.getAttribute("mentor");
		  MentorStudentMapping msm = (MentorStudentMapping) session.getAttribute("msm");
		  
	      try {
	    	  Portfolio portfolio=mentorService.viewPortfolioById(pid);
	          response.setContentType("application/pdf");
	          response.setHeader("Content-Disposition", "attachment; filename=" + portfolio.getStudent().getLastname() + "_portfolio.pdf");

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
	          if (portfolio.getStudent().getImage() != null) {
	              Blob imageBlob = portfolio.getStudent().getImage();
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
	          document.add(new Paragraph(portfolio.getStudent().getFirstname() + " " + portfolio.getStudent().getLastname())
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
}
