package com.app.mla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.mla.domain.Grade;

/**
 * @author Sahithi
 *
 */
public interface GradeRepository extends JpaRepository<Grade, Long> {
	
	@Query("SELECT g FROM Grade g WHERE g.task.id = :taskId")
	List<Grade> findAllGrades(@Param("taskId") Long taskId);

	@Modifying
	@Query(value="DELETE FROM Grade WHERE subject = :subjectId", nativeQuery=true)
	void deleteBySubject(@Param("subjectId") Long subjectId);
	
	@Query("SELECT g FROM Grade g WHERE g.subject.id = :subjectId AND g.student.id = :studentId")
	List<Grade> findGradesForStudent(@Param("subjectId") Long subjectId, @Param("studentId") Long studentId);
	
}
