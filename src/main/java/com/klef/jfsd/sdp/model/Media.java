package com.klef.jfsd.sdp.model;

import java.sql.Blob;
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
@Table(name="media_table")
public class Media 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="media_id")
	private int id;
	@Column(name="media_caption",nullable = false,length=50)
	private String caption;
	@Column(name="media_mediatype",nullable = false,length=10)
	private String mediatype;
	@Column(name="media_filename",nullable = false,length=50)
	private String mediafilename;
	@CreationTimestamp
	@Column(name="media_dateuploaded",nullable = false)
	private Date dateuploaded;
	@Column(name="media",nullable = false)
	private Blob media;
	
	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getMediatype() {
		return mediatype;
	}

	public void setMediatype(String mediatype) {
		this.mediatype = mediatype;
	}

	public Date getDateuploaded() {
		return dateuploaded;
	}

	public void setDateuploaded(Date dateuploaded) {
		this.dateuploaded = dateuploaded;
	}

	public Blob getMedia() {
		return media;
	}

	public void setMedia(Blob media) {
		this.media = media;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getMediafilename() {
		return mediafilename;
	}

	public void setMediafilename(String mediafilename) {
		this.mediafilename = mediafilename;
	}
}
