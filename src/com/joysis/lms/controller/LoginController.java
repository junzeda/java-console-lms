package com.joysis.lms.controller;

import com.joysis.lms.dao.AdminDAO;
import com.joysis.lms.dao.StudentDAO;
import com.joysis.lms.model.Admin;
import com.joysis.lms.model.Student;
import com.joysis.lms.view.LoginView;
import com.joysis.lms.view.admin.AdminHomepageView;
import com.joysis.lms.view.student.AccountSettingsView;
import com.joysis.lms.view.student.StudentHomepageView;

public class LoginController {
	private final StudentDAO studentDAO;
	private final AdminDAO adminDAO;
	private LoginView loginView;
	private AdminHomepageView adminHomepageView;
	private StudentHomepageView studentHomepageView;
	private AccountSettingsView accountSettingsView;
	
	public LoginController(StudentDAO studentDAO, AdminDAO adminDAO) {
		this.studentDAO = studentDAO;
		this.adminDAO = adminDAO;
	}
	
	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}
	
	public void setAdminHomepageView(AdminHomepageView adminHomepageView) {
		this.adminHomepageView = adminHomepageView;
	}
	
	public void setStudentHomepageView(StudentHomepageView studentHomepageView) {
		this.studentHomepageView = studentHomepageView;
	}
	
	public void setAccountSettingsView(AccountSettingsView accountSettingsView) {
		this.accountSettingsView = accountSettingsView;
	}
	
	
	public boolean handleSelectedAccount(int choice) {
		switch(choice) {
		case 1:
			loginView.promptStudentLogin();
			return true;
		case 2:
			loginView.promptAdminLogin();
			return true;
		case 0:
			System.out.println("	System exit... Thank you!");
			System.exit(0);
		default:
			return false;
		}
	}

	public void loginStudentAccount(String studentId, String password) {
		Student student = studentDAO.loginStudent(studentId, password);
		if(student != null) {
			studentHomepageView.setStudent(student);
			studentHomepageView.showStudentHomepageView();
		}else {
			System.out.println("	Invalid credentials. Please try again.");
		}
	}
	
	public void loginAdminAccount(String username, String password) {
		Admin admin  = adminDAO.loginAdmin(username, password);
		if(admin != null) {
			adminHomepageView.setAdmin(admin);
			adminHomepageView.showAdminHomepage();
		}else {
			System.out.println("	Invalid credentials. Please try again.");
		}
	}
}
