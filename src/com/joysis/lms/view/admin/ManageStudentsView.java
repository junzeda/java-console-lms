package com.joysis.lms.view.admin;

import java.util.Scanner;

import com.joysis.lms.controller.admin.ManageStudentsController;
import com.joysis.lms.model.Student;
import com.joysis.lms.util.ConsoleStyles;

public class ManageStudentsView {
	private final Scanner scanner;
	private final ManageStudentsController studentsController;
	
	public ManageStudentsView(Scanner scanner, ManageStudentsController studentsController) {
		this.scanner = scanner;
		this.studentsController = studentsController;
	}
	

	
	public void showStudentsMenu() {
        while (true) {
        	System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Students", ConsoleStyles.YELLOW, ConsoleStyles.BOLD) 
            +" ----------------------------------------------------------------------------------------\n");
			System.out.printf("%-41s |\n","	");
            System.out.printf("%-41s |\n","	[1] Show All Students");
            System.out.printf("%-41s |\n","	[2] Add Student");
            System.out.printf("%-41s |\n","	[3] Update Student");
            System.out.printf("%-41s |\n","	[4] Delete Student");
            System.out.printf("%-41s |\n","	");
            System.out.printf("%-41s |\n","	[0] <- Back to Homepage");
            System.out.printf("%-41s |\n","	");
            System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");
            
            

            System.out.print("	Enter your choice: ");
            if (!scanner.hasNextInt()) {
                System.out.println("	Invalid input. Please enter a number\n\n");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            boolean valid = studentsController.handleStudentsSelection(choice);
            if (!valid) {
                System.out.println("	Invalid option. Try again.\n\n");
            }
        }

    }
	
	public void promptAddStudent() {
		System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Students / Add", ConsoleStyles.YELLOW, ConsoleStyles.BOLD) 
        +" ----------------------------------------------------------------------------------\n");
    	System.out.print("	Student ID: ");
    	String studentId = scanner.nextLine().trim();
        System.out.print("	First Name: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("	Last Name: ");
        String lastName = scanner.nextLine().trim();
        System.out.print("	Contact Number: ");
        String contactNumber = scanner.nextLine().trim();
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");
        
       studentsController.addStudent(studentId, firstName, lastName, contactNumber);
    	
    }
	
	public void promptUpdateStudent(){
		System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Students / Update", ConsoleStyles.YELLOW, ConsoleStyles.BOLD) 
        +" -------------------------------------------------------------------------------\n");
        System.out.print("	Enter Student ID to update: ");
        String studentId = scanner.nextLine();
        
        Student student = studentsController.getStudentById(studentId);
        if(student == null){
            System.out.println("	Student not found.");
            return;
        }
        System.out.println("	Note: Leave it blank if you want to keep the current data.\n");
        System.out.print("	First Name [ " + student.getFirstName() + " ]: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("	Last Name [ " + student.getLastName() + " ]: ");
        String lastName = scanner.nextLine().trim();
        System.out.print("	Contact Number [ " + student.getContactNumber() + " ]: ");
        String contactNumber = scanner.nextLine().trim();
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");
        
        studentsController.updateStudent(studentId, firstName, lastName, contactNumber);
        
    }
	
	public void promptArchiveStudentById() {
		System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Students / Delete", ConsoleStyles.YELLOW, ConsoleStyles.BOLD) 
        +" -------------------------------------------------------------------------------\n");
        System.out.print("	Enter Student ID to delete: ");
        String studentId = scanner.nextLine();
        
        studentsController.findStudentById(studentId);
        System.out.print("	Are you sure your want to delete this student? (yes/no): ");
        String input = scanner.nextLine().toLowerCase();
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");
        if (input.equals("yes") || input.equals("y")) {
            studentsController.archiveStudentById(studentId);
        }else if(input.equals("no") || input.equals("n")){
            System.out.println("	Back to Student Menu...");
        }else {
            System.out.println("	Invalid input. Try again.");
        }
    }

}
