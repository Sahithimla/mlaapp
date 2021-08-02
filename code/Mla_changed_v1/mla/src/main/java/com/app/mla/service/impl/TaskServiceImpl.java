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

import com.app.mla.domain.Grade;
import com.app.mla.domain.Schedule;
import com.app.mla.domain.Task;
import com.app.mla.domain.User;
import com.app.mla.dto.GradeDTO;
import com.app.mla.dto.ScheduleDTO;
import com.app.mla.dto.TaskDTO;
import com.app.mla.dto.UserDTO;
import com.app.mla.exception.BadRequestException;
import com.app.mla.exception.DuplicateRecordException;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.exception.ResourceNotFoundException;
import com.app.mla.repository.ScheduleRepository;
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
	ScheduleRepository scheduleRepository;
	
	@Autowired
	GradeService gradeService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void addTask(TaskDTO taskDTO) throws BadRequestException, DuplicateRecordException, MLAServiceException {
		logger.debug("TaskServiceImpl :: addTask -> START");
		validateTaskDetails(taskDTO);
		Optional<Schedule> schedule = scheduleRepository.findById(taskDTO.getScheduleId());

		if (schedule != null) {
			// Add Task
			Task task = taskRepository.save(EntityMapper.getTask(taskDTO, schedule));
			// Add Students Grade
			gradeService.addGrade(task);
		} else {
			logger.error("Selected Subject does not exists.");
			throw new BadRequestException("Selected Subject dosenot exists.");
		}
		logger.debug("TaskServiceImpl :: addTask -> END");
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void editTask(TaskDTO taskDTO) throws DuplicateRecordException, ResourceNotFoundException, MLAServiceException {
		
		logger.debug("TaskServiceImpl :: editTask -> START");
		Task task = taskRepository.findById(taskDTO.getId()).get();
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
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public List<TaskDTO> getTasksTaskdForStudent(Long subjectId, Long studentId) {

		logger.debug("TaskServiceImpl :: getTasksTaskdForStudent -> START");
		List<TaskDTO> taskList = new ArrayList<>();
		List<Task> tasks = taskRepository.findTasksTaskdForStudent(subjectId, studentId);
		for (Task task : tasks) {
			taskList.add(EntityMapper.getTaskDTO(task));
		}
		logger.debug("TaskServiceImpl :: getTasksTaskdForStudent : no of Tasks : " + taskList.size());
		logger.debug("TaskServiceImpl :: getTasksTaskdForStudent -> END");
		return taskList;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void deleteTask(Long taskId) throws ResourceNotFoundException, MLAServiceException {
		
		logger.debug("TaskServiceImpl :: deleteTask -> START");
		Task task = taskRepository.findById(taskId).get();
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
	public void deleteScheduledAllTasks(Long scheduleId) throws MLAServiceException {
		
		logger.debug("TaskServiceImpl :: deleteScheduleAllTasks -> START");
		taskRepository.deleteScheduledAllTasks(scheduleId);
		int a=5,b=0;
		System.out.println(a / b); // throw Exception
		logger.debug("TaskServiceImpl :: deleteScheduleAllTasks -> END");
	}
	

	private void validateTaskDetails(TaskDTO taskDTO) throws BadRequestException {

		if (taskDTO.getScheduleId() == null) {
			logger.error("Schedule is required");
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
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
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
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public List<TaskDTO> getTasksBySubject(Long subjectId) {

		logger.debug("TaskServiceImpl :: getTasksBySubject -> START");
		List<TaskDTO> taskList = new ArrayList<>();
		List<Task> tasks = taskRepository.findTasksBySubject(subjectId);
		for (Task task : tasks) {
			taskList.add(EntityMapper.getTaskDTO(task));
		}
		logger.debug("TaskServiceImpl :: getTasksBySubject : no of Tasks : " + taskList.size());
		logger.debug("TaskServiceImpl :: getTasksBySubject -> END");
		return taskList;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
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
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
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
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public List<TaskDTO> getTasksByUser(String userName, String userType) {

		logger.debug("TaskServiceImpl :: getProcessTasks -> START");
		List<TaskDTO> taskList = new ArrayList<>();
		List<Task> tasks;
		if(userType.toUpperCase() == "STUDENT")
			tasks = taskRepository.findProcessTasksByStudentUser(userName);
		else
			tasks = taskRepository.findProcessTasksByNonStudentUser(userName);
		
		for (Task task : tasks) {
			taskList.add(EntityMapper.getTaskDTO(task));
		}
		logger.debug("TaskServiceImpl :: getProcessTasks : no of Tasks : " + taskList.size());
		logger.debug("TaskServiceImpl :: getProcessTasks -> END");
		return taskList;
	}
	
	 @Override
	 @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
		public List<GradeDTO> getStudentsByTask(Long taskId) {

			logger.debug("TaskServiceImpl :: getStudentsByTask -> START");
			List<GradeDTO> gradeList = new ArrayList<>();
			List<Grade> grades = taskRepository.findStudentsByTask(taskId);
			for (Grade grade : grades) {
				gradeList.add(EntityMapper.getGradeDTO(grade));
			}
			logger.debug("TaskServiceImpl :: getProcessTasks : no of Tasks : " + gradeList.size());
			logger.debug("TaskServiceImpl :: getProcessTasks -> END");
			return gradeList;
		}

}
