package com.klef.jfsd.sdp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.sdp.model.Mentor;
import com.klef.jfsd.sdp.model.Project;
import com.klef.jfsd.sdp.model.Student;

import jakarta.transaction.Transactional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>
{
	@Query("delete from Project p where p.id=?1")
	@Modifying
	@Transactional
	public int deleteProject(int pid);

	@Query("select p from Project p where p.student=?1")
	public List<Project> viewProjectsByStudent(Student s);
	
	@Query("from Project p where p.id=?1")
	public Project viewProjectById(int pid);
	
	@Query("select p from Project p left join fetch p.media where p.id=?1")
	public Project viewProjectWithMedia(int pid);
	
	@Query("select p.id as id, p.title as title, p.datecreated as datecreated, p.status as status, " +
		       "s.id as studentid, s.lastname as lastname, " +
		       "COUNT(CASE WHEN m.isReviewed = false THEN 1 END) AS pendingMilestones " +
		       "from Project p " +
		       "left join milestones m ON p.id = m.project.id " +
		       "inner join Student s ON p.student.id = s.id " +
		       "inner join MentorStudentMapping msm ON msm.student.id = s.id " +
		       "where msm.mentor=?1 " +
		       "GROUP by p.id, s.id, s.lastname, p.title, p.datecreated, p.status")
	public List<Object[]> viewprojectswithmilestonestatus(Mentor m);
	
	@Query("select count(p) from Project p where p.student=?1")
	public int getTotalProjectsCountByStudent(Student s);
	
	@Query("select count(p) from Project p where p.student=?1 and p.status='COMPLETED'")
	public int getCompletedProjectsCountByStudent(Student s);
	
	@Query("select count(p) from Project p where p.student=?1 and p.status='IN_DEVELOPMENT'")
	public int getPendingProjectsCountByStudent(Student s);
	
	@Query("SELECT p FROM Project p where p.student=?1 ORDER BY p.datecreated DESC")
	public List<Project> getRecentTop3ProjectsByStudent(Student s,Pageable  pageable);
	
	@Query("select count(p) from Project p inner join MentorStudentMapping msm on msm.student=p.student where msm.mentor=?1")
	public int totalProjectsUnderMentor(Mentor m);
	
	@Query("select count(p) from Project p inner join MentorStudentMapping msm on msm.student=p.student where msm.mentor=?1 and p.status='COMPLETED'")
	public int totalCompletedProjectsUnderMentor(Mentor m);
	
	@Query("select count(p) from Project p inner join MentorStudentMapping msm on msm.student=p.student where msm.mentor=?1 and p.status='IN_DEVELOPMENT'")
	public int totalPendingProjectsUnderMentor(Mentor m);
	
	@Query("from Project p join MentorStudentMapping msm on msm.student=p.student where msm.mentor=?1 ORDER BY p.dateupdated DESC")
	public List<Project> getRecentTop3ProjectsByMentor(Mentor m,Pageable  pageable);
	
	@Query("SELECT p from Project p inner join MentorStudentMapping msm on msm.student=p.student WHERE msm.mentor.id=?1 and p.submitForReview=true")
	public List<Project> findStudentProjectsByMentor(int mid);
	
	
}
