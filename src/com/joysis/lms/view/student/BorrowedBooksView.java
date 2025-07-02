package com.joysis.lms.view.student;

import java.util.Scanner;

import com.joysis.lms.controller.student.BorrowedBooksController;
import com.joysis.lms.util.ConsoleStyles;

public class BorrowedBooksView {
	private final Scanner scanner;
	private final BorrowedBooksController borrowedController;

	public BorrowedBooksView(Scanner scanner, BorrowedBooksController borrowedController) {
		this.scanner = scanner;
		this.borrowedController = borrowedController;
	}

	public void showBorrowedBooks() {
		System.out.println("\n" + ConsoleStyles.colorize("Homepage / My Borrowed Books", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)+ " ------------------------------------------------------------------------------------------");
		System.out.printf("%-110s |\n","	");
		borrowedController.currentBorrowedBooks();
		System.out.printf("%-110s |\n","	");
		System.out.printf("%-110s |\n","	[0] <- Back to Homepage");
		System.out.printf("%-110s |\n","	");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------\n");

		
		while(true) {
			
			System.out.print("	Enter your choice: ");
			if(!scanner.hasNextInt()) {
				System.out.println("	Invalid input. Please enter a number.");
				scanner.next();
				continue;
			}
			int choice = scanner.nextInt();
			if(choice == 0) {
				System.out.println("	Back to Homepage...");
				break;
			}else {
				System.out.println("	Invalid option. Please try again.");
			}
		}
		
	}

}
