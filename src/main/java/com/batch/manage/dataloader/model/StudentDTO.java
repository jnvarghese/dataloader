package com.batch.manage.dataloader.model;

public class StudentDTO {
	
	private String studentId;
	
	private String nameOfChild;
	
	private String gender;
	
	private String grade;
	
	private String favoriteColor;
	
	private String favoriteGame;
	
	private String nameOfParent;
	
	private String occupationOfParent;
	
	private String motherTongue;
	
	private String dateOfBirth;
	
	private String address;
	
	private String hobby;
	
	private String projectLocation;
	
	private String talent;
	
	private String recentAchivements;
	
	private String addLinkForPicture;

	
	

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getFavoriteColor() {
		return favoriteColor;
	}

	public void setFavoriteColor(String favoriteColor) {
		this.favoriteColor = favoriteColor;
	}

	public String getFavoriteGame() {
		return favoriteGame;
	}

	public void setFavoriteGame(String favoriteGame) {
		this.favoriteGame = favoriteGame;
	}


	public String getNameOfParent() {
		return nameOfParent;
	}

	public void setNameOfParent(String nameOfParent) {
		this.nameOfParent = nameOfParent;
	}

	public String getOccupationOfParent() {
		return occupationOfParent;
	}

	public void setOccupationOfParent(String occupationOfParent) {
		this.occupationOfParent = occupationOfParent;
	}

	
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNameOfChild() {
		return nameOfChild;
	}

	public void setNameOfChild(String nameOfChild) {
		this.nameOfChild = nameOfChild;
	}

	public String getMotherTongue() {
		return motherTongue;
	}

	public void setMotherTongue(String motherTongue) {
		this.motherTongue = motherTongue;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getTalent() {
		return talent;
	}

	public void setTalent(String talent) {
		this.talent = talent;
	}

	public String getRecentAchivements() {
		return recentAchivements;
	}

	public void setRecentAchivements(String recentAchivements) {
		this.recentAchivements = recentAchivements;
	}

	public String getAddLinkForPicture() {
		return addLinkForPicture;
	}

	public void setAddLinkForPicture(String addLinkForPicture) {
		this.addLinkForPicture = addLinkForPicture;
	}

	public String getProjectLocation() {
		return projectLocation;
	}

	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}

	@Override
	public String toString() {
		return "StudentDTO [studentId=" + studentId + ", nameOfChild=" + nameOfChild + ", gender=" + gender + ", grade="
				+ grade + ", favoriteColor=" + favoriteColor + ", favoriteGame=" + favoriteGame + ", nameOfParent="
				+ nameOfParent + ", occupationOfParent=" + occupationOfParent + ", motherTongue=" + motherTongue
				+ ", dateOfBirth=" + dateOfBirth + ", address=" + address + ", hobby=" + hobby + ", projectLocation="
				+ projectLocation + ", talent=" + talent + ", recentAchivements=" + recentAchivements
				+ ", addLinkForPicture=" + addLinkForPicture + "]";
	}
	  
    
}
