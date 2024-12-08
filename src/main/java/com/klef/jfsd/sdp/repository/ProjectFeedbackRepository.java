package com.klef.jfsd.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.sdp.model.ProjectFeedback;
import com.klef.jfsd.sdp.model.Student;

@Repository
public interface ProjectFeedbackRepository extends JpaRepository<ProjectFeedback, Integer>
{
	@Query("select pf from ProjectFeedback pf where pf.project.student=?1")
	public List<ProjectFeedback> viewprojectfeedback(Student s);

}
