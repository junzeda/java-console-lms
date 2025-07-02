package com.joysis.lms.dao;

import java.util.List;

import com.joysis.lms.model.Book;

public interface BookDAO {
	
	public List<Book> getAllBooks();
	public boolean insertBook(Book book);
	public boolean updateBook(Book book);
	public Book getBookById(int bookId);
	public boolean archiveBookById(int bookId);
	public boolean restoreBooksById(int bookId);
	public boolean restoreAllBooks();
	public int getTotalBooks();
	public List<Book> searchBooks(String search);
	
}
