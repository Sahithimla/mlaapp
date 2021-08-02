package com.app.mla.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.app.mla.domain.Subject;
import com.app.mla.domain.Task;
import com.app.mla.domain.User;

/**
 * @author Sahithi
 *
 */

public class GradeDTO implements Serializable {

	private static final long serialVersionUID = 7786997461372063657L;

	private Long id;

	private String grade;

	private String subject;

	private String instructor;

	private String student;

	private Long task;
	
	private Date startDate;
	
	private Date endDate;
	
	private String startTime;
	
	private String endTime;

	public GradeDTO() {
		super();
	}

	public GradeDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public Long getTask() {
		return task;
	}

	public void setTask(Long task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return "Grade [id=" + id + ", grade=" + grade + ", subject=" + subject + ", instructor=" + instructor
				+ ", student=" + student + ", task=" + task + "]";
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

}
