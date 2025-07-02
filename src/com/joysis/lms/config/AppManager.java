package com.joysis.lms.config;

import java.util.Scanner;

import com.joysis.lms.controller.LoginController;
import com.joysis.lms.controller.admin.AdminDashboardController;
import com.joysis.lms.controller.admin.AdminHomepageController;
import com.joysis.lms.controller.admin.ManageAuthorsController;
import com.joysis.lms.controller.admin.ManageBooksController;
import com.joysis.lms.controller.admin.ManageBorrowTransactionController;
import com.joysis.lms.controller.admin.ManageCategoriesController;
import com.joysis.lms.controller.admin.ManagePublishersController;
import com.joysis.lms.controller.admin.ManageStudentsController;
import com.joysis.lms.controller.student.AccountSettingsController;
import com.joysis.lms.controller.student.BookSuggestionController;
import com.joysis.lms.controller.student.BorrowedBooksController;
import com.joysis.lms.controller.student.BrowseBooksController;
import com.joysis.lms.controller.student.DueSoonController;
import com.joysis.lms.controller.student.OverDueController;
import com.joysis.lms.controller.student.PendingRequestController;
import com.joysis.lms.controller.student.StudentHomepageController;
import com.joysis.lms.controller.student.TransactionHistoryController;
import com.joysis.lms.dao.AdminDAO;
import com.joysis.lms.dao.AuthorDAO;
import com.joysis.lms.dao.BookDAO;
import com.joysis.lms.dao.BorrowBookDAO;
import com.joysis.lms.dao.CategoryDAO;
import com.joysis.lms.dao.PublisherDAO;
import com.joysis.lms.dao.StudentDAO;
import com.joysis.lms.dao.impl.AdminDAOImpl;
import com.joysis.lms.dao.impl.AuthorDAOImpl;
import com.joysis.lms.dao.impl.BookDAOImpl;
import com.joysis.lms.dao.impl.BorrowBookDAOImpl;
import com.joysis.lms.dao.impl.CategoryDAOImpl;
import com.joysis.lms.dao.impl.PublisherDAOImpl;
import com.joysis.lms.dao.impl.StudentDAOImpl;
import com.joysis.lms.service.AuthorService;
import com.joysis.lms.service.BookService;
import com.joysis.lms.service.PublisherService;
import com.joysis.lms.service.StudentService;
import com.joysis.lms.view.LoginView;
import com.joysis.lms.view.admin.AdminDashboardView;
import com.joysis.lms.view.admin.AdminHomepageView;
import com.joysis.lms.view.admin.ManageAuthorsView;
import com.joysis.lms.view.admin.ManageBooksView;
import com.joysis.lms.view.admin.ManageBorrowTransactionView;
import com.joysis.lms.view.admin.ManageCategoriesView;
import com.joysis.lms.view.admin.ManagePublishersView;
import com.joysis.lms.view.admin.ManageStudentsView;
import com.joysis.lms.view.student.AccountSettingsView;
import com.joysis.lms.view.student.BookSuggestionView;
import com.joysis.lms.view.student.BorrowedBooksView;
import com.joysis.lms.view.student.BrowseBooksView;
import com.joysis.lms.view.student.DueSoonView;
import com.joysis.lms.view.student.OverDueView;
import com.joysis.lms.view.student.PendingRequestView;
import com.joysis.lms.view.student.StudentHomepageView;
import com.joysis.lms.view.student.TransactionHistoryView;

public class AppManager {

	public static void start() {
		Scanner scanner = new Scanner(System.in);
		
		//DAO

		BorrowBookDAO borrowDAO = new BorrowBookDAOImpl();

		// Login Account Section
		AdminDAO adminDAO = new AdminDAOImpl();
		StudentDAO studentDAO = new StudentDAOImpl();
		LoginController loginController = new LoginController(studentDAO, adminDAO);
		LoginView loginView = new LoginView(scanner, loginController);
		loginController.setLoginView(loginView); // To inject loginview to controller

		// Admin Manage Authors Section
		AuthorService authorService = new AuthorService();
		AuthorDAO authorDAO = new AuthorDAOImpl();
		ManageAuthorsController authorsController = new ManageAuthorsController(authorDAO, authorService);
		ManageAuthorsView authorsView = new ManageAuthorsView(scanner, authorsController);
		authorsController.setAuthorsView(authorsView);
		
		// Admin Manage Students Section
		StudentService studentService = new StudentService();
		ManageStudentsController studentsController = new ManageStudentsController(studentDAO, studentService);
		ManageStudentsView studentsView = new ManageStudentsView(scanner, studentsController);
		studentsController.setStudentsView(studentsView);
		
		// Admin Manage Categories Section
		CategoryDAO categoryDAO = new CategoryDAOImpl();
		ManageCategoriesController categoriesController = new ManageCategoriesController(categoryDAO);
		ManageCategoriesView categoriesView = new ManageCategoriesView(scanner, categoriesController);
		categoriesController.setCategoriesView(categoriesView);
		
		// Admin Manage Publishers Section
		PublisherService publisherService = new PublisherService();
		PublisherDAO publisherDAO = new PublisherDAOImpl();
		ManagePublishersController publishersController = new ManagePublishersController(publisherDAO, publisherService);
		ManagePublishersView publishersView = new ManagePublishersView(scanner, publishersController);
		publishersController.setPublishersView(publishersView);
		
		
		// Admin Manage Books
		BookService bookService = new BookService();
		BookDAO bookDAO = new BookDAOImpl();
		ManageBooksController booksController = new ManageBooksController(bookDAO, authorDAO, publisherDAO, categoryDAO, bookService);
		ManageBooksView booksView = new ManageBooksView(scanner, booksController, authorsController, publishersController, categoriesController);
		booksController.setBooksView(booksView);
		
		// Admin Dashboard
		AdminDashboardController dashboardController = new AdminDashboardController(bookDAO, authorDAO, publisherDAO, categoryDAO, studentDAO, borrowDAO);
		AdminDashboardView dashboardView = new AdminDashboardView(scanner, dashboardController);
		
		// Admin Borrow Transaction Section
		ManageBorrowTransactionController borrowTransactionController = new ManageBorrowTransactionController(borrowDAO, studentDAO, bookDAO);
		ManageBorrowTransactionView borrowTransactionView = new ManageBorrowTransactionView(scanner, borrowTransactionController);
		borrowTransactionController.setBorrowTransactionView(borrowTransactionView);

		// Admin Homepage Section
		AdminHomepageController adminHomeController = new AdminHomepageController(loginView, authorsView, studentsView, categoriesView, publishersView, booksView, dashboardView, borrowTransactionView);
		AdminHomepageView adminHomepageView = new AdminHomepageView(scanner, adminHomeController);
		
		
		// Browse Book Section
		BrowseBooksController browseController = new BrowseBooksController(bookDAO, authorDAO, publisherDAO, categoryDAO, bookService, borrowDAO);
		BrowseBooksView browseView = new BrowseBooksView(scanner, browseController);
		browseController.setBooksView(browseView);
		
		// Transaction History Section
		TransactionHistoryController transactionController = new TransactionHistoryController(borrowDAO, bookDAO, authorDAO, categoryDAO);
		TransactionHistoryView transactionView = new TransactionHistoryView(scanner, transactionController);
		
		// Borrowed Books Section
		BorrowedBooksController borrowedBooksController = new BorrowedBooksController(borrowDAO, bookDAO, authorDAO, categoryDAO);
		BorrowedBooksView borrowedBooksView = new BorrowedBooksView(scanner, borrowedBooksController);
		
		//Pending Request Section
		PendingRequestController pendingReqController = new PendingRequestController(borrowDAO, bookDAO, authorDAO, categoryDAO);
		PendingRequestView pendingReqView = new PendingRequestView(scanner, pendingReqController);
		
		DueSoonController dueSoonController = new DueSoonController(borrowDAO, bookDAO, authorDAO, categoryDAO);
		DueSoonView dueSoonView = new DueSoonView(scanner, dueSoonController);
		
		OverDueController overDueController = new OverDueController(borrowDAO, bookDAO, authorDAO, categoryDAO);
		OverDueView overDueView = new OverDueView(scanner,overDueController);
		
		// Book Suggestions Section
		BookSuggestionController suggestionController = new BookSuggestionController();
		BookSuggestionView suggestionView = new BookSuggestionView(scanner, suggestionController);
		
		// Student Account Settings View
		AccountSettingsController accountSettingsController = new AccountSettingsController(studentDAO);
		AccountSettingsView accountSettingsView = new AccountSettingsView(scanner, accountSettingsController);
		accountSettingsController.setAccountSettingsView(accountSettingsView);
		
		// Student Homepage Section
		StudentHomepageController studentHomeController = new StudentHomepageController(loginView, browseView, transactionView,
				suggestionView, accountSettingsView, borrowedBooksView,pendingReqView, dueSoonView, overDueView);
		StudentHomepageView studentHomeView = new StudentHomepageView(scanner, studentHomeController);
		
		// Injecting admin homepage to admin controllers
		loginController.setAdminHomepageView(adminHomepageView);
		authorsController.setAdminHomepageView(adminHomepageView);
		studentsController.setAdminHomepageView(adminHomepageView);
		categoriesController.setAdminHomepageView(adminHomepageView);
		publishersController.setAdminHomepageView(adminHomepageView);
		booksController.setAdminHomepageView(adminHomepageView);
		borrowTransactionController.setAdminHomepageView(adminHomepageView);
		
		// Injecting student homepage to student controllers
		loginController.setStudentHomepageView(studentHomeView);
		loginController.setAccountSettingsView(accountSettingsView);
		accountSettingsController.setStudentHomepageView(studentHomeView);
		browseController.setStudentHomepageView(studentHomeView);
		borrowedBooksController.setStudentHomepageView(studentHomeView);
		pendingReqController.setStudentHomepageView(studentHomeView);
		transactionController.setStudentHomepageView(studentHomeView);
		dueSoonController.setStudentHomepageView(studentHomeView);
		overDueController.setStudentHomepageView(studentHomeView);
		
		accountSettingsView.setStudentHomepageView(studentHomeView);
		browseView.setStudentHomepageView(studentHomeView);

		// This will display first if the app starts running
		loginView.selectAccountType();
	}

}
