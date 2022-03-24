package com.batch.manage.dataloader.common.s3;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

@Service
public class S3Wrapper {

	@Autowired
	private AmazonS3 amazonS3Client;

	@Value("${amazon.s3.default-bucket}")
	private String bucket;
	
	@Value("${amazon.s3.student-profile-picture}")
	private String student_profile_picture_bucket;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(S3Wrapper.class);


	public byte[] downloadDataFile(String file) throws IOException {

		LOGGER.debug("Downloading student file,  " +file);
		
		GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, file);

		S3Object s3Object = amazonS3Client.getObject(getObjectRequest);

		S3ObjectInputStream objectInputStream = s3Object.getObjectContent();

		byte[] bytes = IOUtils.toByteArray(objectInputStream);

		return bytes;
	}
	
	public PutObjectResult upload(byte[] image, Long uploadKey, String imageLinkRef, Long project, String uploadedBy) {
		
		String bucket = student_profile_picture_bucket+"/"+project;
		
		StringBuilder sb = new StringBuilder();
		sb.append(uploadKey);
		if(!StringUtils.isEmpty(imageLinkRef)) {
			sb.append("-");
			sb.append(imageLinkRef);
		}
		sb.append(".png");
		
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType("image/png");
        metadata.addUserMetadata("x-amz-meta-title", "uploaded by "+uploadedBy);
        InputStream stream = new ByteArrayInputStream(image);
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, sb.toString(), stream, metadata);

		putObjectRequest.setCannedAcl(CannedAccessControlList.Private);

		PutObjectResult putObjectResult = amazonS3Client.putObject(putObjectRequest);

		IOUtils.closeQuietly(stream);

		return putObjectResult;
	}

}