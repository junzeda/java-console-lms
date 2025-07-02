package com.joysis.lms.controller.admin;

import java.util.List;

import com.joysis.lms.dao.StudentDAO;
import com.joysis.lms.model.Student;
import com.joysis.lms.service.StudentService;
import com.joysis.lms.view.admin.AdminHomepageView;
import com.joysis.lms.view.admin.ManageStudentsView;

public class ManageStudentsController {
	private final StudentDAO studentDAO;
	private final StudentService studentService;
	private ManageStudentsView studentsView;
	private AdminHomepageView adminHomepageView;

	public ManageStudentsController(StudentDAO studentDAO, StudentService studentService) {
		this.studentDAO = studentDAO;
		this.studentService = studentService;
	}

	public void setStudentsView(ManageStudentsView studentsView) {
		this.studentsView = studentsView;
	}
	
	public void setAdminHomepageView(AdminHomepageView adminHomepageView) {
		this.adminHomepageView = adminHomepageView;
	}

	public boolean handleStudentsSelection(int choice) {
		switch (choice) {
		case 1:
			displayAllStudents();
			return true;
		case 2:
			studentsView.promptAddStudent();
			return true;
		case 3:
			studentsView.promptUpdateStudent();
			return true;
		case 4:
			studentsView.promptArchiveStudentById();
			return true;
		case 0:
			adminHomepageView.showAdminHomepage();
			return true;
		default:
			return false;
		}
	}
	
	public void displayAllStudents() {
		List<Student> students = studentDAO.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("	No studentse found.");
            return;
        }

        
        System.out.println("\n=============================================== List of Students ================================================");
        System.out.printf("%-15s %-15s %-15s %-20s %-25s %-25s%n", 
                "Student ID", "First Name", "Last Name", "Contact Number", "Created At", "Updated At");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        
        for (Student student : students) {
            System.out.printf("%-15s %-15s %-15s %-20s %-25s %-25s%n",
            		student.getStudentId(),
            		student.getFirstName(),
            		student.getLastName(),
            		student.getContactNumber(),
            		student.getCreatedAt(),
            		student.getUpdatedAt());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("=================================================================================================================");
	}

	public void addStudent(String studentId, String firstName, String lastName, String contactNumber) {
		boolean isValid = studentService.validateAddStudent(studentId, firstName, lastName, contactNumber);
		if (isValid) {
			Student student = new Student(studentId, firstName, lastName, contactNumber);
			boolean addStudentSuccess = studentDAO.insertStudent(student);
			if (addStudentSuccess) {
				displayAllStudents();
				System.out.println("	New student added successfully!");
			} else {
				System.out.println("	Failed to add new student!");
			}
		} else {
			System.out.println("	Please fill out all the required fields.");
		}

	}

	public Student getStudentById(String studentId) {
		return studentDAO.getStudentById(studentId);
	}
	
	public void findStudentById(String studentId){
        Student student = studentDAO.getStudentById(studentId);
        if(student != null){
        	System.out.println("\n	Student Details");
            System.out.println("	Student ID: " + student.getStudentId());
            System.out.println("	First Name: " + student.getFirstName());
            System.out.println("	Last Name: " + student.getLastName());
            System.out.println("	Contact Number: " + student.getContactNumber());
            System.out.println("	Created At: " + student.getCreatedAt());
            System.out.println("	Updated At: " + student.getUpdatedAt());
        }else{
            System.out.println("	Student not found.");
        }
    }

	public void updateStudent(String studentId, String firstName, String lastName, String contactNumber) {
		Student oldStudentVal = studentDAO.getStudentById(studentId);
		Student newStudentVal = studentService.checkEmptyField(oldStudentVal, firstName, lastName, contactNumber);
		boolean updateSuccess = studentDAO.updateStudent(newStudentVal);
		if (updateSuccess) {
			System.out.println("	Student updated successfully.");
		} else {
			System.out.println("	Failed to update data with Student ID: " + studentId);
		}

	}
	
	public void archiveStudentById(String studentId) {
    	boolean archiveSuccess = studentDAO.archiveStudentById(studentId);
    	if(archiveSuccess) {
    		System.out.println("	Student deleted successfully!");
    	}else {
    		System.out.println("	Failed to delete data with Student ID: " + studentId);
    	}
    }
	
}
