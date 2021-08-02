package com.app.mla.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Sahithi
 *
 */

public class ScheduleDTO implements Serializable {

	private static final long serialVersionUID = -6677277724648681563L;

	private Long id;

	private Long subjectId;

	private String topic;

	private String description;

	private Long instructorId;

	private String instructorUserName;

	private String instructorTelephone;

	private Date startDate;

	private Date endDate;

	private String startTime;

	private String endTime;

	private String every;

	private String audioEnabled;

	private String videoEnabled;

	public ScheduleDTO() {
		super();
	}

	public ScheduleDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(Long instructorId) {
		this.instructorId = instructorId;
	}

	public String getInstructorUserName() {
		return instructorUserName;
	}

	public void setInstructorUserName(String instructorUserName) {
		this.instructorUserName = instructorUserName;
	}

	public String getInstructorTelephone() {
		return instructorTelephone;
	}

	public void setInstructorTelephone(String instructorTelephone) {
		this.instructorTelephone = instructorTelephone;
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

	public String getEvery() {
		return every;
	}

	public void setEvery(String every) {
		this.every = every;
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
		return "ScheduleDTO [id=" + id + ", subjectId=" + subjectId + ", topic=" + topic + ", description=" + description
				+ ", instructorId=" + instructorId + ", instructorUserName=" + instructorUserName
				+ ", instructorTelephone=" + instructorTelephone + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", every=" + every + ", audioEnabled="
				+ audioEnabled + ", videoEnabled=" + videoEnabled + "]";
	}

}
