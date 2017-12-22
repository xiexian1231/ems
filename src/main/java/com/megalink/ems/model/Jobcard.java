package com.megalink.ems.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "\"Jobcard\"")
public class Jobcard {
	@Id
	@SequenceGenerator(name = "Jobcard_ID_seq", allocationSize = 1, initialValue = 1, sequenceName = "Jobcard_ID_seq")
	@GeneratedValue(generator = "Jobcard_ID_seq", strategy = GenerationType.SEQUENCE)
	@Column(name = "\"ID\"")
	private Integer id;

	@Column(name = "\"NUMBER\"")
	private String number;

	@Column(name = "\"VERSION\"")
	private String version;

	@Column(name = "\"CHAPTER\"")
	private String chapter;

	@Column(name = "\"CATEGORY\"")
	private String category;

	@Column(name = "\"SYNCSTATE\"")
	private String syncState;

	@Column(name = "\"FILEPATH\"")
	private String filePath;

	@Column(name = "\"MECHANIC\"")
	private String mechanic;

	@Column(name = "\"INSPECTOR\"")
	private String inspector;

	@Column(name = "\"COMPLETESTATE\"")
	private String completeState;

	@Column(name = "\"SYNCTIME\"")
	private Date syncTime;

	@Column(name = "\"AIRCRAFTNUMBER\"")
	private String aircraftNumber;

	@Column(name = "\"WORKINGHOURS\"")
	private String workingHours;

	public Jobcard() {
		super();
	}

	public Jobcard(Integer id, String number, String version, String chapter, String category, String syncState,
			String filePath, String mechanic, String inspector, String completeState, Date syncTime,
			String aircraftNumber, String workingHours) {
		super();
		this.id = id;
		this.number = number;
		this.version = version;
		this.chapter = chapter;
		this.category = category;
		this.syncState = syncState;
		this.filePath = filePath;
		this.mechanic = mechanic;
		this.inspector = inspector;
		this.completeState = completeState;
		this.syncTime = syncTime;
		this.aircraftNumber = aircraftNumber;
		this.workingHours = workingHours;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSyncState() {
		return syncState;
	}

	public void setSyncState(String syncState) {
		this.syncState = syncState;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getMechanic() {
		return mechanic;
	}

	public void setMechanic(String mechanic) {
		this.mechanic = mechanic;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getCompleteState() {
		return completeState;
	}

	public void setCompleteState(String completeState) {
		this.completeState = completeState;
	}

	public Date getSyncTime() {
		return syncTime;
	}

	public void setSyncTime(Date syncTime) {
		this.syncTime = syncTime;
	}

	public String getAircraftNumber() {
		return aircraftNumber;
	}

	public void setAircraftNumber(String aircraftNumber) {
		this.aircraftNumber = aircraftNumber;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

}
