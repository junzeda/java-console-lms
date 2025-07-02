package com.joysis.lms.controller.admin;

import com.joysis.lms.dao.AuthorDAO;
import com.joysis.lms.model.Author;
import com.joysis.lms.service.AuthorService;
import com.joysis.lms.util.ConsoleStyles;
import com.joysis.lms.view.admin.AdminHomepageView;
import com.joysis.lms.view.admin.ManageAuthorsView;

import java.util.List;

public class ManageAuthorsController {

    private final AuthorDAO authorDAO;
    private final AuthorService authorService;
    private ManageAuthorsView authorsView;
    private AdminHomepageView adminHomepageView;

    public ManageAuthorsController(AuthorDAO authorDAO, AuthorService authorService) {
        this.authorDAO = authorDAO;
        this.authorService = authorService;
    }
    
    public void setAuthorsView(ManageAuthorsView authorsView) {
    	this.authorsView = authorsView;
    }
    
    public void setAdminHomepageView(AdminHomepageView adminHomepageView) {
    	this.adminHomepageView = adminHomepageView;
    }
    

    public boolean handleAuthorSelection(int choice) {
        switch (choice) {
            case 1:
                displayAllAuthors();
                return true;
            case 2:
            	authorsView.promptAddAuthor();
                return true;
            case 3:
            	authorsView.promptUpdateAuthor();
                return true;
            case 4:
                authorsView.promptArchiveAuthorById();
                return true;
            case 0:
            	adminHomepageView.showAdminHomepage();
            	return true;
            default:
                return false;
        }
    }

    public void displayAllAuthors() {
        List<Author> authors = authorDAO.getAllAuthors();
        if (authors.isEmpty()) {
            System.out.println("	No authors found.");
            return;
        }

        System.out.println("\n"+ConsoleStyles.colorize("Admin Homepage / Manage Authors / All Authors", ConsoleStyles.YELLOW, ConsoleStyles.BOLD)
        +" =======================================================================================");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-20s %-15s %-15s %-25s %-25s %-5s%n",
                "| ID", "| First Name", "| Last Name", "| Birth Date", "| Nationality", "| Created At", "| Updated At","|");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        
        for (Author author : authors) {
            System.out.printf("%-10s %-15s %-20s %-15s %-15s %-25s %-25s %-5s%n",
                    "| "+author.getAuthorId(),
                    "| "+author.getFirstName(),
                    "| "+author.getLastName(),
                    "| "+author.getBirthDate(),
                    "| "+author.getNationality(),
                    "| "+author.getCreatedAt(),
                    "| "+author.getUpdatedAt(),
                    "| ");
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("=====================================================================================================================================");
    }
    
    public Author getAuthorById(int authorId) {
    	return authorDAO.getAuthorById(authorId);
    	
    }
    
    public void addAuthor(String firstName, String lastName, String birthDate, String nationality) {
    	boolean isValid = authorService.validateAddAuthor(firstName, lastName, birthDate, nationality);
    	if(isValid) {
    		Author author = new Author(firstName, lastName, birthDate, nationality);
    		boolean addAuthorSuccess = authorDAO.insertAuthor(author);
    		if(addAuthorSuccess) {
    			System.out.println("	New author added successfully!");
    		}else {
    			System.out.println("	Failed to add new author!");
    		}
    	}else {
    		System.out.println("	Please fill out all the required fields.");
    	}
    	
    }
    
    public void updateAuthor(int authorId, String firstName, String lastName, String birthDate, String nationality) {
    	Author oldAuthorVal = authorDAO.getAuthorById(authorId);
    	Author newAuthorVal = authorService.checkEmptyField(oldAuthorVal, firstName, lastName, birthDate, nationality);
    	boolean updateSuccess = authorDAO.updateAuthor(newAuthorVal);
    	if(updateSuccess) {
    		System.out.println("	Author updated successfully.");
    	}else {
    		System.out.println("	Failed to update data with Author ID: " + authorId);
    	}
 
    }
    
    public void findAuthorById(int authorId){
        Author author = authorDAO.getAuthorById(authorId);
        if(author != null){
        	System.out.println("\n	Author Details");
            System.out.println("	Author ID: " + author.getAuthorId());
            System.out.println("	First Name: " + author.getFirstName());
            System.out.println("	Last Name: " + author.getLastName());
            System.out.println("	Birth Date: " + author.getBirthDate());
            System.out.println("	Nationality: " + author.getNationality());
            System.out.println("	Created At: " + author.getCreatedAt());
            System.out.println("	Updated At: " + author.getUpdatedAt());
        }else{
            System.out.println("	Author not found.");
        }
    }
    
    public void archiveAuthorById(int authorId) {
    	boolean archiveSuccess = authorDAO.archiveAuthorById(authorId);
    	if(archiveSuccess) {
    		System.out.println("	Author deleted successfully!");
    	}else {
    		System.out.println("	Failed to delete data with Author ID: " + authorId);
    	}
    }
    
}
