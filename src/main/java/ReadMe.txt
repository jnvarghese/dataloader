https://blog.codecentric.de/en/2013/06/spring-batch-2-2-javaconfig-part-2-jobparameters-executioncontext-and-stepscope/

JobParameters jobParameters= new JobParametersBuilder().addString("file.name", "filename.txt").toJobParameters();   
JobExecution execution = jobLauncher.run(job, jobParameters);  

https://docs.spring.io/spring-batch/trunk/reference/html/configureStep.html
Job Scope