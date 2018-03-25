package com.batch.manage.dataloader.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FileUpload {

	@Id
	Long id;
	
	@Column(name="agencyid")
	Long agencyId;
	
	@Column(name="projectid")
	Long projectId;
	
	@Column(name="filename")
	String fileName;
	
	@Column(name="filedata")
	byte[] fileData;
	
	String status;
	
	@Column(name="batchexecutionstatus")
	int batchExecutionStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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

	public int getBatchExecutionStatus() {
		return batchExecutionStatus;
	}

	public void setBatchExecutionStatus(int batchExecutionStatus) {
		this.batchExecutionStatus = batchExecutionStatus;
	}
	
	
	
	
}
