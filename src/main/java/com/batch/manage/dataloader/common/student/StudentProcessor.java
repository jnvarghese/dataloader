package com.batch.manage.dataloader.common.student;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.batch.manage.dataloader.model.StudentDTO;
import com.batch.manage.dataloader.model.entity.Student;

public class StudentProcessor implements ItemProcessor<StudentDTO, Student> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentProcessor.class);
    DateFormat df = new SimpleDateFormat("MM/dd/YYYY");
    
    private Long jobId;
    private Long projectId;
    
	public StudentProcessor(Long jobId, Long projectId) {
		this.jobId =jobId;
		this.projectId = projectId;
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Job Id "+this.jobId+ " and project id is "+ this.projectId+ " in LoggingStudentProcessor constructor");
		}
	}

    @Override
    public Student process(StudentDTO dto) throws Exception {
    	
    	LOGGER.info("Processing the information of {} student", dto);
        Student item = new Student();
        //item.setStudentCode(dto.getStudentId().split("-")[2]);
        item.setProjectId(this.projectId);
        item.setJobId(this.jobId);
    	item.setAddress(dto.getAddress());
    	item.setBaseLanguage(dto.getMotherTongue());
    	if(null != dto.getDateOfBirth() || !dto.getDateOfBirth().isEmpty() || !"".equals(dto.getDateOfBirth().trim())) {
	    	item.setDateOfBirth(df.format(new Date(Long.valueOf(dto.getDateOfBirth()))));
	    }
    	item.setFavColor(dto.getFavoriteColor());
    	item.setFavGame(dto.getFavoriteGame());
    	if("MALE".equalsIgnoreCase(dto.getGender())) {
    		item.setGender("M");
    	}else if("FEMALE".equalsIgnoreCase(dto.getGender())) {
    		item.setGender("F");
    	}else {
    		LOGGER.warn(" Invalid gender {} for child {} , refer job {}", dto.getGender(), dto.getNameOfChild(), this.jobId);
    		item.setGender("F");
    	}
    	if(dto.getGrade().contains(".")) {
    		item.setGrade(dto.getGrade().split("\\.")[0]);
    	}else {
    		item.setGrade(dto.getGrade());
    	}
    	
    	item.setHobbies(dto.getHobby());
    	item.setNameOfGuardian(dto.getNameOfParent());
    	item.setOccupationOfGuardian(dto.getOccupationOfParent());
    	item.setRecentAchivements(dto.getRecentAchivements());
    	item.setStudentName(dto.getNameOfChild());
    	item.setTalent(dto.getTalent());    
    	item.setCreatedBy(1L);
        return item;
    }
    
    
}
