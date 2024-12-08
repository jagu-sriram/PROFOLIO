package com.klef.jfsd.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.sdp.model.CustomMilestone;
import com.klef.jfsd.sdp.model.Mentor;

@Repository
public interface CustomMileStoneRepository extends JpaRepository<CustomMilestone, Integer>
{
	@Query("select cm from CustomMilestone cm JOIN cm.project p JOIN p.student s JOIN MentorStudentMapping msm ON msm.student = s where msm.mentor=?1 AND cm.status='PENDING'")
	public List<CustomMilestone> getPendingCustomMilestonesByMentor(Mentor m);

}
