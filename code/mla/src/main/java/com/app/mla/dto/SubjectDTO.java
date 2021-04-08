package com.app.mla.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Sahithi
 *
 */
public class SubjectDTO implements Serializable {

	private static final long serialVersionUID = -1017126399319836133L;

	private Long id;

	private String title;

	private String description;

	private String subjectTerm;

	private String subjectType;

	private String subjectId;

	private String mailingAlias;

	private Long instructorId;

	private UserDTO instructor;

	private Date startDate;

	private Date endDate;

	private String startTime;

	private String endTime;

	private Long duration;

	private String audioEnabled;

	private String videoEnabled;

	public SubjectDTO() {
		super();
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

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getMailingAlias() {
		return mailingAlias;
	}

	public void setMailingAlias(String mailingAlias) {
		this.mailingAlias = mailingAlias;
	}

	public Long getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(Long instructorId) {
		this.instructorId = instructorId;
	}

	public UserDTO getInstructor() {
		return instructor;
	}

	public void setInstructor(UserDTO instructor) {
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
		return "SubjectDTO [id=" + id + ", title=" + title + ", description=" + description + ", subjectTerm="
				+ subjectTerm + ", subjectType=" + subjectType + ", subjectId=" + subjectId + ", mailingAlias="
				+ mailingAlias + ", instructorId=" + instructorId + ", instructor=" + instructor + ", startDate="
				+ startDate + ", endDate=" + endDate + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", duration=" + duration + ", audioEnabled=" + audioEnabled + ", videoEnabled=" + videoEnabled + "]";
	}

}
