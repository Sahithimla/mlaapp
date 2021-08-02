package com.app.mla.service;

import java.util.List;

import com.app.mla.dto.SubjectDTO;
import com.app.mla.dto.UserDTO;
import com.app.mla.exception.MLAServiceException;

/**
 * Interface for Enroll operations
 * @author Sahithi
 */
public interface EnrollService {

	/**
	 * Method to get all Subjects
	 * @param 
	 * @return List<SubjectDTO>
	 */
	List<SubjectDTO> getAllSubjects() throws MLAServiceException;

	/**
	 * Method to get Students to be Enroll
	 * @param subjectId
	 * @return List<UserDTO>
	 */
	List<UserDTO> getStudentsToEnroll(Long subjectId) throws MLAServiceException;
	
	/**
	 * Method to Enroll Students to a Subject
	 * @param subjectId, studentIds
	 * @return void
	 */
	void enrollStudents(Long subjectId, List<Long> studentIds) throws MLAServiceException;
	
	/**
	 * Method to get Enrolled Students
	 * @param subjectId
	 * @return List<UserDTO>
	 */
	List<UserDTO> getStudentsEnrolled(Long subjectId) throws MLAServiceException;

	/**
	 * Method to DisEnroll Students to a Subject
	 * @param subjectId, studentIds
	 * @return void
	 */
	void disEnrollStudents(Long subjectId, List<Long> studentIds) throws MLAServiceException;
	
	/**
	 * Method to get Students Enrolled Subjects
	 * @param studentId
	 * @return List<SubjectDTO>
	 */
	List<SubjectDTO> getSubjectsEnrolledByStudent(Long studentId) throws MLAServiceException;
	
}
