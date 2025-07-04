package com.joysis.lms.controller.student;

import java.util.List;

import com.joysis.lms.dao.AuthorDAO;
import com.joysis.lms.dao.BookDAO;
import com.joysis.lms.dao.BorrowBookDAO;
import com.joysis.lms.dao.CategoryDAO;
import com.joysis.lms.model.Author;
import com.joysis.lms.model.Book;
import com.joysis.lms.model.BorrowBook;
import com.joysis.lms.model.Category;
import com.joysis.lms.model.Student;
import com.joysis.lms.view.student.StudentHomepageView;

public class PendingRequestController {
	private final BorrowBookDAO borrowDAO;
	private final AuthorDAO authorDAO;
	private final BookDAO bookDAO;
	private final CategoryDAO categoryDAO;
	private StudentHomepageView homepageView;
	
	public PendingRequestController(BorrowBookDAO borrowDAO, BookDAO bookDAO, AuthorDAO authorDAO, CategoryDAO categoryDAO) {
		this.borrowDAO = borrowDAO;
		this.bookDAO = bookDAO;
		this.authorDAO = authorDAO;
		this.categoryDAO = categoryDAO;
	}
	
	public void setStudentHomepageView(StudentHomepageView homepageView) {
		this.homepageView = homepageView;
	}
	
	public void getPendingBooks() {
		Student student = homepageView.getStudent();
		List<BorrowBook> borrows = borrowDAO.getStudentPendingBooks(student.getStudentId());
		if(borrows.isEmpty()) {
			System.out.println("No current transaction.");
		}
		
		for(BorrowBook borrow : borrows) {
			Book book = bookDAO.getBookById(borrow.getBookId());
			Author author = authorDAO.getAuthorById(book.getAuthorId());
			Category category = categoryDAO.getCategoryById(book.getCategoryId());
			
			System.out.printf("%-110s |\n","	");
			System.out.printf("%-110s |\n","	Transaction ID: " + borrow.getBorrowId());
			System.out.printf("%-110s |\n","	Requested At: " + borrow.getRequestedAt());
			System.out.printf("%-110s |\n","	Book ID: " + book.getBookId());
			System.out.printf("%-110s |\n","	Book Title: " + book.getTitle());
			System.out.printf("%-110s |\n","	Author: " + author.getFirstName() + " " + author.getLastName());
			System.out.printf("%-110s |\n","	Category: " + category.getCategoryName());
			
		}
		
	
	}
}
