package com.danny.store.java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danny.store.java.entities.Category;
import com.danny.store.java.entities.Product;
import com.danny.store.java.exceptions.PathNotFoundException;
import com.danny.store.java.exceptions.UserBadRequestException;
import com.danny.store.java.serviceInterfaces.CategoryService;

@RestController
@RequestMapping(path = "api/v1/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping
	Category addCategory(@RequestBody Category category)
			throws UserBadRequestException {

		return categoryService.addCategory(category);
	}

	@PutMapping("{id}")
	Category updateCategory(@PathVariable(name = "id") Long id, @RequestBody Category category)
			throws PathNotFoundException, UserBadRequestException {

		return categoryService.updateCategory(id, category);
	}

	@DeleteMapping("{id}")
	String deleteCategoryById(@PathVariable(name = "id") Long id)
			throws PathNotFoundException {

		return categoryService.deleteCategoryById(id);
	}

	@GetMapping("{id}")
	Category getCategoryById(@PathVariable(name = "id") Long id)
			throws PathNotFoundException {

		return categoryService.getCategoryById(id);
	}

	@GetMapping
	List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}
	
	
	@GetMapping("{categoryId}/products")
	List<Product> getAllProducstByCategory(
			@PathVariable(name = "categoryId") Long categoryId,
			@RequestParam(name = "page", required = false) int pageNumber
			) throws PathNotFoundException{
		
		return categoryService.getAllProductsByCategory(categoryId, pageNumber);
	}
	
}
