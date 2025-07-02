package com.joysis.lms.dao;

import java.util.List;

import com.joysis.lms.model.Category;

public interface CategoryDAO {

	public List<Category> getAllCategories();
	public boolean insertCategory(Category category);
	public Category getCategoryById(int bookCategoryId);
	public boolean updateCategory(Category category);
	public boolean deleteCategory(int bookCategoryId);
	public int getTotalCategories();
}
