package com.app.mla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.mla.domain.Subject;

/**
 * @author Sahithi
 *
 */
public interface SubjectRepository extends JpaRepository<Subject, Long> {

	@Query("SELECT s FROM Subject s WHERE s.id = :id")
	Subject findSubjectById(@Param("id") Long id);
	
	@Query("SELECT s FROM Subject s WHERE (s.title) = lower(:title)")
	Subject findByTitle(@Param("title") String title);
	
	@Query("SELECT s FROM Subject s WHERE s.instructor.id = :instructorId")
	List<Subject> findSubjectsByInstructorId(@Param("instructorId") Long instructorId);
	
	@Query(value="SELECT * FROM Subject WHERE ID IN (SELECT SUBJECT_ID FROM ENROLL WHERE STUDENT_ID=:studentId)", nativeQuery=true)
	 List<Subject> findSubjectsEnrolledByStudent(@Param("studentId") Long studentId);

}
