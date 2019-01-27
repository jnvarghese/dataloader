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
public class StudentMaxout {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "studentid")
	private Long studentId;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollmentid", nullable = false)
	private Enrollment enrollment;
	/*@Column(name="enrollmentid")
	private Long enrollmentId;*/
	@Column(name="maxout")
	private Date maxOut;
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
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
	public StudentMaxout(Long studentId, Enrollment enrollment, Date maxOut) {
		super();
		this.studentId = studentId;
		this.enrollment = enrollment;
		this.maxOut = maxOut;
	}
	@Override
	public String toString() {
		return "StudentMaxout [id=" + id + ", studentId=" + studentId + ", enrollment=" + enrollment + ", maxOut="
				+ maxOut + "]";
	}

	
}
