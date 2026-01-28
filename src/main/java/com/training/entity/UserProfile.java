package com.training.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class UserProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int profileId;

	private String name;
	private char gender;
	private long contactNo;
	private boolean isAccActive;
	private double feePaid;
	private String occupation;
	private LocalDate dob;
	private LocalDateTime joinedAt;

//	@Embedded
//    private Address address;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "home_street")),
			@AttributeOverride(name = "state", column = @Column(name = "home_state")),
			@AttributeOverride(name = "city", column = @Column(name = "home_city")),
			@AttributeOverride(name = "zip", column = @Column(name = "home_zip")),
			@AttributeOverride(name = "country", column = @Column(name = "home_country")) })
	private Address homeAddress;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "office_street")),
			@AttributeOverride(name = "city", column = @Column(name = "office_city")),
			@AttributeOverride(name = "state", column = @Column(name = "office_state")),
			@AttributeOverride(name = "zip", column = @Column(name = "office_zip")),
			@AttributeOverride(name = "country", column = @Column(name = "office_country")) })
	private Address officeAddress;

	@OneToOne(mappedBy = "profile", fetch = FetchType.LAZY)
	private User user;

	public UserProfile() {
		super();
	}

	public UserProfile(int profileId, String name, char gender, long contactNo, boolean isAccActive, double feePaid,
			String occupation, LocalDate dob, LocalDateTime joinedAt) {
		super();
		this.profileId = profileId;
		this.name = name;
		this.gender = gender;
		this.contactNo = contactNo;
		this.isAccActive = isAccActive;
		this.feePaid = feePaid;
		this.occupation = occupation;
		this.dob = dob;
		this.joinedAt = joinedAt;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public boolean isAccActive() {
		return isAccActive;
	}

	public void setAccActive(boolean isAccActive) {
		this.isAccActive = isAccActive;
	}

	public double getFeePaid() {
		return feePaid;
	}

	public void setFeePaid(double feePaid) {
		this.feePaid = feePaid;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public LocalDateTime getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(LocalDateTime joinedAt) {
		this.joinedAt = joinedAt;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Address getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	
}
