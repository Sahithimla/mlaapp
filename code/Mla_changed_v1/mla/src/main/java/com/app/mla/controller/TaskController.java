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

import com.app.mla.dto.GradeDTO;
import com.app.mla.dto.TaskDTO;
import com.app.mla.dto.UserDTO;
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

	
	@PostMapping("/add")
	public ResponseEntity<Void> addTask(@RequestBody TaskDTO taskDTO)
			throws DuplicateRecordException, MLAServiceException {
		logger.debug("TaskController :: addTask -> START");
		this.taskService.addTask(taskDTO);
		logger.debug("TaskController :: addTask -> END");
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Void> editTask(@RequestBody TaskDTO taskDTO)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskController :: editTask -> START");
		this.taskService.editTask(taskDTO);
		logger.debug("TaskController :: editTask -> END");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/tasks/task/student/{subjectId}/{studentId}")
	public ResponseEntity<List<TaskDTO>> getTasksTaskdforStudent(@PathVariable Long subjectId, @PathVariable Long studentId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskController :: getTasksTaskdforStudent -> START");
		List<TaskDTO> taskDTOs = this.taskService.getTasksTaskdForStudent(subjectId, studentId);
		logger.debug("TaskController :: getTasksTaskdforStudent -> END");
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
	
	
	@GetMapping("/processTasks/admin/{subjectId}")
	public ResponseEntity<List<TaskDTO>> getProcessTasksByAdmin(@PathVariable Long subjectId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskController :: getProcessTasksByAdmin -> START");
		List<TaskDTO> taskDTOs = this.taskService.getProcessTasksByAdmin(subjectId);
		logger.debug("TaskController :: getProcessTasksByAdmin -> END");
		return new ResponseEntity<>(taskDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/getTasksBySubject/{subjectId}")
	public ResponseEntity<List<TaskDTO>> getTasksBySubject(@PathVariable Long subjectId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskController :: getTasksBySubject -> START");
		List<TaskDTO> taskDTOs = this.taskService.getTasksBySubject(subjectId);
		logger.debug("TaskController :: getTasksBySubject -> END");
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
	
	@GetMapping("/processTasks/getTasksByUser")
	public ResponseEntity<List<TaskDTO>> getTasksByUser(@RequestParam String userName, @RequestParam String userType)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskController :: getTasksByUser -> START");
		List<TaskDTO> taskDTOs = this.taskService.getTasksByUser(userName, userType);
		logger.debug("TaskController :: getTasksByStudent -> END");
		return new ResponseEntity<>(taskDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/getStudentsByTask/{taskId}")
	public ResponseEntity<List<GradeDTO>> getStudentsByTask(@PathVariable Long taskId)
			throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskController :: getStudentsByTask -> START");
		List<GradeDTO> gradeDTOs = this.taskService.getStudentsByTask(taskId);
		logger.debug("TaskController :: getStudentsByTask -> END");
		return new ResponseEntity<>(gradeDTOs, HttpStatus.OK);
	}
	
}
