package com.klef.jfsd.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.klef.jfsd.sdp.model.PortfolioFeedback;
import com.klef.jfsd.sdp.model.Student;

public interface PortfolioFeedbackRepository extends JpaRepository<PortfolioFeedback,Integer>
{
	@Query("select pf from PortfolioFeedback pf where pf.portfolio.student=?1")
	public List<PortfolioFeedback> viewPortfolioFeedbackbyStudent(Student s);
}
