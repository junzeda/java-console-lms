package com.joysis.lms.controller.admin;

import java.util.List;

import com.joysis.lms.dao.PublisherDAO;
import com.joysis.lms.model.Publisher;
import com.joysis.lms.service.PublisherService;
import com.joysis.lms.view.admin.AdminHomepageView;
import com.joysis.lms.view.admin.ManagePublishersView;

public class ManagePublishersController {
	private final PublisherDAO publisherDAO;
	private final PublisherService publisherService;
	private ManagePublishersView publishersView;
	private AdminHomepageView adminHomepageView;

	public ManagePublishersController(PublisherDAO publisherDAO, PublisherService publisherService) {
		this.publisherDAO = publisherDAO;
		this.publisherService = publisherService;
	}

	public void setPublishersView(ManagePublishersView publishersView) {
		this.publishersView = publishersView;
	}

	public void setAdminHomepageView(AdminHomepageView adminHomepageView) {
		this.adminHomepageView = adminHomepageView;
	}

	public boolean handlePublishersSelection(int choice) {
		switch (choice) {
		case 1:
			displayAllPublishers();
			return true;
		case 2:
			publishersView.promptAddPublisher();;
			return true;
		case 3:
			publishersView.promptUpdatePublisher();
			return true;
		case 4:
			publishersView.promptArchivePublisherById();
			return true;
		case 0:
			adminHomepageView.showAdminHomepage();
			return true;
		default:
			return false;
		}
	}
	
	public void displayAllPublishers() {
		List<Publisher> publishers = publisherDAO.getAllPublishers();
        if (publishers.isEmpty()) {
            System.out.println("	No Publisher found.");
            return;
        }

        System.out.println("\n======================================================= List of Publisher =======================================================");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-30s %-20s %-30s %-30s %-30s%n",
                "| Publisher ID", "| Name", "| Email", "| Contact Number", "| Address", "| Created At", "| Updated At","|");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        for (Publisher publisher : publishers) {
            System.out.printf("%-15s %-15s %-30s %-20s %-30s %-30s %-30s%n",
                    "| "+publisher.getPublisherId(),
                    "| "+publisher.getName(),
                    "| "+publisher.getEmail(),
                    "| "+publisher.getContactNumber(),
                    "| "+publisher.getAddress(),
                    "| "+publisher.getCreatedAt(),
                    "| "+publisher.getUpdatedAt(),
                    "| ");
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("================================================================================================================================");
	}
	
	public void addPublisher(String name, String email, String contactNumber, String address) {
		boolean isValid = publisherService.validateAddPublisher(name, email, contactNumber, address);
    	if(isValid) {
    		Publisher publisher = new Publisher(name, email, contactNumber, address);
    		boolean addSuccess = publisherDAO.insertPublisher(publisher);
    		if(addSuccess) {
    			displayAllPublishers();
    			System.out.println("	New Publisher added successfully!");
    		}else {
    			System.out.println("	Failed to add new Publisher!");
    		}
    	}else {
    		System.out.println("	Please fill out all the required fields.");
    	}
	}
	
	public Publisher getPublisherById(int publisherId) {
		return publisherDAO.getPublisherById(publisherId);
	}
	
	public void updatePublisher(int publisherId, String name, String email, String contactNumber, String address) {
		Publisher oldPublisherVal = publisherDAO.getPublisherById(publisherId);
    	Publisher newPublisherVal = publisherService.checkEmptyField(oldPublisherVal, name, email, contactNumber, address);
    	boolean updateSuccess = publisherDAO.updatePublisher(newPublisherVal);
    	if(updateSuccess) {
    		System.out.println("	Publisher updated successfully.");
    	}else {
    		System.out.println("	Failed to update data with Publisher ID: " + publisherId);
    	}
	}
	
	public void findPublisherById(int publisherId) {
		Publisher publisher = publisherDAO.getPublisherById(publisherId);
        if(publisher != null){
        	System.out.println("\n	Publisher Details");
            System.out.println("	Publisher ID: " + publisher.getPublisherId());
            System.out.println("	Name: " + publisher.getName());
            System.out.println("	Email: " + publisher.getEmail());
            System.out.println("	Contact Number: " + publisher.getContactNumber());
            System.out.println("	Address: " + publisher.getAddress());
            System.out.println("	Created At: " + publisher.getCreatedAt());
            System.out.println("	Updated At: " + publisher.getUpdatedAt());
        }else{
            System.out.println("	Publisher not found.");
        }
	}
	
	public void archivePublisherById(int publisherId) {
		boolean archiveSuccess = publisherDAO.archivePublisherById(publisherId);
    	if(archiveSuccess) {
    		System.out.println("	Publisher deleted successfully!");
    	}else {
    		System.out.println("	Failed to delete data with Publisher ID: " + publisherId);
    	}
	}

}
