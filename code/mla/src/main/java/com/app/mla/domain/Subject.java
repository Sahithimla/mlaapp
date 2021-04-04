package com.app.mla.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class represents SUBJECT table
 * 
 * @author Sahithi
 *
 */
@Entity
@Table(name = "SUBJECT")
public class Subject implements Serializable {

	private static final long serialVersionUID = -3316061524983939428L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "subject_term")
	private String subjectTerm;

	@Column(name = "subject_type")
	private String subjectType;

	@Column(name = "mailing_alias")
	private String mailingAlias;

	@ManyToOne(optional = true)
    @JoinColumn(name ="instructor")
	private User instructor;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "start_time")
	private String startTime;

	@Column(name = "end_time")
	private String endTime;

	@Column(name = "duration")
	private Long duration;

	@Column(name = "audio_enabled")
	private String audioEnabled;

	@Column(name = "video_enabled")
	private String videoEnabled;

	public Subject() {
		super();
	}

	public Subject(Long id) {
		super();
		this.id = id;
	}

	public Subject(Long id, String title, String description, String subjectTerm, String subjectType,
			String mailingAlias, User instructor, Date startDate, Date endDate, String startTime, String endTime,
			Long duration, String audioEnabled, String videoEnabled) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.subjectTerm = subjectTerm;
		this.subjectType = subjectType;
		this.mailingAlias = mailingAlias;
		this.instructor = instructor;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
		this.audioEnabled = audioEnabled;
		this.videoEnabled = videoEnabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getSubjectTerm() {
		return subjectTerm;
	}

	public void setSubjectTerm(String subjectTerm) {
		this.subjectTerm = subjectTerm;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	public String getMailingAlias() {
		return mailingAlias;
	}

	public void setMailingAlias(String mailingAlias) {
		this.mailingAlias = mailingAlias;
	}

	public User getInstructor() {
		return instructor;
	}

	public void setInstructor(User instructor) {
		this.instructor = instructor;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getAudioEnabled() {
		return audioEnabled;
	}

	public void setAudioEnabled(String audioEnabled) {
		this.audioEnabled = audioEnabled;
	}

	public String getVideoEnabled() {
		return videoEnabled;
	}

	public void setVideoEnabled(String videoEnabled) {
		this.videoEnabled = videoEnabled;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", title=" + title + ", description=" + description + ", subjectTerm="
				+ subjectTerm + ", subjectType=" + subjectType + ", mailingAlias=" + mailingAlias + ", instructor="
				+ instructor + ", startDate=" + startDate + ", endDate=" + endDate + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", duration=" + duration + ", audioEnabled=" + audioEnabled
				+ ", videoEnabled=" + videoEnabled + "]";
	}

}
