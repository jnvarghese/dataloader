package com.batch.manage.dataloader.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.batch.manage.dataloader.model.entity.Initiative;

public interface MissionDAO  extends CrudRepository<Initiative, Long> {

	@Query(value = "select * FROM initiative WHERE name=:name", nativeQuery = true)
	Initiative findOne(@Param("name") String name);
}
