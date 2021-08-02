package com.app.mla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.mla.domain.Schedule;
import com.app.mla.domain.Subject;

/**
 * @author Sahithi
 *
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	@Query("SELECT s FROM Subject s WHERE s.id NOT IN (SELECT t.subject.id FROM Schedule t)")
	List<Subject> findAllSubjectsToBeScheduleByAdmin();

	@Query("SELECT s FROM Subject s WHERE s.instructor.id=:instructorId AND s.id NOT IN (SELECT t.subject.id FROM Schedule t WHERE t.instructor.id=:instructorId)")
	List<Subject> findSubjectsToBeScheduleByInstructor(@Param("instructorId") Long instructorId);

	@Query("SELECT s FROM Subject s WHERE s.id IN (SELECT t.subject.id FROM Schedule t)")
	List<Subject> findAllSubjectsScheduledByAdmin();

	@Query("SELECT s FROM Subject s WHERE s.instructor.id=:instructorId AND s.id IN (SELECT t.subject.id FROM Schedule t WHERE t.instructor.id=:instructorId)")
	List<Subject> findSubjectsScheduledByInstructor(@Param("instructorId") Long instructorId);

	@Query("SELECT t FROM Schedule t WHERE t.subject.id = :subjectId")
	List<Schedule> findAllSchedulesScheduledByAdmin(@Param("subjectId") Long subjectId);

	@Query("SELECT t FROM Schedule t WHERE t.subject.id = :subjectId AND t.instructor.id = :instructorId")
	List<Schedule> findSchedulesScheduledByInstructor(@Param("subjectId") Long subjectId,
			@Param("instructorId") Long instructorId);

	@Modifying
	@Query(value = "DELETE FROM Schedule WHERE subject = :subjectId", nativeQuery = true)
	void deleteSubjectAllSchedules(@Param("subjectId") Long subjectId);

	@Query("SELECT t FROM Schedule t WHERE t.subject.id = :subjectId AND (SYSDATE() between t.startDate AND t.endDate)")
	List<Schedule> findAllProcessSchedulesByAdmin(@Param("subjectId") Long subjectId);

	@Query("SELECT t FROM Schedule t WHERE t.subject.id = :subjectId AND (SYSDATE() between t.startDate AND t.endDate) AND t.instructor.id = :instructorId")
	List<Schedule> findProcessSchedulesByInstructor(@Param("subjectId") Long subjectId,
			@Param("instructorId") Long instructorId);

	@Query("SELECT t FROM Schedule t WHERE t.instructor.userName = :userName AND (SYSDATE() between t.startDate AND t.endDate)")
	List<Schedule> findProcessSchedulesByNonStudentUser(@Param("userName") String userName);

	@Query("SELECT s FROM Schedule s WHERE s.subject.id = :subjectId")
	List<Schedule> findScheduleBySubject(@Param("subjectId") Long subjectId);
}
