package com.joysis.lms.view.admin;

import com.joysis.lms.controller.admin.ManageAuthorsController;
import com.joysis.lms.model.Author;
import com.joysis.lms.util.ConsoleStyles;

import java.util.Scanner;

public class ManageAuthorsView {

    private final Scanner scanner;
    private final ManageAuthorsController authorsController;

    public ManageAuthorsView(Scanner scanner, ManageAuthorsController authorsController) {
        this.scanner = scanner;
        this.authorsController = authorsController;
    }

    public void showAuthorsMenu() {
        while (true) {
        	System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Authors", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)
            +" ---------------------------------------------------------------------------------------");
			System.out.printf("%-110s |\n","	");
            System.out.printf("%-110s |\n","	[1] Show All Authors");
            System.out.printf("%-110s |\n","	[2] Add Author");
            System.out.printf("%-110s |\n","	[3] Update Author");
            System.out.printf("%-110s |\n","	[4] Delete Author");
            System.out.printf("%-110s |\n","	");
            System.out.printf("%-110s |\n","	[0] <- Back to Homepage");
            System.out.printf("%-110s |\n","	");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            
            
            

            System.out.print("	Enter your choice: ");
            if (!scanner.hasNextInt()) {
                System.out.println("	Invalid input. Please enter a number\n\n");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            boolean valid = authorsController.handleAuthorSelection(choice);
            if (!valid) {
                System.out.println("	Invalid option. Try again.\n\n");
            }
        }

    }
    
    public void promptAddAuthor() {
    	System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Authors / Add", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)+" ---------------------------------------------------------------------------------\n");
        System.out.print("	First Name: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("	Last Name: ");
        String lastName = scanner.nextLine().trim();
        System.out.print("	Birth Date (Format: yyyy-mm-dd): ");
        String birthDate = scanner.nextLine().trim();
        System.out.print("	Nationality: ");
        String nationality = scanner.nextLine();
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");
        
        
        
        authorsController.addAuthor(firstName, lastName, birthDate, nationality);
    	
    }
    
    public void promptUpdateAuthor() {
    	System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Authors / Update", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)
        +" ------------------------------------------------------------------------------\n");
    	System.out.print("	Enter Author ID to update: ");
    	if(!scanner.hasNextInt()) {
    		System.out.println("	Invalid input. Please try again.");
    		scanner.next();
    		return;
    	}
    	int authorId = scanner.nextInt();
    	scanner.nextLine();
    	
    	Author author = authorsController.getAuthorById(authorId);
    	if(author == null) {
    		System.out.println("	No author found.");
    		return;
    	}
    	System.out.println("	Note: Leave blank to keep current value.\n");
    	System.out.print("	First Name [ " + author.getFirstName() + " ]: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("	Last Name [ " + author.getLastName() + " ]: ");
        String lastName = scanner.nextLine().trim();
        System.out.print("	Birth Date (Format: yyyy-mm-dd) [ " + author.getBirthDate() + " ]: ");
        String birthDate = scanner.nextLine().trim();
        System.out.print("	Nationality [ " + author.getNationality() + " ]: ");
        String nationality = scanner.nextLine().trim();
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");
        
        
        
        authorsController.updateAuthor(authorId, firstName, lastName, birthDate, nationality);
    	
    	
    }
    
    public void promptArchiveAuthorById() {
    	System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Authors / Delete", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)
        +" ------------------------------------------------------------------------------\n");
        System.out.print("	Enter Author ID to delete: ");
        int authorId = scanner.nextInt();
        scanner.nextLine();
        authorsController.findAuthorById(authorId);
        System.out.print("	Are you sure your want to delete this author? (yes/no): ");
        String input = scanner.nextLine().toLowerCase();
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");
        
        if (input.equals("yes") || input.equals("y")) {
            authorsController.archiveAuthorById(authorId);
        }else if(input.equals("no") || input.equals("n")){
            System.out.println("	Back to Author Menu...");
        }else {
            System.out.println("	Invalid input. Try again.");
        }
        
    }
    
    
    

    
}
