package com.batch.manage.dataloader.model;

public class ReceiptDTO {

	private String receipt;
	
	private String date;
	
	private String firstname;
	
	private String middlename;
	
	private String lastname;
	
	private String fullname;
	
	private String transaction;
	
	private String amount;
	
	private String initiative;
	
	private String streetaddress;
	
	private String city;
	
	private String state;
	
	private String zip;
	
	private String parish;
	
	private String code;
	
	private String email1;
	
	private String email2;
	
	private String phone1;
	
	private String phone2;
	
	private String category;
	
	private String type;
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getInitiative() {
		return initiative;
	}

	public void setInitiative(String initiative) {
		this.initiative = initiative;
	}

	public String getStreetaddress() {
		return streetaddress;
	}

	public void setStreetaddress(String streetaddress) {
		this.streetaddress = streetaddress;
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getParish() {
		return parish;
	}

	public void setParish(String parish) {
		this.parish = parish;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	@Override
	public String toString() {
		return "ReceiptDTO [receipt=" + receipt + ", date=" + date + ", firstname=" + firstname + ", middlename="
				+ middlename + ", lastname=" + lastname + ", fullname=" + fullname + ", transaction=" + transaction
				+ ", amount=" + amount + ", initiative=" + initiative + ", streetaddress=" + streetaddress + ", city="
				+ city + ", state=" + state + ", zip=" + zip + ", parish=" + parish + ", code=" + code + ", email1="
				+ email1 + ", email2=" + email2 + ", phone1=" + phone1 + ", phone2=" + phone2 + ", category=" + category
				+ ", type=" + type + "]";
	}
	
	
	
}
