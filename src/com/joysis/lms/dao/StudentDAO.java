package com.joysis.lms.dao;

import java.util.List;

import com.joysis.lms.model.Student;

public interface StudentDAO {
	
	public Student loginStudent(String studentId, String password);
	public List<Student> getAllStudents();
	public boolean insertStudent(Student student);
	public boolean updateStudent(Student student);
	public boolean archiveStudentById(String studentId); // archive/delete student
	public Student getStudentById(String studentId);
	public boolean restoreAllStudents();
	public boolean restoreStudentById(String studentId);
	public int getTotalStudents();
	public boolean updateStudentPass(Student student);
}
