package com.joysis.lms.dao;

import java.util.List;

import com.joysis.lms.model.Author;

public interface AuthorDAO {

	public List<Author> getAllAuthors();
	public boolean insertAuthor(Author author);
	public boolean updateAuthor(Author author);
	public boolean archiveAuthorById(int authorId); // archive/delete author
	public Author getAuthorById(int authorId);
	public boolean restoreAllAuthors();
	public boolean restoreAuthorById(int authorId);
	public int getTotalAuthors();
}
