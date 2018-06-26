package com.batch.manage.dataloader.model.entity.receipts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Receipts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long receiptId;
	
	@Column(name="rdate")
	private String rDate;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="middlename")
	private String middleName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="fullname")
	private String fullName;
	
	@Column(name="transaction")
	private String transaction;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="initiativeid")
	private Long initiativeId;
	
	@Column(name="streetaddress")
	private String streetAddress;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="zipcode")
	private String zipCode;
	
	/*@Column(name="centerid")
	private Long centerId;*/
	
	@Column(name="receipttype")
	private int receiptType;
	
	@Column(name="referenceid")
	private Long referenceId;
	
	@Column(name="email1")
	private String email1;
	
	@Column(name="email2")
	private String email2;
	
	@Column(name="phone1")
	private String phone1;
	
	@Column(name="phone2")
	private String phone2;
	
	@Column(name="type")
	private int type;
	
	@Column(name="createdby")
	private Long createdby;

	@Column(name="status")
	private int status;
	
	@Column(name="updatedby")
	private Long updatedBy;

	public Long getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Long receiptId) {
		this.receiptId = receiptId;
	}

	public String getrDate() {
		return rDate;
	}

	public void setrDate(String rDate) {
		this.rDate = rDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Long getInitiativeId() {
		return initiativeId;
	}

	public void setInitiativeId(Long initiativeId) {
		this.initiativeId = initiativeId;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	


	public int getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(int receiptType) {
		this.receiptType = receiptType;
	}

	public Long getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(Long createdby) {
		this.createdby = createdby;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "Receipts [receiptId=" + receiptId + ", rDate=" + rDate + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", fullName=" + fullName + ", transaction=" + transaction
				+ ", amount=" + amount + ", initiativeId=" + initiativeId + ", streetAddress=" + streetAddress
				+ ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", receiptType=" + receiptType + ", referenceId=" + referenceId + ", email1=" + email1 + ", email2="
				+ email2 + ", phone1=" + phone1 + ", phone2=" + phone2 + ", type=" + type + ", createdby=" + createdby
				+ ", status=" + status + ", updatedBy=" + updatedBy + "]";
	}

	
	
	

}
