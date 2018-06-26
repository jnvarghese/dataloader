package com.batch.manage.dataloader.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.batch.manage.dataloader.model.entity.Organization;

public interface OrganizationDAO extends CrudRepository<Organization, Long> {

	@Query(value = "select * FROM organization WHERE code=:code", nativeQuery = true)
	Organization findOne(@Param("code") String code);
}
