package com.app.mla.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.mla.dto.UserDTO;
import com.app.mla.exception.DuplicateRecordException;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.exception.ResourceNotFoundException;
import com.app.mla.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/add")
	public ResponseEntity<Void> addUser(@RequestBody UserDTO userDTO) throws DuplicateRecordException, MLAServiceException {
		logger.debug("UserController :: addUser -> START");
		this.userService.addUser(userDTO);
		logger.debug("UserController :: addUser -> END");
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Void> editUser(@RequestBody UserDTO userDTO) throws DuplicateRecordException, ResourceNotFoundException, MLAServiceException {
		logger.debug("UserController :: editUser -> START");
		this.userService.editUser(userDTO);
		logger.debug("UserController :: editUser -> END");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws ResourceNotFoundException, MLAServiceException {
		logger.debug("UserController :: deleteUser -> START");
		this.userService.deleteUser(userId);
		logger.debug("UserController :: deleteUser -> END");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/details/{userName}")
	public ResponseEntity<UserDTO> getUser(@PathVariable String userName) throws ResourceNotFoundException, MLAServiceException {
		logger.debug("UserController :: getUserById -> START");
		UserDTO userDTO = this.userService.getUserByUserName(userName);
		logger.debug("UserController :: getUserById -> END");
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	
	@GetMapping("/all/{userType}")
	public ResponseEntity<List<UserDTO>> getAllUsersByType(@PathVariable String userType) throws ResourceNotFoundException, MLAServiceException {
		logger.debug("UserController :: getUserById -> START");
		List<UserDTO> userDTOs = this.userService.getUsersByType(userType);
		logger.debug("UserController :: getUserById -> END");
		return new ResponseEntity<>(userDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/all/forEmail")
	public ResponseEntity<List<UserDTO>> getAllUsersForEmail() throws MLAServiceException {
		logger.debug("UserController :: getAllUsersForEmail -> START");
		List<UserDTO> userDTOs = this.userService.getAllUsersForEmail();
		logger.debug("UserController :: getAllUsersForEmail -> END");
		return new ResponseEntity<>(userDTOs, HttpStatus.OK);
	}
	
}
