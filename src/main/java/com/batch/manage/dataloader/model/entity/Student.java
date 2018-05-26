package com.batch.manage.dataloader.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="projectid")
	private Long projectId;
	
	@Column(name="studentname")
	private String studentName;
	
	//@Column(name="studentcode")
	//private String studentCode;
	
	private String gender;
	
	private String grade;
	
	@Column(name="favcolor")
	private String favColor;
	
	@Column(name="favgame")
	private String favGame;
	
	@Column(name="nameofguardian")
	private String nameOfGuardian;
	
	@Column(name="occupationofguardian")
	private String occupationOfGuardian;
	
	@Column(name="baselanguage")
	private String baseLanguage;
	
	@Column(name="dateofbirth")
	private String dateOfBirth;
	
	@Column(name="jobid")
	private Long jobId;
	
	private String address;
	
	private String hobbies;
	
	private String talent;
	
	@Column(name="createdby")
	private Long createdBy;
	
	@Column(name="recentachivements")
	private String recentAchivements;
	
	@Column(name="imagelinkref")
	private String imageLinkRef;
	
	@Column(name="profilepicture")
	private byte[] profilePicture;
	
	
	
	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
/*
	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}
*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public String getFavColor() {
		return favColor;
	}

	public void setFavColor(String favColor) {
		this.favColor = favColor;
	}

	public String getFavGame() {
		return favGame;
	}

	public void setFavGame(String favGame) {
		this.favGame = favGame;
	}

	public String getNameOfGuardian() {
		return nameOfGuardian;
	}

	public void setNameOfGuardian(String nameOfGuardian) {
		this.nameOfGuardian = nameOfGuardian;
	}

	public String getOccupationOfGuardian() {
		return occupationOfGuardian;
	}

	public void setOccupationOfGuardian(String occupationOfGuardian) {
		this.occupationOfGuardian = occupationOfGuardian;
	}

	public String getBaseLanguage() {
		return baseLanguage;
	}

	public void setBaseLanguage(String baseLanguage) {
		this.baseLanguage = baseLanguage;
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

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
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

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	
	public String getImageLinkRef() {
		return imageLinkRef;
	}

	public void setImageLinkRef(String imageLinkRef) {
		this.imageLinkRef = imageLinkRef;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", projectId=" + projectId + ", studentName=" + studentName + ", gender=" + gender
				+ ", grade=" + grade + ", favColor=" + favColor + ", favGame=" + favGame + ", nameOfGuardian="
				+ nameOfGuardian + ", occupationOfGuardian=" + occupationOfGuardian + ", baseLanguage=" + baseLanguage
				+ ", dateOfBirth=" + dateOfBirth + ", jobId=" + jobId + ", address=" + address + ", hobbies=" + hobbies
				+ ", talent=" + talent + ", createdBy=" + createdBy + ", recentAchivements=" + recentAchivements + "]";
	}	
	
}
