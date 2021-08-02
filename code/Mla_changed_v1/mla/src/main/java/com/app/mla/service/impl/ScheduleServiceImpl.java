package com.app.mla.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Date;
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
import com.app.mla.domain.Schedule;
import com.app.mla.domain.Task;
import com.app.mla.dto.SubjectDTO;
import com.app.mla.dto.TaskDTO;
import com.app.mla.dto.ScheduleDTO;
import com.app.mla.exception.BadRequestException;
import com.app.mla.exception.DuplicateRecordException;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.exception.ResourceNotFoundException;
import com.app.mla.repository.SubjectRepository;
import com.app.mla.repository.ScheduleRepository;
import com.app.mla.service.TaskService;
import com.app.mla.service.ScheduleService;
import com.app.mla.util.EntityMapper;

/**
 * @author Sahithi
 *
 */
@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	ScheduleRepository scheduleRepository;

	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	TaskService taskService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public List<SubjectDTO> getSubjectsToBeScheduleByAdmin() {

		logger.debug("ScheduleServiceImpl :: getSubjectsToBeScheduleByAdmin -> START");
		List<SubjectDTO> subjectList = new ArrayList<>();
		List<Subject> subjects = scheduleRepository.findAllSubjectsToBeScheduleByAdmin();
		for (Subject subject : subjects) {
			subjectList.add(EntityMapper.getSubjectDTO(subject));
		}
		logger.debug("ScheduleServiceImpl :: getSubjectsToBeScheduleByAdmin : no of Subjects : " + subjectList.size());
		logger.debug("ScheduleServiceImpl :: getSubjectsToBeScheduleByAdmin -> END");
		return subjectList;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public List<SubjectDTO> getSubjectsToBeScheduleByInstructor(Long instructorId) {

		logger.debug("ScheduleServiceImpl :: getSubjectsToBeScheduleByInstructor -> START");
		List<SubjectDTO> subjectList = new ArrayList<>();
		List<Subject> subjects = scheduleRepository.findSubjectsToBeScheduleByInstructor(instructorId);
		for (Subject subject : subjects) {
			subjectList.add(EntityMapper.getSubjectDTO(subject));
		}
		logger.debug("ScheduleServiceImpl :: getSubjectsToBeScheduleByInstructor : no of Subjects : " + subjectList.size());
		logger.debug("ScheduleServiceImpl :: getSubjectsToBeScheduleByInstructor -> END");
		return subjectList;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void addSchedule(ScheduleDTO scheduleDTO) throws BadRequestException, DuplicateRecordException, MLAServiceException {
		logger.debug("ScheduleServiceImpl :: addSchedule -> START");
		validateScheduleDetails(scheduleDTO);
		Optional<Subject> subject = subjectRepository.findById(scheduleDTO.getSubjectId());

		if (subject != null) {
			// Add Schedule
			Schedule schedule = scheduleRepository.save(EntityMapper.getSchedule(scheduleDTO, subject));
			
			// Add Scheduled Tasks

			Date startDate = schedule.getStartDate();
			Date currentDate = schedule.getStartDate();
			Date endDate = schedule.getEndDate();
			
			String[] days = schedule.getRepeatSchedule().toLowerCase().split(" ");
			
			Integer[] days_ints = new Integer[days.length];
			for(int i=0; i<days.length; i++) {
				switch(days[i]) {
				case "monday":
					days_ints[i] = 1;
					break;
				case "tuesday":
					days_ints[i] = 2;
					break;
				case "wednesday":
					days_ints[i] = 3;
					break;
				case "thursday":
					days_ints[i] = 4;
					break;
				case "friday":
					days_ints[i] = 5;
					break;
				case "saturday":
					days_ints[i] = 6;
					break;
				case "sunday":
					days_ints[i] = 0;
					break;
				}
			}
			
			Task task = new Task();
			while(currentDate.before(endDate)) {
				currentDate = new Date(currentDate.getTime() + (1000 * 60 * 60 * 24));
				System.out.println(startDate.toString());
				System.out.println(currentDate.toString());
				System.out.println(currentDate.getDay());
				System.out.println(days[0]);
				System.out.println(days_ints[0]);
				
				if(Arrays.asList(days_ints).contains(currentDate.getDay())) {
					task.setSchedule(schedule);
					task.setTopic("");
					task.setDescription("");
					task.setStartDate(currentDate);
					task.setEndDate(currentDate);
					task.setStartTime(schedule.getStartTime());
					task.setEndTime(schedule.getEndTime());
					taskService.addTask(EntityMapper.getTaskDTO(task));
				}
			}
			
		} else {
			logger.error("Selected Subject does not exists.");
			throw new BadRequestException("Selected Subject dosenot exists.");
		}
		logger.debug("ScheduleServiceImpl :: addSchedule -> END");
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public List<SubjectDTO> getSubjectsScheduledByAdmin() {

		logger.debug("ScheduleServiceImpl :: getSubjectsScheduledByAdmin -> START");
		List<SubjectDTO> subjectList = new ArrayList<>();
		List<Subject> subjects = scheduleRepository.findAllSubjectsScheduledByAdmin();
		for (Subject subject : subjects) {
			subjectList.add(EntityMapper.getSubjectDTO(subject));
		}
		logger.debug("ScheduleServiceImpl :: getSubjectsScheduledByAdmin : no of Subjects : " + subjectList.size());
		logger.debug("ScheduleServiceImpl :: getSubjectsScheduledByAdmin -> END");
		return subjectList;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public List<SubjectDTO> getSubjectsScheduledByInstructor(Long instructorId) {

		logger.debug("ScheduleServiceImpl :: getSubjectsScheduledByInstructor -> START");
		List<SubjectDTO> subjectList = new ArrayList<>();
		List<Subject> subjects = scheduleRepository.findSubjectsScheduledByInstructor(instructorId);
		for (Subject subject : subjects) {
			subjectList.add(EntityMapper.getSubjectDTO(subject));
		}
		logger.debug("ScheduleServiceImpl :: getSubjectsScheduledByInstructor : no of Subjects : " + subjectList.size());
		logger.debug("ScheduleServiceImpl :: getSubjectsScheduledByInstructor -> END");
		return subjectList;
	}	

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public List<ScheduleDTO> getSchedulesScheduledByAdmin(Long subjectId) {

		logger.debug("ScheduleServiceImpl :: getSchedulesScheduledByAdmin -> START");
		List<ScheduleDTO> scheduleList = new ArrayList<>();
		List<Schedule> schedules = scheduleRepository.findAllSchedulesScheduledByAdmin(subjectId);
		for (Schedule schedule : schedules) {
			scheduleList.add(EntityMapper.getScheduleDTO(schedule));
		}
		logger.debug("ScheduleServiceImpl :: getSchedulesScheduledByAdmin : no of Schedules : " + scheduleList.size());
		logger.debug("ScheduleServiceImpl :: getSchedulesScheduledByAdmin -> END");
		return scheduleList;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public List<ScheduleDTO> getSchedulesScheduledByInstructor(Long subjectId, Long instructorId) {

		logger.debug("ScheduleServiceImpl :: getSchedulesScheduledByInstructor -> START");
		List<ScheduleDTO> scheduleList = new ArrayList<>();
		List<Schedule> schedules = scheduleRepository.findSchedulesScheduledByInstructor(subjectId, instructorId);
		for (Schedule schedule : schedules) {
			scheduleList.add(EntityMapper.getScheduleDTO(schedule));
		}
		logger.debug("ScheduleServiceImpl :: getSchedulesScheduledByInstructor : no of Schedules : " + scheduleList.size());
		logger.debug("ScheduleServiceImpl :: getSchedulesScheduledByInstructor -> END");
		return scheduleList;
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void deleteSchedule(Long scheduleId) throws ResourceNotFoundException, MLAServiceException {
		logger.debug("ScheduleServiceImpl :: deleteSchedule -> START");
		Schedule schedule = scheduleRepository.findById(scheduleId).get();
		if (schedule != null) {
			
			taskService.deleteScheduledAllTasks(scheduleId);
			scheduleRepository.delete(schedule);
		} else {
			logger.error("Schedule does not exists to delete");
			throw new ResourceNotFoundException("Schedule does not exists to delete");
		}
		logger.debug("ScheduleServiceImpl :: deleteSchedule -> END");
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public Schedule getscheduleBySubject(Long subjectId) {
		logger.debug("ScheduleServiceImpl :: getscheduleBySubject -> START");
		Schedule schedule = scheduleRepository.findScheduleBySubject(subjectId).get(0);
		if (schedule != null) {
			
			return schedule;
		} else {
			logger.error("Schedule does not exists to delete");
			throw new ResourceNotFoundException("Schedule does not exists to delete");
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void deleteSubjectAllSchedules(Long subjectId) throws MLAServiceException {
		logger.debug("ScheduleServiceImpl :: deleteSubjectAllSchedules --> START");
		scheduleRepository.deleteSubjectAllSchedules(subjectId);
		logger.debug("ScheduleServiceImpl :: deleteSubjectAllSchedules -> END");
	}

	private void validateScheduleDetails(ScheduleDTO scheduleDTO) throws BadRequestException {

		if (scheduleDTO.getSubjectId() == null) {
			logger.error("Subject is required");
			throw new BadRequestException("Subject is required");
		}
		if (scheduleDTO.getStartDate() == null) {
			logger.error("StartDate is required");
			throw new BadRequestException("StartDate is required");
		}
		if (scheduleDTO.getEndDate() == null) {
			logger.error("EndDate is required");
			throw new BadRequestException("EndDate is required");
		}
		if (StringUtils.isEmpty(scheduleDTO.getStartTime())) {
			logger.error("StartTime is required");
			throw new BadRequestException("StartTime is required");
		}
		if (StringUtils.isEmpty(scheduleDTO.getEndTime())) {
			logger.error("EndTime is required");
			throw new BadRequestException("EndTime is required");
		}
		if (StringUtils.isEmpty(scheduleDTO.getEvery())) {
			logger.error("Every field is required");
			throw new BadRequestException("Every field is required");
		}
	}
	

}
