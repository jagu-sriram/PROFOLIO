package com.klef.jfsd.sdp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.klef.jfsd.sdp.model.Achievement;
import com.klef.jfsd.sdp.model.AchievementType;
import com.klef.jfsd.sdp.model.CustomMilestone;
import com.klef.jfsd.sdp.model.Media;
import com.klef.jfsd.sdp.model.MileStone;
import com.klef.jfsd.sdp.model.MileStoneProgress;
import com.klef.jfsd.sdp.model.Portfolio;
import com.klef.jfsd.sdp.model.PortfolioDTO;
import com.klef.jfsd.sdp.model.PortfolioFeedback;
import com.klef.jfsd.sdp.model.PortfolioProject;
import com.klef.jfsd.sdp.model.PredefinedMilestones;
import com.klef.jfsd.sdp.model.Project;
import com.klef.jfsd.sdp.model.ProjectFeedback;
import com.klef.jfsd.sdp.model.Skill;
import com.klef.jfsd.sdp.model.SkillType;
import com.klef.jfsd.sdp.model.Student;
import com.klef.jfsd.sdp.repository.AchievementRepository;
import com.klef.jfsd.sdp.repository.AchievementTypeRepository;
import com.klef.jfsd.sdp.repository.CustomMileStoneRepository;
import com.klef.jfsd.sdp.repository.MediaRepository;
import com.klef.jfsd.sdp.repository.MileStoneProgressRepository;
import com.klef.jfsd.sdp.repository.MileStoneRepository;
import com.klef.jfsd.sdp.repository.PortfolioFeedbackRepository;
import com.klef.jfsd.sdp.repository.PortfolioProjectRepository;
import com.klef.jfsd.sdp.repository.PortfolioRepository;
import com.klef.jfsd.sdp.repository.ProjectFeedbackRepository;
import com.klef.jfsd.sdp.repository.ProjectRepository;
import com.klef.jfsd.sdp.repository.SkillRepository;
import com.klef.jfsd.sdp.repository.SkillTypeRepository;
import com.klef.jfsd.sdp.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private MediaRepository mediaRepository;
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private AchievementRepository achievementRepository;
	
	@Autowired
	private SkillTypeRepository skillTypeRepository;
	
	@Autowired
	private AchievementTypeRepository achievementTypeRepository;
	
	@Autowired
	private PortfolioRepository portfolioRepository;
	
	@Autowired
	private PortfolioProjectRepository portfolioProjectRepository;
	
	@Autowired
	private MileStoneRepository mileStoneRepository;
	
	@Autowired
	private CustomMileStoneRepository customMileStoneRepository;
	
	@Autowired
	private MileStoneProgressRepository mileStoneProgressRepository;
	
	@Autowired
	private PortfolioFeedbackRepository portfolioFeedbackRepository;
	
	@Autowired
	private ProjectFeedbackRepository projectFeedbackRepository;

	@Override
	public Student checkStudentLogin(String email, String password) 
	{
		return studentRepository.checkstudentlogin(email, password);
	}


	@Override
	public String uploadProject(Project project) 
	{
		projectRepository.save(project);
		List<MileStone> staticMilestones = PredefinedMilestones.getStaticMilestones(project);
        mileStoneRepository.saveAll(staticMilestones);
		return "Project Uploaded Successfully";
	}


	@Override
	public String deleteProject(int projectid) {
		projectRepository.deleteProject(projectid);
		return "Project Deleted Successfully";
	}


	@Override
	public List<Project> viewProjectsByStudent(Student s) {
		return projectRepository.viewProjectsByStudent(s);
	}


	@Override
	public Project viewProjectById(int pid) {
		return projectRepository.viewProjectById(pid);
	}


	@Override
	public String updateProject(Project project) {
		projectRepository.save(project);
		return "Project Updated Successfully";
	}


	@Override
	public String uploadMedia(Media media) {
		mediaRepository.save(media);
		return "Media Uploaded Successfully";
	}


	@Override
	public Project viewProjectWithMedia(int pid) {
		return projectRepository.viewProjectWithMedia(pid);
	}


	@Override
	public Media viewMediaByID(int mid) {
		return mediaRepository.findById(mid).get();
	}


//	@Override
//	public String addSkill(Skill skill) {
//		skillRepository.save(skill);
//		return "Skill Added Successfully";
//	}
//
//
//	@Override
//	public String addAchievement(Achievement achievement) {
//		achievementRepository.save(achievement);
//		return "Record Added Successfully";
//	}
//
//
	@Override
	public List<SkillType> displayAllSkillTypes() {
		return skillTypeRepository.findAll();
	}
//
//
//	@Override
//	public SkillType getSkillTypeByName() {
//		// TODO Auto-generated method stub
//		return null;
//	}


	@Override
	public List<AchievementType> displayAllAchievementTypes() {
		return achievementTypeRepository.findAll();
	}

	@Override
	public String savePortfolio(Student student, PortfolioDTO portfolioDTO) {
	    // Check if Portfolio exists for the student
	    Portfolio portfolio = portfolioRepository.findPortfolioByStudent(student);
	    
	    if (portfolio == null) {
	        // If the Portfolio doesn't exist, create a new one
	        portfolio = new Portfolio();
	        portfolio.setStudent(student);
	    }
	    
	    // Update Portfolio details
	    portfolio.setTagline(portfolioDTO.getTagline());
	    portfolio.setInfo(portfolioDTO.getInfo());
	    portfolio.setStatus(portfolioDTO.getStatus());
	    portfolio = portfolioRepository.save(portfolio);

	    // Save or Update Skills
	    if (portfolioDTO.getSkills() != null) {
	        // Fetch existing skills for the portfolio
	        List<Skill> existingSkills = skillRepository.findByPortfolio(portfolio);

	        // Prepare a set of incoming skill type IDs as integers
	        Set<Integer> incomingSkillTypeIds = portfolioDTO.getSkills().stream()
	                .filter(skillDTO -> skillDTO.getSkillTypeId() > 0)
	                .map(PortfolioDTO.SkillDTO::getSkillTypeId)
	                .collect(Collectors.toSet());

	        System.out.println("Incoming Skill Type IDs: " + incomingSkillTypeIds);

	        // Delete skills in the database but not in the incoming DTO
	        for (Skill existingSkill : existingSkills) {
	            if (!incomingSkillTypeIds.contains(existingSkill.getSkillType().getId())) {
	                skillRepository.delete(existingSkill);
	            }
	        }

	        // Add or update incoming skills
	        for (PortfolioDTO.SkillDTO skillDTO : portfolioDTO.getSkills()) {
	            if (skillDTO.getSkillTypeId() > 0) {
	                SkillType skillType = skillTypeRepository.getSkillTypeById((long) skillDTO.getSkillTypeId());

	                if (skillType != null) {
	                    // Check if the skill already exists
	                    Skill existingSkill = skillRepository.findByPortfolioAndSkillType(portfolio, skillType);
	                    if (existingSkill != null) {
	                        // Update the existing skill
	                        existingSkill.setPercentage(skillDTO.getProficiency());
	                        skillRepository.save(existingSkill);
	                    } else {
	                        // Create a new skill
	                        Skill newSkill = new Skill();
	                        newSkill.setSkillType(skillType);
	                        newSkill.setPercentage(skillDTO.getProficiency());
	                        newSkill.setPortfolio(portfolio);
	                        skillRepository.save(newSkill);
	                    }
	                } else {
	                    System.out.println("SkillType not found for ID: " + skillDTO.getSkillTypeId());
	                }
	            } else {
	                System.out.println("Invalid SkillType ID for skill: " + skillDTO);
	            }
	        }
	    }




	    // Save or Update Achievements
	    if (portfolioDTO.getAchievements() != null) {
	        // Fetch existing achievements for the portfolio
	        List<Achievement> existingAchievements = achievementRepository.findByPortfolio(portfolio);

	        // Prepare a set of incoming achievement titles
	        Set<String> incomingAchievementTitles = portfolioDTO.getAchievements().stream()
	                .map(PortfolioDTO.AchievementDTO::getTitle)
	                .collect(Collectors.toSet());

	        System.out.println("Incoming Achievement Titles: " + incomingAchievementTitles);

	        // Delete achievements from the database that are not in the incoming DTO
	        for (Achievement existingAchievement : existingAchievements) {
	            if (!incomingAchievementTitles.contains(existingAchievement.getTitle())) {
	                achievementRepository.delete(existingAchievement);
	            }
	        }

	        // Add or update incoming achievements
	        for (PortfolioDTO.AchievementDTO achievementDTO : portfolioDTO.getAchievements()) {
	            if (achievementDTO.getAchievementTypeId() != 0) {
	                AchievementType achievementType = achievementTypeRepository.getAchievementTypeById((long) achievementDTO.getAchievementTypeId());

	                if (achievementType != null) {
	                    // Check if the achievement already exists
	                    Achievement existingAchievement = achievementRepository.findByPortfolioAndTitle(portfolio, achievementDTO.getTitle());
	                    if (existingAchievement != null) {
	                        // Update the existing achievement
	                        existingAchievement.setDescription(achievementDTO.getDescription());
	                        existingAchievement.setAchievementType(achievementType);
	                        achievementRepository.save(existingAchievement);
	                    } else {
	                        // Create a new achievement
	                        Achievement achievement = new Achievement();
	                        achievement.setTitle(achievementDTO.getTitle());
	                        achievement.setDescription(achievementDTO.getDescription());
	                        achievement.setAchievementType(achievementType);
	                        achievement.setPortfolio(portfolio);
	                        achievementRepository.save(achievement);
	                    }
	                } else {
	                    System.out.println("AchievementType not found for ID: " + achievementDTO.getAchievementTypeId());
	                }
	            } else {
	                System.out.println("Invalid AchievementType ID for achievement: " + achievementDTO);
	            }
	        }
	    }


	    // Save or Update Projects
	    if (portfolioDTO.getSelectedProjects() != null) {
	        
	        List<PortfolioProject> existingPortfolioProjects = portfolioProjectRepository.findByPortfolio(portfolio);

	        // Prepare a set of incoming project IDs
	        Set<Integer> incomingProjectIds = portfolioDTO.getSelectedProjects().stream()
	                .map(PortfolioDTO.ProjectDTO::getProjectId)
	                .collect(Collectors.toSet());

	        System.out.println("Incoming Project IDs: " + incomingProjectIds);

	        // Delete portfolio projects from the database that are not in the incoming DTO
	        for (PortfolioProject existingPortfolioProject : existingPortfolioProjects) {
	            if (!incomingProjectIds.contains(existingPortfolioProject.getProject().getId())) {
	                portfolioProjectRepository.delete(existingPortfolioProject);
	            }
	        }

	        // Add or update incoming projects
	        for (PortfolioDTO.ProjectDTO projectDTO : portfolioDTO.getSelectedProjects()) {
	           
	            Project project = projectRepository.findById(projectDTO.getProjectId()).orElse(null);

	            if (project != null) {
	                PortfolioProject existingPortfolioProject = portfolioProjectRepository.findByPortfolioAndProject(portfolio, project);

	                if (existingPortfolioProject != null) {
	                    // Update the existing PortfolioProject
	                    existingPortfolioProject.setOrderIndex(projectDTO.getOrder());
	                    portfolioProjectRepository.save(existingPortfolioProject);
	                } else {
	                    // Create new PortfolioProject
	                    PortfolioProject portfolioProject = new PortfolioProject();
	                    portfolioProject.setPortfolio(portfolio);
	                    portfolioProject.setProject(project);
	                    portfolioProject.setOrderIndex(projectDTO.getOrder());
	                    portfolioProjectRepository.save(portfolioProject);
	                }
	            } else {
	                System.out.println("Project not found for ID: " + projectDTO.getProjectId());
	            }
	        }
	    }


	    return "Portfolio Updated";
	}
	


	@Override
	public String updatestudentprofile(Student student) 
	{
		Student s = studentRepository.findById(student.getId()).get();
		
		
		s.setPassword(student.getPassword());
		if(student.getImage()!=null) {
			s.setImage(student.getImage());
		}
        studentRepository.save(s);
		
		return "Student updated successfully";
		
	}
	
	@Override
	public Student displayStudentbyID(int sid) 
	{
		return studentRepository.findById(sid).get();
	}


	@Override
	public Portfolio findPortfolioByStudent(Student student) {
		return portfolioRepository.findPortfolioByStudent(student);
	}


	@Override
	public String addCustomMileStone(CustomMilestone customMilestone) {
		customMileStoneRepository.save(customMilestone);
		return "MileStone Sent For Review";
	}


	@Override
	public List<MileStone> viewallmilestones(int pid) {
		return mileStoneRepository.findall(pid);
	}


	@Override
	public String updateMilestone(MileStone mileStone) {
		mileStoneRepository.save(mileStone);
		return "Milestone updated successfully";
	}


	@Override
	public MileStone viewMilestoneById(int mid) {
		return mileStoneRepository.findById(mid).get();
	}
	
	@Override
	public String setMilestoneProgress(MileStoneProgress mileStoneProgress) {
		MileStoneProgress mp=mileStoneProgressRepository.findByMilestone(mileStoneProgress.getMilestone());
		if(mp!=null) {
			mp.setStudentpercentage(mileStoneProgress.getStudentpercentage());
		}
		else {
			mp=new MileStoneProgress();
			mp.setStudentpercentage(mileStoneProgress.getStudentpercentage());
			mp.setMentorpercentage(0);
			mp.setMilestone(mileStoneProgress.getMilestone());
		}
		mileStoneProgressRepository.save(mp);
		return "MileStone Progress by Student is set";
	}


	@Override
	public int getTotalStudentProjectsCount(Student s) {
		return projectRepository.getTotalProjectsCountByStudent(s);
	}


	@Override
	public int getCompletedStudentProjectsCount(Student s) {
		return projectRepository.getCompletedProjectsCountByStudent(s);
	}


	@Override
	public int getPendingStudentProjectsCount(Student s) {
		return projectRepository.getPendingProjectsCountByStudent(s);
	}


	@Override
	public List<Project> getRecentTop3ProjectsByStudent(Student s) {
		Pageable p=PageRequest.of(0, 3);
		return projectRepository.getRecentTop3ProjectsByStudent(s, p);
	}


	@Override
	public List<MileStone> getPendingMilstonesByStudent(Student s) {
		return mileStoneRepository.getPendingMileStonesByStudent(s);
	}
	
	@Override
	public List<PortfolioFeedback> viewPortfolioFeedback(Student s) 
	{
		
		return portfolioFeedbackRepository.viewPortfolioFeedbackbyStudent(s);
	}


	@Override
	public List<ProjectFeedback> viewProjectFeedback(Student s) {
		
		return projectFeedbackRepository.viewprojectfeedback(s);
	}

}
