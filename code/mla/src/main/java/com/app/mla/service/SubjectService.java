package com.app.mla.service;

import java.util.List;

import com.app.mla.dto.SubjectDTO;
import com.app.mla.exception.DuplicateRecordException;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.exception.ResourceNotFoundException;

/**
 * Interface for Subject operations
 * @author Sahithi
 */
public interface SubjectService {

	/**
	 * Method to Add the Subject details
	 * 
	 * @param subjectDTO
	 * @return void
	 */
	void addSubject(SubjectDTO subjectDTO) throws DuplicateRecordException, MLAServiceException;

	/**
	 * Method to Edit the Subject details
	 * 
	 * @param subjectDTO
	 * @return void
	 */
	void editSubject(SubjectDTO subjectDTO)
			throws DuplicateRecordException, ResourceNotFoundException, MLAServiceException;

	/**
	 * Method to delete the Subject details
	 * 
	 * @param subjectId
	 * @return void
	 */
	void deleteSubject(Long subjectId) throws ResourceNotFoundException, MLAServiceException;

	/**
	 * Method to get the Subject details by subjectId
	 * 
	 * @param subjectId
	 * @return SubjectDTO
	 */
	SubjectDTO getSubjectById(Long subjectId) throws ResourceNotFoundException, MLAServiceException;

	/**
	 * Method to get all Subjects
	 * 
	 * @param none
	 * @return List<SubjectDTO>
	 */
	List<SubjectDTO> getAllSubjects();
	
	/**
	 * Method to get all Subjects by User ID
	 * 
	 * @param userId
	 * @return List<SubjectDTO>
	 */
	List<SubjectDTO> getSubjectsByInstructorId(Long instructorId);

}
