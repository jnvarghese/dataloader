package com.batch.manage.dataloader.service;

import org.springframework.data.repository.CrudRepository;

import com.batch.manage.dataloader.model.entity.Student;

public interface StudentDAO extends CrudRepository<Student, Long> {

}
