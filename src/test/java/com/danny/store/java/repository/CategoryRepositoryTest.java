package com.danny.store.java.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.danny.store.java.entities.Category;
import com.danny.store.java.repositories.CategoryRepository;

@DataJpaTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@BeforeEach
	void setUp() {
		Category category = Category
				.builder()
				.title("test category")
				.build();
		
		category = entityManager.persist(category);
		
		System.out.println(category);
	}
	
	@AfterEach
	void clearUp() {
		entityManager.clear();
	}
	
	
	@Test
	void validIfListOfProductNotEmpty() {
		List<Category> category = categoryRepository
				.findAll();
		assertEquals(false, category.isEmpty());
	}
}
