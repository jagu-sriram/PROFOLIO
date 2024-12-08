package com.klef.jfsd.sdp.service;

import java.util.List;
import java.util.Map;

import com.klef.jfsd.sdp.model.CustomMilestone;
import com.klef.jfsd.sdp.model.Media;
import com.klef.jfsd.sdp.model.Mentor;
import com.klef.jfsd.sdp.model.MileStone;
import com.klef.jfsd.sdp.model.MileStoneFeedback;
import com.klef.jfsd.sdp.model.MileStoneProgress;
import com.klef.jfsd.sdp.model.Portfolio;
import com.klef.jfsd.sdp.model.PortfolioFeedback;
import com.klef.jfsd.sdp.model.Project;
import com.klef.jfsd.sdp.model.ProjectFeedback;
import com.klef.jfsd.sdp.model.Student;

public interface MentorService 
{
	public Mentor checkmentorlogin(String email,String pwd);
	
	public List<Student> viewstudents(int mid);
	public int getTotalProjectsCountUnderMentor(Mentor m);
	public int getCompletedProjectsCountMentor(Mentor m);
	public int getPendingProjectsCountUnderMentor(Mentor m);
	public List<Project> getRecentTop3ProjectsByMentor(Mentor m);
	
	public List<Project> viewprojects(int mid);
	public Project saveProject(Project project);
	
	public Project viewProjectWithMedia(int pid);
	public Media viewMediaByID(int mid);
	public Project viewProjectById(int pid);
	
	public String addProjectFeedback(ProjectFeedback feedback);
	
	public List<Map<String,Object>> viewProjectsithMilestoneStatus(Mentor m);
	public MileStone viewMilestoneById(int mid);

	public MileStone updatemilestone(MileStone milestone);

	public String addMilestoneFeedback(MileStoneFeedback mileStoneFeedback);
	
	public List<CustomMilestone> getPendingCustomMilestones(Mentor m);
	public CustomMilestone getCustomMilestoneById(int mid);
	public List<MileStone> getallMilestonesByProject(int pid);
	public List<MileStone> getUnreviewedMileStonesByMentor(Mentor m);
	
	public String saveCustomMilestone(CustomMilestone customMilestone);
	public String approveCustomMilestone(CustomMilestone customMilestone,int order);
	
	public String setMilestoneProgress(MileStoneProgress mileStoneProgress);
	
	public List<Portfolio> viewportfolio(int mid);
	public String addPortfolioFeedback(PortfolioFeedback portfolioFeedback);
	public Portfolio viewPortfolioById(int pid);
	
}
