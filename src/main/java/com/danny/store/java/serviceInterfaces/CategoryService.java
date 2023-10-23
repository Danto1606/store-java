package com.danny.store.java.serviceInterfaces;

import java.util.List;

import com.danny.store.java.entities.Category;
import com.danny.store.java.entities.Product;
import com.danny.store.java.exceptions.PathNotFoundException;
import com.danny.store.java.exceptions.UserBadRequestException;

public interface CategoryService {
	Category addCategory(Category category) 
			throws UserBadRequestException;
	
	Category updateCategory(Long id, Category category)
			throws PathNotFoundException,
			UserBadRequestException;
	
	String deleteCategoryById(Long id)
			throws PathNotFoundException;
	
	Category getCategoryById(Long id) 
			throws PathNotFoundException;
	
	List<Category> getAllCategories();
	

	public List<Product> getAllProductsByCategory(
			Long categoryId, 
			int pageNumber
			)throws PathNotFoundException;
}
