package com.app.mla.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.mla.dto.SubjectDTO;
import com.app.mla.dto.UserDTO;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.service.EnrollService;

@RestController
@RequestMapping("/api/enroll")
public class EnrollController {

	@Autowired
	EnrollService enrollService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/subjects/all")
	public ResponseEntity<List<SubjectDTO>> getSubjects() throws MLAServiceException {
		logger.debug("EnrollController :: getSubjects -> START");
		List<SubjectDTO> subjectDTOs = this.enrollService.getAllSubjects();
		logger.debug("EnrollController :: getSubjects -> END");
		return new ResponseEntity<>(subjectDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/students/toEnroll/{subjectId}")
	public ResponseEntity<List<UserDTO>> getStudentsToEnroll(@PathVariable Long subjectId) throws MLAServiceException {
		logger.debug("EnrollController :: getStudentsToEnroll -> START");
		List<UserDTO> userDTOs = this.enrollService.getStudentsToEnroll(subjectId);
		logger.debug("EnrollController :: getStudentsToEnroll -> END");
		return new ResponseEntity<>(userDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/students/{subjectId}")
	public ResponseEntity<Void> enrollStudents(@PathVariable Long subjectId, @RequestBody List<Long> studentIds) throws MLAServiceException {
		logger.debug("EnrollController :: enrollStudents -> START");
		this.enrollService.enrollStudents(subjectId, studentIds);
		logger.debug("EnrollController :: enrollStudents -> END");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/students/enrolled/{subjectId}")
	public ResponseEntity<List<UserDTO>> getStudentsEnrolled(@PathVariable Long subjectId) throws MLAServiceException {
		logger.debug("EnrollController :: getStudentsEnrolled -> START");
		List<UserDTO> userDTOs = this.enrollService.getStudentsEnrolled(subjectId);
		logger.debug("EnrollController :: getStudentsEnrolled -> END");
		return new ResponseEntity<>(userDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/disEnroll/students/{subjectId}")
	public ResponseEntity<Void> disEnrollStudents(@PathVariable Long subjectId, @RequestBody List<Long> studentIds) throws MLAServiceException {
		logger.debug("EnrollController :: disEnrollStudents -> START");
		this.enrollService.disEnrollStudents(subjectId, studentIds);
		logger.debug("EnrollController :: disEnrollStudents -> END");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/subjects/enrolled/student/{studentId}")
	public ResponseEntity<List<SubjectDTO>> getSubjectsEnrolledByStudent(@PathVariable Long studentId) throws MLAServiceException {
		logger.debug("EnrollController :: getSubjectsEnrolledByStudent -> START");
		List<SubjectDTO> subjectDTOs = this.enrollService.getSubjectsEnrolledByStudent(studentId);
		logger.debug("EnrollController :: getSubjectsEnrolledByStudent -> END");
		return new ResponseEntity<>(subjectDTOs, HttpStatus.OK);
	}
	
}
