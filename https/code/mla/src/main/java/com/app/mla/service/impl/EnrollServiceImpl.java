package com.app.mla.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.mla.domain.Subject;
import com.app.mla.domain.User;
import com.app.mla.dto.SubjectDTO;
import com.app.mla.dto.UserDTO;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.repository.SubjectRepository;
import com.app.mla.repository.UserRepository;
import com.app.mla.service.EnrollService;
import com.app.mla.util.EntityMapper;

/**
 * @author Sahithi
 *
 */
@Service("enrollService")
public class EnrollServiceImpl implements EnrollService {

	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	UserRepository userRepository;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public List<SubjectDTO> getAllSubjects() throws MLAServiceException {
		logger.debug("EnrollServiceImpl :: getAllSubjects -> START");
		List<SubjectDTO> subjectList = new ArrayList<>();
		List<Subject> subjects = subjectRepository.findAll();
		for (Subject subject : subjects) {
			subjectList.add(EntityMapper.getSubjectDTOMinDetails1(subject));
		}
		logger.debug("EnrollServiceImpl :: getAllSubjects : no of Subjects : " + subjectList.size());
		logger.debug("EnrollServiceImpl :: getAllSubjects -> END");
		return subjectList;
	}

	@Override
	public List<UserDTO> getStudentsToEnroll(Long subjectId) throws MLAServiceException {
		logger.debug("EnrollServiceImpl :: getStudentsToEnroll -> START");
		List<UserDTO> userList = new ArrayList<>();
		List<User> users = userRepository.findStudentsToEnroll(subjectId);
		for (User user : users) {
			userList.add(EntityMapper.getUserDTOEnroll(user));
		}
		logger.debug("EnrollServiceImpl :: getStudentsToEnroll : no of Students : " + userList.size());
		logger.debug("EnrollServiceImpl :: getStudentsToEnroll -> END");
		return userList;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void enrollStudents(Long subjectId, List<Long> studentIds) throws MLAServiceException {
		logger.debug("EnrollServiceImpl :: enrollStudents -> START");		
		logger.debug("EnrollServiceImpl :: enrollStudents -> studentIds: "+studentIds);
		try {
			for (Long studentId : studentIds) {
				userRepository.enrollStudent(studentId, subjectId);
			}
		} catch (Exception e) {
			logger.error("Exception Occurred: " + e.getMessage());
			throw new MLAServiceException(e.getMessage());
		}
		logger.debug("EnrollServiceImpl :: getStudentsEnroll -> END");
	}
	
	@Override
	public List<UserDTO> getStudentsEnrolled(Long subjectId) throws MLAServiceException {
		logger.debug("EnrollServiceImpl :: getStudentsEnrolled -> START");
		List<UserDTO> userList = new ArrayList<>();
		List<User> users = userRepository.findStudentsEnrolled(subjectId);
		for (User user : users) {
			userList.add(EntityMapper.getUserDTOEnroll(user));
		}
		logger.debug("EnrollServiceImpl :: getStudentsEnrolled : no of Students : " + userList.size());
		logger.debug("EnrollServiceImpl :: getStudentsEnrolled -> END");
		return userList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void disEnrollStudents(Long subjectId, List<Long> studentIds) throws MLAServiceException {
		logger.debug("EnrollServiceImpl :: disEnrollStudents -> START");		
		logger.debug("EnrollServiceImpl :: disEnrollStudents -> studentIds: "+studentIds);
		try {
			for (Long studentId : studentIds) {
				userRepository.disEnrollStudent(studentId, subjectId);
			}
		} catch (Exception e) {
			logger.error("Exception Occurred: " + e.getMessage());
			throw new MLAServiceException(e.getMessage());
		}
		logger.debug("EnrollServiceImpl :: disEnrollStudents -> END");
	}

	@Override
	public List<SubjectDTO> getSubjectsEnrolledByStudent(Long studentId) throws MLAServiceException {
		logger.debug("EnrollServiceImpl :: getSubjectsEnrolledByStudent -> START");
		List<SubjectDTO> subjectList = new ArrayList<>();
		List<Subject> subjects = subjectRepository.findSubjectsEnrolledByStudent(studentId);
		for (Subject subject : subjects) {
			subjectList.add(EntityMapper.getSubjectDTOMinDetails2(subject));
		}
		logger.debug("EnrollServiceImpl :: getSubjectsEnrolledByStudent : no of Subjects : " + subjectList.size());
		logger.debug("EnrollServiceImpl :: getSubjectsEnrolledByStudent -> END");
		return subjectList;
	}

}
