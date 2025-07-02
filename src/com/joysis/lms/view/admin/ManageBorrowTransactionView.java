package com.joysis.lms.view.admin;

import java.util.Scanner;

import com.joysis.lms.controller.admin.ManageBorrowTransactionController;

public class ManageBorrowTransactionView {
	private final Scanner scanner;
	private final ManageBorrowTransactionController borrowTransactionController;
	
	public ManageBorrowTransactionView(Scanner scanner, ManageBorrowTransactionController borrowTransactionController) {
		this.scanner = scanner;
		this.borrowTransactionController = borrowTransactionController;
	}
	
	public void showBorrowTransactionMenu() {
        while (true) {
        	System.out.println("\nManage Borrow Transaction -------------------------------------");
			System.out.printf("%-41s |\n","	");
            System.out.printf("%-41s |\n","	[1] Pending Borrow Requests");
            System.out.printf("%-41s |\n","	[2] Return Book");
            System.out.printf("%-41s |\n","	[3] Incoming Due Date Return");
            System.out.printf("%-41s |\n","	[4] Overdue Book Tracker");
            System.out.printf("%-41s |\n","	[5] Transaction History");
            System.out.printf("%-41s |\n","	");
            System.out.printf("%-41s |\n","	[0] <- Back to Homepage");
            System.out.printf("%-41s |\n","	");
            System.out.println("--------------------------------------------------");
            

            System.out.print("	Enter your choice: ");
            if (!scanner.hasNextInt()) {
                System.out.println("	Invalid input. Please enter a number\n\n");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            boolean valid = borrowTransactionController.handleTransactionSelection(choice);
            if (!valid) {
                System.out.println("	Invalid option. Try again.\n\n");
            }
        }

    }
	
	public void promptPendingRequestId() {
		System.out.println("\nManage Borrow Transaction / Pending Request -------------------------------------\n");
		boolean hasPending = borrowTransactionController.displayAllPendingRequest();
		if(!hasPending) {
			System.out.println("	No pending request.");
			return;
		}
        
		System.out.print("	Enter Borrow Transaction ID: ");
		int borrowBookId = scanner.nextInt();
		scanner.nextLine();
        

		boolean isExists = borrowTransactionController.getBorrowerReqDetails(borrowBookId);
		if(!isExists) {
			System.out.println("	Borrow Transaction ID not found.");
			return;
		}
		
		
		System.out.println();
		System.out.print("	Accept Request? (yes/no): ");
        String input = scanner.nextLine().toLowerCase();
        System.out.println("\n--------------------------------------------------");
        if (input.equals("yes") || input.equals("y")) {
        	borrowTransactionController.acceptBorrowRequest(borrowBookId);
        }else if(input.equals("no") || input.equals("n")){
        	System.out.print("	Remarks: ");
        	String remarks = scanner.nextLine();
        	borrowTransactionController.rejectBorrowRequest(borrowBookId, remarks);
        }else {
            System.out.println("	Invalid input. Try again.");
        }
	}
	
	public void promptPendingReturnId() {
		System.out.println("\nManage Borrow Transaction / Pending Return -------------------------------------\n");
		boolean hasPending = borrowTransactionController.displayAllToReturn();
		if(!hasPending) {
			System.out.println("	No pending return.");
			return;
		}
		
		System.out.print("	Enter Borrow Transaction ID: ");
		int borrowBookId = scanner.nextInt();
		scanner.nextLine();
        

		boolean isExists = borrowTransactionController.getBorrowerReturnDetails(borrowBookId);
		if(!isExists) {
			System.out.println("	Borrow Transaction ID not found.");
			return;
		}
		
		
		System.out.println();
		System.out.print("	Accept Return Borrowed Book? (yes/no): ");
        String input = scanner.nextLine().toLowerCase();
        if (input.equals("yes") || input.equals("y")) {
        	System.out.print("	Damage/Lost book? (yes/no): ");
        	String damageLost = scanner.nextLine();
        	double amount;
        	String remarks = "";
        	if (damageLost.equals("yes") || damageLost.equals("y")) {
        		System.out.println("	Note: If an overdue book is returned with damage or reported lost, a corresponding penalty or replacement fee will be added in addition to the overdue fine.");
        		System.out.println();
        		System.out.print("	Enter Amount: ");
        		amount = scanner.nextDouble();
        		scanner.nextLine();
        		System.out.print("	Remarks: ");
        		remarks = scanner.nextLine();
        	} else if(damageLost.equals("no") || damageLost.equals("n")) {
        		amount = 0;
        		remarks = "Returned in good condition.";
        	}else {
        		System.out.println("	Invalid input. Try again.");
        		return;
        	}
        	
        	borrowTransactionController.acceptBookReturn(borrowBookId, amount, remarks);
        }else if(input.equals("no") || input.equals("n")){
        	System.out.println("	Back to manage borrow transaction...");
        	return;
        }else if(input.equals("exit")){
        	System.out.println("	Back to manage borrow transaction...");
        	return;
        }else {
            System.out.println("	Invalid input. Try again.");
        }

        System.out.println("\n--------------------------------------------------");
	}
	
	public void searchStudentTransacHistory() {
		System.out.println("\nManage Borrow Transaction / Search -------------------------------------\n");
		System.out.print("	Enter Student ID: ");
		String studentId = scanner.nextLine();
		
		boolean hasTransacHistory = borrowTransactionController.displayStudentTransacHistory(studentId);
		if(!hasTransacHistory) {
			System.out.println(" Student ID: "+ studentId + " has no history transaction." );
		}
	}

}
