package com.klef.jfsd.sdp.model;


import java.sql.Blob;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

@Entity
@Table(name="project_table")
public class Project 
{
	@Id
	@Column(name="project_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="project_title",nullable=false,length=50)
	private String title;
	@Column(name="project_description",nullable = false,length = 500)
	private String description;
//	@Column(name="project_framework",nullable = false,length = 50)
//	private String framework;
	@CreationTimestamp
	@Column(name="project_datecreated",nullable = false)
	private Date datecreated;
	@UpdateTimestamp
	@Column(name="project_dateupdated",nullable = false)
	private Date dateupdated;
	
	public enum ProjectStatus {
        IN_DEVELOPMENT,
        COMPLETED
    }
	@Enumerated(EnumType.STRING)
	@Column(name="project_status",nullable = false)
	private ProjectStatus status;
	@Column(name="project_link",nullable=true)
	private String link;
	@Column(nullable = false)
	private float progress;
	
	@Column(name="submitForReview")
	private boolean submitForReview;
	
	@Column(name="project_icon")
	private Blob projecticon;
	
	@ManyToOne
	@JoinColumn(name="student_id",nullable=false)
	private Student student;
	
	@OneToMany(mappedBy = "project",cascade = CascadeType.ALL)
	@OrderBy("orderIndex ASC")
	private List<MileStone> milestones;
//	
//	@OneToMany(mappedBy = "project",cascade = CascadeType.ALL)
//	private List<Feedback> feedbacks;
//	
	@OneToMany(mappedBy = "project",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Media> media;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<PortfolioProject> portfolioProjects;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CustomMilestone> custommilestones;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Date getDateupdated() {
		return dateupdated;
	}
	public void setDateupdated(Date dateupdated) {
		this.dateupdated = dateupdated;
	}
	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}
	public Date getDatecreated() {
		return datecreated;
	}
	public List<Media> getMedia() {
		return media;
	}
	public void setMedia(List<Media> media) {
		this.media = media;
	}
	public List<PortfolioProject> getPortfolioProjects() {
		return portfolioProjects;
	}
	public void setPortfolioProjects(List<PortfolioProject> portfolioProjects) {
		this.portfolioProjects = portfolioProjects;
	}
	public float getProgress() {
		return progress;
	}
	public void setProgress(float progress) {
		this.progress = progress;
	}
	public List<MileStone> getMilestones() {
		return milestones;
	}
	public void setMilestones(List<MileStone> milestones) {
		this.milestones = milestones;
	}
	public String getStatus() {
	    return status.name();
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}
	public boolean isSubmitForReview() {
		return submitForReview;
	}
	public void setSubmitForReview(boolean submitForReview) {
		this.submitForReview = submitForReview;
	}
	public Blob getProjecticon() {
		return projecticon;
	}
	public void setProjecticon(Blob projecticon) {
		this.projecticon = projecticon;
	}
	public List<CustomMilestone> getCustommilestones() {
		return custommilestones;
	}
	public void setCustommilestones(List<CustomMilestone> custommilestones) {
		this.custommilestones = custommilestones;
	}
	
	

}
