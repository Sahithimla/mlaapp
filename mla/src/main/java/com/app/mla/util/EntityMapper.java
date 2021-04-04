package com.app.mla.util;

import java.util.Optional;

import com.app.mla.domain.Grade;
import com.app.mla.domain.Subject;
import com.app.mla.domain.Task;
import com.app.mla.domain.User;
import com.app.mla.dto.GradeDTO;
import com.app.mla.dto.SubjectDTO;
import com.app.mla.dto.TaskDTO;
import com.app.mla.dto.UserDTO;

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
		if(user.getUserType().equalsIgnoreCase("ADMIN")){
			userType = "Admin";
		} else if(user.getUserType().equalsIgnoreCase("INSTRUCTOR")){
			userType = "Instructor";
		} else if(user.getUserType().equalsIgnoreCase("STUDENT")){
			userType = "Student";
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
	
	public static Task getTask(TaskDTO taskDTO, Optional<Subject> subject) {
		
		Task task = new Task();
		//task.setId(id);
		task.setSubject(subject.get());
		task.setTopic(subject.get().getTitle());
		task.setDescription(subject.get().getDescription());
		task.setInstructor(subject.get().getInstructor());
		task.setStartDate(taskDTO.getStartDate());
		task.setEndDate(taskDTO.getEndDate());
		task.setStartTime(taskDTO.getStartTime());
		task.setEndTime(taskDTO.getEndTime());
		task.setRepeatTask(taskDTO.getEvery());
		task.setAudioEnabled(subject.get().getAudioEnabled());
		task.setVideoEnabled(subject.get().getVideoEnabled());		
		return task;
	}
	
	public static TaskDTO getTaskDTO(Task task) {
		
		TaskDTO taskDTO = new TaskDTO();		
		taskDTO.setId(task.getId());
		taskDTO.setSubjectId(task.getSubject().getId());
		taskDTO.setTopic(task.getTopic());
		taskDTO.setDescription(task.getDescription());
		taskDTO.setInstructorId(task.getInstructor().getId());
		taskDTO.setInstructorUserName(task.getInstructor().getUserName());
		taskDTO.setInstructorTelephone(task.getInstructor().getTelephone());
		taskDTO.setStartDate(task.getStartDate());
		taskDTO.setEndDate(task.getEndDate());
		taskDTO.setStartTime(task.getStartTime());
		taskDTO.setEndTime(task.getEndTime());
		taskDTO.setEvery(task.getRepeatTask());
		taskDTO.setAudioEnabled(task.getAudioEnabled());
		taskDTO.setVideoEnabled(task.getVideoEnabled());		
		return taskDTO;
	}
	
	public static Task editTask(Task task, TaskDTO taskDTO){
		
		task.setId(taskDTO.getId());
		task.setTopic(taskDTO.getTopic());
		task.setDescription(taskDTO.getDescription());
		task.setStartDate(taskDTO.getStartDate());
		task.setEndDate(taskDTO.getEndDate());
		task.setStartTime(taskDTO.getStartTime());		
		task.setEndTime(taskDTO.getEndTime());
		task.setRepeatTask(taskDTO.getEvery());
		//task.setSubject(new Subject(taskDTO.getSubjectId()));
		//task.setInstructor(new User(taskDTO.getInstructorId()));		
		//task.setAudioEnabled(taskDTO.getAudioEnabled());
		//task.setVideoEnabled(taskDTO.getVideoEnabled());
		return task;
	}
	
	public static Grade getGrade(Task task, User student) {
		
		Grade grade = new Grade();
		//grade.setId(id);
		//grade.setGrade(null);
		grade.setSubject(task.getSubject());
		grade.setInstructor(task.getInstructor());
		grade.setStudent(student);
		grade.setTask(task);
		return grade;
	}
	
	public static GradeDTO getGradeDTO(Grade grade) {
		
		GradeDTO gradeDTO = new GradeDTO();		
		gradeDTO.setId(grade.getId());
		gradeDTO.setGrade(grade.getGrade());
		gradeDTO.setUserName(grade.getStudent().getUserName());
		gradeDTO.setFirstName(grade.getStudent().getFirstName());
		gradeDTO.setLastName(grade.getStudent().getLastName());
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
		gradeDTO.setTopic(grade.getSubject().getTitle());
		return gradeDTO;
	}

}
