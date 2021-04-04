package com.app.mla.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class represents GRADE table
 * 
 * @author Sahithi
 *
 */
@Entity
@Table(name = "GRADE")
public class Grade implements Serializable {

	private static final long serialVersionUID = 3720002293178196539L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "grade")
	private String grade;

	@ManyToOne(optional = true)
	@JoinColumn(name = "subject")
	private Subject subject;

	@ManyToOne(optional = true)
	@JoinColumn(name = "instructor")
	private User instructor;

	@ManyToOne(optional = true)
	@JoinColumn(name = "student")
	private User student;

	@ManyToOne(optional = true)
	@JoinColumn(name = "task")
	private Task task;

	public Grade() {
		super();
	}

	public Grade(Long id) {
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

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public User getInstructor() {
		return instructor;
	}

	public void setInstructor(User instructor) {
		this.instructor = instructor;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return "Grade [id=" + id + ", grade=" + grade + ", subject=" + subject + ", instructor=" + instructor
				+ ", student=" + student + ", task=" + task + "]";
	}

}
