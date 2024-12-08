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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="milestone_table")
public class MileStone 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="milestone_id")
	private int id;
	@Column(name="milestone_title",nullable = false,length=50)
	private String title;
	@Column(name="milestone_desc",nullable = false,length=200)
	private String description;
//	@Column(name="milestone_duedate",nullable = false)
//    private LocalDate dueDate;
	
	public enum Status {
        NOT_STARTED,
        IN_PROGRESS,
        COMPLETED
    }
	@Enumerated(EnumType.STRING)
	@Column(name="milestone_status",nullable = false)
    private Status status;
    @Column(nullable = false)
	private boolean isReviewed;
	@Column(name="order_for_project")
	private int orderIndex;
	
	 @Column(nullable = false)
	 private float progressPercentage;
	
	@OneToMany(mappedBy = "milestone", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MileStoneFeedback> feedback;
	
	@OneToOne(mappedBy = "milestone",cascade = CascadeType.ALL,orphanRemoval = true)
	private MileStoneProgress mileStoneProgress;
	
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

//	public LocalDate getDueDate() {
//		return dueDate;
//	}
//
//	public void setDueDate(LocalDate dueDate) {
//		this.dueDate = dueDate;
//	}


	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<MileStoneFeedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<MileStoneFeedback> feedback) {
		this.feedback = feedback;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public float getProgressPercentage() {
		return progressPercentage;
	}

	public void setProgressPercentage(float progressPercentage) {
		this.progressPercentage = progressPercentage;
	}

	public boolean isReviewed() {
		return isReviewed;
	}

	public void setReviewed(boolean isReviewed) {
		this.isReviewed = isReviewed;
	}

	public MileStoneProgress getMileStoneProgress() {
		return mileStoneProgress;
	}

	public void setMileStoneProgress(MileStoneProgress mileStoneProgress) {
		this.mileStoneProgress = mileStoneProgress;
	}
    
    
}
