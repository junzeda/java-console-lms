package com.joysis.lms.model;

import java.time.LocalDateTime;

public class Publisher {
	
	private int publisherId;
	private String name;
	private String email;
	private String contactNumber;
	private String address;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public Publisher() {}
	
	public Publisher(String name, String email, String contactNumber, String address) {
		super();
		this.name = name;
		this.email = email;
		this.contactNumber = contactNumber;
		this.address = address;
	}

	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
