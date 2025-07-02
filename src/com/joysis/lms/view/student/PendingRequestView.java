package com.joysis.lms.view.student;

import java.util.Scanner;

import com.joysis.lms.controller.student.PendingRequestController;
import com.joysis.lms.util.ConsoleStyles;

public class PendingRequestView {
	private final Scanner scanner;
	private final PendingRequestController pendingReqController;
	
	public PendingRequestView(Scanner scanner, PendingRequestController pendingReqController) {
		this.scanner = scanner;
		this.pendingReqController = pendingReqController;
	}
	
	public void showPendingBooks() {
		System.out.println("\n" + ConsoleStyles.colorize("Homepage / Pending Request", ConsoleStyles.YELLOW, ConsoleStyles.BOLD) + " --------------------------------------------------------------------------------------------");
		System.out.printf("%-110s |\n","	");
		pendingReqController.getPendingBooks();
		System.out.printf("%-110s |\n","	");
		System.out.printf("%-110s |\n","	[0] <- Back to Homepage");
		System.out.printf("%-110s |\n","	");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------\n");
		
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

