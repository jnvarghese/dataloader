package com.batch.manage.dataloader;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.batch.manage.dataloader.config.sponsorjob.SponsorJobConfig;

@RunWith(SpringJUnit4ClassRunner.class)
//@IntegrationTest
//@ActiveProfiles("batchtest")
//@SpringBootTest(classes = { SponsorExcelFileToDatabaseJobConfig.class})
//@ContextConfiguration(classes = { SponsorExcelFileToDatabaseJobConfig.class})

@ContextConfiguration(classes = SponsorJobConfig.class, loader = AnnotationConfigContextLoader.class)
//@WebIntegrationTest
public class SponsorTest {

	 @Autowired
	    private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Test
	public void test() {
		assertNotNull(jobLauncherTestUtils);
		
	}
	
	@Test
    public void launchJob() throws Exception {
		
	}
	
	
}
