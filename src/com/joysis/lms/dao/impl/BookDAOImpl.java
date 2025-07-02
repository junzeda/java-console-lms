package com.joysis.lms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.joysis.lms.config.DBConnection;
import com.joysis.lms.dao.BookDAO;
import com.joysis.lms.model.Book;
import com.joysis.lms.util.DateTimeUtil;

public class BookDAOImpl implements BookDAO {

	@Override
	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM books WHERE is_archived = 0";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setTitle(rs.getString("title"));
				book.setAuthorId(rs.getInt("author_id"));
				book.setPublisherId(rs.getInt("publisher_id"));
				book.setIsbn(rs.getString("isbn"));
				book.setCategoryId(rs.getInt("category_id"));
				book.setPublicationYear(rs.getInt("publication_year"));
				book.setPageCount(rs.getInt("page_count"));
				book.setPageCount(rs.getInt("page_count"));
				book.setLanguage(rs.getString("language"));
				book.setAvailableCopies(rs.getInt("available_copies"));
				book.setIssuedBooks(rs.getInt("issued_books"));
				book.setIsAvailable(rs.getBoolean("is_available"));
				book.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				book.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
				books.add(book);
			}
		} catch (SQLException e) {
			System.out.println("Get All Books Failed: " + e.getMessage());
		}
		return books;
	}

	@Override
	public boolean insertBook(Book book) {
		String sql = "INSERT INTO books (title, author_id, publisher_id, isbn, category_id, publication_year, page_count, language, available_copies,"
				+ "issued_books, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, book.getTitle());
			pstmt.setInt(2, book.getAuthorId());
			pstmt.setInt(3, book.getPublisherId());
			pstmt.setString(4, book.getIsbn());
			pstmt.setInt(5, book.getCategoryId());
			pstmt.setInt(6, book.getPublicationYear());
			pstmt.setInt(7, book.getPageCount());
			pstmt.setString(8, book.getLanguage());
			pstmt.setInt(9, book.getAvailableCopies());
			pstmt.setInt(10, book.getIssuedBooks());

			Timestamp timestamp = DateTimeUtil.getCurrentSqlTimestamp();
			pstmt.setTimestamp(11, timestamp);
			pstmt.setTimestamp(12, timestamp);
			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			System.out.println("Insert Book Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateBook(Book book) {
		String sql = "UPDATE books SET title = ? , author_id = ?, publisher_id = ?, isbn = ?, category_id = ?, publication_year = ?, "
				+ "page_count = ?, language = ?, available_copies = ?, issued_books = ?, updated_at = ? WHERE book_id = ? AND is_archived = 0";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, book.getTitle());
			pstmt.setInt(2, book.getAuthorId());
			pstmt.setInt(3, book.getPublisherId());
			pstmt.setString(4, book.getIsbn());
			pstmt.setInt(5, book.getCategoryId());
			pstmt.setInt(6, book.getPublicationYear());
			pstmt.setInt(7, book.getPageCount());
			pstmt.setString(8, book.getLanguage());
			pstmt.setInt(9, book.getAvailableCopies());
			pstmt.setInt(10, book.getIssuedBooks());

			Timestamp timestamp = DateTimeUtil.getCurrentSqlTimestamp();
			pstmt.setTimestamp(11, timestamp);
			pstmt.setInt(12, book.getBookId());
			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			System.out.println("Update Book Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public Book getBookById(int bookId) {
		String sql = "SELECT * FROM books WHERE book_id = ? AND is_archived = 0";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, bookId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setTitle(rs.getString("title"));
				book.setAuthorId(rs.getInt("author_id"));
				book.setPublisherId(rs.getInt("publisher_id"));
				book.setIsbn(rs.getString("isbn"));
				book.setCategoryId(rs.getInt("category_id"));
				book.setPublicationYear(rs.getInt("publication_year"));
				book.setPageCount(rs.getInt("page_count"));
				book.setPageCount(rs.getInt("page_count"));
				book.setLanguage(rs.getString("language"));
				book.setAvailableCopies(rs.getInt("available_copies"));
				book.setIssuedBooks(rs.getInt("issued_books"));
				book.setIsAvailable(rs.getBoolean("is_available"));
				book.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				book.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
				return book;
			}
		} catch (SQLException e) {
			System.out.println("Get Book By ID Failed: " + e.getMessage());
		}
		return null;
	}

	@Override
	public boolean archiveBookById(int bookId) {
		String sql = "UPDATE books SET is_archived = 1 WHERE book_id = ? AND is_archived = 0";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, bookId);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Archive Book By ID Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean restoreBooksById(int bookId) {
		String sql = "UPDATE books SET is_archived = 0  WHERE book_id = ? AND is_archived = 1";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, bookId);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Restore Book By ID Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean restoreAllBooks() {
		String sql = "UPDATE books SET is_archived = 0  WHERE is_archived = 1";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Restore Book By ID Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public int getTotalBooks() {
		String sql = "SELECT COUNT(*) AS total_rows FROM books WHERE is_archived = 0";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if(rs.next()) {
				return rs.getInt("total_rows");
			}
		} catch (SQLException e) {
			System.out.println("Get Count Books Failed: " + e.getMessage());
		}
		return 0;
	}

	@Override
	public List<Book> searchBooks(String keyword) {
		List<Book> books = new ArrayList<>();
		String search = "%" + keyword + "%";
		String sql = "SELECT b.*"
				+ "FROM books b"
				+ " JOIN authors a ON b.author_id = a.author_id"
				+ " JOIN publishers p ON b.publisher_id = p.publisher_id"
				+ " JOIN book_categories c ON b.category_id = c.book_category_id"
				+ " WHERE b.title LIKE ?"
				+ " OR CONCAT(a.first_name, ' ', a.last_name) LIKE ?"
				+ " OR p.name LIKE ?"
				+ " OR c.category LIKE ? AND b.is_archived = 0";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
				pstmt.setString(1, search);
				pstmt.setString(2, search);
				pstmt.setString(3, search);
				pstmt.setString(4, search);
				
				ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setTitle(rs.getString("title"));
				book.setAuthorId(rs.getInt("author_id"));
				book.setPublisherId(rs.getInt("publisher_id"));
				book.setIsbn(rs.getString("isbn"));
				book.setCategoryId(rs.getInt("category_id"));
				book.setPublicationYear(rs.getInt("publication_year"));
				book.setPageCount(rs.getInt("page_count"));
				book.setPageCount(rs.getInt("page_count"));
				book.setLanguage(rs.getString("language"));
				book.setAvailableCopies(rs.getInt("available_copies"));
				book.setIssuedBooks(rs.getInt("issued_books"));
				book.setIsAvailable(rs.getBoolean("is_available"));
				book.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				book.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
				books.add(book);
			}
		} catch (SQLException e) {
			System.out.println("Search Books Failed: " + e.getMessage());
		}
		return books;
	}


}
