package com.joysis.lms.view.admin;

import java.util.Scanner;

import com.joysis.lms.controller.admin.ManagePublishersController;
import com.joysis.lms.model.Publisher;
import com.joysis.lms.util.ConsoleStyles;

public class ManagePublishersView {
	private final Scanner scanner;
	private final ManagePublishersController publishersController;
	
	public ManagePublishersView(Scanner scanner, ManagePublishersController publishersController) {
		this.scanner = scanner;
		this.publishersController = publishersController;
	}
	
	

    
    
	public void showPublishersMenu() {
        while (true) {
        	System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Publishers", ConsoleStyles.YELLOW, ConsoleStyles.BOLD) 
            +" --------------------------------------------------------------------------------------\n");
			System.out.printf("%-41s |\n","	");
            System.out.printf("%-41s |\n","	[1] Show All Publishers");
            System.out.printf("%-41s |\n","	[2] Add Publisher");
            System.out.printf("%-41s |\n","	[3] Update Publisher");
            System.out.printf("%-41s |\n","	[4] Delete Publisher");
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

            boolean valid = publishersController.handlePublishersSelection(choice);
            if (!valid) {
                System.out.println("	Invalid option. Try again.\n\n");
            }
        }

    }
	
	public void promptAddPublisher() {
		System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Publishers / Add", ConsoleStyles.YELLOW, ConsoleStyles.BOLD) 
        +" --------------------------------------------------------------------------------\n");
        System.out.print("	Name: ");
        String name = scanner.nextLine();
        System.out.print("	Email: ");
        String email = scanner.nextLine();
        System.out.print("	Contact Number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("	Address: ");
        String address = scanner.nextLine();
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");
        
        
        publishersController.addPublisher(name, email, contactNumber, address);
    	
    }
    
    public void promptUpdatePublisher() {
    	System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Publishers / Update", ConsoleStyles.YELLOW, ConsoleStyles.BOLD) 
        +" -----------------------------------------------------------------------------\n");
    	System.out.print("	Enter Publisher ID to update: ");
    	if(!scanner.hasNextInt()) {
    		System.out.println("	Invalid input. Please try again.");
    		scanner.next();
    		return;
    	}
    	int publisherId = scanner.nextInt();
    	scanner.nextLine();
    	
    	Publisher publisher = publishersController.getPublisherById(publisherId);
    	if(publisher == null) {
    		System.out.println("	No publisher found.");
    		return;
    	}
    	System.out.println("	Note: Leave it blank if you want to keep the current data.\n");
    	System.out.print("	Name [ " + publisher.getName() + " ]: ");
        String name = scanner.nextLine().trim();
        System.out.print("	Email [ " + publisher.getEmail() + " ]: ");
        String email = scanner.nextLine().trim();
        System.out.print("	Contact Number [ " + publisher.getContactNumber() + " ]: ");
        String contactNumber = scanner.nextLine().trim();
        System.out.print("	Address [ " + publisher.getAddress() + " ]: ");
        String address = scanner.nextLine().trim();
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");
        
        
  
        publishersController.updatePublisher(publisherId, name, email, contactNumber, address);
    	
    	
    }
    
    public void promptArchivePublisherById() {
    	System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Publishers / Delete", ConsoleStyles.YELLOW, ConsoleStyles.BOLD) 
        +" -----------------------------------------------------------------------------\n");
        System.out.print("	Enter Publisher ID to delete: ");
        int publisherId = scanner.nextInt();
        scanner.nextLine();
        publishersController.findPublisherById(publisherId);
        System.out.print("	Are you sure your want to delete this Publisher? (yes/no): ");
        String input = scanner.nextLine().toLowerCase();
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");
        
        if (input.equals("yes") || input.equals("y")) {
            publishersController.archivePublisherById(publisherId);
        }else if(input.equals("no") || input.equals("n")){
            System.out.println("	Back to Publisher Menu...");
        }else {
            System.out.println("	Invalid input. Try again.");
        }
    }

}
