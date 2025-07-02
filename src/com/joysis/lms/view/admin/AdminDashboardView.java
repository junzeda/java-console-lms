package com.joysis.lms.view.admin;

import java.util.Scanner;

import com.joysis.lms.controller.admin.AdminDashboardController;
import com.joysis.lms.util.ConsoleStyles;

public class AdminDashboardView {
	private final Scanner scanner;
	private final AdminDashboardController dashboardController;

	public AdminDashboardView(Scanner scanner, AdminDashboardController dashboardController) {
		this.scanner = scanner;
		this.dashboardController = dashboardController;
	}

	public void showAdminDashboard() {
		int totalBooks = dashboardController.getTotalBooks();
		int totalAuthors = dashboardController.getTotalAuthors();
		int totalPublishers = dashboardController.getTotalPublishers();
		int totalCategories = dashboardController.getTotalCategories();
		int totalStudents = dashboardController.getTotalStudents();
		int totalBorrowToday = dashboardController.getTotalBorrowedToda();
		int totalReturn = dashboardController.getTotalReturnedToday();
		int totalIncomingDue = dashboardController.getTotalIncomingDue();
		int totalCompletedTransac = dashboardController.getTotalTransaction();
		// while (true) {
		System.out.println("\n"
				+ ConsoleStyles.colorize("Admin Homepage / Dashboard", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)
				+ " --------------------------------------------------------------------------------------------");
		System.out.printf("%-54s %-54s |\n", "	", "	");
		System.out.printf("%-54s %-54s |\n", "	Total number of books: " + totalBooks,
				"	Today's issued books: " + totalBorrowToday);
		System.out.printf("%-54s %-54s |\n", "	Total authors: " + totalAuthors,
				"	Today's returned books: " + totalReturn);
		System.out.printf("%-54s %-54s |\n", "	Total publishers: " + totalPublishers,
				"	Incoming due returns: " + totalIncomingDue);
		System.out.printf("%-54s %-54s |\n", "	Number of categories: " + totalCategories,
				"	Total Completed Transaction: " + totalCompletedTransac);
		System.out.printf("%-54s %-54s |\n", "	Total registered members: " + totalStudents, "	");
		System.out.printf("%-54s %-54s |\n", "	", "	");
		System.out.printf("%-54s %-54s |\n", "	[0] <- Back to Admin Homepage", "	");
		System.out.printf("%-54s %-54s |\n", "	", "	");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");

		System.out.print("	Enter your choice: ");
		scanner.next();

		// }
	}

}
