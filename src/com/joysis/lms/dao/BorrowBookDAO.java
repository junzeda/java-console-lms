package com.joysis.lms.dao;

import java.util.List;

import com.joysis.lms.model.BorrowBook;

public interface BorrowBookDAO {
	
	public List<BorrowBook> getAllCompletedTrans();
	public boolean insertBorrowRequest(BorrowBook borrowBook);
	public boolean archiveBorrowBookById(int borrowBookId);
	public int getTotalBorrowBooks();
	public List<BorrowBook> getAllPendingRequest();
	public List<BorrowBook> getAllPendingReturn();
	public BorrowBook getPendingRequestById(int borrowBookId);
	public BorrowBook getPendingReturnById(int borrowBookId);
	public boolean acceptBorrowRequestById(int borrowBookId);
	public boolean rejectBorrowRequestById(int borrowBookId, String remarks);
	public List<BorrowBook> getIncomingDueDateReturn();
	public List<BorrowBook> getOverdueReturn();
	public List<BorrowBook> getStudentTransacHistoryById(String studentId);
	public boolean acceptBookReturn(BorrowBook borrowBook);
	public int getTotalBorrowedToday();
	public int getTotalReturnToday();
	public int getTotalIncomingDue();
	public int getTotalTransaction();
	public List<BorrowBook> getStudentBorrowedBooks(String studentId);
	public List<BorrowBook> getStudentPendingBooks(String studentId);
	public List<BorrowBook> getStudentIncomingDue(String studentId);
	public List<BorrowBook> getStudentOverDue(String studentId);

}
