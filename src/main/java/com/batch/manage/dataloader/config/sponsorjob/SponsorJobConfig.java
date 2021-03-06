package com.batch.manage.dataloader.config.sponsorjob;

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

import com.batch.manage.dataloader.common.sponsor.SponsorProcessor;
import com.batch.manage.dataloader.common.sponsor.SponsorWriter;
import com.batch.manage.dataloader.listener.DataLoadJobExecutionListener;
import com.batch.manage.dataloader.model.SponsorDTO;
import com.batch.manage.dataloader.model.entity.Sponsor;

@Configuration
public class SponsorJobConfig {

	@Bean
	@StepScope
	ItemStreamReader<SponsorDTO> excelSponsorReader(@Value("#{jobExecutionContext['data']}") byte[] data) {
		PoiItemReader<SponsorDTO> reader = new PoiItemReader<>();
		reader.setLinesToSkip(1);
		reader.setInputStream(data);
		reader.setRowMapper(excelRowMapper());
		return reader;
	}

	private RowMapper<SponsorDTO> excelRowMapper() {
		BeanWrapperRowMapper<SponsorDTO> rowMapper = new BeanWrapperRowMapper<>();
		rowMapper.setTargetType(SponsorDTO.class);
		return rowMapper;
	}

	@Bean
	@StepScope
	ItemProcessor<SponsorDTO, Sponsor> excelSponsorProcessor(@Value("#{jobExecutionContext['jobId']}") Long jobId,
			@Value("#{jobExecutionContext['referenceId']}") Long parishId,
			@Value("#{jobExecutionContext['parishName']}") String parishName,
			@Value("#{jobExecutionContext['missionname']}") String missionname,
			@Value("#{jobExecutionContext['startingCode']}") String startingCode,
			@Value("#{jobExecutionContext['startingStudentCode']}") String startingStudentCode,
			@Value("#{jobExecutionContext['category']}") String category) {
		return new SponsorProcessor(jobId, parishId, parishName, missionname, startingCode, startingStudentCode, category);
	}

	@Bean
	ItemWriter<Sponsor> excelSponsorWriter() {
		return new SponsorWriter();
	}

	@Bean
	DataLoadJobExecutionListener dataLoadJobExecutionListener() {
		return new DataLoadJobExecutionListener();
	}

	@Bean
	Step sponsorStep(ItemReader<SponsorDTO> excelSponsorReader,
			ItemProcessor<SponsorDTO, Sponsor> excelSponsorProcessor, ItemWriter<Sponsor> excelSponsorWriter,
			StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("sponsorStep").<SponsorDTO, Sponsor>chunk(1)
				.reader(excelSponsorReader).processor(excelSponsorProcessor).writer(excelSponsorWriter).build();
	}

	@Bean
	Job sponsorJob(JobBuilderFactory jobBuilderFactory,
			@Qualifier("sponsorStep") Step excelStudentStep) {
		return jobBuilderFactory.get("sponsorJob").incrementer(new RunIdIncrementer())
				.listener(dataLoadJobExecutionListener()).flow(excelStudentStep).end().build();
	}
}