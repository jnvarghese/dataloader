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
public class Sponsee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollmentid", nullable = false)
	private Enrollment enrollment;
	//@Column(name="enrollmentid")
	//private Long enrollmentId;
	
	@Column(name="expirationmonth")
	private int expirationMonth;
	
	@Column(name="expirationyear")
	private int expirationYear;
	
	@Column(name="studentid")
	private Long studentId;
	
	@Column(name="createddate")
	private Date createdDate;

	@Column(name="updateddate")
	private Date updatedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Enrollment getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}

	public int getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public int getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Sponsee(Enrollment enrollment, int expirationMonth, int expirationYear, Long studentId) {
		super();
		this.enrollment = enrollment;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.studentId = studentId;
	}
	
	
}
