package com.app.mla.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.mla.dto.LoginDTO;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.exception.ValidationFailedException;
import com.app.mla.service.LoginService;

@RestController
@RequestMapping("/api/app")
public class LoginController {

	@Autowired
	LoginService loginService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@PostMapping("/login")
	public ResponseEntity<Boolean> login(@RequestBody LoginDTO loginDTO)
			throws ValidationFailedException, MLAServiceException {
		logger.debug("LoginController :: login -> START");
		boolean login = this.loginService.login(loginDTO);
		logger.debug("LoginController :: login -> END");
		return new ResponseEntity<>(login, HttpStatus.OK);
	}

	@PostMapping("/logout")
	public ResponseEntity<Boolean> logout(@RequestBody LoginDTO loginDTO)
			throws ValidationFailedException, MLAServiceException {
		logger.debug("LoginController :: logout -> START");
		boolean logout = this.loginService.logout(loginDTO);
		logger.debug("LoginController :: logout -> END");
		return new ResponseEntity<>(logout, HttpStatus.OK);
	}

	@PutMapping("/changePassword")
	public ResponseEntity<Boolean> changePassword(@RequestBody LoginDTO loginDTO)
			throws ValidationFailedException, MLAServiceException {
		logger.debug("LoginController :: changePassword -> START");
		boolean changePassword = this.loginService.changePassword(loginDTO);
		logger.debug("LoginController :: changePassword -> END");
		return new ResponseEntity<>(changePassword, HttpStatus.OK);
	}

}
