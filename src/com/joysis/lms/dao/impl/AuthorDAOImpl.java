package com.joysis.lms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.joysis.lms.config.DBConnection;
import com.joysis.lms.dao.AuthorDAO;
import com.joysis.lms.model.Author;
import com.joysis.lms.util.DateTimeUtil;

public class AuthorDAOImpl implements AuthorDAO{

	@Override
	public List<Author> getAllAuthors() {
		String sql = "SELECT * FROM authors WHERE is_archived = 0";
		List<Author> authors = new ArrayList<>();
		
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			
			while(rs.next()) {
				Author author = new Author();
				author.setAuthorId(rs.getInt("author_id"));
				author.setFirstName(rs.getString("first_name"));
				author.setLastName(rs.getString("last_name"));
				author.setBirthDate(rs.getString("birth_date"));
				author.setNationality(rs.getString("nationality"));
				author.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				author.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
				authors.add(author);
			}
			
		} catch (SQLException e) {
			System.out.println("Get All Authors Failed: " + e.getMessage());
		}
		return authors;
	}

	@Override
	public boolean insertAuthor(Author author) {
		String sql = "INSERT INTO authors (first_name, last_name, birth_date, nationality, created_at, updated_at) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, author.getFirstName());
			pstmt.setString(2, author.getLastName());
			pstmt.setString(3, author.getBirthDate());
			pstmt.setString(4, author.getNationality());
			
			Timestamp timestamp = DateTimeUtil.getCurrentSqlTimestamp();
			pstmt.setTimestamp(5, timestamp);
			pstmt.setTimestamp(6, timestamp);
			
			return pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.out.println("Insert Author Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateAuthor(Author author) {
		String sql = "UPDATE authors SET first_name = ?, last_name = ?, birth_date = ?, nationality = ?, updated_at = ? WHERE author_id = ? AND is_archived = 0";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, author.getFirstName());
			pstmt.setString(2, author.getLastName());
			pstmt.setString(3, author.getBirthDate());
			pstmt.setString(4, author.getNationality());
			
			Timestamp timestamp = DateTimeUtil.getCurrentSqlTimestamp();
			pstmt.setTimestamp(5, timestamp);
			pstmt.setInt(6, author.getAuthorId());
			
			return pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.out.println("Update Author Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean archiveAuthorById(int authorId) {
		String sql = "UPDATE authors SET is_archived = 1 WHERE author_id = ? AND is_archived = 0";
		
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, authorId);
			return pstmt.executeUpdate() > 0;
			
		}catch (SQLException e) {
			System.out.println("Archive Author By ID Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public Author getAuthorById(int authorId) {
		String sql = "SELECT * FROM authors WHERE author_id = ? AND is_archived = 0";
		
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, authorId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Author author = new Author();
				author.setAuthorId(rs.getInt("author_id"));
                author.setFirstName(rs.getString("first_name"));
                author.setLastName(rs.getString("last_name"));
                author.setBirthDate(rs.getString("birth_date"));
                author.setNationality(rs.getString("nationality"));
                author.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                author.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                return author;
			}
			
		} catch (SQLException e) {
			System.out.println("Get Author By ID Failed: " + e.getMessage());
		}
		return null;
	}

	@Override
	public boolean restoreAllAuthors() {
		String sql = "UPDATE authors SET is_archived = 0 WHERE is_archived = 1";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Restore All Authors Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean restoreAuthorById(int authorId) {
		String sql = "UPDATE authors SET is_archived = 0 WHERE author_id = ? AND is_archived = 1";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, authorId);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Restore Authors Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public int getTotalAuthors() {
		String sql  = "SELECT COUNT(*) AS total_rows FROM authors WHERE is_archived = 0";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				return rs.getInt("total_rows");
			}
			
		} catch (SQLException e) {
			System.out.println("Get Count Authors Failed: " + e.getMessage());
		}
		return 0;
	}

}
