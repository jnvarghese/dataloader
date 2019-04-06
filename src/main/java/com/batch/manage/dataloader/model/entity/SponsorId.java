package com.batch.manage.dataloader.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SponsorId {

	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "SponsorId [id=" + id + "]";
	}
		
}
