package com.batch.manage.dataloader.common;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.batch.manage.dataloader.model.entity.Student;
import com.batch.manage.dataloader.service.StudentDAO;

public class LoggingStudentWriter implements ItemWriter<Student> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingStudentWriter.class);
    
    @Autowired
    private StudentDAO studentDAO;

    @Override
    public void write(List<? extends Student> items) throws Exception {
        LOGGER.info("Received the information of {} students", items.size());

       // items.forEach(i -> LOGGER.debug("Received the information of a student: {}", i));
        
        for(Student dto : items) {
        	studentDAO.save(dto);
        }
    }

}
