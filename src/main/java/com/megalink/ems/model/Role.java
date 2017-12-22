package com.megalink.ems.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "\"Role\"")
public class Role implements java.io.Serializable {

	@Id
	@SequenceGenerator(name = "Role_ID_seq", allocationSize = 1, initialValue = 1, sequenceName = "Role_ID_seq")
	@GeneratedValue(generator = "Role_ID_seq", strategy = GenerationType.SEQUENCE)
	@Column(name = "\"ID\"")
	private Integer id;

	@Column(name = "\"NAME\"", nullable = false)
	private String name;

	@Column(name = "\"DESCRIPTION\"")
	private String description;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "\"User_Role\"", joinColumns = {
			@JoinColumn(name = "\"Role_ID\"", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "\"User_ID\"", nullable = false, updatable = false) })
	private Set<User> users = new HashSet<User>(0);

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "\"Role_Authority\"", joinColumns = {
			@JoinColumn(name = "\"Role_ID\"", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "\"Authority_ID\"", nullable = false, updatable = false) })
	private Set<Authority> authorities = new HashSet<Authority>(0);

	public Role() {
		super();
	}

	public Role(Integer id, String name, String description, Set<User> users, Set<Authority> authorities) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.users = users;
		this.authorities = authorities;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

}
