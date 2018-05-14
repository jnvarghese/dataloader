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

import com.batch.manage.dataloader.model.entity.Receipt;
import com.batch.manage.dataloader.model.entity.Sponsor;



@Entity
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="jobid")
	private Long jobId;
	
	@OneToOne()
	@JoinColumn(name = "sponsorid")
	private Sponsor spn;
	
	@OneToOne
    @JoinColumn(name="receiptid")
    private Receipt receipt;
	
	@Column(name="paymentdate")
	private Date paymentDate;

	@Column(name="effectivedate")
	private Date effectiveDate;

	@Column(name="contributionamount")
	private double contributionAmount;
	
	@Column(name="miscamount")
	private double miscAmount;
	
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
	
	@Transient
	private Date localExpDate;

	
	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

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


}
