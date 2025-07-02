package com.joysis.lms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.joysis.lms.config.DBConnection;
import com.joysis.lms.dao.BorrowBookDAO;
import com.joysis.lms.model.BorrowBook;
import com.joysis.lms.util.DateTimeUtil;

public class BorrowBookDAOImpl implements BorrowBookDAO {

	@Override
	public List<BorrowBook> getAllCompletedTrans() {
		List<BorrowBook> borrows = new ArrayList<>();
		String sql = "SELECT * FROM borrow_books WHERE request_status = 'completed' AND is_archived = 0";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			while(rs.next()) {
				BorrowBook borrow = new BorrowBook();
				borrow.setBorrowId(rs.getInt("borrow_id"));
				borrow.setStudentId(rs.getString("student_id"));
				borrow.setBookId(rs.getInt("book_id"));
				borrow.setBorrowDate(rs.getTimestamp("borrow_date").toLocalDateTime());
				borrow.setDueDate(rs.getTimestamp("due_date").toLocalDateTime());
				borrow.setReturnDate(rs.getTimestamp("return_date").toLocalDateTime());
				borrow.setReturned(rs.getBoolean("is_returned"));
				borrow.setLate(rs.getBoolean("is_late"));
				borrow.setFineAmount(rs.getDouble("fine_amount"));
				borrow.setRequestStatus(rs.getString("request_status"));
				borrow.setRemarks(rs.getString("remarks"));
				borrow.setRequestedAt(rs.getTimestamp("requested_at").toLocalDateTime());
				borrows.add(borrow);
				
			}
		} catch (SQLException e) {
			System.out.println("Get All Completed Transaction Failed: " + e.getMessage());
		}
		return borrows;
	}

	@Override
	public boolean insertBorrowRequest(BorrowBook borrowBook) {
		String sql = "INSERT INTO borrow_books (student_id, book_id, request_status, requested_at) VALUES (?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, borrowBook.getStudentId());
			pstmt.setInt(2, borrowBook.getBookId());
			pstmt.setString(3, "pending");

			Timestamp timestamp = DateTimeUtil.getCurrentSqlTimestamp();
			pstmt.setTimestamp(4, timestamp);
			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			System.out.println("Insert Borrow Request Failed: " + e.getMessage());
			return false;
		}
	}


	@Override
	public boolean archiveBorrowBookById(int borrowBookId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getTotalBorrowBooks() {
		String sql  = "SELECT COUNT(*) AS total_rows FROM borrow_books WHERE request_status = 'pending'";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				return rs.getInt("total_row");
			}
		} catch (SQLException e) {
			System.out.println("Get Total Pending Request Failed: " + e.getMessage());
		}
		return 0;
	}

	@Override
	public List<BorrowBook> getAllPendingRequest() {
		List<BorrowBook> borrowBooks = new ArrayList<>();
		String sql = "SELECT borrow_id, student_id, book_id, requested_at FROM borrow_books WHERE request_status = 'pending'";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while(rs.next()) {
				BorrowBook borrow = new BorrowBook();
				borrow.setBorrowId(rs.getInt("borrow_id"));
				borrow.setStudentId(rs.getString("student_id"));
				borrow.setBookId(rs.getInt("book_id"));
				borrow.setRequestedAt(rs.getTimestamp("requested_at").toLocalDateTime());
				borrowBooks.add(borrow);
			}
		} catch (SQLException e) {
			System.out.println("Get All Pending Request Failed: " + e.getMessage());
		}
		return borrowBooks;
	}

	@Override
	public List<BorrowBook> getAllPendingReturn() {
		List<BorrowBook> borrowBooks = new ArrayList<>();
		String sql = "SELECT * FROM borrow_books WHERE is_returned = 0 AND request_status = 'accepted' AND is_archived = 0";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			while(rs.next()) {
				BorrowBook borrow = new BorrowBook();
				borrow.setBorrowId(rs.getInt("borrow_id"));
				borrow.setStudentId(rs.getString("student_id"));
				borrow.setBookId(rs.getInt("book_id"));
				borrow.setBorrowDate(rs.getTimestamp("borrow_date").toLocalDateTime());
				borrow.setDueDate(rs.getTimestamp("due_date").toLocalDateTime());
				borrow.setRequestedAt(rs.getTimestamp("requested_at").toLocalDateTime());
				borrowBooks.add(borrow);
			}
		} catch (SQLException e) {
			System.out.println("Get All Pending Return Failed: " + e.getMessage());
		}
		return borrowBooks;
	}

	@Override
	public BorrowBook getPendingRequestById(int borrowBookId) {
		String sql = "SELECT borrow_id, student_id, book_id, requested_at FROM borrow_books WHERE borrow_id = ? AND request_status = 'pending'";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, borrowBookId);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				BorrowBook  borrow = new BorrowBook();
				borrow.setBorrowId(rs.getInt("borrow_id"));
				borrow.setStudentId(rs.getString("student_id"));
				borrow.setBookId(rs.getInt("book_id"));
				borrow.setRequestedAt(rs.getTimestamp("requested_at").toLocalDateTime());
				return borrow;
			}
		} catch (SQLException e) {
			System.out.println("Get Pending Request By ID Failed: " + e.getMessage());
		}
		return null;
	}

	@Override
	public BorrowBook getPendingReturnById(int borrowBookId) {
		String sql = "SELECT * FROM borrow_books WHERE is_returned = 0 AND request_status = 'accepted' AND is_archived = 0 AND borrow_id = ?";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, borrowBookId);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				BorrowBook  borrowBook = new BorrowBook();
				borrowBook.setBorrowId(rs.getInt("borrow_id"));
				borrowBook.setStudentId(rs.getString("student_id"));
				borrowBook.setBookId(rs.getInt("book_id"));
				borrowBook.setBorrowDate(rs.getTimestamp("borrow_date").toLocalDateTime());
				borrowBook.setDueDate(rs.getTimestamp("due_date").toLocalDateTime());
				borrowBook.setRequestedAt(rs.getTimestamp("requested_at").toLocalDateTime());
				return borrowBook;
			}
		} catch (SQLException e) {
			System.out.println("Get Pending Request By ID Failed: " + e.getMessage());
		}
		return null;
	}

	@Override
	public boolean acceptBorrowRequestById(int borrowBookId) {
		String sql = "UPDATE borrow_books SET borrow_date = ?, due_date = ?, is_late = ?, fine_amount = ?, "
				+ "request_status = ? WHERE borrow_id = ? AND is_archived = 0";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			LocalDateTime borrowDate = LocalDateTime.now();
			LocalDateTime dueDate = borrowDate.plusDays(5);
			
			pstmt.setTimestamp(1, Timestamp.valueOf(borrowDate));
			pstmt.setTimestamp(2, Timestamp.valueOf(dueDate));
			pstmt.setBoolean(3, false);
			pstmt.setDouble(4, 0);
			pstmt.setString(5, "accepted");
			pstmt.setInt(6, borrowBookId);
			
			return pstmt.executeUpdate() > 0;
			
			
		} catch (SQLException e) {
			System.out.println("Accept Borrow Request Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean rejectBorrowRequestById(int borrowBookId, String remarks) {
		String sql = "UPDATE borrow_books SET request_status = ?, remarks = ? WHERE borrow_id = ? AND is_archived = 0";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, "rejected");
			pstmt.setString(2, remarks);
			pstmt.setInt(3, borrowBookId);
			
			return pstmt.executeUpdate() > 0;
			
			
		} catch (SQLException e) {
			System.out.println("Reject Borrow Request Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<BorrowBook> getIncomingDueDateReturn() {
		List<BorrowBook> borrowBooks = new ArrayList<>();
		String sql = "SELECT * FROM borrow_books WHERE is_returned = 0 AND request_status = 'accepted' AND is_archived = 0 AND due_date BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 2 DAY)";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			while(rs.next()) {
				BorrowBook borrow = new BorrowBook();
				borrow.setBorrowId(rs.getInt("borrow_id"));
				borrow.setStudentId(rs.getString("student_id"));
				borrow.setBookId(rs.getInt("book_id"));
				borrow.setBorrowDate(rs.getTimestamp("borrow_date").toLocalDateTime());
				borrow.setDueDate(rs.getTimestamp("due_date").toLocalDateTime());
				borrow.setRequestedAt(rs.getTimestamp("requested_at").toLocalDateTime());
				borrowBooks.add(borrow);
			}
		} catch (SQLException e) {
			System.out.println("Get All Pending Return Failed: " + e.getMessage());
		}
		return borrowBooks;
	}

	@Override
	public List<BorrowBook> getOverdueReturn() {
		List<BorrowBook> borrowBooks = new ArrayList<>();
		String sql = "SELECT * FROM borrow_books WHERE is_returned = 0 AND request_status = 'accepted' AND is_archived = 0 AND due_date < NOW()";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			while(rs.next()) {
				BorrowBook borrow = new BorrowBook();
				borrow.setBorrowId(rs.getInt("borrow_id"));
				borrow.setStudentId(rs.getString("student_id"));
				borrow.setBookId(rs.getInt("book_id"));
				borrow.setBorrowDate(rs.getTimestamp("borrow_date").toLocalDateTime());
				borrow.setDueDate(rs.getTimestamp("due_date").toLocalDateTime());
				borrow.setRequestedAt(rs.getTimestamp("requested_at").toLocalDateTime());
				borrowBooks.add(borrow);
			}
		} catch (SQLException e) {
			System.out.println("Get All Pending Return Failed: " + e.getMessage());
		}
		return borrowBooks;
	}

	@Override
	public List<BorrowBook> getStudentTransacHistoryById(String studentId) {
		List<BorrowBook> borrows = new ArrayList<>();
		String sql = "SELECT * FROM borrow_books WHERE student_id = ? AND request_status = 'completed' AND is_archived = 0";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, studentId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BorrowBook borrow = new BorrowBook();
				borrow.setBorrowId(rs.getInt("borrow_id"));
				borrow.setStudentId(rs.getString("student_id"));
				borrow.setBookId(rs.getInt("book_id"));
				borrow.setBorrowDate(rs.getTimestamp("borrow_date").toLocalDateTime());
				borrow.setDueDate(rs.getTimestamp("due_date").toLocalDateTime());
				borrow.setReturnDate(rs.getTimestamp("return_date").toLocalDateTime());
				borrow.setReturned(rs.getBoolean("is_returned"));
				borrow.setLate(rs.getBoolean("is_late"));
				borrow.setFineAmount(rs.getDouble("fine_amount"));
				borrow.setRequestStatus(rs.getString("request_status"));
				borrow.setRemarks(rs.getString("remarks"));
				borrow.setRequestedAt(rs.getTimestamp("requested_at").toLocalDateTime());
				borrows.add(borrow);
			}
		} catch (SQLException e) {
			System.out.println("Get Student Transaction Failed: " + e.getMessage());
		}
				
		return borrows;
	}

	@Override
	public boolean acceptBookReturn(BorrowBook borrowBook) {
		String sql = "UPDATE borrow_books SET fine_amount = ?, return_date = ?, request_status = 'completed',  remarks = ? WHERE borrow_id = ?";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setDouble(1, borrowBook.getFineAmount());
			
			Timestamp timestamp = DateTimeUtil.getCurrentSqlTimestamp();
			pstmt.setTimestamp(2, timestamp);
			pstmt.setString(3, borrowBook.getRemarks());
			pstmt.setInt(4, borrowBook.getBorrowId());
			return pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.out.println("Accept Book Return Failed: " + e.getMessage());
			return false;
		}
		
	}

	@Override
	public int getTotalBorrowedToday() {
		String sql  = "SELECT COUNT(*) AS total_rows FROM borrow_books WHERE borrow_date = NOW() AND request_status = 'accepted' AND is_archived = 0";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				return rs.getInt("total_rows");
			}
			
		} catch (SQLException e) {
			System.out.println("Get Count Total Borrowed Today Failed: " + e.getMessage());
		}
		return 0;
	}

	@Override
	public int getTotalReturnToday() {
		String sql  = "SELECT COUNT(*) AS total_rows FROM borrow_books WHERE return_date = NOW() AND request_status = 'completed' AND is_archived = 0";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				return rs.getInt("total_rows");
			}
			
		} catch (SQLException e) {
			System.out.println("Get Count Total Borrowed Today Failed: " + e.getMessage());
		}
		return 0;
	}

	@Override
	public int getTotalIncomingDue() {
		String sql  = "SELECT COUNT(*) AS total_rows FROM borrow_books WHERE request_status = 'accepted' AND is_archived = 0 AND due_date BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 2 DAY)";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				return rs.getInt("total_rows");
			}
			
		} catch (SQLException e) {
			System.out.println("Get Count Total Borrowed Today Failed: " + e.getMessage());
		}
		return 0;
	}

	@Override
	public int getTotalTransaction() {
		String sql  = "SELECT COUNT(*) AS total_rows FROM borrow_books WHERE request_status = 'completed' AND is_archived = 0 ";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				return rs.getInt("total_rows");
			}
			
		} catch (SQLException e) {
			System.out.println("Get Count Total Borrowed Today Failed: " + e.getMessage());
		}
		return 0;
	}

	@Override
	public List<BorrowBook> getStudentBorrowedBooks(String studentId) {
		List<BorrowBook> borrows = new ArrayList<>(); 
		String sql = "SELECT * FROM borrow_books WHERE student_id = ? AND request_status = 'accepted' AND is_archived = 0";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, studentId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BorrowBook borrow = new BorrowBook();
				borrow.setBorrowId(rs.getInt("borrow_id"));
				borrow.setBookId(rs.getInt("book_id"));
				borrow.setBorrowDate(rs.getTimestamp("borrow_date").toLocalDateTime());
				borrow.setDueDate(rs.getTimestamp("due_date").toLocalDateTime());
				borrow.setRequestedAt(rs.getTimestamp("requested_at").toLocalDateTime());
				borrows.add(borrow);
			}
			
		} catch (SQLException e) {
			System.out.println("Get Student Borrowed Books Failed: " + e.getMessage());
		}
		
		return borrows;
	}

	@Override
	public List<BorrowBook> getStudentPendingBooks(String studentId) {
		List<BorrowBook> borrows = new ArrayList<>(); 
		String sql = "SELECT * FROM borrow_books WHERE student_id = ? AND request_status = 'pending' AND is_archived = 0";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, studentId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BorrowBook borrow = new BorrowBook();
				borrow.setBorrowId(rs.getInt("borrow_id"));
				borrow.setBookId(rs.getInt("book_id"));
				borrow.setRequestedAt(rs.getTimestamp("requested_at").toLocalDateTime());
				borrows.add(borrow);
			}
			
		} catch (SQLException e) {
			System.out.println("Get Student Borrowed Books Failed: " + e.getMessage());
		}
		
		return borrows;
	}

	@Override
	public List<BorrowBook> getStudentIncomingDue(String studentId) {
		List<BorrowBook> borrowBooks = new ArrayList<>();
		String sql = "SELECT * FROM borrow_books WHERE student_id = ? AND request_status = 'accepted' AND is_archived = 0 AND due_date BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 2 DAY)";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, studentId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BorrowBook borrow = new BorrowBook();
				borrow.setBorrowId(rs.getInt("borrow_id"));
				borrow.setBookId(rs.getInt("book_id"));
				borrow.setBorrowDate(rs.getTimestamp("borrow_date").toLocalDateTime());
				borrow.setDueDate(rs.getTimestamp("due_date").toLocalDateTime());
				borrow.setRequestedAt(rs.getTimestamp("requested_at").toLocalDateTime());
				borrowBooks.add(borrow);
			}
		} catch (SQLException e) {
			System.out.println("Get All Pending Return Failed: " + e.getMessage());
		}
		return borrowBooks;
	}

	@Override
	public List<BorrowBook> getStudentOverDue(String studentId) {
		List<BorrowBook> borrowBooks = new ArrayList<>();
		String sql = "SELECT * FROM borrow_books WHERE student_id = ? AND request_status = 'accepted' AND is_archived = 0 AND due_date < NOW()";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, studentId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BorrowBook borrow = new BorrowBook();
				borrow.setBorrowId(rs.getInt("borrow_id"));
				borrow.setBookId(rs.getInt("book_id"));
				borrow.setBorrowDate(rs.getTimestamp("borrow_date").toLocalDateTime());
				borrow.setDueDate(rs.getTimestamp("due_date").toLocalDateTime());
				borrow.setRequestedAt(rs.getTimestamp("requested_at").toLocalDateTime());
				borrowBooks.add(borrow);
			}
		} catch (SQLException e) {
			System.out.println("Get All Pending Return Failed: " + e.getMessage());
		}
		return borrowBooks;
	}

}
