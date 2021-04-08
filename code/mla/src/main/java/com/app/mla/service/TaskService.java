package com.app.mla.service;

import java.util.List;

import com.app.mla.dto.SubjectDTO;
import com.app.mla.dto.TaskDTO;
import com.app.mla.exception.DuplicateRecordException;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.exception.ResourceNotFoundException;

/**
 * Interface for Task operations
 * 
 * @author Sahithi
 */
public interface TaskService {
	
	/**
	 * Method to get Subjects To Be Schedule By Admin
	 * @param 
	 * @return List<SubjectDTO>
	 */
	List<SubjectDTO> getSubjectsToBeScheduleByAdmin();
	
	/**
	 * Method to get Subjects To Be Schedule By Instructor
	 * @param instructorId
	 * @return List<SubjectDTO>
	 */
	List<SubjectDTO> getSubjectsToBeScheduleByInstructor(Long instructorId);

	/**
	 * Method to add a Task
	 * @param TaskDTO
	 * @return void
	 */
	void addTask(TaskDTO taskDTO) throws DuplicateRecordException, MLAServiceException;

	/**
	 * Method to get Subjects Scheduled By Admin
	 * @param 
	 * @return List<SubjectDTO>
	 */
	List<SubjectDTO> getSubjectsScheduledByAdmin();
	
	/**
	 * Method to get Subjects Scheduled By Instructor
	 * @param instructorId
	 * @return List<SubjectDTO>
	 */
	List<SubjectDTO> getSubjectsScheduledByInstructor(Long instructorId);

	/**
	 * Method to edit a Task
	 * @param TaskDTO
	 * @return void
	 */
	void editTask(TaskDTO taskDTO) throws DuplicateRecordException, ResourceNotFoundException, MLAServiceException;

	/**
	 * Method to get Tasks Scheduled By Admin for a Subject
	 * @param subjectId
	 * @return List<TaskDTO>
	 */
	List<TaskDTO> getTasksScheduledByAdmin(Long subjectId);
	
	/**
	 * Method to get Tasks Scheduled By Instructor for a Subject
	 * @param subjectId, instructorId
	 * @return List<TaskDTO>
	 */
	List<TaskDTO> getTasksScheduledByInstructor(Long subjectId, Long instructorId);
	
	/**
	 * Method to get Tasks Scheduled To Student for a Subject
	 * @param subjectId, studentId
	 * @return List<TaskDTO>
	 */
	List<TaskDTO> getTasksScheduledForStudent(Long subjectId, Long studentId);

	/**
	 * Method to delete a Task
	 * @param taskId
	 * @return void
	 */
	void deleteTask(Long taskId) throws ResourceNotFoundException, MLAServiceException;

	/**
	 * Method to delete Subject All Tasks
	 * @param subjectId
	 * @return void
	 */
	void deleteSubjectAllTasks(Long subjectId) throws ResourceNotFoundException, MLAServiceException;
	
	/**
	 * Method to get Process Tasks By Admin
	 * @param subjectId
	 * @return List<TaskDTO>
	 */
	List<TaskDTO> getProcessTasksByAdmin(Long subjectId);
	
	/**
	 * Method to get Process Tasks By Instructor
	 * @param subjectId, instructorId
	 * @return List<TaskDTO>
	 */
	List<TaskDTO> getProcessTasksByInstructor(Long subjectId, Long instructorId);
	
	/**
	 * Method to get Process Tasks By Student
	 * @param subjectId, studentId
	 * @return List<TaskDTO>
	 */
	List<TaskDTO> getProcessTasksByStudent(Long subjectId, Long studentId);
	
}
