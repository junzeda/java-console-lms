package com.joysis.lms.service;

import com.joysis.lms.model.Author;

public class AuthorService {

	public boolean validateAddAuthor(String firstName, String lastName, String birthDate, String nationality) {
		
		if(firstName.isEmpty() || lastName.isEmpty() || birthDate.isEmpty() || nationality.isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	public Author checkEmptyField(Author author, String firstName, String lastName, String birthDate, String nationality) {
		if(!firstName.isEmpty()) {
			author.setFirstName(firstName);
		}
		
		if(!lastName.isEmpty()) {
			author.setLastName(lastName);
		}
		
		if(!birthDate.isEmpty()) {
			author.setBirthDate(birthDate);
		}
		
		if(!nationality.isEmpty()) {
			author.setNationality(nationality);
		}
		
		return author;
	}
}
