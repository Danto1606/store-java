package com.danny.store.java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.danny.store.java.services.CategoryService;

@RestController
@RequestMapping(path = "api/v1/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping
	ResponseEntity<Category> addCategory(@RequestBody Category category)
			throws UserBadRequestException {

		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(categoryService.addCategory(category));
	}

	@PutMapping("{id}")
	ResponseEntity<Category> updateCategory(@PathVariable(name = "id") Long id, @RequestBody Category category)
			throws PathNotFoundException, UserBadRequestException {

		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(categoryService.updateCategory(id, category));
	}

	@DeleteMapping("{id}")
	String deleteCategoryById(@PathVariable(name = "id") Long id)
			throws PathNotFoundException {

		return categoryService.deleteCategoryById(id);
	}

	@GetMapping("{id}")
	ResponseEntity<Category> getCategoryById(@PathVariable(name = "id") Long id)
			throws PathNotFoundException {

		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(categoryService.getCategoryById(id));
	}

	@GetMapping
	ResponseEntity<List<Category>> getAllCategories() {
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(categoryService.getAllCategories());
	}
	
	
	@GetMapping("{categoryId}/products")
	ResponseEntity<List<Product>> getAllProducstByCategory(
			@PathVariable(name = "categoryId") Long categoryId,
			@RequestParam(name = "page", required = false) int pageNumber
			) throws PathNotFoundException{
		
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(categoryService.getAllProductsByCategory(categoryId, pageNumber));
	}
	
}
