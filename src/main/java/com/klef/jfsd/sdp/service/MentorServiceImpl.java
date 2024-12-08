package com.klef.jfsd.sdp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.klef.jfsd.sdp.model.CustomMilestone;
import com.klef.jfsd.sdp.model.CustomMilestone.Status;
import com.klef.jfsd.sdp.model.Media;
import com.klef.jfsd.sdp.model.Mentor;
import com.klef.jfsd.sdp.model.MileStone;
import com.klef.jfsd.sdp.model.MileStoneFeedback;
import com.klef.jfsd.sdp.model.MileStoneProgress;
import com.klef.jfsd.sdp.model.Portfolio;
import com.klef.jfsd.sdp.model.PortfolioFeedback;
import com.klef.jfsd.sdp.model.Project;
import com.klef.jfsd.sdp.model.Project.ProjectStatus;
import com.klef.jfsd.sdp.model.ProjectFeedback;
import com.klef.jfsd.sdp.model.Student;
import com.klef.jfsd.sdp.repository.CustomMileStoneRepository;
import com.klef.jfsd.sdp.repository.MediaRepository;
import com.klef.jfsd.sdp.repository.MentorRepository;
import com.klef.jfsd.sdp.repository.MentorStudentMappingRepository;
import com.klef.jfsd.sdp.repository.MileStoneFeedbackRepository;
import com.klef.jfsd.sdp.repository.MileStoneProgressRepository;
import com.klef.jfsd.sdp.repository.MileStoneRepository;
import com.klef.jfsd.sdp.repository.PortfolioFeedbackRepository;
import com.klef.jfsd.sdp.repository.PortfolioRepository;
import com.klef.jfsd.sdp.repository.ProjectFeedbackRepository;
import com.klef.jfsd.sdp.repository.ProjectRepository;

@Service
public class MentorServiceImpl implements MentorService
{
	@Autowired
	private MentorRepository mentorRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private MediaRepository mediaRepository;
	
	@Autowired
	private MentorStudentMappingRepository mentorStudentMappingRepository;
	
	@Autowired
	private ProjectFeedbackRepository projectFeedbackRepository;

	@Autowired
	private MileStoneRepository mileStoneRepository;
	
	@Autowired
	private MileStoneFeedbackRepository mileStoneFeedbackRepository;
	
	@Autowired
	private CustomMileStoneRepository customMileStoneRepository;
	
	@Autowired
	private MileStoneProgressRepository mileStoneProgressRepository;
	
	@Autowired
	private PortfolioFeedbackRepository portfolioFeedbackRepository;
	
	@Autowired
	private PortfolioRepository portfolioRepository;
	
	@Override
	public Mentor checkmentorlogin(String email, String pwd) 
	{
		return mentorRepository.checkMentorLogin(email, pwd);
		
	}

	@Override
	public List<Student> viewstudents(int mid) 
	{
		
		return mentorStudentMappingRepository.findStudentByMentor(mid);
	}

	@Override
	public List<Project> viewprojects(int mid) 
	{
		
		return projectRepository.findStudentProjectsByMentor(mid);
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
	public Project viewProjectById(int pid) 
	{
		return projectRepository.viewProjectById(pid);
	}

	@Override
	public String addProjectFeedback(ProjectFeedback feedback) 
	{
		projectFeedbackRepository.save(feedback);
		return "Feedback submitted successfully";
	}

	@Override
	public List<Map<String, Object>> viewProjectsithMilestoneStatus(Mentor m) {
		List<Object[]> queryResult = projectRepository.viewprojectswithmilestonestatus(m);
        List<Map<String, Object>> projects = new ArrayList<>();

        for (Object[] row : queryResult) {
            Map<String, Object> project = new HashMap<>();
            project.put("id", row[0]);
            project.put("title", row[1]);
            project.put("datecreated", row[2]);
            project.put("projectStatus", row[3]);
            project.put("studentId", row[4]);
            project.put("studentLastname", row[5]);
            project.put("pendingMilestones", row[6]);
            projects.add(project);
        }

        return projects;

	}

	@Override
	public MileStone viewMilestoneById(int mid) {
		return mileStoneRepository.findById(mid).get();
	}

	@Override
	public MileStone updatemilestone(MileStone milestone) {
		MileStone m= mileStoneRepository.save(milestone);
		updateProjectProgress(m.getProject());
		return m;
	}

	private void updateProjectProgress(Project project) {
		 List<MileStone> milestones = project.getMilestones();
		 if (milestones == null || milestones.isEmpty()) {
	        project.setProgress(0);
	        projectRepository.save(project);
	        return;
	    }
		
		float totalprogress=0;
		for (MileStone milestone : milestones) {
			totalprogress += milestone.getProgressPercentage();
	    }
		float avgprogress=totalprogress/milestones.size();
		if(avgprogress==100) {
			project.setStatus(ProjectStatus.COMPLETED);
		}
		else {
			project.setStatus(ProjectStatus.IN_DEVELOPMENT);
		}
		project.setProgress(avgprogress);
		projectRepository.save(project);
	}

	@Override
	public String addMilestoneFeedback(MileStoneFeedback mileStoneFeedback) {
		mileStoneFeedbackRepository.save(mileStoneFeedback);
		return "Feedback Added Successfully";
	}

	@Override
	public List<CustomMilestone> getPendingCustomMilestones(Mentor m) {
		return customMileStoneRepository.getPendingCustomMilestonesByMentor(m);
	}

	@Override
	public CustomMilestone getCustomMilestoneById(int mid) {
		return customMileStoneRepository.findById(mid).get();
	}

	@Override
	public List<MileStone> getallMilestonesByProject(int pid) {
		return mileStoneRepository.findall(pid);
	}

	@Override
	public String approveCustomMilestone(CustomMilestone customMilestone, int order) {
		customMilestone.setStatus(Status.ACCEPTED);
		customMileStoneRepository.save(customMilestone);
		
		List<MileStone> milestones=mileStoneRepository.findall(customMilestone.getProject().getId());
		rearrangeMileStonesOrderIndex(milestones, order);
		
		MileStone milestone=new MileStone();
		milestone.setTitle(customMilestone.getTitle());
		milestone.setDescription(customMilestone.getDescription());
		milestone.setOrderIndex(order+1);
		milestone.setProgressPercentage(0);
		milestone.setStatus(MileStone.Status.NOT_STARTED);
		milestone.setReviewed(true);
		milestone.setProject(customMilestone.getProject());
		mileStoneRepository.save(milestone);
		
		return "MileStone Approved";
		
	}
	
	private void rearrangeMileStonesOrderIndex(List<MileStone> milestones,int position) {
		for (MileStone milestone : milestones) {
	        if (milestone.getOrderIndex() > position) {
	            milestone.setOrderIndex(milestone.getOrderIndex() + 1);
	            mileStoneRepository.save(milestone);
	        }
	    }
	}

	@Override
	public String saveCustomMilestone(CustomMilestone customMilestone) {
		customMileStoneRepository.save(customMilestone);
		return "Custom MileStone Added";
	}

	@Override
	public String setMilestoneProgress(MileStoneProgress mileStoneProgress) {
		MileStoneProgress mp=mileStoneProgressRepository.findByMilestone(mileStoneProgress.getMilestone());
		if(mp!=null) {
			mp.setMentorpercentage(mileStoneProgress.getMentorpercentage());
			mp.setStudentpercentage(mileStoneProgress.getMentorpercentage());
		}
		else {
			mp=new MileStoneProgress();
			mp.setMentorpercentage(mileStoneProgress.getMentorpercentage());
			mp.setStudentpercentage(mileStoneProgress.getMentorpercentage());
			mp.setMilestone(mileStoneProgress.getMilestone());
		}
		mileStoneProgressRepository.save(mp);
		return "MileStone Progress by Faculty is set";
	}

	@Override
	public int getTotalProjectsCountUnderMentor(Mentor m) {
		return projectRepository.totalProjectsUnderMentor(m);
	}

	@Override
	public int getCompletedProjectsCountMentor(Mentor m) {
		return projectRepository.totalCompletedProjectsUnderMentor(m);
	}

	@Override
	public int getPendingProjectsCountUnderMentor(Mentor m) {
		return projectRepository.totalPendingProjectsUnderMentor(m);
	}

	@Override
	public List<Project> getRecentTop3ProjectsByMentor(Mentor m) {
		Pageable p=PageRequest.of(0, 3);
		return projectRepository.getRecentTop3ProjectsByMentor(m, p);
	}

	@Override
	public List<MileStone> getUnreviewedMileStonesByMentor(Mentor m) {
		return mileStoneRepository.getUnReviewedMileStonesByMentor(m);
	}

	@Override
	public String addPortfolioFeedback(PortfolioFeedback portfolioFeedback) 
	{
		portfolioFeedbackRepository.save(portfolioFeedback);
		return "Feedback submitted successfully";
	}

	@Override
	public List<Portfolio> viewportfolio(int mid) {
		
		return portfolioRepository.findStudentPortfolioByMentor(mid);
	}

	@Override
	public Portfolio viewPortfolioById(int pid) {
		
		return portfolioRepository.findById(pid).get();
	}

	@Override
	public Project saveProject(Project project) {
		return projectRepository.save(project);
	}
}
