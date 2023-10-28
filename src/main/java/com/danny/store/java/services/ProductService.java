package com.danny.store.java.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.danny.store.java.entities.Product;
import com.danny.store.java.exceptions.PathNotFoundException;
import com.danny.store.java.exceptions.UserBadRequestException;
import com.danny.store.java.models.ProductModel;

public interface ProductService {
	Product addProduct(
			ProductModel product, 
			List<MultipartFile> images
			)throws UserBadRequestException, IOException;
	Product getProductById(Long id) 
			throws PathNotFoundException;
	Product updateProduct(Long id, ProductModel product) 
			throws UserBadRequestException, PathNotFoundException;
	String deleteProductById(Long id)
			throws PathNotFoundException;
	List<Product> getAllProducts(Integer pageNumber);
}
