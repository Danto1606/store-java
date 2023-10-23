package com.danny.store.java.services;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.danny.store.java.entities.Category;
import com.danny.store.java.entities.Product;
import com.danny.store.java.exceptions.PathNotFoundException;
import com.danny.store.java.exceptions.UserBadRequestException;
import com.danny.store.java.models.ProductModel;
import com.danny.store.java.repositories.CategoryRepository;
import com.danny.store.java.repositories.ProductRepository;
import com.danny.store.java.serviceInterfaces.ProductService;
import com.danny.store.java.util.ImageUtil;

@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	ImageUtil imageUtil;

	@Override
	public Product addProduct(
			ProductModel product,
			List<MultipartFile> images
			) throws UserBadRequestException, IOException {
		Optional<Category> category = categoryRepository.findById(product.getCategoryId());
		
		product.setImages(
				imageUtil.saveAll(images, "products")
				);
		
		if(category.isEmpty()) throw new UserBadRequestException("invalid category");
		
		Product result = productRepository.save(
				product.toProduct(category.get()));
		
		return result;
	}

	@Override
	public Product getProductById(Long id) throws PathNotFoundException {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent())
			return product.get();
		throw new PathNotFoundException("product not found");
	}

	@Override
	public Product updateProduct(Long id, ProductModel product)
			throws UserBadRequestException, PathNotFoundException {
		
		Optional<Product> productDb = productRepository.findById(id);
		if (productDb.isEmpty())
			throw new PathNotFoundException("product not found");
		return productDb.get();
	}

	@Override
	public String deleteProductById(Long id) throws PathNotFoundException {
		if (!productRepository.existsById(id)) {
			throw new PathNotFoundException("product not found");
		}
		productRepository.deleteById(id);
		return "product deleted sucessfully";
	}


	@Override
	public List<Product> getAllProducts(int pageNumber) {
		
		pageNumber = Objects.isNull(pageNumber) ? 0: pageNumber;
		
		Pageable page = PageRequest.of(
				pageNumber,
				20,
				Sort.by("name"));
		
		List<Product> result = productRepository.findAll(page)
				.getContent();
		
	
		
		return result;
	}

	

}
