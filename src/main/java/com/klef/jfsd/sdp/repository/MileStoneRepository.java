package com.klef.jfsd.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.sdp.model.Mentor;
import com.klef.jfsd.sdp.model.MileStone;
import com.klef.jfsd.sdp.model.Student;

@Repository
public interface MileStoneRepository extends JpaRepository<MileStone, Integer>
{
	@Query("select m from MileStone m where m.project.id=?1 order by m.orderIndex ")
	public List<MileStone> findall(int pid);
	
	@Query("from MileStone m where m.project.student=?1 and m.status='IN_PROGRESS'")
	public List<MileStone> getPendingMileStonesByStudent(Student s);
	
	@Query("from MileStone m join MentorStudentMapping msm on msm.student=m.project.student where m.isReviewed=false and msm.mentor=?1")
	public List<MileStone> getUnReviewedMileStonesByMentor(Mentor m);
}
