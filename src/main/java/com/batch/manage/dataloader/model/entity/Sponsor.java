package com.batch.manage.dataloader.model.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.batch.manage.dataloader.model.entity.enrollment.Enrollment;
import com.batch.manage.dataloader.model.entity.enrollment.SponsorMaxout;

@Entity
public class Sponsor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(mappedBy = "spn", cascade = CascadeType.ALL )
	private Enrollment ern;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "spnMax")
	private Set<SponsorMaxout> sponsorMaxOuts  = new HashSet<>();
	
	@Column(name="parishid")
	private Long parishId;

	@Column(name="sponsorcode")
	private String sponsorCode;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="middleinitial")
	private String middleInitial;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="nickname")
	private String nickName;
	
	@Column(name="dayofbirth")
	private String dayOfBirth;
	
	@Column(name="monthofbirth")
	private String monthOfBirth;
	
	@Column(name="sponsorstatus")
	private int sponsorStatus;
	
	@Column(name="emailaddress")
	private String emailAddress;
	
	@Column(name="emailaddress2")
	private String emailAddress2;
	
	@Column(name="transactionremarks")
	private String transactionRemarks;	
	
	@Column(name="createdby")
	private Long createdBy;
	
	//@Column(name="appartmentnumber")
	//private String appartmentNumber;
	
	private String street;
	
	private String city;
	
	private String state;
	
	@Column(name="postalcode")
	private String postalCode;
	
	@Column(name="hasanycosponser")
	private boolean hasAnyCoSponser;
	
	@Column(name="cosponsername")
	private String coSponserName;
	
	@Column(name="createddate")
	private Date createdDate;
	
	@Column(name="updateddate")
	private Date updatedDate;
	
	@Column(name="jobid")
	private Long jobId;

	private String phone1;
	
	private String phone2;
	
	
	
	
	

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getEmailAddress2() {
		return emailAddress2;
	}

	public void setEmailAddress2(String emailAddress2) {
		this.emailAddress2 = emailAddress2;
	}

	public String getTransactionRemarks() {
		return transactionRemarks;
	}

	public void setTransactionRemarks(String transactionRemarks) {
		this.transactionRemarks = transactionRemarks;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParishId() {
		return parishId;
	}

	public void setParishId(Long parishId) {
		this.parishId = parishId;
	}

	public String getSponsorCode() {
		return sponsorCode;
	}

	public void setSponsorCode(String sponsorCode) {
		this.sponsorCode = sponsorCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(String dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public String getMonthOfBirth() {
		return monthOfBirth;
	}

	public void setMonthOfBirth(String monthOfBirth) {
		this.monthOfBirth = monthOfBirth;
	}

	public int getSponsorStatus() {
		return sponsorStatus;
	}

	public void setSponsorStatus(int sponsorStatus) {
		this.sponsorStatus = sponsorStatus;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
    /*
	public String getAppartmentNumber() {
		return appartmentNumber;
	}

	public void setAppartmentNumber(String appartmentNumber) {
		this.appartmentNumber = appartmentNumber;
	} */

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public boolean isHasAnyCoSponser() {
		return hasAnyCoSponser;
	}

	public void setHasAnyCoSponser(boolean hasAnyCoSponser) {
		this.hasAnyCoSponser = hasAnyCoSponser;
	}

	public String getCoSponserName() {
		return coSponserName;
	}

	public void setCoSponserName(String coSponserName) {
		this.coSponserName = coSponserName;
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

	public Enrollment getErn() {
		return ern;
	}

	public void setErn(Enrollment ern) {
		this.ern = ern;
	}
	public Set<SponsorMaxout> getSponsorMaxOuts() {
		return sponsorMaxOuts;
	}

	public void setSponsorMaxOuts(Set<SponsorMaxout> sponsorMaxOuts) {
		this.sponsorMaxOuts = sponsorMaxOuts;
	}

	@Override
	public String toString() {
		return "Sponsor [id=" + id + ", ern=" + ern + ", sponsorMaxOuts=" + sponsorMaxOuts + ", parishId=" + parishId
				+ ", sponsorCode=" + sponsorCode + ", firstName=" + firstName + ", middleInitial=" + middleInitial
				+ ", lastName=" + lastName + ", nickName=" + nickName + ", dayOfBirth=" + dayOfBirth + ", monthOfBirth="
				+ monthOfBirth + ", sponsorStatus=" + sponsorStatus + ", emailAddress=" + emailAddress
				+ ", emailAddress2=" + emailAddress2 + ", transactionRemarks=" + transactionRemarks + ", createdBy="
				+ createdBy + ", street=" + street + ", city=" + city + ", state=" + state + ", postalCode="
				+ postalCode + ", hasAnyCoSponser=" + hasAnyCoSponser + ", coSponserName=" + coSponserName
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", jobId=" + jobId + ", phone1="
				+ phone1 + ", phone2=" + phone2 + "]";
	}


	
}
