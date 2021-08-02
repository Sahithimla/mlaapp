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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.mla.dto.SubjectDTO;
import com.app.mla.domain.Schedule;
import com.app.mla.domain.Task;
import com.app.mla.dto.ScheduleDTO;
import com.app.mla.exception.DuplicateRecordException;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.exception.ResourceNotFoundException;
import com.app.mla.service.GradeService;
import com.app.mla.service.ScheduleService;
import com.app.mla.service.TaskService;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	GradeService gradeService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/subjects/toBeSchedule/admin")
	public ResponseEntity<List<SubjectDTO>> getSubjectsToBeScheduleByAdmin()
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("ScheduleController :: getSubjectsToBeScheduleByAdmin -> START");
		List<SubjectDTO> subjectDTOs = this.scheduleService.getSubjectsToBeScheduleByAdmin();
		logger.debug("ScheduleController :: getSubjectsToBeScheduleByAdmin -> END");
		return new ResponseEntity<>(subjectDTOs, HttpStatus.OK);
	}

	@GetMapping("/subjects/toBeSchedule/instructor/{instructorId}")
	public ResponseEntity<List<SubjectDTO>> getSubjectsToBeScheduleByInstructor(@PathVariable Long instructorId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("ScheduleController :: getSubjectsToBeScheduleByInstructor -> START");
		List<SubjectDTO> subjectDTOs = this.scheduleService.getSubjectsToBeScheduleByInstructor(instructorId);
		logger.debug("ScheduleController :: getSubjectsToBeScheduleByInstructor -> END");
		return new ResponseEntity<>(subjectDTOs, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Void> addSchedule(@RequestBody ScheduleDTO scheduleDTO)
			throws DuplicateRecordException, MLAServiceException {
		logger.debug("ScheduleController :: addSchedule -> START");
		this.scheduleService.addSchedule(scheduleDTO);
		logger.debug("ScheduleController :: addSchedule -> END");
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/subjects/scheduled/admin")
	public ResponseEntity<List<SubjectDTO>> getSubjectsScheduledByAdmin()
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("ScheduleController :: getSubjectsScheduledByAdmin -> START");
		List<SubjectDTO> subjectDTOs = this.scheduleService.getSubjectsScheduledByAdmin();
		logger.debug("ScheduleController :: getSubjectsScheduledByAdmin -> END");
		return new ResponseEntity<>(subjectDTOs, HttpStatus.OK);
	}

	@GetMapping("/subjects/scheduled/instructor/{instructorId}")
	public ResponseEntity<List<SubjectDTO>> getSubjectsScheduledByInstructor(@PathVariable Long instructorId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("ScheduleController :: getSubjectsScheduledByInstructor -> START");
		List<SubjectDTO> subjectDTOs = this.scheduleService.getSubjectsScheduledByInstructor(instructorId);
		logger.debug("ScheduleController :: getSubjectsScheduledByInstructor -> END");
		return new ResponseEntity<>(subjectDTOs, HttpStatus.OK);
	}

	@GetMapping("/schedules/scheduled/admin/{subjectId}")
	public ResponseEntity<List<ScheduleDTO>> getSchedulesScheduledByAdmin(@PathVariable Long subjectId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("ScheduleController :: getSchedulesScheduledByAdmin -> START");
		List<ScheduleDTO> scheduleDTOs = this.scheduleService.getSchedulesScheduledByAdmin(subjectId);
		logger.debug("ScheduleController :: getSchedulesScheduledByAdmin -> END");
		return new ResponseEntity<>(scheduleDTOs, HttpStatus.OK);
	}

	@GetMapping("/schedules/scheduled/instructor/{subjectId}/{instructorId}")
	public ResponseEntity<List<ScheduleDTO>> getSchedulesScheduledByInstructor(@PathVariable Long subjectId,
			@PathVariable Long instructorId) throws ResourceNotFoundException, MLAServiceException {
		logger.debug("ScheduleController :: getSchedulesScheduledByInstructor -> START");
		List<ScheduleDTO> scheduleDTOs = this.scheduleService.getSchedulesScheduledByInstructor(subjectId,
				instructorId);
		logger.debug("ScheduleController :: getSchedulesScheduledByInstructor -> END");
		return new ResponseEntity<>(scheduleDTOs, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{scheduleId}")
	public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("ScheduleController :: deleteSchedule -> START");
		this.scheduleService.deleteSchedule(scheduleId);
		logger.debug("ScheduleController :: deleteSchedule -> END");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/all/{subjectId}")
	public ResponseEntity<Void> deleteSubjectAllSchedules(@PathVariable Long subjectId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("ScheduleController :: deleteSubjectAllSchedules -> START");
		
		Schedule schedule = this.scheduleService.getscheduleBySubject(subjectId);
		this.gradeService.deleteAllGradesBySubject(subjectId);
		this.taskService.deleteScheduledAllTasks(schedule.getId());
		this.scheduleService.deleteSubjectAllSchedules(subjectId);
		logger.debug("ScheduleController :: deleteSubjectAllSchedules -> END");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
