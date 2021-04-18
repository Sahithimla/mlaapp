package com.app.mla.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.mla.dto.GradeDTO;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.exception.ResourceNotFoundException;
import com.app.mla.service.GradeService;

@RestController
@RequestMapping("/api/grade")
public class GradeController {

	@Autowired
	GradeService gradeService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/all/{taskId}")
	public ResponseEntity<List<GradeDTO>> getAllGrades(@PathVariable Long taskId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("GradeController :: getAllGrades -> START");
		List<GradeDTO> gradeDTOs = this.gradeService.getAllGrades(taskId);
		logger.debug("GradeController :: getAllGrades -> END");
		return new ResponseEntity<>(gradeDTOs, HttpStatus.OK);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Void> editTask(@RequestBody GradeDTO gradeDTO)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("GradeController :: editTask -> START");
		this.gradeService.editGrade(gradeDTO);
		logger.debug("GradeController :: editTask -> END");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/student/{subjectId}/{studentId}")
	public ResponseEntity<List<GradeDTO>> getStudentGradeForTask(@PathVariable Long subjectId, @PathVariable Long studentId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("GradeController :: getStudentGradeForTask -> START");
		List<GradeDTO> gradeDTOs = this.gradeService.getStudentGradeForTask(subjectId, studentId);
		logger.debug("GradeController :: getStudentGradeForTask -> END");
		return new ResponseEntity<>(gradeDTOs, HttpStatus.OK);
	}
	
}
