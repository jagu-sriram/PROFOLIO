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
@Table(name="milestone_feedback_table")
public class MileStoneFeedback 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String comment;
	@CreationTimestamp
	@Column(nullable = false)
	private Date feedbackDate;
	@ManyToOne
    @JoinColumn(name = "milestone_id")
	private MileStone milestone;
	
	@ManyToOne
    @JoinColumn(name = "custom_milestone_id")
    private CustomMilestone customMilestone;
	
//	@ManyToOne
//	@JoinColumn(name = "mentor_id", nullable = false)
//	private Mentor mentor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getFeedbackDate() {
		return feedbackDate;
	}
	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}
	public MileStone getMileStone() {
		return milestone;
	}
	public void setMileStone(MileStone milestone) {
		this.milestone = milestone;
	}
	public CustomMilestone getCustomMilestone() {
		return customMilestone;
	}
	public void setCustomMilestone(CustomMilestone customMilestone) {
		this.customMilestone = customMilestone;
	}
	

}
