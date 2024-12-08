package com.klef.jfsd.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.klef.jfsd.sdp.model.Mentor;
import com.klef.jfsd.sdp.model.MentorStudentMapping;
import com.klef.jfsd.sdp.model.Student;

public interface MentorStudentMappingRepository extends JpaRepository<MentorStudentMapping,Integer>
{
	@Query("select count(mcm) from MentorStudentMapping mcm where mcm.mentor = ?1 and mcm.student = ?2")
	public long checkmstudentmapping(Mentor mentor,Student student);
	
	@Query("select count(s) from MentorStudentMapping s where s.student = ?1")
	public long checkstudent(Student student);
	
	@Query("SELECT m.student FROM MentorStudentMapping m WHERE m.mentor.id = ?1")
	public List<Student> findStudentByMentor(int mid);
	
}
