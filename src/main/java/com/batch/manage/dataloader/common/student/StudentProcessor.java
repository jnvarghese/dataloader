package com.batch.manage.dataloader.common.student;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.batch.manage.dataloader.model.StudentDTO;
import com.batch.manage.dataloader.model.entity.Student;
import com.batch.manage.dataloader.service.StudentDAO;

public class StudentProcessor implements ItemProcessor<StudentDTO, Student> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentProcessor.class);
    DateFormat df = new SimpleDateFormat("MM/dd/YYYY");
    
    private Long jobId;
    private Long projectId;
    
    @Autowired
    StudentDAO studentDAO;
    
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
    	
    	Student item = null;
    	if(dto.getCode().length() > 0) {
    		
    		List<Student> students =  studentDAO.findByCodeAndProject(dto.getCode().split("\\.")[0], this.projectId);
    		LOGGER.info("Row contains student code "+dto.getCode()+" students size is: "+students.size());
    		if(students.size() > 1) {
    			LOGGER.warn(" MORE STUDENTS RETURNED FOR CODE "+dto.getCode()+" AND PROJECT "+this.projectId);
    			int size = students.size();
    			item = students.get(size-1);
    			students.remove(size-1);
    			LOGGER.info("Removed students list " +students);
    			for(Student st: students) {
    				System.out.println();
    				st.setStatus("1");
    				studentDAO.save(st);
    			}
    		}else if(students.size() > 0) {
    			item = students.get(0);
    		}else {
    			LOGGER.warn(" No Students returned" );
    		}
    		
    		if(item == null) {
    			LOGGER.warn(" Unable to find the studend with code {} "+ dto.getCode());
    			item= new Student();
    		}
    	} else {
    		item= new Student();
    	}
         
        //item.setStudentCode(dto.getStudentId().split("-")[2]);
        item.setProjectId(this.projectId);
        item.setJobId(this.jobId);
    	item.setAddress(dto.getAddress());
    	
    	if(null != dto.getDateOfBirth() || !dto.getDateOfBirth().isEmpty() || !"".equals(dto.getDateOfBirth().trim())) {
	    	item.setDateOfBirth(df.format(new Date(Long.valueOf(dto.getDateOfBirth()))));
	    }
    	
    	if("MALE".toLowerCase().equals(dto.getGender().trim().toLowerCase())) {
    		item.setGender("M");
    	}else if("FEMALE".toLowerCase().equals(dto.getGender().trim().toLowerCase())) {
    		item.setGender("F");
    	}else {
    		LOGGER.warn(" Invalid gender {} for child {} , refer job {}", dto.getGender(), dto.getNameOfChild(), this.jobId);
    		item.setGender("F");
    	}
    	if(dto.getGrade().contains(".")) {
    		if(dto.getGrade().split("\\.")[0].equals("0")) {
    			item.setStatus("1");
    		}else {
    			item.setGrade(dto.getGrade().split("\\.")[0]);
    		}
    	}else {
    		if(null != dto.getGrade())	
    			item.setGrade(dto.getGrade());
    	}
    	
    	if(null != dto.getFavoriteColor())
    		item.setFavColor(dto.getFavoriteColor());
    	if(null != dto.getFavoriteGame())
    		item.setFavGame(dto.getFavoriteGame());
    	if(null != dto.getMotherTongue())
    		item.setBaseLanguage(dto.getMotherTongue());
    	if(null != dto.getHobby())
    		item.setHobbies(dto.getHobby());
    	if(null != dto.getNameOfParent())
    		item.setNameOfGuardian(dto.getNameOfParent());
    	if(null != dto.getOccupationOfParent())
    		item.setOccupationOfGuardian(dto.getOccupationOfParent());
    	if(null != dto.getRecentAchivements())
    		item.setRecentAchivements(dto.getRecentAchivements());
    	if(null != dto.getNameOfChild())
    		item.setStudentName(dto.getNameOfChild());
    	if(null != dto.getTalent())
    		item.setTalent(dto.getTalent());    
    	item.setCreatedBy(1L);
    	//item.setProfilePicture(dto.getAddLinkForPicture());
    	//item.setImageLinkRef(dto.getAddLinkForPicture());
        return item;
    }
    
    
}
