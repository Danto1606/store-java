package com.danny.store.java.repository;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.danny.store.java.entities.Category;
import com.danny.store.java.entities.Product;
import com.danny.store.java.repositories.ProductRepository;

@DataJpaTest
public class ProductRepositoryTest {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@BeforeEach
	void setUp(){
		Category category = Category
				.builder()
				.title("test")
				.build();
		
		category = entityManager.persist(category);
		
		Product product = Product
				.builder()
				.name("test product")
				.category(category)
				.build();
		
		entityManager.persist(product);
	}
	
	
}
