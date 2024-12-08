package com.klef.jfsd.sdp.model;

import java.sql.Blob;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="student_table")
public class Student 
{
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name="student_id")
	  private int id;
	  @Column(name="student_firstname",nullable=false,length = 50)
	  private String firstname;
	  @Column(name="student_lastname",nullable=false,length = 50)
	  private String lastname;
	  @Column(name = "student_gender",nullable = false,length = 20)
	  private String gender;
	  @Column(name = "student_dept",nullable = false,length = 50)
	  private String department;
	  @Column(name="student_email",nullable=false,unique = true,length = 50)
	  private String email;
	  @Column(name="student_password",nullable=false,length = 50)
	  private String password;
	  @Column(name="student_contact",nullable=false,length = 10)
	  private String contact;
	  @Column(name = "student_age",nullable = false)
	  private float age;
	  @Column(name = "student_dob",nullable = false)
	  private String dateofBirth;
	@Column(name="student_profilephoto",nullable = true)
	private Blob image;
	
	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Project> projectlists;

	@OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
	private Portfolio portfolio;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

//	public Blob getProfilephoto() {
//		return profilephoto;
//	}
//
//	public void setProfilephoto(Blob profilephoto) {
//		this.profilephoto = profilephoto;
//	}

	public List<Project> getProjectlists() {
		return projectlists;
	}

	public void setProjectlists(List<Project> projectlists) {
		this.projectlists = projectlists;
	}

	public float getAge() {
		return age;
	}

	public void setAge(float age) {
		this.age = age;
	}

	public String getDateofBirth() {
		return dateofBirth;
	}

	public void setDateofBirth(String dateofBirth) {
		this.dateofBirth = dateofBirth;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}
	
	

}
