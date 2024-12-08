package com.klef.jfsd.sdp.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.klef.jfsd.sdp.model.Admin;
import com.klef.jfsd.sdp.model.Media;
import com.klef.jfsd.sdp.model.Mentor;
import com.klef.jfsd.sdp.model.MentorStudentMapping;
import com.klef.jfsd.sdp.model.Project;
import com.klef.jfsd.sdp.model.ProjectFeedback;
import com.klef.jfsd.sdp.model.Student;

public interface AdminService 
{
	public Admin checkadminlogin(String uname,String pwd);
	
	public String addstudent(Student student);
	public List<Student> viewallstudents();
	public String deletestudent(int sid);
	public String updatestudent(Student student);
	public Student findStudentbyID(int sid);
	
	public List<Project> viewallprojects();
	public Project viewProjectById(int pid);
	public Project viewProjectWithMedia(int pid);
	public Media viewMediaByID(int mid);
	
	public String addProjectFeedback(ProjectFeedback feedback);
	
	public String addMentor(Mentor mentor);
	public List<Mentor> viewAllMentors();
	public String deleteMentor(int mid);
	public String updateMentor(Mentor mentor);
	public Mentor findMentorByID(int mid);
	
	public String mentorStudentMapping(MentorStudentMapping msm);
	public List<MentorStudentMapping> displayMentorStudentMapping();
	public long checkMentorStudentMapping(Mentor m,Student s);
	public long checkstudent(Student s);
	
//	public String saveStudentcsv(MultipartFile file);
//	public String saveMentorcsv(MultipartFile file);
	
}
