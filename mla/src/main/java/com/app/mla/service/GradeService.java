package com.app.mla.service;

import java.util.List;

import com.app.mla.domain.Task;
import com.app.mla.dto.GradeDTO;
import com.app.mla.exception.DuplicateRecordException;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.exception.ResourceNotFoundException;

/**
 * Interface for Grade operations
 * 
 * @author Sahithi
 */
public interface GradeService {

	/**
	 * Method to add Grade for the Task
	 * @param Task
	 * @return void
	 */
	void addGrade(Task task) throws DuplicateRecordException, MLAServiceException;
	
	/**
	 * Method to get All Grades for a Task
	 * @param Task
	 * @return List<GradeDTO>
	 */
	List<GradeDTO> getAllGrades(Long taskId);
	
	/**
	 * Method to Grade
	 * @param GradeDTO
	 * @return void
	 */
	void editGrade(GradeDTO gradeDTO) throws ResourceNotFoundException, MLAServiceException;
	
	/**
	 * Method to get Students All Grades
	 * @param Task
	 * @return List<GradeDTO>
	 */
	List<GradeDTO> getStudentGradeForTask(Long subjectId, Long studentId) throws MLAServiceException;
}
