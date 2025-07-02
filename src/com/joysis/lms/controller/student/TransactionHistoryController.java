package com.joysis.lms.controller.student;

import java.util.List;

import com.joysis.lms.dao.AuthorDAO;
import com.joysis.lms.dao.BookDAO;
import com.joysis.lms.dao.BorrowBookDAO;
import com.joysis.lms.dao.CategoryDAO;
import com.joysis.lms.model.Book;
import com.joysis.lms.model.BorrowBook;
import com.joysis.lms.model.Student;
import com.joysis.lms.util.ConsoleStyles;
import com.joysis.lms.view.student.BorrowedBooksView;
import com.joysis.lms.view.student.StudentHomepageView;

public class TransactionHistoryController {
	private BorrowedBooksView borrowedView;
	private StudentHomepageView homepageView;
	private final BorrowBookDAO borrowDAO;
	private final AuthorDAO authorDAO;
	private final BookDAO bookDAO;
	private final CategoryDAO categoryDAO;
	
	public TransactionHistoryController(BorrowBookDAO borrowDAO, BookDAO bookDAO, AuthorDAO authorDAO, CategoryDAO categoryDAO) {
		this.borrowDAO = borrowDAO;
		this.bookDAO = bookDAO;
		this.authorDAO = authorDAO;
		this.categoryDAO = categoryDAO;
	}
	
	public void setBorrowedViews(BorrowedBooksView borrowedView) {
		this.borrowedView = borrowedView;
	}
	
	public void setStudentHomepageView(StudentHomepageView homepageView) {
		this.homepageView = homepageView;
	}
	
	
	public void getTransactionHistory() {
		Student student = homepageView.getStudent();
		List<BorrowBook> borrowBooks = borrowDAO.getStudentTransacHistoryById(student.getStudentId());
		if (!borrowBooks.isEmpty()) {
			
			System.out.println("---------------------------------------------------------------------"
					+ "-------------------------------------------------------------------------------------------------------------"
					+ "---------------------------------------------------");
			System.out.printf("%-30s %-35s %-25s %-25s %-25s %-25s %-30s %-25s %-5s%n",
					"| Borrow Transaction ID", "| Book Title", "| Borrow Date", "| Due Date",
					"| Return Date", "| Fine Amount", "| Remarks", "| Requested At", "| ");
			System.out.println("---------------------------------------------------------------------"
					+ "-------------------------------------------------------------------------------------------------------------"
					+ "---------------------------------------------------");

			for (BorrowBook borrow : borrowBooks) {
				Book book = bookDAO.getBookById(borrow.getBookId());
				System.out.printf("%-30s %-35s %-25s %-25s %-25s %-25s %-30s %-25s %-5s%n",
						"| " + borrow.getBorrowId(),
						"| " + ConsoleStyles.limitStringWithEllipsis(book.getTitle(), 30), 
						"| " + borrow.getBorrowDate(),
						"| " + borrow.getDueDate(),
						"| " + borrow.getReturnDate(),
						"| " + borrow.getFineAmount(),
						"| " + ConsoleStyles.limitStringWithEllipsis(borrow.getRemarks(), 27),
						"| " + borrow.getRequestedAt(),
						"| ");
			}

			System.out.println("---------------------------------------------------------------------"
					+ "-------------------------------------------------------------------------------------------------------------"
					+ "---------------------------------------------------");
		}
	
	}

}
