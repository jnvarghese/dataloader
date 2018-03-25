package com.batch.manage.dataloader.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.batch.manage.dataloader.model.StudentDTO;
import com.batch.manage.dataloader.model.entity.Student;

public class LoggingStudentProcessor implements ItemProcessor<StudentDTO, Student> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingStudentProcessor.class);

    
    private Long jobId;
    private Long projectId;
    
	public LoggingStudentProcessor(Long jobId, Long projectId) {
		this.jobId =jobId;
		this.projectId = projectId;
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Job Id "+this.jobId+ " and project id is "+ this.projectId+ " in LoggingStudentProcessor constructor");
		}
	}

    @Override
    public Student process(StudentDTO dto) throws Exception {
        Student item = new Student();
        item.setProjectId(this.projectId);
        item.setJobId(this.jobId);
    	item.setAddress(dto.getAddress());
    	item.setBaseLanguage(dto.getMotherTongue());
    	item.setDateOfBirth(dto.getDateOfBirth());
    	item.setFavColor(dto.getFavoriteColor());
    	item.setFavGame(dto.getFavoriteGame());
    	item.setGender(dto.getGender());
    	item.setHobbies(dto.getHobby());
    	item.setNameOfGuardian(dto.getNameOfParent());
    	item.setOccupationOfGuardian(dto.getOccupationOfParent());
    	item.setRecentAchivements(dto.getRecentAchivements());
    	item.setStudentName(dto.getNameOfChild());
    	item.setTalent(dto.getTalent());    
        return item;
    }
    
    
}
