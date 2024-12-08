package com.klef.jfsd.sdp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="mentorstudentmapping_table")
public class MentorStudentMapping 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int mappingid;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mentor_id") // foreign key column name
	private Mentor mentor;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id") // foreign key column name
	private Student student;

	public int getMappingid() {
		return mappingid;
	}

	public void setMappingid(int mappingid) {
		this.mappingid = mappingid;
	}

	public Mentor getMentor() {
		return mentor;
	}

	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
