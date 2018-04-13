package com.batch.manage.dataloader.service;

import org.springframework.data.repository.CrudRepository;

import com.batch.manage.dataloader.model.entity.Sponsor;

public interface SponsorDAO extends CrudRepository<Sponsor, Long> {

}
