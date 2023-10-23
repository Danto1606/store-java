package com.danny.store.java.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danny.store.java.entities.Category;
import com.danny.store.java.entities.Product;
import com.danny.store.java.exceptions.PathNotFoundException;
import com.danny.store.java.exceptions.UserBadRequestException;
import com.danny.store.java.repositories.CategoryRepository;
import com.danny.store.java.serviceInterfaces.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService{
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) 
			throws UserBadRequestException {
		
		return categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Long id, Category category) 
			throws PathNotFoundException, UserBadRequestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteCategoryById(Long id) 
			throws PathNotFoundException {
		boolean exists = categoryRepository.existsById(id);
		if(!exists) throw new PathNotFoundException();
		categoryRepository.deleteById(id);		
		return "deleted sucessfully";
	}

	@Override
	public Category getCategoryById(Long id) 
			throws PathNotFoundException {
		Optional<Category> result = categoryRepository.findById(id);
		
		if(result.isEmpty()) throw new PathNotFoundException("category not found");
		
		return result.get();
	
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public List<Product> getAllProductsByCategory(Long categoryId, int pageNumber) 
			throws PathNotFoundException {

		pageNumber = Objects.isNull(pageNumber) ? 0: pageNumber;
		
		Optional<Category> result = categoryRepository.findById(categoryId);
		if(result.isEmpty()) throw new PathNotFoundException("category not found");
		
		List<Product> products = result.get().getProducts();	
		
		int start = 0 + (20 * pageNumber);
		int end = 19 + (20 * pageNumber);
			
		return products.subList(start, end);
		
	}

}
