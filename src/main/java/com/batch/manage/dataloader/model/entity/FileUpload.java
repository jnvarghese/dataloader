package com.batch.manage.dataloader.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FileUpload {

	@Id
	private Long id;
	
	@Column(name="referenceid")
	private Long referenceId;
	
	@Column(name="initiativeid")
	private Long initiativeId;
	
	@Column(name="filename")
	private String fileName;
	
	@Column(name="filedata")
	private byte[] fileData;
	
	private String status;
	
	private String type;
	
	private String uploaduri;
	
	@Column(name="jobid")
	private Long jobId;
	
	@Column(name="batchexecutionstatus")
	int batchExecutionStatus;
	
	

	public Long getInitiativeId() {
		return initiativeId;
	}

	public void setInitiativeId(Long initiativeId) {
		this.initiativeId = initiativeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUploaduri() {
		return uploaduri;
	}

	public void setUploaduri(String uploaduri) {
		this.uploaduri = uploaduri;
	}

	public int getBatchExecutionStatus() {
		return batchExecutionStatus;
	}

	public void setBatchExecutionStatus(int batchExecutionStatus) {
		this.batchExecutionStatus = batchExecutionStatus;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

}
