package com.joysis.lms.controller.admin;

import com.joysis.lms.view.LoginView;
import com.joysis.lms.view.admin.AdminDashboardView;
import com.joysis.lms.view.admin.ManageAuthorsView;
import com.joysis.lms.view.admin.ManageBooksView;
import com.joysis.lms.view.admin.ManageBorrowTransactionView;
import com.joysis.lms.view.admin.ManageCategoriesView;
import com.joysis.lms.view.admin.ManagePublishersView;
import com.joysis.lms.view.admin.ManageStudentsView;

public class AdminHomepageController {
	private final LoginView loginView;
	private final ManageAuthorsView authorsView;
	private final ManageStudentsView studentsView;
	private final ManageCategoriesView categoriesView;
	private final ManagePublishersView publishersView;
	private final ManageBooksView booksView;
	private final ManageBorrowTransactionView borrowView;
	private final AdminDashboardView dashboardView;
	
	public AdminHomepageController(LoginView loginView, ManageAuthorsView authorsView, ManageStudentsView studentsView, ManageCategoriesView categoriesView,
			ManagePublishersView publishersView, ManageBooksView booksView, AdminDashboardView dashboardView, ManageBorrowTransactionView borrowView) {
		this.loginView = loginView;
		this.authorsView = authorsView;
		this.studentsView = studentsView;
		this.categoriesView = categoriesView;
		this.publishersView = publishersView;
		this.booksView = booksView;
		this.dashboardView = dashboardView;
		this.borrowView = borrowView;
	}
	
	public boolean handleHomepageSelection(int choice) {
		switch (choice) {
		case 1:
			dashboardView.showAdminDashboard();
			return true;
		case 2:
			booksView.showBooksMenu();
			return true;
		case 3:
			authorsView.showAuthorsMenu();
			return true;
		case 4:
			publishersView.showPublishersMenu();
			return true;
		case 5:
			categoriesView.showCategoriesMenu();
			return true;
		case 6:
			studentsView.showStudentsMenu();
			return true;
		case 7:
			borrowView.showBorrowTransactionMenu();
			return true;
		case 0:
			System.out.println(" Back to login page...");
			loginView.selectAccountType();
		default:
			return false;
		}
	}

}
