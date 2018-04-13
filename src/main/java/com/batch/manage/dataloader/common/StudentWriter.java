package com.batch.manage.dataloader.common;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.batch.manage.dataloader.model.entity.Student;
import com.batch.manage.dataloader.service.StudentDAO;

public class StudentWriter implements ItemWriter<Student> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentWriter.class);
    
    @Autowired
    private StudentDAO studentDAO;

    @Override
    public void write(List<? extends Student> items) throws Exception {
        LOGGER.info("Received the information of {} students", items.size());

        items.forEach(i -> LOGGER.debug("Received the information of a student: {}", i));
        
        for(Student dto : items) {
        	studentDAO.save(dto);
        }
    }

}
