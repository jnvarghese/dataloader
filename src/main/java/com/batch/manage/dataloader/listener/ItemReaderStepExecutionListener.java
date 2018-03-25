package com.batch.manage.dataloader.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;

public class ItemReaderStepExecutionListener implements StepExecutionListener {

	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		System.out.println(" --  after "+ arg0);
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		/*String jobstatus = (String) stepExecution.getJobExecution().getExecutionContext().get("jobstatus");
		System.out.println(" ---jobstatus--- " + jobstatus);
		if(jobstatus == "skip") {
			stepExecution.setTerminateOnly();
			stepExecution.setStatus(BatchStatus.ABANDONED);
			stepExecution.setExitStatus(ExitStatus.COMPLETED);
		}
		System.out.println(" --  before "+ stepExecution);
*/
	}
	
}
