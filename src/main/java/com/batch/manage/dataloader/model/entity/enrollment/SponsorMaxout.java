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

import com.batch.manage.dataloader.model.entity.Sponsor;

@Entity
public class SponsorMaxout {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sponsorid", nullable = false)
	private Sponsor spnMax;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollmentid", nullable = false)
	private Enrollment enrollment;
	
	@Column(name = "maxout")
	private Date maxOut;
	
	public Sponsor getSpnMax() {
		return spnMax;
	}
	public void setSpnMax(Sponsor spnMax) {
		this.spnMax = spnMax;
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
	
	
	public SponsorMaxout(Sponsor spnMax, Enrollment enrollment, Date maxOut) {
		super();
		this.spnMax = spnMax;
		this.enrollment = enrollment;
		this.maxOut = maxOut;
	}
	@Override
	public String toString() {
		return "SponsorMaxout [id=" + id + ", spnMax=" + spnMax + ", enrollment=" + enrollment + ", maxOut=" + maxOut
				+ "]";
	}
}
