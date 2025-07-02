package com.joysis.lms.view;

import java.util.Scanner;

import com.joysis.lms.controller.LoginController;
import com.joysis.lms.util.ConsoleStyles;

public class LoginView {
	private final Scanner scanner;
	private final LoginController loginController;

	public LoginView(Scanner scanner, LoginController loginController) {
		this.scanner = scanner;
		this.loginController = loginController;
	}
	

	// Login selection if admin or student account
	public void selectAccountType() {
		while (true) {
			System.out.println("\n"+ConsoleStyles.colorize("Select Account Type", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)+" ---------------------------------------------------------------------------------------------------");
			System.out.printf("%-110s |\n","	");
            System.out.printf("%-110s |\n","	[1] Student");
            System.out.printf("%-110s |\n","	[2] Admin");
            System.out.printf("%-110s |\n","	");
            System.out.printf("%-110s |\n","	[0] Exit");
            System.out.printf("%-110s |\n","	");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            
//            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
//            System.out.println("\n"+ConsoleStyles.colorize("Select Account type", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)+" ---------------------------------------------------------------------------------------------------");
            
			
			System.out.print("	Enter your choice: ");
			if (!scanner.hasNextInt()) {
				System.out.println("	Invalid input. Please input a number.");
				scanner.next();
				continue;
			}

			int choice = scanner.nextInt();
			scanner.nextLine();
			
			boolean isValid = loginController.handleSelectedAccount(choice);
			if(!isValid) {
				System.out.println("	Invalid selected option. Try again.");
			}
			
		}

	}
	
	// Student login section
	public void promptStudentLogin() {

		System.out.println("\n"+ConsoleStyles.colorize("Select Account Type / Student Login", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)+" -----------------------------------------------------------------------------------");
		System.out.print("\n	Student ID: ");
		String studentId = scanner.nextLine();
		System.out.print("	Password: ");
		String password = scanner.nextLine();
		System.out.println("\n-----------------------------------------------------------------------------------------------------------------------");
		loginController.loginStudentAccount(studentId, password);
		
		
	}
	
	// Admin login section
	public void promptAdminLogin() {
		System.out.println("\n"+ConsoleStyles.colorize("Select Account Type / Admin Login", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)+" -------------------------------------------------------------------------------------");
		System.out.print("\n	Username: ");
		String username = scanner.nextLine();
		System.out.print("	Password: ");
		String password = scanner.nextLine();
		System.out.println("\n-----------------------------------------------------------------------------------------------------------------------");
		loginController.loginAdminAccount(username, password);
	}

}
