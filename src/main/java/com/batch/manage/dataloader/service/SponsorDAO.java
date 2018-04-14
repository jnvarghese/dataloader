package com.batch.manage.dataloader.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.batch.manage.dataloader.model.entity.Sponsor;

public interface SponsorDAO extends CrudRepository<Sponsor, Long> {

	 @Query(value = "select * from sponsor where sponsorCode=:sponsorCode", nativeQuery = true)
	 Sponsor findOne(@Param("sponsorCode") String sponsorCode);
}
