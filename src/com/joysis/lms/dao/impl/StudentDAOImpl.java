package com.joysis.lms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.joysis.lms.config.DBConnection;
import com.joysis.lms.dao.StudentDAO;
import com.joysis.lms.model.Student;
import com.joysis.lms.util.DateTimeUtil;

public class StudentDAOImpl implements StudentDAO{
	
	@Override
	public Student loginStudent(String studentId, String password) {
		String sql = "SELECT * FROM students WHERE student_id = ? AND password = ? AND is_archived = 0";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, studentId);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getString("student_id"));
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setPassword(rs.getString("password"));
				student.setContactNumber(rs.getString("contact_number"));
				return student;
			}
			
		} catch(SQLException e) {
			System.out.println("Login Student Failed: " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<>();
		String sql = "SELECT * FROM students WHERE is_archived = 0";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			while(rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getString("student_id"));
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setContactNumber(rs.getString("contact_number"));
				student.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				student.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
				students.add(student);
			}
		}catch(SQLException e) {
			System.out.println("Get All Students Failed: " + e.getMessage());
		}
		return students;
		
	}

	@Override
	public boolean insertStudent(Student student) {
		String sql = "INSERT INTO students (student_id, first_name, last_name, password, contact_number, created_at, updated_at) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			String studentId = student.getStudentId();
			pstmt.setString(1, studentId);
			pstmt.setString(2, student.getFirstName());
			pstmt.setString(3, student.getLastName());
			
			String password = studentId.substring(studentId.length() - 4) + student.getLastName();
            pstmt.setString(4, password);
            pstmt.setString(5, student.getContactNumber());
            
            Timestamp timestamp = DateTimeUtil.getCurrentSqlTimestamp();
            pstmt.setTimestamp(6, timestamp);
            pstmt.setTimestamp(7, timestamp);
            
            return pstmt.executeUpdate() > 0; // return boolean value
			
		} catch (SQLException e) {
			System.out.println("Insert Student Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateStudent(Student student) {
		String sql = "UPDATE students SET first_name = ?, last_name = ?, contact_number = ?, updated_at = ? WHERE student_id = ? AND is_archived = 0";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, student.getFirstName());
			pstmt.setString(2, student.getLastName());
			pstmt.setString(3, student.getContactNumber());
			pstmt.setTimestamp(4, DateTimeUtil.getCurrentSqlTimestamp());
			pstmt.setString(5, student.getStudentId());
			
			return pstmt.executeUpdate() > 0; // return boolean value
			
		} catch (SQLException e) {
			System.out.println("Update Student Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean archiveStudentById(String studentId) {
		String sql = "UPDATE students SET is_archived = 1 WHERE student_id = ?";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, studentId);
			return pstmt.executeUpdate() > 0;
			
		}catch(SQLException e) {
			System.out.println("Archive Student Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public Student getStudentById(String studentId) {
		String sql = "SELECT * FROM students WHERE student_id = ?";
		
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, studentId);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getString("student_id"));
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setContactNumber(rs.getString("contact_number"));
				student.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				student.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
				return student;
			}
			
		} catch (SQLException e) {
			System.out.println("Get Student By ID Failed: " + e.getMessage());
		}
		return null;
	}

	@Override
	public boolean restoreAllStudents() {
		String sql = "UPDATE students SET is_archived = 0 WHERE is_archived = 1";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			return pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.out.println("Restore All Students Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean restoreStudentById(String studentId) {
		String sql = "UPDATE students SET is_archived = 0 WHERE student_id = ? AND is_archived = 1";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, studentId);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Restore Students By ID Failed: " + e.getMessage());
			return false;
		}
	}

	@Override
	public int getTotalStudents() {
		String sql  = "SELECT COUNT(*) AS total_rows FROM students WHERE is_archived = 0";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				return rs.getInt("total_rows");
			}
			
		} catch (SQLException e) {
			System.out.println("Get Count Students Failed: " + e.getMessage());
		}
		return 0;
	}


	@Override
	public boolean updateStudentPass(Student student) {
		String sql = "UPDATE students SET password = ? WHERE student_id = ? AND is_archived = 0";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, student.getPassword());
			pstmt.setString(2, student.getStudentId());
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Update Student Pass Failed: " + e.getMessage());
			return false;
		}
	}

}
