package com.batch.manage.dataloader.common.s3;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

@Service
public class S3Wrapper {

	@Autowired
	private AmazonS3 amazonS3Client;

	@Value("${amazon.s3.default-bucket}")
	private String bucket;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(S3Wrapper.class);


	public byte[] downloadDataFile(String file) throws IOException {

		LOGGER.debug("Downloading student file,  " +file);
		
		GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, file);

		S3Object s3Object = amazonS3Client.getObject(getObjectRequest);

		S3ObjectInputStream objectInputStream = s3Object.getObjectContent();

		byte[] bytes = IOUtils.toByteArray(objectInputStream);

		return bytes;
	}

}