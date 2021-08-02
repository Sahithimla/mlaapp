package com.app.mla.service;

import java.util.List;

import com.app.mla.dto.SubjectDTO;
import com.app.mla.domain.Schedule;
import com.app.mla.dto.ScheduleDTO;
import com.app.mla.exception.DuplicateRecordException;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.exception.ResourceNotFoundException;

/**
 * Interface for Schedule operations
 * 
 * @author Sahithi
 */
public interface ScheduleService {
	
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
	 * Method to add a Schedule
	 * @param ScheduleDTO
	 * @return void
	 */
	void addSchedule(ScheduleDTO scheduleDTO) throws DuplicateRecordException, MLAServiceException;

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
	 * Method to get Schedules Scheduled By Admin for a Subject
	 * @param subjectId
	 * @return List<ScheduleDTO>
	 */
	List<ScheduleDTO> getSchedulesScheduledByAdmin(Long subjectId);
	
	/**
	 * Method to get Schedules Scheduled By Instructor for a Subject
	 * @param subjectId, instructorId
	 * @return List<ScheduleDTO>
	 */
	List<ScheduleDTO> getSchedulesScheduledByInstructor(Long subjectId, Long instructorId);

	/**
	 * Method to delete a Schedule
	 * @param scheduleId
	 * @return void
	 */
	void deleteSchedule(Long scheduleId) throws ResourceNotFoundException, MLAServiceException;

	/**
	 * Method to delete Subject All Schedules
	 * @param subjectId
	 * @return void
	 */
	void deleteSubjectAllSchedules(Long subjectId) throws ResourceNotFoundException, MLAServiceException;

	Schedule getscheduleBySubject(Long subjectId);

}
