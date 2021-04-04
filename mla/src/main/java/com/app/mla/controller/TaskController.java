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
import com.app.mla.dto.TaskDTO;
import com.app.mla.exception.DuplicateRecordException;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.exception.ResourceNotFoundException;
import com.app.mla.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

	@Autowired
	TaskService taskService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/subjects/toBeSchedule/admin")
	public ResponseEntity<List<SubjectDTO>> getSubjectsToBeScheduleByAdmin()
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskController :: getSubjectsToBeScheduleByAdmin -> START");
		List<SubjectDTO> subjectDTOs = this.taskService.getSubjectsToBeScheduleByAdmin();
		logger.debug("TaskController :: getSubjectsToBeScheduleByAdmin -> END");
		return new ResponseEntity<>(subjectDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/subjects/toBeSchedule/instructor/{instructorId}")
	public ResponseEntity<List<SubjectDTO>> getSubjectsToBeScheduleByInstructor(@PathVariable Long instructorId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskController :: getSubjectsToBeScheduleByInstructor -> START");
		List<SubjectDTO> subjectDTOs = this.taskService.getSubjectsToBeScheduleByInstructor(instructorId);
		logger.debug("TaskController :: getSubjectsToBeScheduleByInstructor -> END");
		return new ResponseEntity<>(subjectDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Void> addTask(@RequestBody TaskDTO taskDTO)
			throws DuplicateRecordException, MLAServiceException {
		logger.debug("TaskController :: addTask -> START");
		this.taskService.addTask(taskDTO);
		logger.debug("TaskController :: addTask -> END");
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/subjects/scheduled/admin")
	public ResponseEntity<List<SubjectDTO>> getSubjectsScheduledByAdmin()
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskController :: getSubjectsScheduledByAdmin -> START");
		List<SubjectDTO> subjectDTOs = this.taskService.getSubjectsScheduledByAdmin();
		logger.debug("TaskController :: getSubjectsScheduledByAdmin -> END");
		return new ResponseEntity<>(subjectDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/subjects/scheduled/instructor/{instructorId}")
	public ResponseEntity<List<SubjectDTO>> getSubjectsScheduledByInstructor(@PathVariable Long instructorId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskController :: getSubjectsScheduledByInstructor -> START");
		List<SubjectDTO> subjectDTOs = this.taskService.getSubjectsScheduledByInstructor(instructorId);
		logger.debug("TaskController :: getSubjectsScheduledByInstructor -> END");
		return new ResponseEntity<>(subjectDTOs, HttpStatus.OK);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Void> editTask(@RequestBody TaskDTO taskDTO)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskController :: editTask -> START");
		this.taskService.editTask(taskDTO);
		logger.debug("TaskController :: editTask -> END");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/tasks/scheduled/admin/{subjectId}")
	public ResponseEntity<List<TaskDTO>> getTasksScheduledByAdmin(@PathVariable Long subjectId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskController :: getTasksScheduledByAdmin -> START");
		List<TaskDTO> taskDTOs = this.taskService.getTasksScheduledByAdmin(subjectId);
		logger.debug("TaskController :: getTasksScheduledByAdmin -> END");
		return new ResponseEntity<>(taskDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/tasks/scheduled/instructor/{subjectId}/{instructorId}")
	public ResponseEntity<List<TaskDTO>> getTasksScheduledByInstructor(@PathVariable Long subjectId, @PathVariable Long instructorId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskController :: getTasksScheduledByInstructor -> START");
		List<TaskDTO> taskDTOs = this.taskService.getTasksScheduledByInstructor(subjectId, instructorId);
		logger.debug("TaskController :: getTasksScheduledByInstructor -> END");
		return new ResponseEntity<>(taskDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/tasks/scheduled/student/{subjectId}/{studentId}")
	public ResponseEntity<List<TaskDTO>> getTasksScheduledforStudent(@PathVariable Long subjectId, @PathVariable Long studentId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskController :: getTasksScheduledforStudent -> START");
		List<TaskDTO> taskDTOs = this.taskService.getTasksScheduledForStudent(subjectId, studentId);
		logger.debug("TaskController :: getTasksScheduledforStudent -> END");
		return new ResponseEntity<>(taskDTOs, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{taskId}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long taskId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskController :: deleteTask -> START");
		this.taskService.deleteTask(taskId);
		logger.debug("TaskController :: deleteTask -> END");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/all/{subjectId}")
	public ResponseEntity<Void> deleteSubjectAllTasks(@PathVariable Long subjectId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskController :: deleteSubjectAllTasks -> START");
		this.taskService.deleteSubjectAllTasks(subjectId);
		logger.debug("TaskController :: deleteSubjectAllTasks -> END");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/processTasks/admin/{subjectId}")
	public ResponseEntity<List<TaskDTO>> getProcessTasksByAdmin(@PathVariable Long subjectId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskController :: getProcessTasksByAdmin -> START");
		List<TaskDTO> taskDTOs = this.taskService.getProcessTasksByAdmin(subjectId);
		logger.debug("TaskController :: getProcessTasksByAdmin -> END");
		return new ResponseEntity<>(taskDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/processTasks/instructor/{subjectId}/{instructorId}")
	public ResponseEntity<List<TaskDTO>> getProcessTasksByInstructor(@PathVariable Long subjectId, @PathVariable Long instructorId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskController :: getProcessTasksByInstructor -> START");
		List<TaskDTO> taskDTOs = this.taskService.getProcessTasksByInstructor(subjectId, instructorId);
		logger.debug("TaskController :: getProcessTasksByInstructor -> END");
		return new ResponseEntity<>(taskDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/processTasks/student/{subjectId}/{studentId}")
	public ResponseEntity<List<TaskDTO>> getProcessTasksByStudent(@PathVariable Long subjectId, @PathVariable Long studentId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskController :: getProcessTasksByStudent -> START");
		List<TaskDTO> taskDTOs = this.taskService.getProcessTasksByStudent(subjectId, studentId);
		logger.debug("TaskController :: getProcessTasksByStudent -> END");
		return new ResponseEntity<>(taskDTOs, HttpStatus.OK);
	}
	
}
