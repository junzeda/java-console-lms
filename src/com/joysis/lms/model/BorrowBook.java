package com.joysis.lms.model;

import java.time.LocalDateTime;

public class BorrowBook {
	private int borrowId;
	private String studentId;
	private int bookId;
	private LocalDateTime borrowDate;
	private LocalDateTime dueDate;
	private LocalDateTime returnDate;
	private boolean isReturned;
	private boolean isLate;
	private double fineAmount;
	private String requestStatus;
	private String remarks;
	private LocalDateTime requestedAt;
	
	public BorrowBook() {}
	
	
	
	public BorrowBook(int borrowId, String studentId, int bookId, LocalDateTime borrowDate, LocalDateTime dueDate,
			LocalDateTime returnDate, boolean isReturned, boolean isLate, double fineAmount, String requestStatus,
			String remarks, LocalDateTime requestedAt) {
		super();
		this.borrowId = borrowId;
		this.studentId = studentId;
		this.bookId = bookId;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.isReturned = isReturned;
		this.isLate = isLate;
		this.fineAmount = fineAmount;
		this.requestStatus = requestStatus;
		this.remarks = remarks;
		this.requestedAt = requestedAt;
	}



	public String getRequestStatus() {
		return requestStatus;
	}

	public int getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public LocalDateTime getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(LocalDateTime borrowDate) {
		this.borrowDate = borrowDate;
	}
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	public LocalDateTime getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}
	public boolean isReturned() {
		return isReturned;
	}
	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}
	public boolean isLate() {
		return isLate;
	}
	public void setLate(boolean isLate) {
		this.isLate = isLate;
	}
	public double getFineAmount() {
		return fineAmount;
	}
	public void setFineAmount(double fineAmount) {
		this.fineAmount = fineAmount;
	}
	public String isRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public LocalDateTime getRequestedAt() {
		return requestedAt;
	}
	public void setRequestedAt(LocalDateTime requestedAt) {
		this.requestedAt = requestedAt;
	}
	
	
}
