package com.app.mla.dto;

import java.io.Serializable;

/**
 * @author Sahithi
 *
 */

public class LoginDTO implements Serializable {

	private static final long serialVersionUID = -4015321500376792357L;

	private String userName;

	private String password;

	private String newPassword1;

	private String newPassword2;

	public LoginDTO() {
		super();
	}

	public LoginDTO(String userName, String password, String newPassword1, String newPassword2) {
		super();
		this.userName = userName;
		this.password = password;
		this.newPassword1 = newPassword1;
		this.newPassword2 = newPassword2;
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

	public String getNewPassword1() {
		return newPassword1;
	}

	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

	@Override
	public String toString() {
		return "LoginDTO [userName=" + userName + ", password=" + password + ", newPassword1=" + newPassword1
				+ ", newPassword2=" + newPassword2 + "]";
	}

}
