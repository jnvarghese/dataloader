package com.batch.manage.dataloader.listener;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.batch.manage.dataloader.model.entity.FileUpload;
import com.batch.manage.dataloader.service.FileUploadDAO;

public class DataLoadJobExecutionListener implements JobExecutionListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataLoadJobExecutionListener.class);

	@Autowired
	FileUploadDAO fileUploadDAO;

	@Override
	public void afterJob(JobExecution jobExecution) {
		LOGGER.debug(" After Job ");
		Long recordId = (Long) jobExecution.getExecutionContext().get("fileUploadRecordId");
		FileUpload fp = fileUploadDAO.findOne(recordId);
		if (null != fp) {
			fp.setBatchExecutionStatus(1);
			fileUploadDAO.save(fp);
		}
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		List<FileUpload> list = new ArrayList<FileUpload>();
		list = fileUploadDAO.findByBatchExecutionStatus();

		if (list.size() > 0) {

			jobExecution.getExecutionContext().put("data", list.get(0).getFileData());
			jobExecution.getExecutionContext().put("fileUploadRecordId", list.get(0).getId());
			jobExecution.getExecutionContext().put("projectId", list.get(0).getProjectId());
			Long jobId = jobExecution.getJobId();
			jobExecution.getExecutionContext().put("jobId", jobId);

		} else {
			jobExecution.getExecutionContext().put("data", null);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Invoke before starting of Job {}", jobExecution);
		}
	}

}
