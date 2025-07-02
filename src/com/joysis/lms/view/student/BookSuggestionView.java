package com.joysis.lms.view.student;

import java.util.Scanner;

import com.joysis.lms.controller.student.BookSuggestionController;


public class BookSuggestionView {
	private final Scanner scanner;
	private final BookSuggestionController suggestionController;
	
	public BookSuggestionView(Scanner scanner, BookSuggestionController suggestionController) {
		this.scanner = scanner;
		this.suggestionController = suggestionController;
	}
	
	public void showBookSuggestionMenu() {
		
	}

}
