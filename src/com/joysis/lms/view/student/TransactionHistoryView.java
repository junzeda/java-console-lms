package com.joysis.lms.view.student;

import java.util.Scanner;

import com.joysis.lms.controller.student.TransactionHistoryController;
import com.joysis.lms.util.ConsoleStyles;

public class TransactionHistoryView {
	private final Scanner scanner;
	private final TransactionHistoryController transactionController;

	public TransactionHistoryView(Scanner scanner, TransactionHistoryController transactionController) {
		this.scanner = scanner;
		this.transactionController = transactionController;
	}

	public void showTransactionHistory() {
		System.out.println("\n"+ConsoleStyles.colorize("Homepage / Transaction History", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)+" ==============================================================="
				+ "=======================================================================================================================================");
		transactionController.getTransactionHistory();
		System.out.println("================================================================================================================================================"
				+ ""
				+ "=====================================================================================\n");
		System.out.println("	[0] <- Back to Homepage");
		System.out.println();
		
		while (true) {

			System.out.print("	Enter your choice: ");
			if (!scanner.hasNextInt()) {
				System.out.println("	Invalid input. Please enter a number.");
				scanner.next();
				continue;
			}
			int choice = scanner.nextInt();
			if (choice == 0) {
				System.out.println("	Back to Homepage...");
				break;
			} else {
				System.out.println("	Invalid option. Please try again.");
			}
		}
		
	}
}
