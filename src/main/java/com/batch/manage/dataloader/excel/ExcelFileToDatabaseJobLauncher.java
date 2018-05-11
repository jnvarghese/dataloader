package com.batch.manage.dataloader.excel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.stereotype.Component;

@Component
public class ExcelFileToDatabaseJobLauncher {


    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelFileToDatabaseJobLauncher.class);

 //   private final Job job;

   // private final JobLauncher jobLauncher;

   /*@Autowired
    ExcelFileToDatabaseJobLauncher(@Qualifier("excelFileToDatabaseJob") Job job, JobLauncher jobLauncher) {
        this.job = job;
        this.jobLauncher = jobLauncher;
    }
    
    @Autowired
    ExcelFileToDatabaseJobLauncher(@Qualifier("sponsorExcelFileToDatabaseJob") Job job, JobLauncher jobLauncher) {
        this.job = job;
        this.jobLauncher = jobLauncher;
    }*/
    
    /*@Scheduled(cron = "${excel.to.database.job.cron}")
    void launchXmlFileToDatabaseJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        LOGGER.info("Starting excelFileToDatabase job");

        jobLauncher.run(job, newExecution());

        LOGGER.info("Stopping excelFileToDatabase job");
    }*/

    private JobParameters newExecution() {
        Map<String, JobParameter> parameters = new HashMap<>();

        JobParameter parameter = new JobParameter(new Date());
        parameters.put("currentTime", parameter);

        return new JobParameters(parameters);
    }
}