package com.joysis.lms.dao;

import java.util.List;

import com.joysis.lms.model.Publisher;

public interface PublisherDAO {
	
	public List<Publisher> getAllPublishers();
	public boolean insertPublisher(Publisher publisher);
	public boolean updatePublisher(Publisher publisher);
	public Publisher getPublisherById(int publisherId);
	public boolean archivePublisherById(int publisherId);
	public boolean restoreAllPublishers();
	public boolean restorePublisherById(int publisherId);
	public int getTotalPublishers();

}
