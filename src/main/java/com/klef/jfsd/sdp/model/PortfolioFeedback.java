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
@Table(name="portfolio_feedback_table")
public class PortfolioFeedback 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="portfolio_feedback_id")
	private int id;
	@CreationTimestamp
	@Column(name="portfolio_feedback_submitteddate",nullable = false)
	private Date submitteddate;
	@Column(name="portfolio_feedback_rating",nullable = false)
	private String rating;
	@Column(name = "portfolio_feedback_message",nullable = false,length = 500)
	private String message;
	
	@ManyToOne
	@JoinColumn(name="portfolio_id")
	private Portfolio portfolio;
	
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public Mentor getMentor() {
		return mentor;
	}

	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}
}
