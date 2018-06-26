package com.batch.manage.dataloader.config.receiptsJob;

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

import com.batch.manage.dataloader.common.receipt.ReceiptProcessor;
import com.batch.manage.dataloader.common.receipt.ReceiptWriter;
import com.batch.manage.dataloader.listener.DataLoadJobExecutionListener;
import com.batch.manage.dataloader.model.ReceiptDTO;
import com.batch.manage.dataloader.model.entity.receipts.Receipts;

@Configuration
public class ReceiptsExcelFileToDatabaseJobConfig {

	@Bean
	@StepScope
	ItemStreamReader<ReceiptDTO> excelReceiptsReader(@Value("#{jobExecutionContext['data']}") byte[] data) {
		PoiItemReader<ReceiptDTO> reader = new PoiItemReader<>();
		reader.setLinesToSkip(1);
		reader.setInputStream(data);
		reader.setRowMapper(excelRowMapper());
		return reader;
	}

	private RowMapper<ReceiptDTO> excelRowMapper() {
		BeanWrapperRowMapper<ReceiptDTO> rowMapper = new BeanWrapperRowMapper<>();
		rowMapper.setTargetType(ReceiptDTO.class);
		return rowMapper;
	}

	@Bean
	@StepScope
	ItemProcessor<ReceiptDTO, Receipts> excelReceiptProcessor(
			@Value("#{jobExecutionContext['jobId']}") Long jobId) {
		return new ReceiptProcessor();
	}

	@Bean
	ItemWriter<Receipts> excelReceiptsWriter() {
		return new ReceiptWriter();
	}

	@Bean
	DataLoadJobExecutionListener dataLoadJobExecutionListener() {
		return new DataLoadJobExecutionListener();
	}

	@Bean
	Step receiptExcelFileToDatabaseStep(ItemReader<ReceiptDTO> excelReceiptsReader,
			ItemProcessor<ReceiptDTO, Receipts> excelReceiptProcessor,
			ItemWriter<Receipts> excelReceiptsWriter, StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("receiptExcelFileToDatabaseStep").<ReceiptDTO, Receipts>chunk(5)
				.reader(excelReceiptsReader).processor(excelReceiptProcessor).writer(excelReceiptsWriter)
				.build();
	}

	@Bean
	Job receiptExcelFileToDatabaseJob(JobBuilderFactory jobBuilderFactory,
			@Qualifier("receiptExcelFileToDatabaseStep") Step excelStudentStep) {
		return jobBuilderFactory.get("receiptExcelFileToDatabaseJob").incrementer(new RunIdIncrementer())
				.listener(dataLoadJobExecutionListener()).flow(excelStudentStep).end().build();
	}
}
