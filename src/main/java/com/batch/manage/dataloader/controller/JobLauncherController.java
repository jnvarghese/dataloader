package com.batch.manage.dataloader.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobLauncherController {
	
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	@Qualifier("sponsorExcelFileToDatabaseJob")
	Job sponsorjob;

	@Autowired
	@Qualifier("excelFileToDatabaseJob")
	Job studentjob;

	@RequestMapping("/launchspjob")
	public String handle() throws Exception {

		Logger logger = LoggerFactory.getLogger(this.getClass());
		try {
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
					.addString("type", "SP").toJobParameters();
			jobLauncher.run(sponsorjob, jobParameters);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return "Done";
	}

	@RequestMapping("/launchstjob")
	public String handleTudentJob() throws Exception {

		Logger logger = LoggerFactory.getLogger(this.getClass());
		try {
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
					.addString("type", "ST").toJobParameters();
			jobLauncher.run(studentjob, jobParameters);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return "Done";
	}
}
