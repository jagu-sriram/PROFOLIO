package com.klef.jfsd.sdp.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "mentor_table")
public class Mentor 
{
	@Id
	@Column(name="mentor_id")
	private int id;
	@Column(name="mentor_firstname")
	private String firstname;
	@Column(name="mentor_lastname",nullable = false)
	private String lastname;
	@Column(name="mentor_gender",nullable = false)
	private String gender;
	@Column(name="mentor_dob",nullable = false)
	private String dateofBirth;
	@Column(name="mentor_qualification",nullable = false)
	private String qualification;
	@Column(name="mentor_desg",nullable = false)
	private String designation;
	@Column(name="mentor_dept",nullable = false)
	private String department;
	@Column(name="mentor_experience",nullable = false)
	private float experience;
	@Column(name="mentor_contact",nullable = false)
	private String contact;
	@Column(name="mentor_email",nullable = false)
	private String email;
	@Column(name="mentor_pwd",nullable = false)
	private String password;
	
	@OneToMany(mappedBy="mentor",cascade = CascadeType.ALL)
	private List<MentorStudentMapping> mentorStudentMapping;
	
	@OneToMany(mappedBy = "mentor",cascade = CascadeType.ALL)
	private List<ProjectFeedback> projectFeedbacks;
	
	@OneToMany(mappedBy = "mentor",cascade = CascadeType.ALL)
	private List<PortfolioFeedback> portfolioFeedbacks;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDateofBirth() {
		return dateofBirth;
	}
	public void setDateofBirth(String dateofBirth) {
		this.dateofBirth = dateofBirth;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public float getExperience() {
		return experience;
	}
	public void setExperience(float experience) {
		this.experience = experience;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
