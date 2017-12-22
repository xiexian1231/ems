package com.megalink.ems.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;

@SuppressWarnings("serial")
@Entity
@Table(name = "\"User\"")
@DynamicInsert
public class User implements java.io.Serializable {

	@Id
	@SequenceGenerator(name = "User_ID_seq", allocationSize = 1, initialValue = 1, sequenceName = "User_ID_seq")
	@GeneratedValue(generator = "User_ID_seq", strategy = GenerationType.SEQUENCE)
	@Column(name = "\"ID\"")
	private Integer id;

	@Column(name = "\"ACCOUNT\"", nullable = false, unique = true)
	private String account;

	@Column(name = "\"PASSWORD\"")
	private String password;

	@Column(name = "\"NAME\"")
	private String name;

	@Column(name = "\"TEAM\"")
	private String team;

	@Column(name = "\"TELEPHONE\"")
	private String telephone;

	@Column(name = "\"EMAIL\"")
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "\"CREATETIME\"", length = 19)
	private Date createTime;

	@Column(name = "\"SALT\"")
	private String salt;

	@Column(name = "\"LOCKED\"", columnDefinition = "int default 1")
	private Integer locked;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "\"User_Role\"", joinColumns = {
			@JoinColumn(name = "\"User_ID\"", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "\"Role_ID\"", nullable = false, updatable = false) })
	private Set<Role> roles = new HashSet<Role>(0);

	public User() {
		super();
	}

	public User(Integer id, String account, String password, String name, String team, String telephone, String email,
			Date createTime, String salt, Integer locked, Set<Role> roles) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.name = name;
		this.team = team;
		this.telephone = telephone;
		this.email = email;
		this.createTime = createTime;
		this.salt = salt;
		this.locked = locked;
		this.roles = roles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
