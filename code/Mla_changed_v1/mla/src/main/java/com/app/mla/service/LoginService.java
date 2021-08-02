package com.app.mla.service;

import java.util.Dictionary;

import com.app.mla.dto.LoginDTO;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.exception.ValidationFailedException;

/**
 * Interface for Login operations
 * 
 * @author Sahithi
 */
public interface LoginService {

	/**
	 * Method to login a User
	 * 
	 * @param LoginDTO
	 * @return Dictionary
	 * @throws ValidationFailedException
	 */
	Dictionary<String, String> login(LoginDTO loginDTO) throws ValidationFailedException, MLAServiceException;

	/**
	 * Method to logout a User
	 * 
	 * @param LoginDTO
	 * @return boolean
	 */
	boolean logout(LoginDTO loginDTO) throws MLAServiceException;

	/**
	 * Method to user change password
	 * 
	 * @param LoginDTO
	 * @return boolean
	 */
	boolean changePassword(LoginDTO loginDTO) throws ValidationFailedException, MLAServiceException;

}
