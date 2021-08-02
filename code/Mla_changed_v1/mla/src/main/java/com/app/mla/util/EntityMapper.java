package com.app.mla.util;

import java.util.Optional;

import com.app.mla.domain.Grade;
import com.app.mla.domain.Subject;
import com.app.mla.domain.Schedule;
import com.app.mla.domain.Task;
import com.app.mla.domain.User;
import com.app.mla.dto.GradeDTO;
import com.app.mla.dto.SubjectDTO;
import com.app.mla.dto.ScheduleDTO;
import com.app.mla.dto.UserDTO;
import com.app.mla.dto.TaskDTO;

/**
 * @author Sahithi
 *
 */

public class EntityMapper {

	public static UserDTO getUserDTO(User user){
		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setAddress(user.getAddress());
		userDTO.setAliasMailId(user.getAliasMailId());
		userDTO.setEmailId(user.getEmailId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		//userDTO.setPassword(user.getPassword());
		userDTO.setSkypeId(user.getSkypeId());
		userDTO.setTelephone(user.getTelephone());
		userDTO.setUserName(user.getUserName());
		userDTO.setUserType(user.getUserType());
		return userDTO;
	}
	
	public static UserDTO getUserDTOForEmail(User user){
		
		UserDTO userDTO = new UserDTO();
		//userDTO.setId(user.getId());
		//userDTO.setAddress(user.getAddress());
		//userDTO.setAliasMailId(user.getAliasMailId());
		userDTO.setEmailId(user.getEmailId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		//userDTO.setPassword(user.getPassword());
		//userDTO.setSkypeId(user.getSkypeId());
		//userDTO.setTelephone(user.getTelephone());
		userDTO.setUserName(user.getUserName());
		String userType = "";
		if(user.getUserType().equalsIgnoreCase("admin")){
			userType = "admin";
		} else if(user.getUserType().equalsIgnoreCase("instructor")){
			userType = "instructor";
		} else if(user.getUserType().equalsIgnoreCase("student")){
			userType = "student";
		} else {
			userType = user.getUserType();
		}
		userDTO.setUserType(userType);
		return userDTO;
	}
	
	public static UserDTO getUserDTOEnroll(User user){
		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setUserName(user.getUserName());
		userDTO.setTelephone(user.getTelephone());
		userDTO.setEmailId(user.getEmailId());
		return userDTO;
	}
	
	public static User getUser(UserDTO userDTO){
		
		User user = new User();
		user.setId(userDTO.getId());
		user.setUserName(userDTO.getUserName());
		user.setAddress(userDTO.getAddress());
		user.setAliasMailId(userDTO.getAliasMailId());
		user.setEmailId(userDTO.getEmailId());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPassword(userDTO.getPassword());
		user.setSkypeId(userDTO.getSkypeId());
		user.setTelephone(userDTO.getTelephone());
		user.setUserName(userDTO.getUserName());
		user.setUserType(userDTO.getUserType());
		return user;
	}
	
	public static User editUser(User user, UserDTO userDTO){
		
		//user.setId(userDTO.getId());
		user.setUserName(userDTO.getUserName());
		user.setAddress(userDTO.getAddress());
		user.setAliasMailId(userDTO.getAliasMailId());
		user.setEmailId(userDTO.getEmailId());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPassword(userDTO.getPassword());
		user.setSkypeId(userDTO.getSkypeId());
		user.setTelephone(userDTO.getTelephone());
		user.setUserName(userDTO.getUserName());
		user.setUserType(userDTO.getUserType());
		return user;
	}
	
	public static UserDTO getInstructor(User user){
		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUserName(user.getUserName());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setEmailId(user.getEmailId());
		return userDTO;
	}
	
	public static SubjectDTO getSubjectDTO(Subject subject){
		
		SubjectDTO subjectDTO = new SubjectDTO();
		subjectDTO.setId(subject.getId());
		subjectDTO.setAudioEnabled(subject.getAudioEnabled());
		subjectDTO.setDescription(subject.getDescription());
		subjectDTO.setDuration(subject.getDuration());
		subjectDTO.setEndDate(subject.getEndDate());
		subjectDTO.setEndTime(subject.getEndTime());
		subjectDTO.setInstructor(getInstructor(subject.getInstructor()));
		subjectDTO.setInstructorId(subject.getInstructor().getId());
		subjectDTO.setMailingAlias(subject.getMailingAlias());
		subjectDTO.setStartDate(subject.getStartDate());
		subjectDTO.setStartTime(subject.getStartTime());
		subjectDTO.setSubjectTerm(subject.getSubjectTerm());
		subjectDTO.setSubjectType(subject.getSubjectType());
		subjectDTO.setSubjectId(subject.getSubjectType() + " " + subject.getId());
		subjectDTO.setTitle(subject.getTitle());
		subjectDTO.setVideoEnabled(subject.getVideoEnabled());
		return subjectDTO;
	}
	
	public static SubjectDTO getSubjectDTOMinDetails1(Subject subject){
		
		SubjectDTO subjectDTO = new SubjectDTO();
		subjectDTO.setId(subject.getId());
		subjectDTO.setSubjectId(subject.getSubjectType() + " " + subject.getId());
		return subjectDTO;
	}
	
	public static SubjectDTO getSubjectDTOMinDetails2(Subject subject){
		SubjectDTO subjectDTO = new SubjectDTO();
		subjectDTO.setId(subject.getId());
		subjectDTO.setSubjectId(subject.getSubjectType() + " " + subject.getId());
		subjectDTO.setTitle(subject.getTitle());
		subjectDTO.setDescription(subject.getDescription());
		return subjectDTO;
	}
	
	public static Subject getSubject(SubjectDTO subjectDTO){
		
		Subject subject = new Subject();
		subject.setId(subjectDTO.getId());
		subject.setAudioEnabled(subjectDTO.getAudioEnabled());
		subject.setDescription(subjectDTO.getDescription());
		subject.setDuration(subjectDTO.getDuration());
		subject.setEndDate(subjectDTO.getEndDate());
		subject.setEndTime(subjectDTO.getEndTime());
		subject.setInstructor(new User(subjectDTO.getInstructorId()));
		subject.setMailingAlias(subjectDTO.getMailingAlias());
		subject.setStartDate(subjectDTO.getStartDate());
		subject.setStartTime(subjectDTO.getStartTime());
		subject.setSubjectTerm(subjectDTO.getSubjectTerm());
		subject.setSubjectType(subjectDTO.getSubjectType());
		subject.setTitle(subjectDTO.getTitle());
		subject.setVideoEnabled(subjectDTO.getVideoEnabled());
		return subject;
	}
	
	public static Subject editSubject(Subject subject, SubjectDTO subjectDTO){
		
		subject.setId(subjectDTO.getId());
		subject.setAudioEnabled(subjectDTO.getAudioEnabled());
		subject.setDescription(subjectDTO.getDescription());
		subject.setDuration(subjectDTO.getDuration());
		subject.setEndDate(subjectDTO.getEndDate());
		subject.setEndTime(subjectDTO.getEndTime());
		subject.setInstructor(new User(subjectDTO.getInstructorId()));
		subject.setMailingAlias(subjectDTO.getMailingAlias());
		subject.setStartDate(subjectDTO.getStartDate());
		subject.setStartTime(subjectDTO.getStartTime());
		subject.setSubjectTerm(subjectDTO.getSubjectTerm());
		subject.setSubjectType(subjectDTO.getSubjectType());
		subject.setTitle(subjectDTO.getTitle());
		subject.setVideoEnabled(subjectDTO.getVideoEnabled());
		return subject;
	}
	
	public static Schedule getSchedule(ScheduleDTO scheduleDTO, Optional<Subject> subject) {
		
		Schedule Schedule = new Schedule();
		//Schedule.setId(id);
		Schedule.setSubject(subject.get());
		Schedule.setTopic(subject.get().getTitle());
		Schedule.setDescription(subject.get().getDescription());
		Schedule.setInstructor(subject.get().getInstructor());
		Schedule.setStartDate(scheduleDTO.getStartDate());
		Schedule.setEndDate(scheduleDTO.getEndDate());
		Schedule.setStartTime(scheduleDTO.getStartTime());
		Schedule.setEndTime(scheduleDTO.getEndTime());
		Schedule.setRepeatSchedule(scheduleDTO.getEvery());
		Schedule.setAudioEnabled(subject.get().getAudioEnabled());
		Schedule.setVideoEnabled(subject.get().getVideoEnabled());		
		return Schedule;
	}
	
	public static ScheduleDTO getScheduleDTO(Schedule Schedule) {
		
		ScheduleDTO scheduleDTO = new ScheduleDTO();		
		scheduleDTO.setId(Schedule.getId());
		scheduleDTO.setSubjectId(Schedule.getSubject().getId());
		scheduleDTO.setTopic(Schedule.getTopic());
		scheduleDTO.setDescription(Schedule.getDescription());
		scheduleDTO.setInstructorId(Schedule.getInstructor().getId());
		scheduleDTO.setInstructorUserName(Schedule.getInstructor().getUserName());
		scheduleDTO.setInstructorTelephone(Schedule.getInstructor().getTelephone());
		scheduleDTO.setStartDate(Schedule.getStartDate());
		scheduleDTO.setEndDate(Schedule.getEndDate());
		scheduleDTO.setStartTime(Schedule.getStartTime());
		scheduleDTO.setEndTime(Schedule.getEndTime());
		scheduleDTO.setEvery(Schedule.getRepeatSchedule());
		scheduleDTO.setAudioEnabled(Schedule.getAudioEnabled());
		scheduleDTO.setVideoEnabled(Schedule.getVideoEnabled());		
		return scheduleDTO;
	}
	
	public static Schedule editSchedule(Schedule Schedule, ScheduleDTO taskDTO){
		
		Schedule.setId(taskDTO.getId());
		Schedule.setTopic(taskDTO.getTopic());
		Schedule.setDescription(taskDTO.getDescription());
		Schedule.setStartDate(taskDTO.getStartDate());
		Schedule.setEndDate(taskDTO.getEndDate());
		Schedule.setStartTime(taskDTO.getStartTime());		
		Schedule.setEndTime(taskDTO.getEndTime());
		Schedule.setRepeatSchedule(taskDTO.getEvery());
		//Schedule.setSubject(new Subject(taskDTO.getSubjectId()));
		//Schedule.setInstructor(new User(taskDTO.getInstructorId()));		
		//Schedule.setAudioEnabled(taskDTO.getAudioEnabled());
		//Schedule.setVideoEnabled(taskDTO.getVideoEnabled());
		return Schedule;
	}
	
	public static Task getTask(TaskDTO taskDTO, Optional<Schedule> schedule) {
		
		Task Task = new Task();
		//Task.setId(id);
		Task.setSchedule(schedule.get());
		Task.setTopic(schedule.get().getTopic());
		Task.setDescription(schedule.get().getDescription());
		Task.setStartDate(taskDTO.getStartDate());
		Task.setEndDate(taskDTO.getEndDate());
		Task.setStartTime(taskDTO.getStartTime());
		Task.setEndTime(taskDTO.getEndTime());	
		return Task;
	}
	
	public static TaskDTO getTaskDTO(Task Task) {
		
		TaskDTO taskDTO = new TaskDTO();		
		taskDTO.setId(Task.getId());
		taskDTO.setScheduleId(Task.getSchedule().getId());
		taskDTO.setTopic(Task.getTopic());
		taskDTO.setDescription(Task.getDescription());
		taskDTO.setStartDate(Task.getStartDate());
		taskDTO.setEndDate(Task.getEndDate());
		taskDTO.setStartTime(Task.getStartTime());
		taskDTO.setEndTime(Task.getEndTime());
		return taskDTO;
	}
	
	public static Task editTask(Task Task, TaskDTO taskDTO){
		
		Task.setId(taskDTO.getId());
		Task.setTopic(taskDTO.getTopic());
		Task.setDescription(taskDTO.getDescription());
		Task.setStartDate(taskDTO.getStartDate());
		Task.setEndDate(taskDTO.getEndDate());
		Task.setStartTime(taskDTO.getStartTime());		
		Task.setEndTime(taskDTO.getEndTime());
		//Task.setSubject(new Subject(taskDTO.getSubjectId()));
		//Task.setInstructor(new User(taskDTO.getInstructorId()));		
		//Task.setAudioEnabled(taskDTO.getAudioEnabled());
		//Task.setVideoEnabled(taskDTO.getVideoEnabled());
		return Task;
	}
	
	
	public static Grade getGrade(Task task, User student) {
		
		Grade grade = new Grade();
		//grade.setId(id);
		//grade.setGrade(null);
		grade.setSubject(task.getSchedule().getSubject());
		grade.setInstructor(task.getSchedule().getSubject().getInstructor());
		grade.setStudent(student);
		grade.setTask(task);
		return grade;
	}
	
	public static GradeDTO getGradeDTO(Grade grade) {
		
		GradeDTO gradeDTO = new GradeDTO();		
		gradeDTO.setId(grade.getId());
		gradeDTO.setGrade(grade.getGrade());
		gradeDTO.setInstructor(grade.getInstructor().getUserName());
		gradeDTO.setStudent(grade.getStudent().getUserName());
		gradeDTO.setSubject(grade.getSubject().getTitle());
		gradeDTO.setTask(grade.getTask().getId());
		gradeDTO.setStartDate(grade.getTask().getStartDate());
		gradeDTO.setEndDate(grade.getTask().getEndDate());
		gradeDTO.setStartTime(grade.getTask().getStartTime());
		gradeDTO.setEndTime(grade.getTask().getEndTime());
		return gradeDTO;
	}
	
	public static Grade editGrade(Grade grade, GradeDTO gradeDTO){
		
		//grade.setId(gradeDTO.getId());
		grade.setGrade(gradeDTO.getGrade());
		return grade;
	}
	
	public static GradeDTO getStudentGradeDTO(Grade grade) {
		
		GradeDTO gradeDTO = new GradeDTO();
		gradeDTO.setId(grade.getId());
		gradeDTO.setGrade(grade.getGrade());
		gradeDTO.setStudent(grade.getStudent().getUserName());
		return gradeDTO;
	}

}
