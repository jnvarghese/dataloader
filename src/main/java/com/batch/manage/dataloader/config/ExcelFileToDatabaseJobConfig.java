package com.batch.manage.dataloader.config;

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

import com.batch.manage.dataloader.common.StudentProcessor;
import com.batch.manage.dataloader.common.StudentWriter;
import com.batch.manage.dataloader.listener.DataLoadJobExecutionListener;
import com.batch.manage.dataloader.model.StudentDTO;
import com.batch.manage.dataloader.model.entity.Student;

//@Configuration	
public class ExcelFileToDatabaseJobConfig {
    
    //@Bean
    //@StepScope
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

    /**
     * If your Excel document has no header, you have to create a custom
     * row mapper and configure it here.
     */
    /*private RowMapper<StudentDTO> excelRowMapper() {
       return new StudentExcelRowMapper();
    }*/

   // @Bean
   // @StepScope
    ItemProcessor<StudentDTO, Student> excelStudentProcessor(
    		@Value("#{jobExecutionContext['jobId']}") Long jobId,
    		@Value("#{jobExecutionContext['projectId']}") Long projectId) {
        return new StudentProcessor(jobId, projectId);
    }

   // @Bean
    ItemWriter<Student> excelStudentWriter() {
        return new StudentWriter();
    }
    
   // @Bean
    DataLoadJobExecutionListener dataLoadJobExecutionListener() {
    	return new DataLoadJobExecutionListener();    	
    }
   // @Bean
    Step excelFileToDatabaseStep(ItemReader<StudentDTO> excelStudentReader,
                                 ItemProcessor<StudentDTO, Student> excelStudentProcessor,
                                 ItemWriter<Student> excelStudentWriter,
                                 StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("excelFileToDatabaseStep")
        		.<StudentDTO, Student>chunk(50)
                .reader(excelStudentReader)
                .processor(excelStudentProcessor)
                .writer(excelStudentWriter)                
                .build();
    }

   // @Bean
    Job excelFileToDatabaseJob(JobBuilderFactory jobBuilderFactory,
                               @Qualifier("excelFileToDatabaseStep") Step excelStudentStep) {
        return jobBuilderFactory.get("excelFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .listener(dataLoadJobExecutionListener())
                .flow(excelStudentStep)
                .end()
                .build();
    }
}