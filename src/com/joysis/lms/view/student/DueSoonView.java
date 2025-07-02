package com.joysis.lms.view.student;

import java.util.Scanner;

import com.joysis.lms.controller.student.DueSoonController;
import com.joysis.lms.util.ConsoleStyles;

public class DueSoonView {
	private final Scanner scanner;
	private final DueSoonController dueController;

	public DueSoonView(Scanner scanner, DueSoonController dueController) {
		this.scanner = scanner;
		this.dueController = dueController;
	}

	public void showIncomingDue() {
		System.out.println("\n"
				+ ConsoleStyles.colorize("Homepage / Due Soon", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)
				+ " ---------------------------------------------------------------------------------------------------1");
		System.out.printf("%-110s |\n", "	");
		dueController.incomingDueBook();
		System.out.printf("%-110s |\n", "	");
		System.out.printf("%-110s |\n","	[0] <- Back to Homepage");
		System.out.printf("%-110s |\n","	");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------\n");

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
