package com.app.mla.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.mla.domain.User;
import com.app.mla.dto.UserDTO;
import com.app.mla.exception.BadRequestException;
import com.app.mla.exception.DuplicateRecordException;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.exception.ResourceNotFoundException;
import com.app.mla.repository.UserRepository;
import com.app.mla.service.UserService;
import com.app.mla.util.EntityMapper;

/**
 * @author Sahithi
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void addUser(UserDTO userDTO) throws BadRequestException, DuplicateRecordException, MLAServiceException {
		logger.debug("UserServiceImpl :: addUser -> START");
		
		if (userRepository.findByUserName(userDTO.getUserName()) == null) {
			validateUserDetails(userDTO);
			userRepository.save(EntityMapper.getUser(userDTO));
		} else {
			logger.error("User with username '" + userDTO.getUserName()
					+ "' already exists. Please choose a different username");
			throw new DuplicateRecordException("User with username '" + userDTO.getUserName()
					+ "' already exists. Please choose a different username");
		}
		logger.debug("UserServiceImpl :: addUser -> END");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void editUser(UserDTO userDTO)
			throws DuplicateRecordException, ResourceNotFoundException, MLAServiceException {
		logger.debug("UserServiceImpl :: editUser -> START");
		User user = userRepository.findUserById(userDTO.getId());
		if (user != null) {
			validateUserDetails(userDTO);
			userRepository.save(EntityMapper.editUser(user, userDTO));
		} else {
			logger.error("User does not exists to edit the details");
			throw new ResourceNotFoundException("User does not exists to edit the details");
		}
		logger.debug("UserServiceImpl :: editUser -> END");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void deleteUser(Long userId) throws ResourceNotFoundException, MLAServiceException {
		logger.debug("UserServiceImpl :: deleteUser -> START");
		User user = userRepository.findUserById(userId);
		if (user != null) {
			userRepository.delete(user);
		} else {
			logger.error("User does not exists to delete");
			throw new ResourceNotFoundException("User does not exists to delete");
		}
		logger.debug("UserServiceImpl :: deleteUser -> END");
	}

	@Override
	public UserDTO getUserById(Long userId) throws ResourceNotFoundException, MLAServiceException {
		logger.debug("UserServiceImpl :: getUserById -> START");
		UserDTO userDTO = null;
		User user = userRepository.findUserById(userId);
		if (user != null) {
			userDTO = EntityMapper.getUserDTO(user);
		} else {
			logger.error("User does not exists");
			throw new ResourceNotFoundException("User does not exists");
		}
		logger.debug("UserServiceImpl :: getUserById : User details : " + userDTO);
		logger.debug("UserServiceImpl :: getUserById -> END");
		return userDTO;
	}

	@Override
	public List<UserDTO> getUsersByType(String userType) {
		logger.debug("UserServiceImpl :: getUsersByType -> START");
		List<UserDTO> userList = new ArrayList<>();
		List<User> users = userRepository.findUsersByType(userType);
		for (User user : users) {
			userList.add(EntityMapper.getUserDTO(user));
		}
		logger.debug("UserServiceImpl :: getUsersByType : no of Users : " + userList.size());
		logger.debug("UserServiceImpl :: getUsersByType -> END");
		return userList;
	}
	
	@Override
	public List<UserDTO> getAllUsersForEmail() {
		logger.debug("UserServiceImpl :: getAllUsersForEmail -> START");
		List<UserDTO> userList = new ArrayList<>();
		List<User> users = userRepository.findAll();
		for (User user : users) {
			userList.add(EntityMapper.getUserDTOForEmail(user));
		}
		logger.debug("UserServiceImpl :: getAllUsersForEmail : no of Users : " + userList.size());
		logger.debug("UserServiceImpl :: getAllUsersForEmail -> END");
		return userList;
	}
	
	private void validateUserDetails(UserDTO userDTO) throws BadRequestException {
		if(StringUtils.isEmpty(userDTO.getUserName())) {
			logger.error("UserName is required");
			throw new BadRequestException("UserName is required");
		}
		if(StringUtils.isEmpty(userDTO.getPassword())) {
			logger.error("Password is required");
			throw new BadRequestException("Password is required");
		}
		if(StringUtils.isEmpty(userDTO.getFirstName())) {
			logger.error("First Name is required");
			throw new BadRequestException("First Name is required");
		}
		if(StringUtils.isEmpty(userDTO.getLastName())) {
			logger.error("Last Name is required");
			throw new BadRequestException("Last Name is required");
		}
		if(StringUtils.isEmpty(userDTO.getUserType())) {
			logger.error("User Type is required");
			throw new BadRequestException("User Type is required");
		}
		if(!StringUtils.isEmpty(userDTO.getUserType()) && !(userDTO.getUserType().equalsIgnoreCase("ADMIN") || userDTO.getUserType().equalsIgnoreCase("INSTRUCTOR") || userDTO.getUserType().equalsIgnoreCase("STUDENT"))) {
			logger.error("User Type should be ADMIN/INSTRUCTOR/STUDENT");
			throw new BadRequestException("User Type should be ADMIN/INSTRUCTOR/STUDENT");
		}
		userDTO.setUserType(userDTO.getUserType().toUpperCase());
		if(StringUtils.isEmpty(userDTO.getEmailId())) {
			logger.error("Email Id is required");
			throw new BadRequestException("Email Id is required");
		}
		if(StringUtils.isEmpty(userDTO.getTelephone())) {
			logger.error("Telephone is required");
			throw new BadRequestException("Telephone is required");
		}
	}

}
