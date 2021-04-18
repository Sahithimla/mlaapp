package com.app.mla.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.mla.domain.Grade;
import com.app.mla.domain.Task;
import com.app.mla.domain.User;
import com.app.mla.dto.GradeDTO;
import com.app.mla.exception.BadRequestException;
import com.app.mla.exception.DuplicateRecordException;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.exception.ResourceNotFoundException;
import com.app.mla.repository.GradeRepository;
import com.app.mla.repository.UserRepository;
import com.app.mla.service.GradeService;
import com.app.mla.util.EntityMapper;

/**
 * @author Sahithi
 *
 */
@Service("gradeService")
public class GradeServiceImpl implements GradeService {

	@Autowired
	GradeRepository gradeRepository;
	
	@Autowired
	UserRepository userRepository;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void addGrade(Task task) throws DuplicateRecordException, MLAServiceException {
		
		logger.debug("GradeServiceImpl :: addGrade -> START");
		if (task != null) {
			// Add Task
			List<User> students = userRepository.findStudentsEnrolled(task.getSubject().getId());
			List<Grade> grades = new ArrayList<>();
			for (User student : students) {
				grades.add(EntityMapper.getGrade(task, student));
			}
			gradeRepository.saveAll(grades);
		} else {
			logger.error("Exception occured");
			throw new BadRequestException("Exception occured");
		}
		logger.debug("GradeServiceImpl :: addGrade -> END");
	}

	@Override
	public List<GradeDTO> getAllGrades(Long taskId) {
		
		logger.debug("GradeServiceImpl :: getAllGrades -> START");
		List<GradeDTO> gradeList = new ArrayList<>();
		List<Grade> grades = gradeRepository.findAllGrades(taskId);
		for (Grade grade : grades) {
			gradeList.add(EntityMapper.getGradeDTO(grade));
		}
		logger.debug("GradeServiceImpl :: getAllGrades : no of Students : " + gradeList.size());
		logger.debug("GradeServiceImpl :: getAllGrades -> END");
		return gradeList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void editGrade(GradeDTO gradeDTO) throws ResourceNotFoundException, MLAServiceException {
		
		logger.debug("GradeServiceImpl :: editGrade -> START");
		Grade grade = gradeRepository.findGradeById(gradeDTO.getId());
		if (grade != null) {
			validateGrade(gradeDTO);
			gradeRepository.save(EntityMapper.editGrade(grade, gradeDTO));
		} else {
			logger.error("Grade does not exists to edit the details");
			throw new ResourceNotFoundException("Grade does not exists to edit the details");
		}
		logger.debug("GradeServiceImpl :: editGrade -> END");
	}
	
	private void validateGrade(GradeDTO gradeDTO) throws BadRequestException {
		
		if(StringUtils.isEmpty(gradeDTO.getGrade())) {
			logger.error("Grade is required");
			throw new BadRequestException("Grade is required");
		}
	}

	@Override
	public List<GradeDTO> getStudentGradeForTask(Long subjectId, Long studentId)
			throws MLAServiceException {

		logger.debug("GradeServiceImpl :: getStudentGradeForTask -> START");
		List<GradeDTO> gradeList = new ArrayList<>();
		List<Grade> grades = gradeRepository.findGradesForStudent(subjectId, studentId);
		for (Grade grade : grades) {
			gradeList.add(EntityMapper.getStudentGradeDTO(grade));
		}
		logger.debug("GradeServiceImpl :: getStudentGradeForTask : Student no of gradeList : " + gradeList.size());
		logger.debug("GradeServiceImpl :: getStudentGradeForTask -> END");
		return gradeList;
	}

}
