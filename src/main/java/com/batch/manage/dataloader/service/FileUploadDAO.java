package com.batch.manage.dataloader.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.batch.manage.dataloader.model.entity.FileUpload;

public interface FileUploadDAO extends CrudRepository<FileUpload, Long> {

	 @Query(value = "select * from file_upload WHERE BATCHEXECUTIONSTATUS = 0 and type= #{type} ORDER BY CREATEDDATE", nativeQuery = true)
	 List<FileUpload> findByBatchExecutionStatus(@Param("type") String type);
	
	 @Query(value = "select * from file_upload where id=:id", nativeQuery = true)
	 FileUpload findOne(@Param("id") Long id);
}
