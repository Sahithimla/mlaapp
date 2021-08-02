package com.app.mla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.mla.domain.Grade;
import com.app.mla.domain.Task;

/**
 * @author Sahithi
 *
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query("SELECT g.task FROM Grade g WHERE g.subject.id = :subjectId AND g.student.id = :studentId")
	List<Task> findTasksTaskdForStudent(@Param("subjectId") Long subjectId, @Param("studentId") Long studentId);

	@Query("SELECT t from Task t WHERE t.schedule.subject.id = :subjectId")
	List<Task> findTasksBySubject(@Param("subjectId") Long subjectId);

	@Modifying
	@Query(value = "DELETE FROM Task WHERE schedule = :scheduleId", nativeQuery = true)
	void deleteScheduledAllTasks(@Param("scheduleId") Long scheduleId);

	@Query("SELECT t FROM Task t WHERE t.schedule.subject.id = :subjectId AND (SYSDATE() between t.startDate AND t.endDate)")
	List<Task> findAllProcessTasksByAdmin(@Param("subjectId") Long subjectId);

	@Query("SELECT t FROM Task t WHERE t.schedule.subject.id = :subjectId AND (SYSDATE() between t.startDate AND t.endDate) AND t.schedule.instructor.id = :instructorId")
	List<Task> findProcessTasksByInstructor(@Param("subjectId") Long subjectId, @Param("instructorId") Long instructorId);

	@Query("SELECT g.task FROM Grade g WHERE g.subject.id = :subjectId AND g.student.id = :studentId AND (SYSDATE() between g.task.startDate AND g.task.endDate)")
	List<Task> findProcessTasksByStudent(@Param("subjectId") Long subjectId, @Param("studentId") Long studentId);

	@Query("SELECT g.task FROM Grade g WHERE g.student.userName = :userName AND (SYSDATE() between g.task.startDate AND g.task.endDate)")
	List<Task> findProcessTasksByStudentUser(@Param("userName") String userName);

	@Query("SELECT t FROM Task t WHERE t.schedule.instructor.userName = :userName AND (SYSDATE() between t.startDate AND t.endDate)")
	List<Task> findProcessTasksByNonStudentUser(@Param("userName") String userName);

	@Query("SELECT g from Grade g WHERE g.task.id = :taskId ")
	List<Grade> findStudentsByTask(@Param("taskId") Long taskId);

}
