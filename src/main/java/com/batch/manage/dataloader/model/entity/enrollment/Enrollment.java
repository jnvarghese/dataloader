package com.batch.manage.dataloader.model.entity.enrollment;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.batch.manage.dataloader.model.entity.Sponsor;



@Entity
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="jobid")
	private Long jobId;
	// @OneToOne()
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sponsorid")
	private Sponsor spn;
	
	@Column(name="paymentdate")
	private Date paymentDate;
	
	@Column(name="effectivedate")
	private Date effectiveDate;
	
	@Column(name="actualamount")
	private double actualamount;
	
	@Column(name="contributionamount")
	private double contributionAmount;
	
	@Column(name="miscamount")
	private double miscAmount;
	
	@Column(name="netamount")
	private double netAmount;
	
	@Column(name="createdby")
	private Long createdBy;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "enrollment")
	private Set<Sponsee> sponsees  = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "enrollment")
	private Set<StudentMaxout> studentMaxOuts  = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "enrollment")
	private Set<SponsorMaxout> sponsorMaxOuts  = new HashSet<>();
	
	@Transient
	private Date localExpDate;

	public Date getLocalExpDate() {
		return localExpDate;
	}

	public void setLocalExpDate(Date localExpDate) {
		this.localExpDate = localExpDate;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getActualamount() {
		return actualamount;
	}

	public void setActualamount(double actualamount) {
		this.actualamount = actualamount;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public Sponsor getSpn() {
		return spn;
	}

	public void setSpn(Sponsor spn) {
		this.spn = spn;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public double getContributionAmount() {
		return contributionAmount;
	}

	public void setContributionAmount(double contributionAmount) {
		this.contributionAmount = contributionAmount;
	}

	public double getMiscAmount() {
		return miscAmount;
	}

	public void setMiscAmount(double miscAmount) {
		this.miscAmount = miscAmount;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Set<Sponsee> getSponsees() {
		return sponsees;
	}

	public void setSponsees(Set<Sponsee> sponsees) {
		this.sponsees = sponsees;
	}

	public Set<StudentMaxout> getStudentMaxOuts() {
		return studentMaxOuts;
	}

	public void setStudentMaxOuts(Set<StudentMaxout> studentMaxOuts) {
		this.studentMaxOuts = studentMaxOuts;
	}

	public Set<SponsorMaxout> getSponsorMaxOuts() {
		return sponsorMaxOuts;
	}

	public void setSponsorMaxOuts(Set<SponsorMaxout> sponsorMaxOuts) {
		this.sponsorMaxOuts = sponsorMaxOuts;
	}


}
