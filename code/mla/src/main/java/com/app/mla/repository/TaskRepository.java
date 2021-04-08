package com.app.mla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.mla.domain.Subject;
import com.app.mla.domain.Task;

/**
 * @author Sahithi
 *
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
	
	@Query("SELECT t FROM Task t WHERE t.id = :id")
	Task findTaskById(@Param("id") Long id);	
	
	/*
	 * @Query("SELECT s FROM Subject s WHERE s.id NOT IN (SELECT t.subject.id FROM Task t)"
	 * ) List<Subject> findAllSubjectsToBeScheduleByAdmin();
	 */
	
	@Query("SELECT s FROM Subject s")
	List<Subject> findAllSubjectsToBeScheduleByAdmin();
	
	/*
	 * @Query("SELECT s FROM Subject s WHERE s.instructor.id=:instructorId AND s.id NOT IN (SELECT t.subject.id FROM Task t WHERE t.instructor.id=:instructorId)"
	 * ) List<Subject> findSubjectsToBeScheduleByInstructor(@Param("instructorId")
	 * Long instructorId);
	 */
	
	@Query("SELECT s FROM Subject s WHERE s.instructor.id=:instructorId")
	List<Subject> findSubjectsToBeScheduleByInstructor(@Param("instructorId") Long instructorId);
	
	@Query("SELECT s FROM Subject s WHERE s.id IN (SELECT t.subject.id FROM Task t)")
	List<Subject> findAllSubjectsScheduledByAdmin();
	
	@Query("SELECT s FROM Subject s WHERE s.instructor.id=:instructorId AND s.id IN (SELECT t.subject.id FROM Task t WHERE t.instructor.id=:instructorId)")
	List<Subject> findSubjectsScheduledByInstructor(@Param("instructorId") Long instructorId);
	
	@Query("SELECT t FROM Task t WHERE t.subject.id = :subjectId")
	List<Task> findAllTasksScheduledByAdmin(@Param("subjectId") Long subjectId);
	
	@Query("SELECT t FROM Task t WHERE t.subject.id = :subjectId AND t.instructor.id = :instructorId")
	List<Task> findTasksScheduledByInstructor(@Param("subjectId") Long subjectId, @Param("instructorId") Long instructorId);
	
	@Query("SELECT g.task FROM Grade g WHERE g.subject.id = :subjectId AND g.student.id = :studentId")
	List<Task> findTasksScheduledForStudent(@Param("subjectId") Long subjectId, @Param("studentId") Long studentId);
	
	@Modifying
	@Query(value="DELETE FROM Task t WHERE t.subject = :subjectId", nativeQuery=true)
	void deleteSubjectAllTasks(@Param("subjectId") Long subjectId);
	
	@Query("SELECT t FROM Task t WHERE t.subject.id = :subjectId AND (SYSDATE() between t.startDate AND t.endDate)")
	List<Task> findAllProcessTasksByAdmin(@Param("subjectId") Long subjectId);
	
	@Query("SELECT t FROM Task t WHERE t.subject.id = :subjectId AND (SYSDATE() between t.startDate AND t.endDate) AND t.instructor.id = :instructorId")
	List<Task> findProcessTasksByInstructor(@Param("subjectId") Long subjectId, @Param("instructorId") Long instructorId);
	
	@Query("SELECT g.task FROM Grade g WHERE g.subject.id = :subjectId AND g.student.id = :studentId AND (SYSDATE() between g.task.startDate AND g.task.endDate)")
	List<Task> findProcessTasksByStudent(@Param("subjectId") Long subjectId, @Param("studentId") Long studentId);
	
}
