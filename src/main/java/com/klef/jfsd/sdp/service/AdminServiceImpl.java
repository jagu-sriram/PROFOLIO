package com.klef.jfsd.sdp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.klef.jfsd.sdp.model.Admin;
//import com.klef.jfsd.sdp.model.CsvUtility;
//import com.klef.jfsd.sdp.model.CsvUtilityForMentor;
import com.klef.jfsd.sdp.model.Media;
import com.klef.jfsd.sdp.model.Mentor;
import com.klef.jfsd.sdp.model.MentorStudentMapping;
import com.klef.jfsd.sdp.model.Project;
import com.klef.jfsd.sdp.model.ProjectFeedback;
import com.klef.jfsd.sdp.model.Student;
import com.klef.jfsd.sdp.repository.AdminRepository;
import com.klef.jfsd.sdp.repository.MediaRepository;
import com.klef.jfsd.sdp.repository.MentorRepository;
import com.klef.jfsd.sdp.repository.MentorStudentMappingRepository;
import com.klef.jfsd.sdp.repository.ProjectFeedbackRepository;
import com.klef.jfsd.sdp.repository.ProjectRepository;
import com.klef.jfsd.sdp.repository.StudentRepository;

@Service
public class AdminServiceImpl implements AdminService
{
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private MediaRepository mediaRepository;
	
	@Autowired
	private ProjectFeedbackRepository projectFeedbackRepository;
	
	@Autowired
	private MentorRepository mentorRepository;
	
	@Autowired
	private MentorStudentMappingRepository mentorStudentMappingRepository;
	
	@Override
	public Admin checkadminlogin(String uname, String pwd) 
	{
		
		return adminRepository.checkadminlogin(uname, pwd);
	}

	@Override
	public String addstudent(Student student) 
	{
		studentRepository.save(student);
		return "Student addedd successfully";
		
	}

	@Override
	public List<Student> viewallstudents() {
		
		return studentRepository.findAll();
	}

	@Override
	public String deletestudent(int sid) 
	{
		studentRepository.deleteById(sid);
		return "Student deleted successfully";
	}

	@Override
	public String updatestudent(Student student) 
	{
		Student s = studentRepository.findById(student.getId()).get();
		
		s.setContact(student.getContact());
		s.setDepartment(student.getDepartment());
		
		studentRepository.save(s);
		
		return "Student updated successfully";
		
	}

	@Override
	public Student findStudentbyID(int sid) {
		return studentRepository.findById(sid).get();
	}

	@Override
	public List<Project> viewallprojects() 
	{

		return projectRepository.findAll();
	}

	@Override
	public Project viewProjectById(int pid) 
	{
		return projectRepository.viewProjectById(pid);
	}
	
	
	@Override
	public Project viewProjectWithMedia(int pid) 
	{
		return projectRepository.viewProjectWithMedia(pid);
	}


	@Override
	public Media viewMediaByID(int mid) 
	{
		return mediaRepository.findById(mid).get();
	}

	@Override
	public String addProjectFeedback(ProjectFeedback feedback) 
	{
		
		projectFeedbackRepository.save(feedback);
		return "Feedback submitted successfully";
	}

	@Override
	public String addMentor(Mentor mentor) 
	{
		mentorRepository.save(mentor);
		return "Mentor added successfully";
	}

	@Override
	public List<Mentor> viewAllMentors() {
		
		return mentorRepository.findAll();
	}

	@Override
	public String deleteMentor(int mid) 
	{
		mentorRepository.deleteById(mid);
		return "Mentor Deleted Successfully";
	}

	@Override
	public String updateMentor(Mentor mentor) 
	{
		Mentor m = mentorRepository.findById(mentor.getId()).get();
		
		m.setContact(mentor.getContact());
		m.setDepartment(mentor.getDepartment());
		
		mentorRepository.save(m);
		
		return "Mentor updated successfully";
	}

	@Override
	public Mentor findMentorByID(int mid) 
	{
		
		return mentorRepository.findById(mid).get();
	}

	@Override
	public String mentorStudentMapping(MentorStudentMapping msm) 
	{
		mentorStudentMappingRepository.save(msm);
		return "Mentor Student Mapping done";
	}

	@Override
	public List<MentorStudentMapping> displayMentorStudentMapping() 
	{
		
		return mentorStudentMappingRepository.findAll();
	}

	@Override
	public long checkMentorStudentMapping(Mentor m, Student s) 
	{
		
		return mentorStudentMappingRepository.checkmstudentmapping(m, s);
	}

	@Override
	public long checkstudent(Student s) {
		
		return mentorStudentMappingRepository.checkstudent(s);
	}

//	public String saveStudentcsv(MultipartFile file) {
//	    try {
//	      List < Student > stuList = CsvUtility.csvToStuList(file.getInputStream());
//	      studentRepository.saveAll(stuList);
//	      return "Student(s) added successfully";
//	    } catch (IOException ex) {
//	      throw new RuntimeException("Data is not store successfully: " + ex.getMessage());
//	    }
//	  }

//	@Override
//	public String saveMentorcsv(MultipartFile file) 
//	{
//		 try {
//		      List < Mentor > mentorlist = CsvUtilityForMentor.csvToMentorList(file.getInputStream());
//		      mentorRepository.saveAll(mentorlist);
//		      return "Mentor(s) added successfully";
//		    } catch (IOException ex) {
//		      throw new RuntimeException("Data is not store successfully: " + ex.getMessage());
//		    }
//	}
	
	
	
}
