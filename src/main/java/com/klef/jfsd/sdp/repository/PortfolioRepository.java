package com.klef.jfsd.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.sdp.model.Portfolio;
import com.klef.jfsd.sdp.model.Student;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer>
{
	@Query("from Portfolio p where p.student=?1")
	public Portfolio findPortfolioByStudent(Student student);

	@Query("SELECT p from Portfolio p inner join MentorStudentMapping msm on msm.student=p.student WHERE msm.mentor.id=?1 and msm.student.portfolio.status='Ready for review'")
	public List<Portfolio> findStudentPortfolioByMentor(int mid);
}
