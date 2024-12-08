package com.klef.jfsd.sdp.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="portfolio_table")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="portfolio_id")
    private int id;

    @Column(name="portfolio_tagline")
    private String tagline;

    @Column(name="portfolio_info",nullable = false,length=500)
    private String info;
    
    @CreationTimestamp
	@Column(name="portfolio_datecreated",nullable = false)
	private Date datecreated;
    
	@UpdateTimestamp
	@Column(name="portfolio_dateupdated",nullable = false)
	private Date dateupdated;
	
	@Column(name="portfolio_status",nullable = false)
	private String status;
	
	@Column(name="portfolio_onlineurl",length = 100)
	private String onlineurl;
    
	@OneToMany(mappedBy = "portfolio",cascade = CascadeType.ALL)
    private List<Skill> skills; // create a class if needed later

	@OneToMany(mappedBy = "portfolio",cascade = CascadeType.ALL)
    private List<Achievement> achievements; // create a class for it later (title and info), can be able to take workexp and certifications

	@OneToMany(mappedBy = "portfolio",cascade = CascadeType.ALL)
    private List<PortfolioProject> selectedprojects; 
    
    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student; // fk from student table (not null)

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Date getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}

	public Date getDateupdated() {
		return dateupdated;
	}

	public void setDateupdated(Date dateupdated) {
		this.dateupdated = dateupdated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOnlineurl() {
		return onlineurl;
	}

	public void setOnlineurl(String onlineurl) {
		this.onlineurl = onlineurl;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<Achievement> getAchievements() {
		return achievements;
	}

	public void setAchievements(List<Achievement> achievements) {
		this.achievements = achievements;
	}

	public List<PortfolioProject> getSelectedprojects() {
		return selectedprojects;
	}

	public void setSelectedprojects(List<PortfolioProject> selectedprojects) {
		this.selectedprojects = selectedprojects;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}


}