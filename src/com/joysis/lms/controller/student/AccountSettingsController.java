package com.joysis.lms.controller.student;

import com.joysis.lms.dao.StudentDAO;
import com.joysis.lms.model.Student;
import com.joysis.lms.view.student.AccountSettingsView;
import com.joysis.lms.view.student.StudentHomepageView;

public class AccountSettingsController {
	private final StudentDAO studentDAO;
	private AccountSettingsView settingsView;
	private StudentHomepageView homepageView;

	public AccountSettingsController(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public void setAccountSettingsView(AccountSettingsView settingsView) {
		this.settingsView = settingsView;
	}

	public void setStudentHomepageView(StudentHomepageView homepageView) {
		this.homepageView = homepageView;
	}

	public boolean handleAccSettingsSelection(int choice) {
		switch (choice) {
		case 1:
			settingsView.promptUpdateName();
			return false;
		case 2:
			settingsView.promptUpdateContact();
			return false;
		case 3:
			settingsView.promptChangePass();
			return false;
		case 0:
			homepageView.showStudentHomepageView();
			return true;
		default:
			return false;
		}

	}
	
	public void updateStudent(String studentId, String firstName, String lastName, String contactNumber) {
		Student student = new Student();
		student.setStudentId(studentId);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setContactNumber(contactNumber);
		boolean updateNameSuccess = studentDAO.updateStudent(student);
		if(updateNameSuccess) {
			System.out.println("	Student updated successfully!");
			homepageView.setStudent(student);
			settingsView.showAccountSettingsMenu();
		} else {
			System.out.println("	Failed to update student name with Student ID:" + studentId);
		}
	}
	
	public void changePassword(String studentId, String newPass) {
		Student student = new Student();
		student.setStudentId(studentId);
		student.setPassword(newPass);
		boolean updatePassSuccess = studentDAO.updateStudentPass(student);
		if(updatePassSuccess) {
			System.out.println("	Student password updated successfully!");
			homepageView.setStudent(student);
		} else {
			System.out.println("	Failed to update student password with Student ID:" + studentId);
		}
	}
}
