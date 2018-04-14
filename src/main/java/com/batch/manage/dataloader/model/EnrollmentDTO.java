package com.batch.manage.dataloader.model;

public class EnrollmentDTO extends SponsorDTO {

	
	
	private String amount;
	
	private String date;
	
	private String total;
	
	private String sponsorId;
	
	private String child1;
	
	private String child2;
	
	private String child3;
	
	private String child4;
	
	private String child5;
	
	private String child6;
	
	private String month;
	
	private String year;

	
	
	
	public String getChild2() {
		return child2;
	}

	public void setChild2(String child2) {
		this.child2 = child2;
	}

	public String getChild3() {
		return child3;
	}

	public void setChild3(String child3) {
		this.child3 = child3;
	}

	public String getChild4() {
		return child4;
	}

	public void setChild4(String child4) {
		this.child4 = child4;
	}

	public String getChild5() {
		return child5;
	}

	public void setChild5(String child5) {
		this.child5 = child5;
	}

	public String getChild6() {
		return child6;
	}

	public void setChild6(String child6) {
		this.child6 = child6;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getSponsorId() {
		return sponsorId;
	}

	public void setSponsorId(String sponsorId) {
		this.sponsorId = sponsorId;
	}

	public String getChild1() {
		return child1;
	}

	public void setChild1(String child1) {
		this.child1 = child1;
	}
/*
	public String getChild2() {
		return child2;
	}

	public void setChild2(String child2) {
		this.child2 = child2;
	}

	public String getChild3() {
		return child3;
	}

	public void setChild3(String child3) {
		this.child3 = child3;
	}

	public String getChild4() {
		return child4;
	}

	public void setChild4(String child4) {
		this.child4 = child4;
	}

	public String getChild5() {
		return child5;
	}

	public void setChild5(String child5) {
		this.child5 = child5;
	}

	public String getChild6() {
		return child6;
	}

	public void setChild6(String child6) {
		this.child6 = child6;
	}
*/
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "EnrollmentDTO [amount=" + amount + ", date=" + date + ", total=" + total + ", sponsorId=" + sponsorId
				+ ", child1=" + child1 + ", month=" + month + ", year=" + year + "]";
	}
	
	
}
