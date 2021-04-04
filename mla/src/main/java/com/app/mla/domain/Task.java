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
 * Class represents TASK table
 * 
 * @author Sahithi
 *
 */
@Entity
@Table(name = "TASK")
public class Task implements Serializable {

	private static final long serialVersionUID = 3768952939869624392L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ManyToOne(optional = true)
	@JoinColumn(name = "subject")
	private Subject subject;

	@Column(name = "topic")
	private String topic;

	@Column(name = "description")
	private String description;

	@ManyToOne(optional = true)
	@JoinColumn(name = "instructor")
	private User instructor;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "start_time")
	private String startTime;

	@Column(name = "end_time")
	private String endTime;

	@Column(name = "repeat_task")
	private String repeatTask;

	@Column(name = "audio_enabled")
	private String audioEnabled;

	@Column(name = "video_enabled")
	private String videoEnabled;

	public Task() {
		super();
	}

	public Task(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
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

	public String getRepeatTask() {
		return repeatTask;
	}

	public void setRepeatTask(String repeatTask) {
		this.repeatTask = repeatTask;
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
		return "Task [id=" + id + ", subject=" + subject + ", topic=" + topic + ", description=" + description
				+ ", instructor=" + instructor + ", startDate=" + startDate + ", endDate=" + endDate + ", startTime="
				+ startTime + ", endTime=" + endTime + ", repeatTask=" + repeatTask + ", audioEnabled=" + audioEnabled
				+ ", videoEnabled=" + videoEnabled + "]";
	}

}
