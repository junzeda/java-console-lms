package com.joysis.lms.model;

import java.time.LocalDateTime;

public class Book {
	private int bookId;
	private String title;
	private int authorId;
	private int publisherId;
	private String isbn;
	private int categoryId;
	private int publicationYear;
	private int pageCount;
	private String language;
	private int availableCopies;
	private int issuedBooks;
	private boolean isAvailable;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public Book() {}
	
	public Book(String title, int authorId, int publisherId, String isbn, int categoryId, int publicationYear, int pageCount,
			String language, int availableCopies) {
		super();
		this.title = title;
		this.authorId = authorId;
		this.publisherId = publisherId;
		this.isbn = isbn;
		this.categoryId = categoryId;
		this.publicationYear = publicationYear;
		this.pageCount = pageCount;
		this.language = language;
		this.availableCopies = availableCopies;
		
	}
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getAvailableCopies() {
		return availableCopies;
	}
	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}
	public int getIssuedBooks() {
		return issuedBooks;
	}
	public void setIssuedBooks(int issuedBooks) {
		this.issuedBooks = issuedBooks;
	}
	public boolean getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
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
