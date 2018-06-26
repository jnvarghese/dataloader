package com.batch.manage.dataloader.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.batch.manage.dataloader.model.entity.Parish;

public interface ParishDAO extends CrudRepository<Parish, Long> {

	@Query(value = "select * FROM parish WHERE code=:code", nativeQuery = true)
	Parish findOne(@Param("code") String code);
}
