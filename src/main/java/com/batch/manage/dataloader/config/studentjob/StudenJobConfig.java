package com.batch.manage.dataloader.config.studentjob;

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

import com.batch.manage.dataloader.common.student.StudentProcessor;
import com.batch.manage.dataloader.common.student.StudentWriter;
import com.batch.manage.dataloader.listener.DataLoadJobExecutionListener;
import com.batch.manage.dataloader.model.StudentDTO;
import com.batch.manage.dataloader.model.entity.Student;

@Configuration
public class StudenJobConfig {

	@Bean
	@StepScope
	ItemStreamReader<StudentDTO> excelStudentReader(@Value("#{jobExecutionContext['data']}") byte[] data) {
		PoiItemReader<StudentDTO> reader = new PoiItemReader<>();
		reader.setLinesToSkip(1);
		reader.setInputStream(data);
		reader.setRowMapper(excelRowMapper());
		return reader;
	}

	private RowMapper<StudentDTO> excelRowMapper() {
		BeanWrapperRowMapper<StudentDTO> rowMapper = new BeanWrapperRowMapper<>();
		rowMapper.setTargetType(StudentDTO.class);
		return rowMapper;
	}

	@Bean
	@StepScope
	ItemProcessor<StudentDTO, Student> excelStudentProcessor(@Value("#{jobExecutionContext['jobId']}") Long jobId,
			@Value("#{jobExecutionContext['referenceId']}") Long projectId) {
		return new StudentProcessor(jobId, projectId);
	}

	@Bean
	ItemWriter<Student> excelStudentWriter() {
		return new StudentWriter();
	}

	@Bean
	DataLoadJobExecutionListener dataLoadJobExecutionListener() {
		return new DataLoadJobExecutionListener();
	}

	@Bean
	Step studentStep(ItemReader<StudentDTO> excelStudentReader,
			ItemProcessor<StudentDTO, Student> excelStudentProcessor, ItemWriter<Student> excelStudentWriter,
			StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("studentStep").<StudentDTO, Student>chunk(50)
				.reader(excelStudentReader).processor(excelStudentProcessor).writer(excelStudentWriter).build();
	}

	@Bean("studentJob")
	Job excelFileToDatabaseJob(JobBuilderFactory jobBuilderFactory,
			@Qualifier("studentStep") Step excelStudentStep) {
		return jobBuilderFactory.get("studentJob").incrementer(new RunIdIncrementer())
				.listener(dataLoadJobExecutionListener()).flow(excelStudentStep).end().build();
	}
}