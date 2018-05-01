package com.batch.manage.dataloader.model.entity.enrollment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SponsorMaxout {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "sponsorid")
	private Long sponsorId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollmentid", nullable = false)
	private Enrollment enrollment;
	/*@Column(name = "enrollmentid")
	private Long enrollmentId;*/
	@Column(name = "maxout")
	private Date maxOut;
	public Long getSponsorId() {
		return sponsorId;
	}
	public void setSponsorId(Long sponsorId) {
		this.sponsorId = sponsorId;
	}
	
	public Enrollment getEnrollment() {
		return enrollment;
	}
	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}
	public Date getMaxOut() {
		return maxOut;
	}
	public void setMaxOut(Date maxOut) {
		this.maxOut = maxOut;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SponsorMaxout(Enrollment enrollment, Date maxOut) {
		super();
		this.sponsorId = enrollment.getSpn().getId();
		this.enrollment = enrollment;
		this.maxOut = maxOut;
	}
	@Override
	public String toString() {
		return "SponsorMaxout [id=" + id + ", sponsorId=" + sponsorId +  ", maxOut="
				+ maxOut + "]";
	}
	
}
