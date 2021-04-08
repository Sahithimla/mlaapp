package com.app.mla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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
	
	@Query("SELECT g FROM Grade g WHERE g.id = :id")
	Grade findGradeById(@Param("id") Long id);
	
	@Query("SELECT g FROM Grade g WHERE g.subject.id = :subjectId AND g.student.id = :studentId")
	List<Grade> findGradesForStudent(@Param("subjectId") Long subjectId, @Param("studentId") Long studentId);
}
