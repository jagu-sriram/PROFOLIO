package com.klef.jfsd.sdp.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="projectfeedback_table")
public class ProjectFeedback
{
	@Id
	@Column(name="projectfeedback_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@CreationTimestamp
	@Column(name="pfeedback_submitteddate",nullable = false)
	private Date submitteddate;
	@Column(name="project_rating",nullable=false)
	private String rating;
	@Column(name="comments",nullable = false)
	private String comments;
	
	@ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

	@ManyToOne
	@JoinColumn(name="mentor_id",nullable=false)
	private Mentor mentor;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getSubmitteddate() {
		return submitteddate;
	}

	public void setSubmitteddate(Date submitteddate) {
		this.submitteddate = submitteddate;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Mentor getMentor() {
		return mentor;
	}

	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}
}
