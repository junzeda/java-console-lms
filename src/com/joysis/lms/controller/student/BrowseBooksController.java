package com.joysis.lms.controller.student;

import java.util.List;

import com.joysis.lms.dao.AuthorDAO;
import com.joysis.lms.dao.BookDAO;
import com.joysis.lms.dao.BorrowBookDAO;
import com.joysis.lms.dao.CategoryDAO;
import com.joysis.lms.dao.PublisherDAO;
import com.joysis.lms.model.Author;
import com.joysis.lms.model.Book;
import com.joysis.lms.model.BorrowBook;
import com.joysis.lms.model.Category;
import com.joysis.lms.model.Publisher;
import com.joysis.lms.service.BookService;
import com.joysis.lms.util.ConsoleStyles;
import com.joysis.lms.view.student.BrowseBooksView;
import com.joysis.lms.view.student.StudentHomepageView;

public class BrowseBooksController {
	private final BookDAO bookDAO;
	private final AuthorDAO authorDAO;
	private final PublisherDAO publisherDAO;
	private final CategoryDAO categoryDAO;
	private final BorrowBookDAO borrowDAO;
	private BrowseBooksView booksView;
	private StudentHomepageView homepageView;
	
	public BrowseBooksController(BookDAO bookDAO, AuthorDAO authorDAO, PublisherDAO publisherDAO,
			CategoryDAO categoryDAO, BookService bookService, BorrowBookDAO borrowDAO) {
		this.bookDAO = bookDAO;
		this.authorDAO = authorDAO;
		this.publisherDAO = publisherDAO;
		this.categoryDAO = categoryDAO;
		this.borrowDAO = borrowDAO;
	}
	
	public void setBooksView(BrowseBooksView booksView) {
		this.booksView = booksView;
	}
	
	public void setStudentHomepageView(StudentHomepageView homepageView) {
		this.homepageView = homepageView;
	}

	public boolean handleBrowseSelection(int choice) {
		switch (choice) {
		case 1:
			displayAllBooks();
			return true;
		case 2:
			booksView.promptSearhBooks();
			return true;
		case 3:
			booksView.promptBorrowBook();
			return true;
		case 4:
			return true;
		case 0:
			homepageView.showStudentHomepageView();
			return true;
		default:
			return false;

		}
	}
	
	public void displayAllBooks() {
		List<Book> books = bookDAO.getAllBooks();
		if (books.isEmpty()) {
			System.out.println("	No books found.");
			return;
		}

		System.out.println("\n"+ConsoleStyles.colorize("Homepage / Browse Books / View All Books", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)+" ==============================================="
				+ "====================================================================================================================================================="
				+ "=================================================================================================================");

		
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------"
						+ "--------------------------------------------------------------------------------------------------------------------------------"
						+ "---------------------------------------------------------------------------------------------");
		System.out.printf("%-15s %-50s %-30s %-30s %-20s %-25s %-20s %-15s %-15s %-20s %-20s %-15s %-30s %-30s %-5s%n",
				"| Book ID", "| Title", "| Author", "| Publisher", "| ISBN", "| Category", "| Publication Year",
				"| Page Count", "| Language", "| Available Copies", "| Issued Books", "| Is Available", "| Created At",
				"| Updated At", "|");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------"
						+ "---------------------------------------------------------------------------------------------------------------------------------"
						+ "---------------------------------------------------------------------------------------------");
		

		for (Book book : books) {
			Author author = authorDAO.getAuthorById(book.getAuthorId());
			Publisher publisher = publisherDAO.getPublisherById(book.getPublisherId());
			Category category = categoryDAO.getCategoryById(book.getCategoryId());

			String isAvailable = "";
			if (book.getIsAvailable()) {
				isAvailable = "Available";
			} else {
				isAvailable = "Not Available";
			}

			System.out.printf(
					"%-15s %-50s %-30s %-30s %-20s %-25s %-20s %-15s %-15s %-20s %-20s %-15s %-30s %-30s %-5s%n",
					"| " + book.getBookId(), "| " + book.getTitle(),
					"| " + author.getFirstName() + " " + author.getLastName(), "| " + publisher.getName(),
					"| " + book.getIsbn(), "| " + category.getCategoryName(), "| " + book.getPublicationYear(),
					"| " + book.getPageCount(), "| " + book.getLanguage(), "| " + book.getAvailableCopies(),
					"| " + book.getIssuedBooks(), "| " + isAvailable, "| " + book.getCreatedAt(),
					"| " + book.getUpdatedAt(), "| ");
		}
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------"
						+ "---------------------------------------------------------------------------------------------------------------------------------"
						+ "---------------------------------------------------------------------------------------------");

		System.out.println("======================================================================================================="
				+ "======================================================================================================================================================"
				+ "=================================================================================================");
	}
	
	public void searchBooks(String keyword) {
		List<Book> books = bookDAO.searchBooks(keyword);
		if (books.isEmpty()) {
			System.out.println("	No books found.");
			return;
		}
		
		System.out.println(ConsoleStyles.colorize("Homepage / Browse Books / Search Result", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)+" ==============================================================="
				+ "===================================================================================================================================================="
				+ "==================================================================================================");
		
	
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------"
						+ "--------------------------------------------------------------------------------------------------------------------------------"
						+ "---------------------------------------------------------------------------------------------");
		System.out.printf("%-15s %-50s %-30s %-30s %-20s %-25s %-20s %-15s %-15s %-20s %-20s %-15s %-30s %-30s %-5s%n",
				"| Book ID", "| Title", "| Author", "| Publisher", "| ISBN", "| Category", "| Publication Year",
				"| Page Count", "| Language", "| Available Copies", "| Issued Books", "| Is Available", "| Created At",
				"| Updated At", "|");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------"
						+ "---------------------------------------------------------------------------------------------------------------------------------"
						+ "---------------------------------------------------------------------------------------------");



		for (Book book : books) {
			Author author = authorDAO.getAuthorById(book.getAuthorId());
			Publisher publisher = publisherDAO.getPublisherById(book.getPublisherId());
			Category category = categoryDAO.getCategoryById(book.getCategoryId());

			String isAvailable = "";
			if (book.getIsAvailable()) {
				isAvailable = "Available";
			} else {
				isAvailable = "Not Available";
			}

			System.out.printf(
					"%-15s %-50s %-30s %-30s %-20s %-25s %-20s %-15s %-15s %-20s %-20s %-15s %-30s %-30s %-5s%n",
					"| " + book.getBookId(), "| " + book.getTitle(),
					"| " + author.getFirstName() + " " + author.getLastName(), "| " + publisher.getName(),
					"| " + book.getIsbn(), "| " + category.getCategoryName(), "| " + book.getPublicationYear(),
					"| " + book.getPageCount(), "| " + book.getLanguage(), "| " + book.getAvailableCopies(),
					"| " + book.getIssuedBooks(), "| " + isAvailable, "| " + book.getCreatedAt(),
					"| " + book.getUpdatedAt(), "| ");
		}
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------"
						+ "---------------------------------------------------------------------------------------------------------------------------------"
						+ "---------------------------------------------------------------------------------------------");
		System.out.println("======================================================================================================="
				+ "======================================================================================================================================================"
				+ "=================================================================================================");
	}
	
	public boolean getBookDetails(int bookId) {
		Book book = bookDAO.getBookById(bookId);
		if(book != null) {
			Author author = authorDAO.getAuthorById(book.getAuthorId());
			Publisher publisher = publisherDAO.getPublisherById(book.getPublisherId());
			Category category = categoryDAO.getCategoryById(book.getCategoryId());
			System.out.println("\n	Books Details");
            System.out.println("	Book ID: " + book.getBookId());
            System.out.println("	Title: " + book.getTitle());
            System.out.println("	Author: " + author.getFirstName() + " " + author.getLastName());
            System.out.println("	Publisher: " + publisher.getName());
            System.out.println("	ISBN: " + book.getIsbn());
            System.out.println("	Category: " + category.getCategoryName());
            System.out.println("	Publication Year: " + book.getPublicationYear());
            System.out.println("	Page Count: " + book.getPageCount());
            System.out.println("	Lanugage: " + book.getLanguage());
			return true;
		}
		
		return false;
	}
	
	public void borrowBook(String studentId, int bookId) {
		BorrowBook borrowBook = new BorrowBook();
		borrowBook.setStudentId(studentId);
		borrowBook.setBookId(bookId);
		boolean borrowRequest = borrowDAO.insertBorrowRequest(borrowBook);
		if(borrowRequest) {
			System.out.println("	Your borrow request has been sent to the librarian. Please proceed to the library and present your Student ID.");
		} else {
			System.out.println("	Borrow request failed");
		}
	}
	

}
