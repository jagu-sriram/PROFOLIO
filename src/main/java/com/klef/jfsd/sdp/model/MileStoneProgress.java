package com.klef.jfsd.sdp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="milestone_progress_table")
public class MileStoneProgress 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private float studentpercentage;
	@Column(nullable = false)
	private float mentorpercentage;
	
	@OneToOne
	@JoinColumn(name="milestone_id",nullable = false,unique = true)
	private MileStone milestone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getStudentpercentage() {
		return studentpercentage;
	}

	public void setStudentpercentage(float studentpercentage) {
		this.studentpercentage = studentpercentage;
	}

	public float getMentorpercentage() {
		return mentorpercentage;
	}

	public void setMentorpercentage(float mentorpercentage) {
		this.mentorpercentage = mentorpercentage;
	}

	public MileStone getMilestone() {
		return milestone;
	}

	public void setMilestone(MileStone milestone) {
		this.milestone = milestone;
	}

}
