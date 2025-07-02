package com.joysis.lms.controller.admin;

import com.joysis.lms.dao.AuthorDAO;
import com.joysis.lms.dao.BookDAO;
import com.joysis.lms.dao.BorrowBookDAO;
import com.joysis.lms.dao.CategoryDAO;
import com.joysis.lms.dao.PublisherDAO;
import com.joysis.lms.dao.StudentDAO;

public class AdminDashboardController {
	private final BookDAO bookDAO;
	private final AuthorDAO authorDAO;
	private final PublisherDAO publisherDAO;
	private final CategoryDAO categoryDAO;
	private final StudentDAO studentDAO;
	private final BorrowBookDAO borrowDAO;
	
	public AdminDashboardController(BookDAO bookDAO, AuthorDAO authorDAO, PublisherDAO publisherDAO, CategoryDAO categoryDAO, StudentDAO studentDAO, BorrowBookDAO borrowDAO) {
		this.bookDAO = bookDAO;
		this.authorDAO = authorDAO;
		this.publisherDAO = publisherDAO;
		this.categoryDAO = categoryDAO;
		this.studentDAO = studentDAO;
		this.borrowDAO = borrowDAO;
	}
	
	public int getTotalBooks() {
		return bookDAO.getTotalBooks();
	}
	
	public int getTotalAuthors() {
		return authorDAO.getTotalAuthors();
	}
	
	public int getTotalPublishers() {
		return publisherDAO.getTotalPublishers();
	}
	
	public int getTotalCategories() {
		return categoryDAO.getTotalCategories();
	}
	
	public int getTotalStudents() {
		return studentDAO.getTotalStudents();
	}
	
	public int getTotalBorrowedToda() {
		return borrowDAO.getTotalBorrowedToday();
	}
	
	public int getTotalReturnedToday() {
		return borrowDAO.getTotalReturnToday();
	}
	
	public int getTotalIncomingDue() {
		return borrowDAO.getTotalIncomingDue();
	}
	
	public int getTotalTransaction() {
		return borrowDAO.getTotalTransaction();
	}
	
	
}
