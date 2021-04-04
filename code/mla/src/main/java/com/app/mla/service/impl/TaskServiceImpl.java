package com.app.mla.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.mla.domain.Subject;
import com.app.mla.domain.Task;
import com.app.mla.dto.SubjectDTO;
import com.app.mla.dto.TaskDTO;
import com.app.mla.exception.BadRequestException;
import com.app.mla.exception.DuplicateRecordException;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.exception.ResourceNotFoundException;
import com.app.mla.repository.SubjectRepository;
import com.app.mla.repository.TaskRepository;
import com.app.mla.service.GradeService;
import com.app.mla.service.TaskService;
import com.app.mla.util.EntityMapper;

/**
 * @author Sahithi
 *
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	GradeService gradeService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public List<SubjectDTO> getSubjectsToBeScheduleByAdmin() {

		logger.debug("TaskServiceImpl :: getSubjectsToBeScheduleByAdmin -> START");
		List<SubjectDTO> subjectList = new ArrayList<>();
		List<Subject> subjects = taskRepository.findAllSubjectsToBeScheduleByAdmin();
		for (Subject subject : subjects) {
			subjectList.add(EntityMapper.getSubjectDTOMinDetails1(subject));
		}
		logger.debug("TaskServiceImpl :: getSubjectsToBeScheduleByAdmin : no of Subjects : " + subjectList.size());
		logger.debug("TaskServiceImpl :: getSubjectsToBeScheduleByAdmin -> END");
		return subjectList;
	}
	
	@Override
	public List<SubjectDTO> getSubjectsToBeScheduleByInstructor(Long instructorId) {

		logger.debug("TaskServiceImpl :: getSubjectsToBeScheduleByInstructor -> START");
		List<SubjectDTO> subjectList = new ArrayList<>();
		List<Subject> subjects = taskRepository.findSubjectsToBeScheduleByInstructor(instructorId);
		for (Subject subject : subjects) {
			subjectList.add(EntityMapper.getSubjectDTOMinDetails1(subject));
		}
		logger.debug("TaskServiceImpl :: getSubjectsToBeScheduleByInstructor : no of Subjects : " + subjectList.size());
		logger.debug("TaskServiceImpl :: getSubjectsToBeScheduleByInstructor -> END");
		return subjectList;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void addTask(TaskDTO taskDTO) throws BadRequestException, DuplicateRecordException, MLAServiceException {
		logger.debug("TaskServiceImpl :: addTask -> START");
		validateTaskDetails(taskDTO);
		Optional<Subject> subject = subjectRepository.findById(taskDTO.getSubjectId());

		if (subject != null) {
			// Add Task
			Task task = taskRepository.save(EntityMapper.getTask(taskDTO, subject));
			// Add Students Grade
			gradeService.addGrade(task);
		} else {
			logger.error("Selected Subject does not exists.");
			throw new BadRequestException("Selected Subject dosenot exists.");
		}
		logger.debug("TaskServiceImpl :: addTask -> END");
	}
	
	@Override
	public List<SubjectDTO> getSubjectsScheduledByAdmin() {

		logger.debug("TaskServiceImpl :: getSubjectsScheduledByAdmin -> START");
		List<SubjectDTO> subjectList = new ArrayList<>();
		List<Subject> subjects = taskRepository.findAllSubjectsScheduledByAdmin();
		for (Subject subject : subjects) {
			subjectList.add(EntityMapper.getSubjectDTOMinDetails1(subject));
		}
		logger.debug("TaskServiceImpl :: getSubjectsScheduledByAdmin : no of Subjects : " + subjectList.size());
		logger.debug("TaskServiceImpl :: getSubjectsScheduledByAdmin -> END");
		return subjectList;
	}
	
	@Override
	public List<SubjectDTO> getSubjectsScheduledByInstructor(Long instructorId) {

		logger.debug("TaskServiceImpl :: getSubjectsScheduledByInstructor -> START");
		List<SubjectDTO> subjectList = new ArrayList<>();
		List<Subject> subjects = taskRepository.findSubjectsScheduledByInstructor(instructorId);
		for (Subject subject : subjects) {
			subjectList.add(EntityMapper.getSubjectDTOMinDetails1(subject));
		}
		logger.debug("TaskServiceImpl :: getSubjectsScheduledByInstructor : no of Subjects : " + subjectList.size());
		logger.debug("TaskServiceImpl :: getSubjectsScheduledByInstructor -> END");
		return subjectList;
	}	

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void editTask(TaskDTO taskDTO) throws DuplicateRecordException, ResourceNotFoundException, MLAServiceException {
		
		logger.debug("TaskServiceImpl :: editTask -> START");
		Task task = taskRepository.findTaskById(taskDTO.getId());
		if (task != null) {
			//validateTaskDetails(taskDTO);
			taskRepository.save(EntityMapper.editTask(task, taskDTO));
		} else {
			logger.error("Task does not exists to edit the details");
			throw new ResourceNotFoundException("Task does not exists to edit the details");
		}
		logger.debug("TaskServiceImpl :: editTask -> END");
	}
	
	@Override
	public List<TaskDTO> getTasksScheduledByAdmin(Long subjectId) {

		logger.debug("TaskServiceImpl :: getTasksScheduledByAdmin -> START");
		List<TaskDTO> taskList = new ArrayList<>();
		List<Task> tasks = taskRepository.findAllTasksScheduledByAdmin(subjectId);
		for (Task task : tasks) {
			taskList.add(EntityMapper.getTaskDTO(task));
		}
		logger.debug("TaskServiceImpl :: getTasksScheduledByAdmin : no of Tasks : " + taskList.size());
		logger.debug("TaskServiceImpl :: getTasksScheduledByAdmin -> END");
		return taskList;
	}
	
	@Override
	public List<TaskDTO> getTasksScheduledByInstructor(Long subjectId, Long instructorId) {

		logger.debug("TaskServiceImpl :: getTasksScheduledByInstructor -> START");
		List<TaskDTO> taskList = new ArrayList<>();
		List<Task> tasks = taskRepository.findTasksScheduledByInstructor(subjectId, instructorId);
		for (Task task : tasks) {
			taskList.add(EntityMapper.getTaskDTO(task));
		}
		logger.debug("TaskServiceImpl :: getTasksScheduledByInstructor : no of Tasks : " + taskList.size());
		logger.debug("TaskServiceImpl :: getTasksScheduledByInstructor -> END");
		return taskList;
	}
	
	@Override
	public List<TaskDTO> getTasksScheduledForStudent(Long subjectId, Long studentId) {

		logger.debug("TaskServiceImpl :: getTasksScheduledForStudent -> START");
		List<TaskDTO> taskList = new ArrayList<>();
		List<Task> tasks = taskRepository.findTasksScheduledForStudent(subjectId, studentId);
		for (Task task : tasks) {
			taskList.add(EntityMapper.getTaskDTO(task));
		}
		logger.debug("TaskServiceImpl :: getTasksScheduledForStudent : no of Tasks : " + taskList.size());
		logger.debug("TaskServiceImpl :: getTasksScheduledForStudent -> END");
		return taskList;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void deleteTask(Long taskId) throws ResourceNotFoundException, MLAServiceException {
		logger.debug("TaskServiceImpl :: deleteTask -> START");
		Task task = taskRepository.findTaskById(taskId);
		if (task != null) {
			taskRepository.delete(task);
		} else {
			logger.error("Task does not exists to delete");
			throw new ResourceNotFoundException("Task does not exists to delete");
		}
		logger.debug("TaskServiceImpl :: deleteTask -> END");
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void deleteSubjectAllTasks(Long subjectId) throws MLAServiceException {
		logger.debug("TaskServiceImpl :: deleteSubjectAllTasks -> START");
		taskRepository.deleteSubjectAllTasks(subjectId);
		logger.debug("TaskServiceImpl :: deleteSubjectAllTasks -> END");
	}

	private void validateTaskDetails(TaskDTO taskDTO) throws BadRequestException {

		if (taskDTO.getSubjectId() == null) {
			logger.error("Subject is required");
			throw new BadRequestException("Subject is required");
		}
		if (taskDTO.getStartDate() == null) {
			logger.error("StartDate is required");
			throw new BadRequestException("StartDate is required");
		}
		if (taskDTO.getEndDate() == null) {
			logger.error("EndDate is required");
			throw new BadRequestException("EndDate is required");
		}
		if (StringUtils.isEmpty(taskDTO.getStartTime())) {
			logger.error("StartTime is required");
			throw new BadRequestException("StartTime is required");
		}
		if (StringUtils.isEmpty(taskDTO.getEndTime())) {
			logger.error("EndTime is required");
			throw new BadRequestException("EndTime is required");
		}
		if (StringUtils.isEmpty(taskDTO.getEvery())) {
			logger.error("Every field is required");
			throw new BadRequestException("Every field is required");
		}
	}

	@Override
	public List<TaskDTO> getProcessTasksByAdmin(Long subjectId) {

		logger.debug("TaskServiceImpl :: getProcessTasksByAdmin -> START");
		List<TaskDTO> taskList = new ArrayList<>();
		List<Task> tasks = taskRepository.findAllProcessTasksByAdmin(subjectId);
		for (Task task : tasks) {
			taskList.add(EntityMapper.getTaskDTO(task));
		}
		logger.debug("TaskServiceImpl :: getProcessTasksByAdmin : no of Tasks : " + taskList.size());
		logger.debug("TaskServiceImpl :: getProcessTasksByAdmin -> END");
		return taskList;
	}
	
	@Override
	public List<TaskDTO> getProcessTasksByInstructor(Long subjectId, Long instructorId) {

		logger.debug("TaskServiceImpl :: getProcessTasks -> START");
		List<TaskDTO> taskList = new ArrayList<>();
		List<Task> tasks = taskRepository.findProcessTasksByInstructor(subjectId, instructorId);
		for (Task task : tasks) {
			taskList.add(EntityMapper.getTaskDTO(task));
		}
		logger.debug("TaskServiceImpl :: getProcessTasks : no of Tasks : " + taskList.size());
		logger.debug("TaskServiceImpl :: getProcessTasks -> END");
		return taskList;
	}
	
	@Override
	public List<TaskDTO> getProcessTasksByStudent(Long subjectId, Long studentId) {

		logger.debug("TaskServiceImpl :: getProcessTasks -> START");
		List<TaskDTO> taskList = new ArrayList<>();
		List<Task> tasks = taskRepository.findProcessTasksByStudent(subjectId, studentId);
		for (Task task : tasks) {
			taskList.add(EntityMapper.getTaskDTO(task));
		}
		logger.debug("TaskServiceImpl :: getProcessTasks : no of Tasks : " + taskList.size());
		logger.debug("TaskServiceImpl :: getProcessTasks -> END");
		return taskList;
	}

}
