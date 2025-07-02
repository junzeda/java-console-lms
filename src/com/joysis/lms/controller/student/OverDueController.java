package com.joysis.lms.controller.student;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
import com.joysis.lms.util.ConsoleStyles;
import com.joysis.lms.view.student.StudentHomepageView;

public class OverDueController {
	private StudentHomepageView homepageView;
	private final BorrowBookDAO borrowDAO;
	private final AuthorDAO authorDAO;
	private final BookDAO bookDAO;
	private final CategoryDAO categoryDAO;
	
	public OverDueController(BorrowBookDAO borrowDAO, BookDAO bookDAO, AuthorDAO authorDAO, CategoryDAO categoryDAO) {
		this.borrowDAO = borrowDAO;
		this.bookDAO = bookDAO;
		this.authorDAO = authorDAO;
		this.categoryDAO = categoryDAO;
	}
	
	public void setStudentHomepageView(StudentHomepageView homepageView) {
		this.homepageView = homepageView;
	}
	
	public void overDueBook() {
		Student student = homepageView.getStudent();
		List<BorrowBook> borrows = borrowDAO.getStudentOverDue(student.getStudentId());
		if(borrows.isEmpty()) {
			System.out.println("No current transaction.");
		}
		
		System.out.printf("%-119s |\n","	"+ConsoleStyles.colorize("Important Notice on Lost or Damaged Books: If a book is lost or returned damaged,", ConsoleStyles.YELLOW));
		System.out.printf("%-119s |\n","	"+ConsoleStyles.colorize("the appropriate replacement or repair fee will be charged. In addition,", ConsoleStyles.YELLOW));
		System.out.printf("%-119s |\n","	"+ConsoleStyles.colorize("any overdue fine incurred before the loss or damage will also be added to the total amount due.", ConsoleStyles.YELLOW));
		
		
		for(BorrowBook borrow : borrows) {
			Book book = bookDAO.getBookById(borrow.getBookId());
			Author author = authorDAO.getAuthorById(book.getAuthorId());
			Category category = categoryDAO.getCategoryById(book.getCategoryId());
			
			long daysOverdue =ChronoUnit.DAYS.between(borrow.getDueDate(),LocalDateTime.now());
			double estimatedFine =  daysOverdue * 10;

			
			System.out.printf("%-110s |\n","	");
			System.out.printf("%-110s |\n","	Transaction ID: " + borrow.getBorrowId());
			System.out.printf("%-110s |\n","	Requested At: " + borrow.getRequestedAt());
			System.out.printf("%-110s |\n","	Book Title: " + book.getTitle());
			System.out.printf("%-110s |\n","	Author: " + author.getFirstName() + " " + author.getLastName());
			System.out.printf("%-110s |\n","	Category: " + category.getCategoryName());
			System.out.printf("%-110s |\n","	Borrow Date: " + borrow.getBorrowDate());
			System.out.printf("%-124s |\n",ConsoleStyles.colorize("		Due Date: " + borrow.getDueDate() + " Overdue Day(s): " + daysOverdue, ConsoleStyles.RED, ConsoleStyles.BOLD));
			System.out.printf("%-124s |\n",ConsoleStyles.colorize("		Estimated Fine: " + estimatedFine, ConsoleStyles.RED, ConsoleStyles.BOLD));

			
		
			
		}
	}

}
