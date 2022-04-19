package com.ekankhek.ekankhek.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Datauploads {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	String filename;
	
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false, name = "user")
	private User user;
	
	String title;
	String description;
	String sharecode;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public String getSharecode() {
		return sharecode;
	}
	public void setSharecode(String sharecode) {
		this.sharecode = sharecode;
	}
}
