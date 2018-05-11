package com.batch.manage.dataloader.config.enrollmentjob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.batch.manage.dataloader.common.enrollment.EnrollmentProcessor;
import com.batch.manage.dataloader.common.enrollment.EnrollmentWritter;
import com.batch.manage.dataloader.listener.DataLoadJobExecutionListener;
import com.batch.manage.dataloader.model.EnrollmentDTO;
import com.batch.manage.dataloader.model.entity.enrollment.Enrollment;

@Configuration
public class EnrollmentExcelFileToDatabaseJobConfig {

	@Bean
	@StepScope
	ItemStreamReader<EnrollmentDTO> excelEnrollmentReader(@Value("#{jobExecutionContext['data']}") byte[] data) {
		PoiItemReader<EnrollmentDTO> reader = new PoiItemReader<>();
		reader.setLinesToSkip(1);
		reader.setInputStream(data);
		reader.setRowMapper(excelRowMapper());
		return reader;
	}

	private RowMapper<EnrollmentDTO> excelRowMapper() {
		BeanWrapperRowMapper<EnrollmentDTO> rowMapper = new BeanWrapperRowMapper<>();
		rowMapper.setTargetType(EnrollmentDTO.class);
		return rowMapper;
	}

	@Bean
	@StepScope
	ItemProcessor<EnrollmentDTO, Enrollment> excelEnrollmentProcessor(
			@Value("#{jobExecutionContext['jobId']}") Long jobId) {
		return new EnrollmentProcessor(jobId);
	}

	@Bean
	ItemWriter<Enrollment> excelEnrollmentWriter() {
		return new EnrollmentWritter();
	}

	@Bean
	DataLoadJobExecutionListener dataLoadJobExecutionListener() {
		return new DataLoadJobExecutionListener();
	}

	@Bean
	Step sponsorExcelFileToDatabaseStep(ItemReader<EnrollmentDTO> excelEnrollmentReader,
			ItemProcessor<EnrollmentDTO, Enrollment> excelEnrollmentProcessor,
			ItemWriter<Enrollment> excelEnrollmentWriter, StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("sponsorExcelFileToDatabaseStep").<EnrollmentDTO, Enrollment>chunk(5)
				.reader(excelEnrollmentReader).processor(excelEnrollmentProcessor).writer(excelEnrollmentWriter)
				.build();
	}

	@Bean
	Job sponsorExcelFileToDatabaseJob(JobBuilderFactory jobBuilderFactory,
			@Qualifier("sponsorExcelFileToDatabaseStep") Step excelStudentStep) {
		return jobBuilderFactory.get("sponsorExcelFileToDatabaseJob").incrementer(new RunIdIncrementer())
				.listener(dataLoadJobExecutionListener()).flow(excelStudentStep).end().build();
	}
}
