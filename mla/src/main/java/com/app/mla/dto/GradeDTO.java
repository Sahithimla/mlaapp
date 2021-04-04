package com.app.mla.dto;

import java.io.Serializable;

/**
 * @author Sahithi
 *
 */

public class GradeDTO implements Serializable {

	private static final long serialVersionUID = 7786997461372063657L;

	private Long id;

	private String userName;

	private String firstName;

	private String lastName;

	private String grade;

	private String topic;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "GradeDTO [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", grade=" + grade + ", topic=" + topic + "]";
	}

}
