package com.app.mla.service;

import java.util.List;

import com.app.mla.dto.UserDTO;
import com.app.mla.exception.DuplicateRecordException;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.exception.ResourceNotFoundException;

/**
 * Interface for User operations
 * @author Sahithi
 */
public interface UserService {

	/**
	 * Method to Add the User details
	 * @param userDTO
	 * @return UserDTO
	 */
	void addUser(UserDTO userDTO) throws DuplicateRecordException, MLAServiceException;
	
	/**
	 * Method to Edit the User details
	 * @param userDTO
	 * @return UserDTO
	 */
	void editUser(UserDTO userDTO) throws DuplicateRecordException, ResourceNotFoundException, MLAServiceException;
	
	/**
	 * Method to delete the User details
	 * @param userId
	 * @return void
	 */
	void deleteUser(Long userId) throws ResourceNotFoundException, MLAServiceException;
	
	/**
	 * Method to get the User details by userId
	 * @param userId
	 * @return UserDTO
	 */
	UserDTO getUserById(Long userId) throws ResourceNotFoundException, MLAServiceException;
	
	/**
	 * Method to get the User details by userName
	 * @param userName
	 * @return UserDTO
	 */
	UserDTO getUserByUserName(String userName) throws ResourceNotFoundException, MLAServiceException;
	
	/**
	 * Method to get all Users by Type
	 * @param userType
	 * @return List<UserDTO>
	 */
	List<UserDTO> getUsersByType(String userType);
	
	/**
	 * Method to get all Users For Email
	 * @param 
	 * @return List<UserDTO>
	 */
	List<UserDTO> getAllUsersForEmail();

}
