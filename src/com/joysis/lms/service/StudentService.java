package com.joysis.lms.service;

import com.joysis.lms.model.Student;

public class StudentService {

	public boolean validateAddStudent(String studentId, String firstName, String lastName, String contactNumber) {

		if (studentId.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || contactNumber.isEmpty()) {
			return false;
		}

		return true;
	}

	public Student checkEmptyField(Student student, String firstName, String lastName, String contactnumber) {
		if (!firstName.isEmpty()) {
			student.setFirstName(firstName);
		}

		if (!lastName.isEmpty()) {
			student.setLastName(lastName);
		}

		if (!contactnumber.isEmpty()) {
			student.setContactNumber(contactnumber);
		}

		return student;
	}

}
