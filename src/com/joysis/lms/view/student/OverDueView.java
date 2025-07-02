package com.joysis.lms.view.student;

import java.util.Scanner;

import com.joysis.lms.controller.student.OverDueController;
import com.joysis.lms.util.ConsoleStyles;

public class OverDueView {
	private final Scanner scanner;
	private final OverDueController overDueController;

	public OverDueView(Scanner scanner, OverDueController overDueController) {
		this.scanner = scanner;
		this.overDueController = overDueController;
	}

	public void showOverDue() {
		System.out.println("\n"
				+ ConsoleStyles.colorize("Homepage / Over Due", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)
				+ " ---------------------------------------------------------------------------------------------------");
		System.out.printf("%-110s |\n", "	");
		overDueController.overDueBook();
		System.out.printf("%-110s |\n", "	");
		System.out.printf("%-110s |\n", "	[0] <- Back to Homepage");
		System.out.printf("%-110s |\n", "	");
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
