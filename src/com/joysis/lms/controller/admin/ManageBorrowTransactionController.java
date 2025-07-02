package com.joysis.lms.controller.admin;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.joysis.lms.dao.BookDAO;
import com.joysis.lms.dao.BorrowBookDAO;
import com.joysis.lms.dao.StudentDAO;
import com.joysis.lms.model.Book;
import com.joysis.lms.model.BorrowBook;
import com.joysis.lms.model.Student;
import com.joysis.lms.view.admin.AdminHomepageView;
import com.joysis.lms.view.admin.ManageBorrowTransactionView;

public class ManageBorrowTransactionController {
	private final BorrowBookDAO borrowBookDAO;
	private final StudentDAO studentDAO;
	private final BookDAO bookDAO;
	private ManageBorrowTransactionView borrowView;
	private AdminHomepageView adminHomepageView;

	public ManageBorrowTransactionController(BorrowBookDAO borrowBookDAO, StudentDAO studentDAO, BookDAO bookDAO) {
		this.borrowBookDAO = borrowBookDAO;
		this.studentDAO = studentDAO;
		this.bookDAO = bookDAO;
	}

	public void setBorrowTransactionView(ManageBorrowTransactionView borrowView) {
		this.borrowView = borrowView;
	}
	
	public void setAdminHomepageView(AdminHomepageView adminHomepageView) {
		this.adminHomepageView = adminHomepageView;
	}

	public boolean handleTransactionSelection(int choice) {
		switch (choice) {
		case 1:
			borrowView.promptPendingRequestId();
			return true;
		case 2:
			borrowView.promptPendingReturnId();
			return true;
		case 3:
			displayIncomingDueReturn();
			return true;
		case 4:
			displayOverdueReturn();
			return true;
		case 5:
			displayAllCompletedTrans();
			borrowView.searchStudentTransacHistory();
			return true;
		case 0:
			adminHomepageView.showAdminHomepage();
			return true;
		default:
			return false;
		}
	}
	
	public void displayAllCompletedTrans() {
		List<BorrowBook> borrowBooks = borrowBookDAO.getAllCompletedTrans();
		if (!borrowBooks.isEmpty()) {
			System.out.println(
					"\n======================================================= List of Completed Transaction =======================================================");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-30s %-20s %-30s %-35s %-25s %-25s %-25s %-25s %-35s %-25s %-5s%n",
					"| Borrow Transaction ID", "| Student ID", "| Name", "| Book Title", "| Borrow Date", "| Due Date",
					"| Return Date", "| Fine Amount", "| Remarks", "| Requested At", "| ");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------------");

			for (BorrowBook borrow : borrowBooks) {
				Student student = studentDAO.getStudentById(borrow.getStudentId());
				Book book = bookDAO.getBookById(borrow.getBookId());
				System.out.printf("%-30s %-20s %-30s %-35s %-25s %-25s %-25s %-25s %-35s %-25s %-5s%n",
						"| " + borrow.getBorrowId(),
						"| " + student.getStudentId(),
						"| " + student.getFirstName() + " " + student.getLastName(),
						"| " + book.getTitle(),
						"| " + borrow.getBorrowDate(),
						"| " + borrow.getDueDate(),
						"| " + borrow.getReturnDate(),
						"| " + borrow.getFineAmount(),
						"| " + borrow.getRemarks(),
						"| " + borrow.getRequestedAt(),
						"| ");
			}
		}

	}
	
	public boolean displayStudentTransacHistory(String studentId) {
		List<BorrowBook> borrowBooks = borrowBookDAO.getStudentTransacHistoryById(studentId);
		if (!borrowBooks.isEmpty()) {
			System.out.println(
					"\n======================================================= Student Transaction =======================================================");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-30s %-20s %-30s %-35s %-25s %-25s %-25s %-25s %-35s %-25s %-5s%n",
					"| Borrow Transaction ID", "| Student ID", "| Name", "| Book Title", "| Borrow Date", "| Due Date",
					"| Return Date", "| Fine Amount", "| Remarks", "| Requested At", "| ");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------------");

			for (BorrowBook borrow : borrowBooks) {
				Student student = studentDAO.getStudentById(borrow.getStudentId());
				Book book = bookDAO.getBookById(borrow.getBookId());
				System.out.printf("%-30s %-20s %-30s %-35s %-25s %-25s %-25s %-25s %-35s %-25s %-5s%n",
						"| " + borrow.getBorrowId(),
						"| " + student.getStudentId(),
						"| " + student.getFirstName() + " " + student.getLastName(),
						"| " + book.getTitle(),
						"| " + borrow.getBorrowDate(),
						"| " + borrow.getDueDate(),
						"| " + borrow.getReturnDate(),
						"| " + borrow.getFineAmount(),
						"| " + borrow.getRemarks(),
						"| " + borrow.getRequestedAt(),
						"| ");
			}
			return true;
		}
		return false;

	}

	public boolean displayAllPendingRequest() {
		List<BorrowBook> borrowBooks = borrowBookDAO.getAllPendingRequest();
		if (!borrowBooks.isEmpty()) {
			System.out.println(
					"\n======================================================= List of Pending Request =======================================================");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-30s %-20s %-35s %-35s %-25s %-5s%n", "| Borrow Transaction ID", "| Student ID",
					"| Name", "| Book Title", "| Requested At", "| ");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------------");

			for (BorrowBook borrow : borrowBooks) {
				Student student = studentDAO.getStudentById(borrow.getStudentId());
				Book book = bookDAO.getBookById(borrow.getBookId());
				System.out.printf("%-30s %-20s %-35s %-35s %-25s %-5s%n", "| " + borrow.getBorrowId(),
						"| " + student.getStudentId(), "| " + student.getFirstName() + " " + student.getLastName(),
						"| " + book.getTitle(), "| " + borrow.getRequestedAt(), "| ");
			}
			return true;
		}

		return false;
	}

	public boolean displayAllToReturn() {
		List<BorrowBook> borrowBooks = borrowBookDAO.getAllPendingReturn();
		if (!borrowBooks.isEmpty()) {
			System.out.println(
					"\n======================================================= List of Pending Return =======================================================");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-30s %-20s %-35s %-35s %-35s %-35s %-25s %-5s%n", "| Borrow Transaction ID",
					"| Student ID", "| Name", "| Book Title", "| Borrow Date", "| Due Date", "| Requested At", "| ");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------------");

			for (BorrowBook borrow : borrowBooks) {
				Student student = studentDAO.getStudentById(borrow.getStudentId());
				Book book = bookDAO.getBookById(borrow.getBookId());
				System.out.printf("%-30s %-20s %-35s %-35s %-35s %-35s %-25s %-5s%n", "| " + borrow.getBorrowId(),
						"| " + student.getStudentId(), "| " + student.getFirstName() + " " + student.getLastName(),
						"| " + book.getTitle(), "| " + borrow.getBorrowDate(), "| " + borrow.getDueDate(),
						"| " + borrow.getRequestedAt(), "| ");
			}
			return true;
		}

		return false;
	}

	public void displayIncomingDueReturn() {
		List<BorrowBook> borrowBooks = borrowBookDAO.getIncomingDueDateReturn();
		if (borrowBooks.isEmpty()) {
			System.out.println("	No incoming due date return.");
			return;
		}

		System.out.println(
				"\n======================================================= List of incoming due date return =======================================================");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-30s %-20s %-35s %-35s %-35s %-35s %-25s %-5s%n", "| Borrow Transaction ID", "| Student ID",
				"| Name", "| Book Title", "| Borrow Date", "| Due Date", "| Requested At", "| ");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------");

		for (BorrowBook borrow : borrowBooks) {
			Student student = studentDAO.getStudentById(borrow.getStudentId());
			Book book = bookDAO.getBookById(borrow.getBookId());
			System.out.printf("%-30s %-20s %-35s %-35s %-35s %-35s %-25s %-5s%n", "| " + borrow.getBorrowId(),
					"| " + student.getStudentId(), "| " + student.getFirstName() + " " + student.getLastName(),
					"| " + book.getTitle(), "| " + borrow.getBorrowDate(), "| " + borrow.getDueDate(),
					"| " + borrow.getRequestedAt(), "| ");
		}
	}
	

	public void displayOverdueReturn() {
		List<BorrowBook> borrowBooks = borrowBookDAO.getOverdueReturn();
		if (borrowBooks.isEmpty()) {
			System.out.println("	No over due date return.");
			return;
		}

		System.out.println(
				"\n======================================================= List of over due date return =======================================================");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-30s %-15s %-30s %-35s %-30s %-30s %-30s %-30s %-5s%n", "| Borrow Transaction ID", "| Student ID",
				"| Name", "| Book Title", "| Borrow Date", "| Due Date", "| Estimated Overdue Fine", "| Requested At", "| ");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------");

		for (BorrowBook borrow : borrowBooks) {
			long daysOverdue =ChronoUnit.DAYS.between(borrow.getDueDate(),LocalDateTime.now());
			double estimatedFine =  daysOverdue * 10;
			Student student = studentDAO.getStudentById(borrow.getStudentId());
			Book book = bookDAO.getBookById(borrow.getBookId());
			System.out.printf("%-30s %-15s %-30s %-35s %-30s %-30s %-30s %-30s %-5s%n", 
					"| " + borrow.getBorrowId(),
					"| " + student.getStudentId(),
					"| " + student.getFirstName() + " " + student.getLastName(),
					"| " + book.getTitle(),
					"| " + borrow.getBorrowDate(),
					"| " + borrow.getDueDate(),
					"| (" + daysOverdue + "day(s) x 10) = "+ estimatedFine,
					"| " + borrow.getRequestedAt(),
					"| ");
		}
	}

	public boolean getBorrowerReqDetails(int borrowBookId) {
		BorrowBook borrowBook = borrowBookDAO.getPendingRequestById(borrowBookId);
		if (borrowBook != null) {
			Student student = studentDAO.getStudentById(borrowBook.getStudentId());
			Book book = bookDAO.getBookById(borrowBook.getBookId());
			System.out.println("\n	Borrower Details");
			System.out.println("	Student ID: " + borrowBook.getStudentId());
			System.out.println("	Name: " + student.getFirstName() + " " + student.getLastName());
			System.out.println("	Request Book Title: " + book.getTitle());
			System.out.println("	Requested Date: " + borrowBook.getRequestedAt());
			return true;
		}
		return false;
	}

	public boolean getBorrowerReturnDetails(int borrowBookId) {
		BorrowBook borrowBook = borrowBookDAO.getPendingReturnById(borrowBookId);
		if (borrowBook != null) {
			Student student = studentDAO.getStudentById(borrowBook.getStudentId());
			Book book = bookDAO.getBookById(borrowBook.getBookId());
			System.out.println("\n	Borrower Details");
			System.out.println("	Student ID: " + borrowBook.getStudentId());
			System.out.println("	Name: " + student.getFirstName() + " " + student.getLastName());
			System.out.println("	Request Book Title: " + book.getTitle());
			System.out.println("	Borrowed Date: " + borrowBook.getBorrowDate());
			System.out.println("	Due Date: " + borrowBook.getDueDate());
			System.out.println("	Requested Date: " + borrowBook.getRequestedAt());
			return true;
		}
		return false;
	}

	public void acceptBorrowRequest(int borrowBookId) {
		boolean isAccepted = borrowBookDAO.acceptBorrowRequestById(borrowBookId);
		if (isAccepted) {
			System.out.println("	Borrow request accepted.");
		}
	}

	public void rejectBorrowRequest(int borrowBookdId, String remarks) {
		boolean isRejected = borrowBookDAO.rejectBorrowRequestById(borrowBookdId, remarks);
		if (isRejected) {
			System.out.println("	Borrow request rejected.");
		}
	}
	
	public void acceptBookReturn(int borrowId, double amount, String remarks) {
		BorrowBook borrow = borrowBookDAO.getPendingReturnById(borrowId);
		
		if(borrow != null) {
			long daysOverdue =ChronoUnit.DAYS.between(borrow.getDueDate(),LocalDateTime.now());
			daysOverdue = Math.max(0, daysOverdue);
			double estimatedFine =  (daysOverdue * 10) + amount;
			borrow.setFineAmount(estimatedFine);
			borrow.setRemarks(remarks);
			boolean success = borrowBookDAO.acceptBookReturn(borrow);
			if(success) {
				System.out.println("Book returned successfully.");
			} else {
				System.out.println("Failed to process return book.");
			}
		}
	}
	

}
