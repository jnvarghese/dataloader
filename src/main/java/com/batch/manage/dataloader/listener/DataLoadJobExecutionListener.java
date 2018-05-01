package com.batch.manage.dataloader.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.batch.manage.dataloader.model.entity.FileUpload;
import com.batch.manage.dataloader.service.FileUploadDAO;

public class DataLoadJobExecutionListener implements JobExecutionListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataLoadJobExecutionListener.class);

	@Autowired
	FileUploadDAO fileUploadDAO;

	@Autowired
	JobOperator jobOperator;

	@Autowired
	JobExplorer jobExplorer;

	@Override
	public void afterJob(JobExecution jobExecution) {

		LOGGER.debug(" After Job ");
		Long recordId = (Long) jobExecution.getExecutionContext().get("fileUploadRecordId");
		FileUpload fp = fileUploadDAO.findOne(recordId);
		if (null != fp) {
			//fp.setBatchExecutionStatus(1);
			//fileUploadDAO.save(fp);
		}
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		LOGGER.debug(" Before Job " + jobExplorer);
		List<FileUpload> list = new ArrayList<FileUpload>();
		list = fileUploadDAO.findByBatchExecutionStatus();
		LOGGER.info("Size of excel files is , {} ", list.size());
		boolean status = false;
		Long jobId = jobExecution.getJobId();
		if (list.size() > 0) {

			jobExecution.getExecutionContext().put("data", list.get(0).getFileData());
			jobExecution.getExecutionContext().put("fileUploadRecordId", list.get(0).getId());
			jobExecution.getExecutionContext().put("projectId", list.get(0).getProjectId());
			jobExecution.getExecutionContext().put("jobId", jobId);
			jobExecution.getExecutionContext().put("parishId", 5);

		} else {
			JobExecution jobExec = jobExplorer.getJobExecution(jobId);
			System.out.println( " Job Exec "+jobExec);
			System.out.println( " Job Exec "+jobExec.getStatus());
			try {
				status = jobOperator.stop(jobId);
			} catch (NoSuchJobExecutionException e) {
				LOGGER.error("NoSuchJobExecutionException while stopping the job, id {}", e.getMessage(), jobId);
			} 
			catch (JobExecutionNotRunningException e) {
				LOGGER.error("JobExecutionNotRunningException while stopping the job, id {}", e.getMessage(), jobId);
			}			
			LOGGER.info("###########Stopped#########");
			
		}

		LOGGER.warn("Stopping the Job due to file availability, {}", status);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Invoke before starting of Job {}", jobExecution);
		}
	}

}
