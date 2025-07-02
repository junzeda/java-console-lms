package com.joysis.lms.view.student;

import java.util.Scanner;

import com.joysis.lms.controller.student.StudentHomepageController;
import com.joysis.lms.model.Student;
import com.joysis.lms.util.ConsoleStyles;

public class StudentHomepageView {
	private final Scanner scanner;
	private final StudentHomepageController homepageController;
	private Student student;

	public StudentHomepageView(Scanner scanner, StudentHomepageController homepageController) {
		this.scanner = scanner;
		this.homepageController = homepageController;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Student getStudent() {
		return student;
	}
	

	public void showStudentHomepageView() {
		while (true) {
			System.out.println("\n"+ConsoleStyles.colorize("Homepage", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)+" --------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-110s |\n", "	");
			System.out.printf("%-110s |\n", "	Welcome to Joysis Online Library!");
			System.out.printf("%-110s |\n", "	");
			System.out.printf("%-110s |\n", "	Account Details:");
			System.out.printf("%-110s |\n", "	Student ID: " + student.getStudentId());
			System.out.printf("%-110s |\n", "	Name: " + student.getFirstName() + " " + student.getLastName());
			System.out.printf("%-110s |\n", "	");
			System.out.printf("%-40s %-69s |\n", "	Navigation Menu", "Notifications & Reminders");
			System.out.printf("%-40s %-69s |\n", "	-------------", "-------------");
			System.out.printf("%-40s %-69s |\n", "	[1] Browse Books", "[5] Due Soon");
			System.out.printf("%-40s %-69s |\n", "	[2] My Borrowed Books", "[6] Overdue");
			System.out.printf("%-40s %-69s |\n", "	[3] Pending Request", "[7] System Announcement");
			System.out.printf("%-40s %-69s |\n", "	[4] Transaction History", " ");
			System.out.printf("%-110s |\n", "	");
			System.out.printf("%-110s |\n", "	-------------------------");
			System.out.printf("%-110s |\n", "	");
			System.out.printf("%-110s |\n", "	[8] Account Settings");
			System.out.printf("%-110s |\n", "	[0] <- Log out");
			System.out.printf("%-110s |\n", "	");
			System.out.println("-----------------------------------------------------------------------------------------------------------------------");

			System.out.print("	Enter your choice: ");
			if(!scanner.hasNextInt()) {
				System.out.println("	Invalid input. Please try again.");
				scanner.next();
				return;
			}
			
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			boolean isValid = homepageController.handleHomeSelection(choice);
			if(!isValid) {
				System.out.println("	Invalid selected option. Please try again.");
				return;
			}
			//settingsView.setStudent(student);
		}

	}
}
