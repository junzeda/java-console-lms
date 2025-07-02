package com.joysis.lms.view.student;

import java.util.Scanner;

import com.joysis.lms.controller.student.AccountSettingsController;
import com.joysis.lms.model.Student;
import com.joysis.lms.util.ConsoleStyles;

public class AccountSettingsView {
	private final Scanner scanner;
	private final AccountSettingsController settingsController;
	private Student student ;
	private StudentHomepageView homepageView;

	public AccountSettingsView(Scanner scanner, AccountSettingsController settingsController) {
		this.scanner = scanner;
		this.settingsController = settingsController;
	}
	
	public void setStudentHomepageView(StudentHomepageView homepageView) {
		this.homepageView = homepageView;
	}

	

	public void showAccountSettingsMenu() {
		student = homepageView.getStudent();
		while (true) {
			System.out.println("\n" + ConsoleStyles.colorize("Homepage / Account Settings", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)
			+ " -------------------------------------------------------------------------------------------");
			System.out.printf("%-110s |\n", "	");
			System.out.printf("%-110s |\n", "	Profile Details:");
			System.out.printf("%-110s |\n", "	Student ID: " + student.getStudentId());
			System.out.printf("%-110s |\n", "	Name: " + student.getFirstName() + " " + student.getLastName());
			System.out.printf("%-110s |\n", "	Contact Number: " + student.getContactNumber());
			System.out.printf("%-110s |\n", "	");
			System.out.printf("%-110s |\n", "	[1] Update Name");
			System.out.printf("%-110s |\n", "	[2] Update Contact Number");
			System.out.printf("%-110s |\n", "	[3] Change Password");
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
			
			boolean isValid = settingsController.handleAccSettingsSelection(choice);
		}
	}
	
	public void promptUpdateName() {
		student = homepageView.getStudent();
		System.out.println("\n" + ConsoleStyles.colorize("Homepage / Account Settings / Update Name", ConsoleStyles.YELLOW, ConsoleStyles.BOLD) + " -----------------------------------------------------------------------------\n");
		System.out.println("	Note: Leave blank to keep current value.\n");
		System.out.print("	First Name [ " + student.getFirstName() +" ]: ");
		String firstName = scanner.nextLine();
		System.out.print("	Last Name [ " + student.getLastName() +" ]: ");
		String lastName = scanner.nextLine();
		System.out.println("-----------------------------------------------------------------------------------------------------------------------\n");
		
		settingsController.updateStudent(student.getStudentId(), firstName, lastName, student.getContactNumber());
	}
	
	public void promptUpdateContact() {
		student = homepageView.getStudent();
		System.out.println("\n" + ConsoleStyles.colorize("Homepage / Account Settings / Update Contact Number", ConsoleStyles.YELLOW, ConsoleStyles.BOLD) 
		+ " -------------------------------------------------------------------");
		System.out.println("\n	Note: Leave blank to keep current value.\n");
		System.out.print("	Contact Number [ " + student.getContactNumber() + " ]: ");
		String contactNumber = scanner.nextLine();
		System.out.println("-----------------------------------------------------------------------------------------------------------------------\n");
		
		
		settingsController.updateStudent(student.getStudentId(), student.getFirstName(), student.getLastName(), contactNumber);
	}
	
	public void promptChangePass() {
		student = homepageView.getStudent();
		System.out.println("\n" + ConsoleStyles.colorize("Homepage / Account Settings / Change Password", ConsoleStyles.YELLOW, ConsoleStyles.BOLD) 
		+ " -------------------------------------------------------------------------\n");
		System.out.println("\n	Note: Make sure the details match the current data.\n");
		System.out.print("	Enter old password: ");
		String oldPass = scanner.nextLine();
		System.out.print("	Enter new password: ");
		String newPass = scanner.nextLine();
		System.out.println("-----------------------------------------------------------------------------------------------------------------------\n");
		
		
		settingsController.changePassword(student.getStudentId(), newPass);
	}

}
