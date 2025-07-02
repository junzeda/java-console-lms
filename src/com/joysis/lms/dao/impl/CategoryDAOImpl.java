package com.joysis.lms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.joysis.lms.config.DBConnection;
import com.joysis.lms.dao.CategoryDAO;
import com.joysis.lms.model.Category;
import com.joysis.lms.util.DateTimeUtil;

public class CategoryDAOImpl implements CategoryDAO{

	@Override
	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<>();
		String sql = "SELECT * FROM book_categories";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			while(rs.next()) {
				Category category = new Category();
				category.setBookCategoryId(rs.getInt("book_category_id"));
				category.setCategoryName(rs.getString("category"));
				category.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				category.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
				categories.add(category);
			}
		} catch (SQLException e) {
			System.out.println("Get All Categories Failed: " + e.getMessage());
		}
		return categories;
	}

	@Override
	public boolean insertCategory(Category category) {
		String sql = "INSERT INTO book_categories (category, created_at, updated_at) VALUES (?, ?, ?)";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, category.getCategoryName());
			
			Timestamp timestamp = DateTimeUtil.getCurrentSqlTimestamp();
			pstmt.setTimestamp(2, timestamp);
			pstmt.setTimestamp(3, timestamp);
			return pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.out.println("Insert Category Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public Category getCategoryById(int bookCategoryId) {
		String sql = "SELECT * FROM book_categories WHERE book_category_id = ?";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, bookCategoryId);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Category category = new Category();
				category.setBookCategoryId(rs.getInt("book_category_id"));
				category.setCategoryName(rs.getString("category"));
				category.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				category.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
				return category;
			}
		} catch (SQLException e) {
			System.out.println("Get Category By ID Failed: " + e.getMessage());
		}
		return null;
	}

	@Override
	public boolean updateCategory(Category category) {
		String sql = "UPDATE book_categories SET category = ?, updated_at = ? WHERE book_category_id = ?";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, category.getCategoryName());
			
			Timestamp timestamp = DateTimeUtil.getCurrentSqlTimestamp();
			pstmt.setTimestamp(2, timestamp);
			pstmt.setInt(3, category.getBookCategoryId());
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Update Category Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteCategory(int bookCategoryId) {
		String sql = "DELETE FROM book_categories WHERE book_category_id = ?";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, bookCategoryId);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Delete Category Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public int getTotalCategories() {
		String sql  = "SELECT COUNT(*) AS total_rows FROM book_categories";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				return rs.getInt("total_rows");
			}
			
		} catch (SQLException e) {
			System.out.println("Get Count Categories Failed: " + e.getMessage());
		}
		return 0;
	}

}
