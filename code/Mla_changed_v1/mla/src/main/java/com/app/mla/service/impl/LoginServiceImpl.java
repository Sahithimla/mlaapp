package com.app.mla.service.impl;

import java.util.Dictionary;
import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.mla.domain.User;
import com.app.mla.dto.LoginDTO;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.exception.ValidationFailedException;
import com.app.mla.repository.UserRepository;
import com.app.mla.service.LoginService;

/**
 * @author Sahithi
 *
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserRepository userRepository;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public Dictionary<String, String> login(LoginDTO loginDTO) throws ValidationFailedException, MLAServiceException {

		logger.debug("LoginServiceImpl :: login -> START");
		boolean login = false;
		Dictionary<String, String> userResp = new Hashtable<String, String>();
		if (StringUtils.isEmpty(loginDTO.getUserName())) {
			logger.error("Username and Password are required");
			throw new ValidationFailedException("Username and Password are required");
		} else {
			User user = userRepository.findByUserName(loginDTO.getUserName());
			if (user != null && user.getPassword().equals(loginDTO.getPassword())) {
				login = true;
				userResp.put("userId", user.getId().toString());
				userResp.put("userName", user.getUserName());
				userResp.put("userType", user.getUserType());
			} else {
				logger.error("Invalid Username/Passsword");
				throw new ValidationFailedException("Invalid Username/Passsword");
			}
		}
		logger.debug("LoginServiceImpl :: login -> END");
		if (login) {
			return userResp;
		} else {
		return null;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public boolean logout(LoginDTO loginDTO) throws MLAServiceException {

		logger.debug("LoginServiceImpl :: logout -> START");
		if (!StringUtils.isEmpty(loginDTO.getUserName())) {
			// TODO - Logout
		} else {
			logger.error("Invalid User");
			throw new ValidationFailedException("Invalid User");
		}
		logger.debug("LoginServiceImpl :: logout -> END");
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public boolean changePassword(LoginDTO loginDTO) throws ValidationFailedException, MLAServiceException {

		logger.debug("LoginServiceImpl :: changePassword -> START");
		boolean changePassword = false;
		if (StringUtils.isEmpty(loginDTO.getPassword()) || StringUtils.isEmpty(loginDTO.getNewPassword1())
				|| StringUtils.isEmpty(loginDTO.getNewPassword2())) {
			logger.error("Old Password/New Password/Re-enter New Password are required");
			throw new ValidationFailedException("Old Password/New Password/Re-enter New Password are required");
		} else if (!loginDTO.getNewPassword1().equals(loginDTO.getNewPassword2())) {
			logger.error("New Password and Re-enter New Password are not matching");
			throw new ValidationFailedException("New Password and Re-enter New Passwords are not matching");
		} else {
			User user = userRepository.findByUserName(loginDTO.getUserName());
			if (user != null) {
				user.setPassword(loginDTO.getNewPassword1());
				userRepository.save(user);
				changePassword = true;
				
			} else {
				logger.error("Invalid User");
				throw new ValidationFailedException("Invalid User");
			}
		}
		logger.debug("LoginServiceImpl :: changePassword -> END");
		return changePassword;
	}

}
