package com.joysis.lms.controller.student;

import com.joysis.lms.view.LoginView;
import com.joysis.lms.view.student.AccountSettingsView;
import com.joysis.lms.view.student.BookSuggestionView;
import com.joysis.lms.view.student.BorrowedBooksView;
import com.joysis.lms.view.student.BrowseBooksView;
import com.joysis.lms.view.student.DueSoonView;
import com.joysis.lms.view.student.OverDueView;
import com.joysis.lms.view.student.PendingRequestView;
import com.joysis.lms.view.student.TransactionHistoryView;

public class StudentHomepageController {
	private final LoginView loginView;
	private final BrowseBooksView browseView;
	private final TransactionHistoryView transactionView;
	private final BookSuggestionView suggestionView;
	private final AccountSettingsView accountSettingsView;
	private final BorrowedBooksView borrowedView;
	private final PendingRequestView pendingReqView;
	private final DueSoonView dueSoonView;
	private final OverDueView overDueView;

	public StudentHomepageController(LoginView loginView, BrowseBooksView browseView,
			TransactionHistoryView transactionView, BookSuggestionView suggestionView,
			AccountSettingsView accountSettingsView, BorrowedBooksView borrowedView, 
			PendingRequestView pendingReqView, DueSoonView dueSoonView, OverDueView overDueView) {
		this.loginView = loginView;
		this.browseView = browseView;
		this.transactionView = transactionView;
		this.suggestionView = suggestionView;
		this.accountSettingsView = accountSettingsView;
		this.borrowedView = borrowedView;
		this.pendingReqView = pendingReqView;
		this.dueSoonView = dueSoonView;
		this.overDueView = overDueView;
	}

	public boolean handleHomeSelection(int choice) {
		switch (choice) {
		case 1:
			browseView.showBrowseBooksMenu();
			return true;
		case 2:
			borrowedView.showBorrowedBooks();
			return true;
		case 3:
			pendingReqView.showPendingBooks();
			return true;
		case 4:
			transactionView.showTransactionHistory();
			return true;
		case 5:
			dueSoonView.showIncomingDue();
			return true;
		case 6:
			overDueView.showOverDue();
			return true;
		case 7:
			System.out.println("System announcement");
			return true;
		case 8:
			accountSettingsView.showAccountSettingsMenu();
			return true;
		case 0:
			loginView.selectAccountType();
			return true;
		default:
			return false;
		}
	}
}
