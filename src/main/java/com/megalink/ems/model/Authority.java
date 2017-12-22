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
@Table(name = "\"Authority\"")
public class Authority implements java.io.Serializable {

	@Id
	@SequenceGenerator(name = "Authority_ID_seq", allocationSize = 1, initialValue = 1, sequenceName = "Authority_ID_seq")
	@GeneratedValue(generator = "Authority_ID_seq", strategy = GenerationType.SEQUENCE)
	@Column(name = "\"ID\"")
	private Integer id;

	@Column(name = "\"NAME\"", nullable = false)
	private String name;

	@Column(name = "\"DESCRIPTION\"")
	private String description;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "\"Role_Authority\"", joinColumns = {
			@JoinColumn(name = "\"Authority_ID\"", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "\"Role_ID\"", nullable = false, updatable = false) })
	private Set<Role> roles = new HashSet<Role>(0);

	public Authority() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Authority(Integer id, String name, String description, Set<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.roles = roles;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
