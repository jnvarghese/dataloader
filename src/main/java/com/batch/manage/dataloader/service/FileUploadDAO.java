package com.batch.manage.dataloader.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.batch.manage.dataloader.model.entity.FileUpload;

public interface FileUploadDAO extends CrudRepository<FileUpload, Long> {

	 @Query(value = "select * from file_upload WHERE BATCHEXECUTIONSTATUS = 0 ORDER BY CREATEDDATE", nativeQuery = true)
	 List<FileUpload> findByBatchExecutionStatus();
	
	 @Query(value = "select * from file_upload where id=:id", nativeQuery = true)
	 FileUpload findOne(@Param("id") Long id);
}
