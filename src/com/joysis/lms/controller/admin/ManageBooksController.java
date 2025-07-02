package com.joysis.lms.controller.admin;

import java.util.List;

import com.joysis.lms.dao.AuthorDAO;
import com.joysis.lms.dao.BookDAO;
import com.joysis.lms.dao.CategoryDAO;
import com.joysis.lms.dao.PublisherDAO;
import com.joysis.lms.model.Author;
import com.joysis.lms.model.Book;
import com.joysis.lms.model.Category;
import com.joysis.lms.model.Publisher;
import com.joysis.lms.service.BookService;
import com.joysis.lms.util.ConsoleStyles;
import com.joysis.lms.view.admin.AdminHomepageView;
import com.joysis.lms.view.admin.ManageBooksView;

public class ManageBooksController {
	private final BookDAO bookDAO;
	private final BookService bookService;
	private final AuthorDAO authorDAO;
	private final PublisherDAO publisherDAO;
	private final CategoryDAO categoryDAO;
	private ManageBooksView booksView;
	private AdminHomepageView adminHomepageView;

	public ManageBooksController(BookDAO bookDAO, AuthorDAO authorDAO, PublisherDAO publisherDAO,
			CategoryDAO categoryDAO, BookService bookService) {
		this.bookDAO = bookDAO;
		this.authorDAO = authorDAO;
		this.publisherDAO = publisherDAO;
		this.categoryDAO = categoryDAO;
		this.bookService = bookService;
	}

	public void setBooksView(ManageBooksView booksView) {
		this.booksView = booksView;
	}

	public void setAdminHomepageView(AdminHomepageView adminHomepageView) {
		this.adminHomepageView = adminHomepageView;
	}

	public boolean handleBooksSelection(int choice) {
		switch (choice) {
		case 1:
			displayAllBooks();
			return true;
		case 2:
			booksView.promptAddBook();
			return true;
		case 3:
			booksView.promptUpdateBook();
			return true;
		case 4:
			booksView.promptArchiveBook();
			return true;
		case 0:
			adminHomepageView.showAdminHomepage();
			return false;
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

		System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Books / All Books", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)
		+" ================================================================================================================="
		+ "================================================================================================================="
		+ "==================================================================================");
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
		System.out.println(
				"================================================================================================================================"
						+ "================================================================================================================================="
						+ "=============================================================================================");
	}

	public Book getBookById(int bookId) {
		return bookDAO.getBookById(bookId);
	}

	public boolean findBookById(int bookId) {
		Book book = bookDAO.getBookById(bookId);

		if (book != null) {
			String isAvailable = "Available";
			if (!book.getIsAvailable()) {
				isAvailable = "Not Available";
			}
			Author author = authorDAO.getAuthorById(book.getAuthorId());
			Publisher publisher = publisherDAO.getPublisherById(book.getPublisherId());
			Category category = categoryDAO.getCategoryById(book.getCategoryId());
			System.out.println("\n	Book Details");
			System.out.println("	Book ID: " + book.getBookId());
			System.out.println("	Title: " + book.getTitle());
			System.out.println("	Author: " + author.getFirstName() + " " + author.getLastName());
			System.out.println("	Publisher: " + publisher.getName());
			System.out.println("	ISBN: " + book.getIsbn());
			System.out.println("	Category: " + category.getCategoryName());
			System.out.println("	Publication Year: " + book.getPublicationYear());
			System.out.println("	Page Count: " + book.getPageCount());
			System.out.println("	Language: " + book.getLanguage());
			System.out.println("	Available Copies: " + book.getAvailableCopies());
			System.out.println("	Available: " + isAvailable);
			System.out.println("	Created At: " + book.getCreatedAt());
			System.out.println("	Updated At: " + book.getUpdatedAt());
			return true;

		}
		return false;

	}

	public void addBook(String title, int authorId, int publisherId, String isbn, int categoryId, int publicationYear,
			int pageCount, String language, int availableCopies) {
		boolean isValid = bookService.validateBook(title, isbn, publicationYear, pageCount, language, availableCopies);
		if (isValid) {
			Book book = new Book(title, authorId, publisherId, isbn, categoryId, publicationYear, pageCount, language,
					availableCopies);
			boolean insertSuccess = bookDAO.insertBook(book);
			if (insertSuccess) {
				System.out.println("	New book added successfully!");
			} else {
				System.out.println("	Failed to add new book!");
			}

		} else {
			System.out.println("	Please fill out all the required fields.");
		}
	}

	public void updateBook(int bookId, String title, int authorId, int publisherId, String isbn, int categoryId,
			int publicationYear, int pageCount, String language, int availableCopies) {
		Book oldBookVal = bookDAO.getBookById(bookId);
		Book newBookVal = bookService.checkEmptyField(oldBookVal, title, authorId, publisherId, isbn, categoryId, publicationYear, pageCount, language,
				availableCopies);

		boolean updateSuccess = bookDAO.updateBook(newBookVal);
		if (updateSuccess) {
			System.out.println("	Book updated successfully.");
		} else {
			System.out.println("	Failed to update data with Book ID: " + bookId);
		}
	}

	public void archiveBookById(int bookId) {
		boolean archiveSuccess = bookDAO.archiveBookById(bookId);
		if (archiveSuccess) {
			System.out.println("	Book deleted successfully!");
		} else {
			System.out.println("	Failed to delete data with Book ID: " + bookId);
		}
	}

}
