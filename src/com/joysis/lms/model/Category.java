package com.joysis.lms.model;

import java.time.LocalDateTime;

public class Category {

	private int bookCategoryId;
	private String categoryName;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public Category() {}
	
	public void setBookCategoryId(int bookCategoryId) {
		this.bookCategoryId = bookCategoryId;
	}
	
	public int getBookCategoryId() {
		return bookCategoryId;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}
