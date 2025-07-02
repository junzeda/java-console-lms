package com.joysis.lms.view.admin;

import java.util.Scanner;

import com.joysis.lms.controller.admin.AdminHomepageController;
import com.joysis.lms.model.Admin;
import com.joysis.lms.util.ConsoleStyles;

public class AdminHomepageView {
	private final Scanner scanner;
	private final AdminHomepageController adminHomeController;
	private Admin admin;

	public AdminHomepageView(Scanner scanner, AdminHomepageController adminHomeController) {
		this.scanner = scanner;
		this.adminHomeController = adminHomeController;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void showAdminHomepage() {
		while (true) {
			System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)+" --------------------------------------------------------------------------------------------------------");
			System.out.printf("%-110s |\n","	");
			System.out.printf("%-110s |\n","	Welcome, Admin!");
			System.out.printf("%-110s |\n","	Account ID: "+ admin.getAdminId() +" | Username: "+ admin.getUsername());
			System.out.printf("%-110s |\n","	");
            System.out.printf("%-110s |\n","	[1] Dashboard");
            System.out.printf("%-110s |\n","	[2] Manage Books");
            System.out.printf("%-110s |\n","	[3] Manage Authors");
            System.out.printf("%-110s |\n","	[4] Manage Publishers");
            System.out.printf("%-110s |\n","	[5] Manage Categories");
            System.out.printf("%-110s |\n","	[6] Manage Students");
            System.out.printf("%-110s |\n","	[7] Manage Borrow Transaction");
            System.out.printf("%-110s |\n","	");
            System.out.printf("%-110s |\n","	[0] <- Log out");
            System.out.printf("%-110s |\n","	");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");

			System.out.print("	Enter your choice: ");

			if (!scanner.hasNextInt()) {
				System.out.println("	Invalid input. Please enter a number.");
				scanner.next();
			}

			int choice = scanner.nextInt();
			scanner.nextLine();

			boolean isValid = adminHomeController.handleHomepageSelection(choice);
			if (!isValid) {
				System.out.println("Invalid selected option. Please try again.");
			}

		}
	}

}
