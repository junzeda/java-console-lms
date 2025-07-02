package com.joysis.lms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.joysis.lms.config.DBConnection;
import com.joysis.lms.dao.AdminDAO;
import com.joysis.lms.model.Admin;

public class AdminDAOImpl implements AdminDAO{

	@Override
	public Admin loginAdmin(String username, String password) {
		String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Admin admin = new Admin();
				admin.setAdminId(rs.getInt("admin_id"));
				admin.setUsername(rs.getString("username"));
				return admin;
			}
			
		} catch(SQLException e) {
			System.out.println("Admin Login Failed: " + e.getMessage());
		}
		return null;
	}

}
