package com.joysis.lms.service;

import com.joysis.lms.model.Book;

public class BookService {

	public boolean validateBook(String title, String isbn, int publicationYear, int pageCount, String language,
			int availableCopies) {
		return !title.isEmpty() || !isbn.isEmpty() || publicationYear != 0 || pageCount != 0 || !language.isEmpty() || availableCopies != 0;
	}
	
	public Book checkEmptyField(Book book, String title, int authorId, int publisherId, String isbn, int categoryId, int publicationYear, int pageCount, String language,
			int availableCopies) {
		if(!title.isEmpty()) {
			book.setTitle(title);
		}
		
		if(authorId != 0) {
			book.setAuthorId(authorId);
		}
		
		if(publisherId != 0) {
			book.setPublisherId(publisherId);
		}
		
		if(!isbn.isEmpty()) {
			book.setIsbn(isbn);
		}
		
		if(categoryId != 0) {
			book.setCategoryId(categoryId);
		}
		
		if(publicationYear != 0) {
			book.setPublicationYear(publicationYear);
		}
		
		if(pageCount != 0) {
			book.setPageCount(pageCount);
		}
		
		if(!language.isEmpty()) {
			book.setLanguage(language);
		}
		
		if(availableCopies != 0) {
			book.setAvailableCopies(availableCopies);
		}
		return book;
	}
}
