package com.batch.manage.dataloader.service;

import org.springframework.data.repository.CrudRepository;

import com.batch.manage.dataloader.model.entity.Receipt;

public interface ReceiptDAO extends CrudRepository<Receipt, Long> {

}
