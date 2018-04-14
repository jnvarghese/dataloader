package com.batch.manage.dataloader.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.batch.manage.dataloader.model.entity.Student;

public interface StudentDAO extends CrudRepository<Student, Long> {
	
	 @Query(value = "select * from student where studentCode=:studentCode", nativeQuery = true)
	 Student findOne(@Param("studentCode") String studentCode);
}
