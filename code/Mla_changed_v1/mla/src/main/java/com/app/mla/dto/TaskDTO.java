package com.app.mla.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Sahithi
 *
 */

public class TaskDTO implements Serializable {

	private static final long serialVersionUID = -6676277724648681563L;

	private Long id;

	private Long scheduleId;

	private String topic;

	private String description;

	private Date startDate;

	private Date endDate;

	private String startTime;

	private String endTime;

	public TaskDTO() {
		super();
	}

	public TaskDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
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

	@Override
	public String toString() {
		return "TaskDTO [id=" + id + ", subjectId=" + scheduleId + ", topic=" + topic + ", description=" + description
				+ ", instructorId=" + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

}
