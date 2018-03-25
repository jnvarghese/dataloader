package com.batch.manage.dataloader.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.batch.manage.dataloader.model.StudentDTO;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping(method = RequestMethod.GET)
    public List<StudentDTO> findStudents() {
        LOGGER.info("Finding all students");

        List<StudentDTO> students = createStudents();
        LOGGER.info("Found {} students", students.size());

        return students;
    }

    private List<StudentDTO> createStudents() {
       /* StudentDTO tony = new StudentDTO();
        tony.setName("Tony Tester");
       

        StudentDTO nick = new StudentDTO();
        nick.setName("Nick Newbie");
       

        StudentDTO ian = new StudentDTO();
        ian.setName("Ian Intermediate");
        
*/
        return Arrays.asList();
    }
}
