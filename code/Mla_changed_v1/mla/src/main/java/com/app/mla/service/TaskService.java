package com.app.mla.service;

import java.util.List;

import com.app.mla.domain.Grade;
import com.app.mla.dto.GradeDTO;
import com.app.mla.dto.ScheduleDTO;
import com.app.mla.dto.TaskDTO;
import com.app.mla.dto.UserDTO;
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
	 * Method to add a Task
	 * @param TaskDTO
	 * @return void
	 */
	void addTask(TaskDTO taskDTO) throws DuplicateRecordException, MLAServiceException;

	/**
	 * Method to edit a Task
	 * @param TaskDTO
	 * @return void
	 */
	void editTask(TaskDTO taskDTO) throws DuplicateRecordException, ResourceNotFoundException, MLAServiceException;

	/**
	 * Method to get Tasks Taskd By Admin for a Schedule
	 * @param scheduleId
	 * @return List<TaskDTO>
	 */

	List<TaskDTO> getTasksTaskdForStudent(Long scheduleId, Long studentId);

	/**
	 * Method to delete a Task
	 * @param taskId
	 * @return void
	 */
	void deleteTask(Long taskId) throws ResourceNotFoundException, MLAServiceException;

	/**
	 * Method to delete Schedule All Tasks
	 * @param scheduleId
	 * @return void
	 */
	void deleteScheduledAllTasks(Long scheduleId) throws ResourceNotFoundException, MLAServiceException;
	
	/**
	 * Method to get Process Tasks By Admin
	 * @param scheduleId
	 * @return List<TaskDTO>
	 */
	List<TaskDTO> getProcessTasksByAdmin(Long scheduleId);
	
	List<TaskDTO> getTasksBySubject(Long subjectId);
	
	/**
	 * Method to get Process Tasks By Instructor
	 * @param scheduleId, instructorId
	 * @return List<TaskDTO>
	 */
	List<TaskDTO> getProcessTasksByInstructor(Long scheduleId, Long instructorId);
	
	/**
	 * Method to get Process Tasks By Student
	 * @param scheduleId, studentId
	 * @return List<TaskDTO>
	 */
	List<TaskDTO> getProcessTasksByStudent(Long scheduleId, Long studentId);
	
	/**
	 * Method to get Tasks by userName and userType
	 * @param userName, userType
	 * @return List<TaskDTO>
	 */
	List<TaskDTO> getTasksByUser(String userName, String userType);
	
	List<GradeDTO> getStudentsByTask(Long taskId);
	
}
