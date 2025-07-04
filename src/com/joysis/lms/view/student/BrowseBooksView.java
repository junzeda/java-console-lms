package com.joysis.lms.view.student;

import java.util.Scanner;

import com.joysis.lms.controller.student.BrowseBooksController;
import com.joysis.lms.model.Student;
import com.joysis.lms.util.ConsoleStyles;

public class BrowseBooksView {
	private final Scanner scanner;
	private final BrowseBooksController browseController;
	private StudentHomepageView homepageView;
	private Student student;
	

	public BrowseBooksView(Scanner scanner, BrowseBooksController browseController) {
		this.scanner = scanner;
		this.browseController = browseController;
	}
	
	public void setStudentHomepageView(StudentHomepageView homepageView) {
		this.homepageView = homepageView;
	}
	
	
	public void showBrowseBooksMenu() {
		while (true) {

			System.out.println("\n"+ConsoleStyles.colorize("Homepage / Browse Books", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)+" -----------------------------------------------------------------------------------------------");
			
			System.out.printf("%-110s |\n", "	");
			System.out.printf("%-110s |\n", "	[1] View All Books");
			System.out.printf("%-110s |\n", "	[2] Search Book");
			System.out.printf("%-110s |\n", "	[3] Borrow Book");
			System.out.printf("%-110s |\n", "	[4] Saved Book(s)");
			System.out.printf("%-110s |\n", "	");
			System.out.printf("%-110s |\n", "	[0] <- Back to homepage");
			System.out.printf("%-110s |\n", "	");
			System.out.println("-----------------------------------------------------------------------------------------------------------------------");

			System.out.print("	Enter your choice: ");
			if (!scanner.hasNextInt()) {
				System.out.println("	Invalid input. Please try again.");
				scanner.next();
				return;
			}

			int choice = scanner.nextInt();
			scanner.nextLine();

			boolean isValid = browseController.handleBrowseSelection(choice);
			if (!isValid) {
				System.out.println("	Invalid selected option. Please try again.");
			}
		}
	}

	public void promptSearhBooks() {
		System.out.println("\n"+ConsoleStyles.colorize("Homepage / Browse Books / Search", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)+" --------------------------------------------------------------------------------------");
		System.out.print("\n	Advanced Search: ");
		String keyword = scanner.nextLine();
		System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");

		browseController.searchBooks(keyword);
	}
	
	public void promptBorrowBook() {
		student = homepageView.getStudent();
		String studentId = student.getStudentId();
		System.out.printf("\n"+ConsoleStyles.colorize("Homepage / Browse Books / Borrow", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)+" --------------------------------------------------------------------------------------");
		System.out.print("\n\n	Enter Book ID: ");
		int bookId = scanner.nextInt();
		scanner.nextLine();
		
		boolean isExists = browseController.getBookDetails(bookId);
		if(!isExists) {
			System.out.println("	Back to Browse Books Menu...");
			return;
		}
		System.out.println("\n	Library Book Return Policy");
		System.out.println("	- All borrowed books must be returned within 3 days from the date of issue.");
		System.out.println("	- Failure to return books on time will result in a fine for overdue.");
		System.out.println("	- Students will also be required to pay a fine for any lost or damaged books.");
		System.out.println();
		System.out.println("	By borrowing a book, you agree to these terms.");
		System.out.println("	Please handle library materials with care and return them promptly to avoid penalties.");
		
		System.out.print("	Would you like to request or borrow this book? (yes/no): ");
		String input = scanner.nextLine().toLowerCase();
		System.out.println();
        if (input.equals("yes") || input.equals("y")) {
        	browseController.borrowBook(studentId, bookId);
        }else if(input.equals("no") || input.equals("n")){
            System.out.println("	Back to Browse Books Menu...");
        }else {
            System.out.println("	Invalid input. Try again.");
        }
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");
	}

}
