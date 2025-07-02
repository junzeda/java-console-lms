package com.joysis.lms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.joysis.lms.config.DBConnection;
import com.joysis.lms.dao.PublisherDAO;
import com.joysis.lms.model.Publisher;
import com.joysis.lms.util.DateTimeUtil;

public class PublisherDAOImpl implements PublisherDAO {

	@Override
	public List<Publisher> getAllPublishers() {
		List<Publisher> publishers = new ArrayList<>();
		String sql = "SELECT * FROM publishers WHERE is_archived = 0";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			while(rs.next()) {
				Publisher publisher = new Publisher();
				publisher.setPublisherId(rs.getInt("publisher_id"));
				publisher.setName(rs.getString("name"));
				publisher.setEmail(rs.getString("email"));
				publisher.setContactNumber(rs.getString("contact_number"));
				publisher.setAddress(rs.getString("address"));
				publisher.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				publisher.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
				publishers.add(publisher);
			}
			
		} catch (SQLException e) {
			System.out.println("Get All Publishers Failed: " + e.getMessage());
		}
		
		return publishers;
	}

	@Override
	public boolean insertPublisher(Publisher publisher) {
		String sql = "INSERT INTO publishers (name, email, contact_number, address, created_at, updated_at) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, publisher.getName());
			pstmt.setString(2, publisher.getEmail());
			pstmt.setString(3, publisher.getContactNumber());
			pstmt.setString(4, publisher.getAddress());
			
			Timestamp timestamp  = DateTimeUtil.getCurrentSqlTimestamp();
			pstmt.setTimestamp(5, timestamp);
			pstmt.setTimestamp(6, timestamp);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Insert Publisher Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updatePublisher(Publisher publisher) {
		String sql = "UPDATE publishers SET name = ?, email = ?, contact_number = ?, address = ?, updated_at = ? WHERE publisher_id = ? AND is_archived = 0";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, publisher.getName());
			pstmt.setString(2, publisher.getEmail());
			pstmt.setString(3, publisher.getContactNumber());
			pstmt.setString(4, publisher.getAddress());
			Timestamp timestamp = DateTimeUtil.getCurrentSqlTimestamp();
			pstmt.setTimestamp(5, timestamp);
			pstmt.setInt(6, publisher.getPublisherId());
			return pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.out.println("Update Publisher Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public Publisher getPublisherById(int publisherId) {
		String sql = "SELECT * FROM publishers WHERE publisher_id = ? AND is_archived = 0";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, publisherId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Publisher publisher = new Publisher();
				publisher.setPublisherId(rs.getInt("publisher_id"));
				publisher.setName(rs.getString("name"));
				publisher.setEmail(rs.getString("email"));
				publisher.setContactNumber(rs.getString("contact_number"));
				publisher.setAddress(rs.getString("address"));
				publisher.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				publisher.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
				return publisher;
			}
			
		} catch (SQLException e) {
			System.out.println("Get Publisher By ID Failed: " + e.getMessage());
		}
		return null;
	}

	@Override
	public boolean archivePublisherById(int publisherId) {
		String sql = "UPDATE publishers SET is_archived = 1 WHERE publisher_id = ? AND is_archived = 0";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, publisherId);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Archived Publisher By ID Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean restoreAllPublishers() {
		String sql = "UPDATE publishers SET is_archived = 0 WHERE is_archived = 1";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Restore All Publishers Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean restorePublisherById(int publisherId) {
		String sql = "UPDATE publishers SET is_archived = 0 WHERE publisher_id = ? AND is_archived = 1";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, publisherId);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Restore All Publishers Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public int getTotalPublishers() {
		String sql  = "SELECT COUNT(*) AS total_rows FROM publishers WHERE is_archived = 0";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				return rs.getInt("total_rows");
			}
			
		} catch (SQLException e) {
			System.out.println("Get Count Publishers Failed: " + e.getMessage());
		}
		return 0;
	}

}
