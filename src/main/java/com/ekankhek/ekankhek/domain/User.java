package com.ekankhek.ekankhek.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	boolean active;
	Date created_date;
	Date last_updated;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}
	
	@Transient
	public String getName() {
		return first_name + " " + last_name;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	private List<UserRole> userRoles;
	
	
	public List<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		List<UserRole> userRoles = new ArrayList();
		if (getUserRoles().size() > 0) {
			userRoles.addAll(getUserRoles());
		} else {
			System.out.println("Failed");
			UserRole userRole = new UserRole(UserRole.ANONYMOUS, null);
		}
		System.out.println(userRoles.size());
		for (UserRole userRole : userRoles) {
			if (userRole.getRole().equalsIgnoreCase(UserRole.ADMINISTRATOR)) {
				authorities.add(new SimpleGrantedAuthority(UserRole.ADMINISTRATOR));
			} else if (userRole.getRole().equalsIgnoreCase(UserRole.ROLEUSER)) {
				authorities.add(new SimpleGrantedAuthority(UserRole.ROLEUSER));
			} else {
				authorities.add(new SimpleGrantedAuthority(UserRole.ANONYMOUS));
			}
		}
		return authorities;

	}

}
