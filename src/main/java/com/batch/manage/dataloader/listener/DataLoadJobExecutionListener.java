package com.batch.manage.dataloader.listener;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
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
		Long jobId = jobExecution.getJobId();
		FileUpload fp = fileUploadDAO.findOne(recordId);
		if (null != fp) {
			fp.setBatchExecutionStatus(1);
			fp.setJobId(jobId);
			//fileUploadDAO.save(fp);
		}
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		
		JobParameters jobParameters = jobExecution.getJobParameters();		
		LOGGER.debug(" Before Job, jobParameters: " + jobParameters);
		List<FileUpload> list = new ArrayList<FileUpload>();
		list = fileUploadDAO.findByBatchExecutionStatus(jobParameters.getString("type"));
		LOGGER.info("Size of excel files is , {} for type {} ", list.size(), jobParameters.getString("type"));
		boolean status = false;
		Long jobId = jobExecution.getJobId();
		if("SP".equalsIgnoreCase(jobParameters.getString("type"))) {
			jobExecution.getExecutionContext().put("parishName", fileUploadDAO.parish(list.get(0).getReferenceId()));
			jobExecution.getExecutionContext().put("missionname", fileUploadDAO.initiative(list.get(0).getInitiativeId()));
			jobExecution.getExecutionContext().put("startingCode", list.get(0).getStartingCode());
			jobExecution.getExecutionContext().put("startingStudentCode", list.get(0).getStartingStudentCode());
			jobExecution.getExecutionContext().put("category", jobParameters.getString("category"));
		}
		if (list.size() > 0) {

			jobExecution.getExecutionContext().put("data", list.get(0).getFileData());
			jobExecution.getExecutionContext().put("fileUploadRecordId", list.get(0).getId());
			jobExecution.getExecutionContext().put("referenceId", list.get(0).getReferenceId());
			jobExecution.getExecutionContext().put("jobId", jobId);
			//jobExecution.getExecutionContext().put("parishId", 5);

		} else {
			JobExecution jobExec = jobExplorer.getJobExecution(jobId);			
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
