package com.joysis.lms.view.admin;

import java.util.Scanner;

import com.joysis.lms.controller.admin.ManageAuthorsController;
import com.joysis.lms.controller.admin.ManageBooksController;
import com.joysis.lms.controller.admin.ManageCategoriesController;
import com.joysis.lms.controller.admin.ManagePublishersController;
import com.joysis.lms.model.Book;
import com.joysis.lms.util.ConsoleStyles;

public class ManageBooksView {
	private final Scanner scanner;
	private final ManageBooksController booksController;
	private final ManageAuthorsController authorsController;
	private final ManagePublishersController publishersController;
	private final ManageCategoriesController categoriesController;
	
	public ManageBooksView(Scanner scanner, ManageBooksController booksController, ManageAuthorsController authorsController, 
			ManagePublishersController publishersController, ManageCategoriesController categoriesController) {
		this.scanner = scanner;
		this.booksController = booksController;
		this.authorsController = authorsController;
		this.publishersController = publishersController;
		this.categoriesController = categoriesController;
	}
	
	public void showBooksMenu() {
        while (true) {
        	System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Books", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)+" -----------------------------------------------------------------------------------------");
            System.out.printf("%-110s |\n","	");
            System.out.printf("%-110s |\n","	[1] Show All Books");
            System.out.printf("%-110s |\n","	[2] Add Book");
            System.out.printf("%-110s |\n","	[3] Update Book");
            System.out.printf("%-110s |\n","	[4] Delete Book");
            System.out.printf("%-110s |\n","	");
            System.out.printf("%-110s |\n","	[0] <- Back to Homepage");
            System.out.printf("%-110s |\n","	");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            
            
           
            System.out.print("	Enter your choice: ");
            if (!scanner.hasNextInt()) {
                System.out.println("	Invalid input. Please enter a number\n\n");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            boolean valid = booksController.handleBooksSelection(choice);
            if (!valid) {
                System.out.println("	Invalid option. Try again.\n\n");
            }
        }

    }
	
	public void promptAddBook() {

        System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Books / Add", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)
        +" -------------------------------------------------------------------------------------\n");
    	authorsController.displayAllAuthors();
    	System.out.print("\n	Enter Author ID: ");
    	if(!scanner.hasNextInt()) {
    		System.out.println("	Invalid input. Please try again.");
    		scanner.next();
    		return;
    	}
    	int authorId = scanner.nextInt();
    	publishersController.displayAllPublishers();
    	System.out.print("	Enter Publisher ID: ");
    	if(!scanner.hasNextInt()) {
    		System.out.println("	Invalid input. Please try again.");
    		scanner.next();
    		return;
    	}
    	int publisherId = scanner.nextInt();
    	categoriesController.displayAllCategories();
    	System.out.print("	Enter Category ID: ");
    	if(!scanner.hasNextInt()) {
    		System.out.println("	Invalid input. Please try again.");
    		scanner.next();
    		return;
    	}
    	int categoryId = scanner.nextInt();
    	scanner.nextLine();
        System.out.print("	Title: ");
        String title = scanner.nextLine();
        System.out.print("	ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("	Publication Year: ");
        int publicationYear = scanner.nextInt();
        System.out.print("	Page Count: ");
        int pageCount = scanner.nextInt();
        scanner.nextLine();
        System.out.print("	Language: ");
        String language = scanner.nextLine();
        System.out.print("	Available Copies: ");
        int availableCopies = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");
        
        
        booksController.addBook(title, authorId, publisherId, isbn, categoryId, publicationYear, pageCount, language, availableCopies);
    	
    }
	
	public void promptUpdateBook() {
		
		System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Books / Update", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)
        +" ----------------------------------------------------------------------------------\n");
		System.out.print("	Enter Book ID to update: ");
		if(!scanner.hasNextInt()) {
			System.out.println("Invalid input. Please try again.");
			scanner.next();
			return;
		}
		
		int bookId = scanner.nextInt();
		scanner.nextLine();
		
		Book book = booksController.getBookById(bookId);
		if(book == null) {
			System.out.println("	No book found.");
			return;
		}
		
		System.out.println("	Note: Leave blank to keep current value.\n");
		
		authorsController.displayAllAuthors();
    	System.out.print("	Enter Author ID [ "+ book.getAuthorId() +" ]: ");
    	if(!scanner.hasNextInt()) {
    		System.out.println("	Invalid input. Please try again.");
    		scanner.next();
    		return;
    	}
    	int authorId = scanner.nextInt();
    	publishersController.displayAllPublishers();
    	System.out.print("	Enter Publisher ID [ "+ book.getPublisherId() +" ]: ");
    	if(!scanner.hasNextInt()) {
    		System.out.println("	Invalid input. Please try again.");
    		scanner.next();
    		return;
    	}
    	int publisherId = scanner.nextInt();
    	categoriesController.displayAllCategories();
    	System.out.print("	Enter Category ID [ "+ book.getCategoryId() +" ]: ");
    	if(!scanner.hasNextInt()) {
    		System.out.println("	Invalid input. Please try again.");
    		scanner.next();
    		return;
    	}
    	int categoryId = scanner.nextInt();
    	scanner.nextLine();
    	
    	System.out.print("	Title [ " + book.getTitle() + " ]: ");
        String title = scanner.nextLine();
        System.out.print("	ISBN [ " + book.getIsbn() + " ]: ");
        String isbn = scanner.nextLine();
        System.out.print("	Publication Year [ " + book.getPublicationYear() + " ]: ");
        int publicationYear = scanner.nextInt();
        System.out.print("	Page Count [ " + book.getPageCount() + " ]: ");
        int pageCount = scanner.nextInt();
        scanner.nextLine();
        System.out.print("	Language [ " + book.getLanguage() + " ]: ");
        String language = scanner.nextLine();
        System.out.print("	Available Copies [ " + book.getAvailableCopies() + " ]: ");
        int availableCopies = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");
        
        
		
        booksController.updateBook(bookId, title, authorId, publisherId, isbn, categoryId, publicationYear, pageCount, language, availableCopies);
	}
	
	public void promptArchiveBook() {
		System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Books / Delete", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)
		+" ----------------------------------------------------------------------------------\n");
		System.out.print("	Enter Book ID to delete: ");
		if(!scanner.hasNextInt()) {
			System.out.println("Invalid input. Please try again.");
			scanner.next();
			return;
		}
		
		int bookId = scanner.nextInt();
		scanner.nextLine();
		
		boolean isExisting = booksController.findBookById(bookId);
		if(!isExisting) {
			System.out.println(" Book not found.");
			return;
		}
		
		System.out.print("	Are you sure your want to delete this book? (yes/no): ");
		String input = scanner.nextLine().toLowerCase();
		System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");
		
		
        if (input.equals("yes") || input.equals("y")) {
            booksController.archiveBookById(bookId);
        }else if(input.equals("no") || input.equals("n")){
            System.out.println("	Back to Boooks Menu...");
        }else {
            System.out.println("	Invalid input. Try again.");
        }
	}

}
