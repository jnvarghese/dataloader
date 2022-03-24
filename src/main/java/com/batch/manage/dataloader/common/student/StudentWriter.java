package com.batch.manage.dataloader.common.student;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.batch.manage.dataloader.common.s3.S3Wrapper;
import com.batch.manage.dataloader.model.entity.Student;
import com.batch.manage.dataloader.service.StudentDAO;

public class StudentWriter implements ItemWriter<Student> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentWriter.class);
    
    @Autowired
    private StudentDAO studentDAO;
    
    @Autowired
	S3Wrapper s3Wrapper;

    @Override
    public void write(List<? extends Student> items) throws Exception {
        LOGGER.info("Received the information of {} students", items.size());

        items.forEach(i -> LOGGER.debug("Received the information of a student: {}", i));
        
        for(Student dto : items) {
        	studentDAO.save(dto);
        	try {
				PutObjectResult result = s3Wrapper.upload(dto.getImage(), dto.getId(), dto.getImageLinkRef(), dto.getProjectId(), "2");
				dto.setUploadstatus("Y");
			} catch (Exception e) {
				LOGGER.error("Unable to upload student picture "+dto.getId());
				e.printStackTrace();
			}
        	
        	
        }
    }

}
