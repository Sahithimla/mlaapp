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

import com.app.mla.domain.Subject;
import com.app.mla.dto.SubjectDTO;
import com.app.mla.exception.BadRequestException;
import com.app.mla.exception.DuplicateRecordException;
import com.app.mla.exception.MLAServiceException;
import com.app.mla.exception.ResourceNotFoundException;
import com.app.mla.repository.SubjectRepository;
import com.app.mla.service.SubjectService;
import com.app.mla.util.EntityMapper;

/**
 * @author Sahithi
 *
 */
@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	SubjectRepository subjectRepository;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void addSubject(SubjectDTO subjectDTO) throws DuplicateRecordException, MLAServiceException {
		logger.debug("SubjectServiceImpl :: addSubject -> START");
		validateSubjectDetails(subjectDTO);
		subjectRepository.save(EntityMapper.getSubject(subjectDTO));
//		if (subjectRepository.findByTitle(subjectDTO.getTitle()) == null) {
//			subjectRepository.save(EntityMapper.getSubject(subjectDTO));
//		} else {
//			logger.error("Subject with title '" + subjectDTO.getTitle()
//					+ "' already exists. Please choose a different title");
//			throw new DuplicateRecordException("Subject with title '" + subjectDTO.getTitle()
//					+ "' already exists. Please choose a different title");
//		}
		logger.debug("SubjectServiceImpl :: addSubject -> END");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void editSubject(SubjectDTO subjectDTO)
			throws DuplicateRecordException, ResourceNotFoundException, MLAServiceException {
		logger.debug("SubjectServiceImpl :: editSubject -> START");
		Subject subject = subjectRepository.findById(subjectDTO.getId()).get();
		if (subject != null) {
			validateSubjectDetails(subjectDTO);
			if(subjectRepository.findByTitle(subjectDTO.getTitle()) != null) {
				logger.error("Subject with title '" + subjectDTO.getTitle()
				+ "' already exists. Please choose a different title");
				throw new DuplicateRecordException("Subject with title '" + subjectDTO.getTitle()
				+ "' already exists. Please choose a different title");
			}
			subjectRepository.save(EntityMapper.editSubject(subject, subjectDTO));
		} else {
			logger.error("Subject does not exists to edit the details");
			throw new ResourceNotFoundException("Subject does not exists to edit the details");
		}
		logger.debug("SubjectServiceImpl :: editSubject -> END");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void deleteSubject(Long subjectId) throws ResourceNotFoundException, MLAServiceException {
		logger.debug("SubjectServiceImpl :: deleteSubject -> START");
		Subject subject = subjectRepository.findById(subjectId).get();
		if (subject != null) {
			subjectRepository.delete(subject);
		} else {
			logger.error("Subject does not exists to delete");
			throw new ResourceNotFoundException("Subject does not exists to delete");
		}
		logger.debug("SubjectServiceImpl :: deleteSubject -> END");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public SubjectDTO getSubjectById(Long subjectId) throws ResourceNotFoundException, MLAServiceException {
		logger.debug("SubjectServiceImpl :: getUserById -> START");
		SubjectDTO subjectDTO = null;
		Subject subject = subjectRepository.findById(subjectId).get();
		if (subject != null) {
			subjectDTO = EntityMapper.getSubjectDTO(subject);
		} else {
			logger.error("Subject does not exists");
			throw new ResourceNotFoundException("Subject does not exists");
		}
		logger.debug("SubjectServiceImpl :: getSubjectById : Subject details : " + subjectDTO);
		logger.debug("SubjectServiceImpl :: getSubjectById -> END");
		return subjectDTO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public List<SubjectDTO> getAllSubjects() {
		logger.debug("SubjectServiceImpl :: getAllSubjects -> START");
		List<SubjectDTO> subjectList = new ArrayList<>();
		List<Subject> subjects = subjectRepository.findAll();
		for (Subject subject : subjects) {
			subjectList.add(EntityMapper.getSubjectDTO(subject));
		}
		logger.debug("SubjectServiceImpl :: getAllSubjects : no of Subjects : " + subjectList.size());
		logger.debug("SubjectServiceImpl :: getAllSubjects -> END");
		return subjectList;
	}
	
	private void validateSubjectDetails(SubjectDTO subjectDTO) throws BadRequestException {
		
		if(StringUtils.isEmpty(subjectDTO.getTitle())) {
			logger.error("Title is required");
			throw new BadRequestException("Title is required");
		}
		if(StringUtils.isEmpty(subjectDTO.getDescription())) {
			logger.error("Description is required");
			throw new BadRequestException("Description is required");
		}
		if(StringUtils.isEmpty(subjectDTO.getSubjectType())) {
			logger.error("SubjectType is required");
			throw new BadRequestException("SubjectType is required");
		}
		/*
		 * if(StringUtils.isEmpty(subjectDTO.getSubjectTerm())) {
		 * logger.error("SubjectTerm is required"); throw new
		 * BadRequestException("SubjectTerm is required"); }
		 */
		if(subjectDTO.getInstructorId() == null) {
			logger.error("Instructor is required");
			throw new BadRequestException("Instructor is required");
		}
		if(subjectDTO.getStartDate()==null) {
			logger.error("StartDate is required");
			throw new BadRequestException("StartDate is required");
		}
		if(subjectDTO.getEndDate()==null) {
			logger.error("EndDate is required");
			throw new BadRequestException("EndDate is required");
		}
		if(StringUtils.isEmpty(subjectDTO.getStartTime())) {
			logger.error("StartTime is required");
			throw new BadRequestException("StartTime is required");
		}
		if(StringUtils.isEmpty(subjectDTO.getEndTime())) {
			logger.error("EndTime is required");
			throw new BadRequestException("EndTime is required");
		}
		/*
		 * if(StringUtils.isEmpty(subjectDTO.getDuration())) {
		 * logger.error("Duration is required"); throw new
		 * BadRequestException("Duration is required"); }
		 */
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public List<SubjectDTO> getSubjectsByInstructorId(Long instructorId) {
		logger.debug("SubjectServiceImpl :: getSubjectsByInstructorId -> START");
		List<SubjectDTO> subjectList = new ArrayList<>();
		List<Subject> subjects = subjectRepository.findSubjectsByInstructorId(instructorId);
		for (Subject subject : subjects) {
			subjectList.add(EntityMapper.getSubjectDTOMinDetails2(subject));
		}
		logger.debug("SubjectServiceImpl :: getSubjectsByInstructorId : no of Subjects : " + subjectList.size());
		logger.debug("SubjectServiceImpl :: getSubjectsByInstructorId -> END");
		return subjectList;
	}

}
