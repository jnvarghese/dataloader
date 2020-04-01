package com.batch.manage.dataloader.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3AutoConfiguration {

	@Value("${amazon.s3.region}")
	private static String region;

	 @Bean
	    public static AmazonS3Client amazonS3Client() {
	        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
	        		.withRegion(Regions.US_EAST_2)
	                .withCredentials(new DefaultAWSCredentialsProviderChain())
	                .build();
	    }
}