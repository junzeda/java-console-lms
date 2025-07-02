package com.joysis.lms.service;

import com.joysis.lms.model.Publisher;

public class PublisherService {
	public boolean validateAddPublisher(String name, String email, String contactNumber, String address) {

		if (name.isEmpty() || email.isEmpty() || contactNumber.isEmpty() || address.isEmpty()) {
			return false;
		}

		return true;
	}

	public Publisher checkEmptyField(Publisher publisher, String name, String email, String contactNumber, String address) {
		if (!name.isEmpty()) {
			publisher.setName(name);
		}

		if (!email.isEmpty()) {
			publisher.setEmail(email);
		}

		if (!contactNumber.isEmpty()) {
			publisher.setContactNumber(contactNumber);
		}

		if (!address.isEmpty()) {
			publisher.setAddress(address);
		}

		return publisher;
	}
}
