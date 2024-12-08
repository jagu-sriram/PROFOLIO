package com.klef.jfsd.sdp.model;

import java.util.List;

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
import jakarta.persistence.Table;

@Entity
@Table(name = "custom_milestone_table")
public class CustomMilestone 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="custom_milestone_id")
	private int id;
	@Column(name="custom_milestone_title",nullable = false,length=50)
	private String title;
	@Column(name="custom_milestone_desc",nullable = false,length=200)
	private String description;
	
	public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED
    }
	@Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
	
	@OneToMany(mappedBy = "customMilestone", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MileStoneFeedback> feedback;
	
    @ManyToOne
    @JoinColumn(name="project_id",nullable = false)
    private Project project;

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<MileStoneFeedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<MileStoneFeedback> feedback) {
		this.feedback = feedback;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	

}
