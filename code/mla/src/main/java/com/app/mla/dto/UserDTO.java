package com.app.mla.dto;

import java.io.Serializable;

/**
 * @author Sahithi
 *
 */

public class UserDTO implements Serializable {

	private static final long serialVersionUID = -926803205901577083L;

	private Long id;

	private String userName;

	private String password;

	private String userType;

	private String firstName;

	private String lastName;

	private String emailId;

	private String telephone;

	private String aliasMailId;

	private String address;

	private String skypeId;

	public UserDTO() {
		super();
	}

	public UserDTO(Long id, String userName, String password, String userType, String firstName, String lastName,
			String emailId, String telephone, String aliasMailId, String address, String skypeId) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.telephone = telephone;
		this.aliasMailId = aliasMailId;
		this.address = address;
		this.skypeId = skypeId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAliasMailId() {
		return aliasMailId;
	}

	public void setAliasMailId(String aliasMailId) {
		this.aliasMailId = aliasMailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSkypeId() {
		return skypeId;
	}

	public void setSkypeId(String skypeId) {
		this.skypeId = skypeId;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", userName=" + userName + ", password=" + password + ", userType=" + userType
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + ", telephone="
				+ telephone + ", aliasMailId=" + aliasMailId + ", address=" + address + ", skypeId=" + skypeId + "]";
	}

}
