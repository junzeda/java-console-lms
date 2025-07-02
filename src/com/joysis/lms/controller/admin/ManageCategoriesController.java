package com.joysis.lms.controller.admin;

import java.util.List;

import com.joysis.lms.dao.CategoryDAO;
import com.joysis.lms.model.Category;
import com.joysis.lms.view.admin.AdminHomepageView;
import com.joysis.lms.view.admin.ManageCategoriesView;

public class ManageCategoriesController {
	private final CategoryDAO categoryDAO;
	private ManageCategoriesView categoriesView;
	private AdminHomepageView adminHomepageView;

	public ManageCategoriesController(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
	
	public void setCategoriesView(ManageCategoriesView categoriesView) {
		this.categoriesView = categoriesView;
	}
	
	public void setAdminHomepageView(AdminHomepageView adminHomepageView) {
		this.adminHomepageView = adminHomepageView;
	}

	public boolean handleCategoriesSelection(int choice) {
		switch (choice) {
		case 1:
			displayAllCategories();
			return true;
		case 2:
			categoriesView.promptAddCategory();
			return true;
		case 3:
			categoriesView.promptUpdateCategory();
			return true;
		case 4:
			categoriesView.promptDeleteCategory();
			return true;
		case 0:
			adminHomepageView.showAdminHomepage();
			return true;
		default:
			return false;
		}
	}

	public void displayAllCategories() {
		List<Category> categories = categoryDAO.getAllCategories();
		if (categories.isEmpty()) {
			System.out.println("	No categories found.");
			return;
		}

		System.out.println("\n================================== List of Category ==================================");
		System.out.printf("%-5s %-15s %-25s %-25s%n", "ID", "Category", "Created At", "Updated At");
		System.out.println("--------------------------------------------------------------------------------------");

		for (Category category : categories) {
			System.out.printf("%-5s %-15s %-25s %-25s%n", category.getBookCategoryId(), category.getCategoryName(),
					category.getCreatedAt(), category.getUpdatedAt());
		}
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("\n======================================================================================");
	}

	public Category getCategoryById(int bookCategoryId) {
		return categoryDAO.getCategoryById(bookCategoryId);
	}
	
	public void findCategoryById(int bookCategoryId) {
		Category category = categoryDAO.getCategoryById(bookCategoryId);
		if(category != null) {
			System.out.println("\n	Category Details");
            System.out.println("	Book Category ID: " + category.getBookCategoryId());
            System.out.println("	Category Name: " + category.getCategoryName());
		}else {
			System.out.println("	Category not found.");
		}
	}

	public void addCategory(String categoryName) {
		if (!categoryName.isEmpty()) {
			Category category = new Category();
			category.setCategoryName(categoryName);
			boolean insertCategorySuccess = categoryDAO.insertCategory(category);
			if (insertCategorySuccess) {
				displayAllCategories();
				System.out.println("	New category added successfully!");
			} else {
				System.out.println("	Failed to add new category!");
			}
		} else {
			System.out.println("	Please complete the required field(s).");
		}
	}

	public void updateCategory(int bookCategoryId, String categoryName) {
		if (!categoryName.isEmpty()) {
			Category category = new Category();
			category.setBookCategoryId(bookCategoryId);
			category.setCategoryName(categoryName);
			boolean updateSuccess = categoryDAO.updateCategory(category);
			if (updateSuccess) {
				displayAllCategories();
				System.out.println("	Category ID: "+ bookCategoryId +" updated successfully!");
			} else {
				System.out.println("	Failed to update data with Category ID: " + bookCategoryId);
			}
		} else {
			System.out.println("	No data were updated.");
		}
	}
	
	public void deleteCategory(int bookCategoryId) {
		boolean deleteSuccess = categoryDAO.deleteCategory(bookCategoryId);
		if(deleteSuccess) {
			displayAllCategories();
			System.out.println("	Category ID: "+ bookCategoryId +" deleted successfully");
		}else {
			System.out.println("	Failed to delete data with ID: "+ bookCategoryId);
		}
	}

}
