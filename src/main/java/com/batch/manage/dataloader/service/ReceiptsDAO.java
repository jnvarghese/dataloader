package com.batch.manage.dataloader.service;

import org.springframework.data.repository.CrudRepository;

import com.batch.manage.dataloader.model.entity.receipts.Receipts;

public interface ReceiptsDAO extends CrudRepository<Receipts, Long> {

}
