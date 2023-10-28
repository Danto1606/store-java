package com.danny.store.java.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.danny.store.java.entities.Product;
import com.danny.store.java.exceptions.PathNotFoundException;
import com.danny.store.java.exceptions.UserBadRequestException;
import com.danny.store.java.models.ProductModel;
import com.danny.store.java.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping
	Product addProduct(
			@RequestPart("details") ProductModel product,
			@RequestPart("images") List<MultipartFile> images
			)throws UserBadRequestException, IOException {

		return productService.addProduct(product, images);
	}

	@GetMapping("{id}")
	Product getProductById(@PathVariable(name = "id", required =  false) Long id)
			throws PathNotFoundException {

		return productService.getProductById(id);
	}

	@PutMapping("{id}")
	Product updateProduct(
			@PathVariable("id") Long id, 
			@RequestPart("details") ProductModel product,
			@RequestParam("images") List<MultipartFile> images
			)throws UserBadRequestException, PathNotFoundException {

		return productService.updateProduct(id, product);
	}

	@DeleteMapping("{id}")
	String deleteProductById(@PathVariable("id") Long id)
			throws PathNotFoundException {

		return productService.deleteProductById(id);
	}

	@GetMapping
	List<Product> getAllProducts(
			@RequestParam(name = "page", required = false) Integer pageNumber
			) {
		
		return productService.getAllProducts(pageNumber);
	}
	
	
}
