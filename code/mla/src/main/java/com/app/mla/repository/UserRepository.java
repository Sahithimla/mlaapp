package com.app.mla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.mla.domain.User;

/**
 * @author Sahithi
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);
	
	@Query("SELECT u FROM User u WHERE u.id = :id")
	User findUserById(@Param("id") Long id);
	
	@Query("SELECT u FROM User u WHERE lower(u.userType) = lower(:userType)")
	List<User> findUsersByType(@Param("userType") String userType);

	@Query(value="SELECT * FROM USER WHERE UPPER(USER_TYPE) = 'STUDENT' AND ID NOT IN (SELECT STUDENT_ID FROM ENROLL WHERE SUBJECT_ID=:subjectId)", nativeQuery=true)
	 List<User> findStudentsToEnroll(@Param("subjectId") Long subjectId);
	
	@Query(value="SELECT * FROM USER WHERE ID IN (SELECT STUDENT_ID FROM ENROLL WHERE SUBJECT_ID=:subjectId)", nativeQuery=true)
	 List<User> findStudentsEnrolled(@Param("subjectId") Long subjectId);
	
	@Modifying
	@Query(value="INSERT INTO ENROLL (STUDENT_ID, SUBJECT_ID) VALUES (:studentId,:subjectId)", nativeQuery=true)
	 void enrollStudent(@Param("studentId") Long studentId, @Param("subjectId") Long subjectId);
	
	@Modifying
	@Query(value="DELETE FROM ENROLL WHERE STUDENT_ID = :studentId AND SUBJECT_ID = :subjectId", nativeQuery=true)
	 void disEnrollStudent(@Param("studentId") Long studentId, @Param("subjectId") Long subjectId);
	
}
