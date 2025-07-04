package com.joysis.lms.view.admin;

import java.util.Scanner;

import com.joysis.lms.controller.admin.ManageCategoriesController;
import com.joysis.lms.model.Category;
import com.joysis.lms.util.ConsoleStyles;

public class ManageCategoriesView {
	private final Scanner scanner;
	private final ManageCategoriesController categoriesController;
	
	public ManageCategoriesView(Scanner scanner, ManageCategoriesController categoriesController) {
		this.scanner = scanner;
		this.categoriesController = categoriesController;
	}
	

	
	public void showCategoriesMenu() {
        while (true) {
        	System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Categories", ConsoleStyles.YELLOW, ConsoleStyles.BOLD) 
            +" --------------------------------------------------------------------------------------\n");
			System.out.printf("%-41s |\n","	");
            System.out.printf("%-41s |\n","	[1] Show All Categories");
            System.out.printf("%-41s |\n","	[2] Add Category");
            System.out.printf("%-41s |\n","	[3] Update Category");
            System.out.printf("%-41s |\n","	[4] Delete Category");
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

            boolean valid = categoriesController.handleCategoriesSelection(choice);
            if (!valid) {
                System.out.println("	Invalid option. Try again.\n\n");
            }
        }

    }
	
	public void promptAddCategory() {
		System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Categories / Add", ConsoleStyles.YELLOW, ConsoleStyles.BOLD) 
        +" --------------------------------------------------------------------------------\n");
    	System.out.print("	Category Name: ");
    	String categoryName = scanner.nextLine().trim();
    	System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");
        
       categoriesController.addCategory(categoryName);
    	
    }
	
	public void promptUpdateCategory(){
		System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Categories / Update", ConsoleStyles.YELLOW, ConsoleStyles.BOLD) 
        +" -----------------------------------------------------------------------------\n");
        System.out.print("	Enter Category ID to update: ");
        if(!scanner.hasNext()) {
        	System.out.println("	Invalid input. Please try again.");
        	scanner.next();
        	return;
        }
        int bookCategoryId = scanner.nextInt();
        scanner.nextLine();
        
        Category category = categoriesController.getCategoryById(bookCategoryId);
        if(category == null){
            System.out.println("	Category not found.");
            return;
        }
        System.out.println("	Note: Leave it blank if you want to keep the current data.\n");
        System.out.print("	First Name [ " + category.getCategoryName() + " ]: ");
        String categoryName = scanner.nextLine().trim();
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");
        
        categoriesController.updateCategory(bookCategoryId, categoryName);
        
    }
	
	public void promptDeleteCategory() {
		System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Categories / Delete", ConsoleStyles.YELLOW, ConsoleStyles.BOLD) 
        +" -----------------------------------------------------------------------------\n");
        System.out.print("	Enter Category ID to delete: ");
        if(!scanner.hasNextInt()) {
        	System.out.println("	Invalid input. Please try again.");
        	scanner.next();
        	return;
        }
        int studentId = scanner.nextInt();
        scanner.nextLine();
        
        categoriesController.findCategoryById(studentId);
        System.out.print("	Are you sure your want to delete this category? (yes/no): ");
        String input = scanner.nextLine().toLowerCase();
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------\n");
        if (input.equals("yes") || input.equals("y")) {
            categoriesController.deleteCategory(studentId);
        }else if(input.equals("no") || input.equals("n")){
            System.out.println("	Back to Categories Menu...");
        }else {
            System.out.println("	Invalid input. Try again.");
        }
	}
	

}
