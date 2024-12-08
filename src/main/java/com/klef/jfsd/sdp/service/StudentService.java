package com.klef.jfsd.sdp.service;

import java.util.List;

import com.klef.jfsd.sdp.model.AchievementType;
import com.klef.jfsd.sdp.model.CustomMilestone;
import com.klef.jfsd.sdp.model.Media;
import com.klef.jfsd.sdp.model.MileStone;
import com.klef.jfsd.sdp.model.MileStoneProgress;
import com.klef.jfsd.sdp.model.Portfolio;
import com.klef.jfsd.sdp.model.PortfolioDTO;
import com.klef.jfsd.sdp.model.PortfolioFeedback;
import com.klef.jfsd.sdp.model.Project;
import com.klef.jfsd.sdp.model.ProjectFeedback;
import com.klef.jfsd.sdp.model.SkillType;
import com.klef.jfsd.sdp.model.Student;

public interface StudentService 
{
	public Student checkStudentLogin(String email,String password);
	
	public String uploadProject(Project project);
	public String deleteProject(int projectid);
	public List<Project> viewProjectsByStudent(Student s);
	public Project viewProjectById(int pid);
	public String updateProject(Project project);
	
	public String uploadMedia(Media media);
	public Project viewProjectWithMedia(int pid);
	public Media viewMediaByID(int mid);
	public int getTotalStudentProjectsCount(Student s);
	public int getCompletedStudentProjectsCount(Student s);
	public int getPendingStudentProjectsCount(Student s);
	public List<Project> getRecentTop3ProjectsByStudent(Student s);
	
	public List<SkillType> displayAllSkillTypes();
	public List<AchievementType> displayAllAchievementTypes();
	public String savePortfolio(Student student,PortfolioDTO portfolioDTO);
	
	public String updatestudentprofile(Student student);
	public Student displayStudentbyID(int sid);
	public Portfolio findPortfolioByStudent(Student student);
	
	public String addCustomMileStone(CustomMilestone customMilestone);
	
	public List<MileStone> viewallmilestones(int pid);
	public List<MileStone> getPendingMilstonesByStudent(Student s);
	
	public String updateMilestone(MileStone mileStone);
	public MileStone viewMilestoneById(int mid);
	
	public String setMilestoneProgress(MileStoneProgress mileStoneProgress);
	
	public List<PortfolioFeedback> viewPortfolioFeedback(Student s);
	public List<ProjectFeedback> viewProjectFeedback(Student s);
}
