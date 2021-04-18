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

import com.app.mla.dto.SubjectDTO;
import com.app.mla.exception.DuplicateRecordException;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.exception.ResourceNotFoundException;
import com.app.mla.service.SubjectService;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

	@Autowired
	SubjectService subjectService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@PostMapping("/add")
	public ResponseEntity<Void> addSubject(@RequestBody SubjectDTO subjectDTO)
			throws DuplicateRecordException, MLAServiceException {
		logger.debug("SubjectController :: addSubject -> START");
		this.subjectService.addSubject(subjectDTO);
		logger.debug("SubjectController :: addSubject -> END");
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/edit")
	public ResponseEntity<Void> editSubject(@RequestBody SubjectDTO subjectDTO)
			throws DuplicateRecordException, ResourceNotFoundException, MLAServiceException {
		logger.debug("SubjectController :: editSubject -> START");
		this.subjectService.editSubject(subjectDTO);
		logger.debug("SubjectController :: editSubject -> END");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{subjectId}")
	public ResponseEntity<Void> deleteSubject(@PathVariable Long subjectId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("SubjectController :: deleteUser -> START");
		this.subjectService.deleteSubject(subjectId);
		logger.debug("SubjectController :: deleteUser -> END");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/details/{subjectId}")
	public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable Long subjectId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("SubjectController :: getSubjectById -> START");
		SubjectDTO subjectDTO = this.subjectService.getSubjectById(subjectId);
		logger.debug("SubjectController :: getSubjectById -> END");
		return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
	}

	@GetMapping("/admin/all")
	public ResponseEntity<List<SubjectDTO>> getAllSubjects() throws ResourceNotFoundException, MLAServiceException {
		logger.debug("SubjectController :: getAllSubjects -> START");
		List<SubjectDTO> subjectDTOs = this.subjectService.getAllSubjects();
		logger.debug("SubjectController :: getAllSubjects -> END");
		return new ResponseEntity<>(subjectDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/instructor/all/{instructorId}")
	public ResponseEntity<List<SubjectDTO>> getSubjectsByInstructorId(@PathVariable Long instructorId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("SubjectController :: getSubjectsByInstructorId -> START");
		List<SubjectDTO> subjectDTOs = this.subjectService.getSubjectsByInstructorId(instructorId);
		logger.debug("SubjectController :: getSubjectsByInstructorId -> END");
		return new ResponseEntity<>(subjectDTOs, HttpStatus.OK);
	}

}
