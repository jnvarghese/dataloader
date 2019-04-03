package com.batch.manage.dataloader.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobLauncherController {
	
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	@Qualifier("sponsorJob")
	Job sponsorjob;
	
	@Autowired
	@Qualifier("sponsorAndStudentJob")
	Job sponsorStudentjob;
	
//	@Autowired
	//@Qualifier("receiptExcelFileToDatabaseJob")
	//Job receiptjob;

	@Autowired
	@Qualifier("studentJob")
	Job studentjob;
	
	/*
	@RequestMapping("/launchrjob")
	public String handleReceipt() throws Exception {

		Logger logger = LoggerFactory.getLogger(this.getClass());
		try {
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
					.addString("type", "R").toJobParameters();
			jobLauncher.run(receiptjob, jobParameters);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return "Done";
	}*/

	@RequestMapping("/launchspjob/{category}")
	public String handleSponsor(@PathVariable(name = "category", required=false) String category) throws Exception {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		try {
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
					.addString("type", "SP")
					.addString("category", category)
					.toJobParameters();
			jobLauncher.run(sponsorjob, jobParameters);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return "Done";
	}

	@RequestMapping("/launchstjob")
	public String handleStudent() throws Exception {

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
	@RequestMapping("/launchssjob")
	public String handleSponsorAndStudent() throws Exception {

		Logger logger = LoggerFactory.getLogger(this.getClass());
		try {
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
					.addString("type", "SS").toJobParameters();
			jobLauncher.run(sponsorStudentjob, jobParameters);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return "Done";
	}
}
