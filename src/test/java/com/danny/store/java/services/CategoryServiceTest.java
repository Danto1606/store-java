package com.danny.store.java.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.danny.store.java.entities.Category;
import com.danny.store.java.exceptions.PathNotFoundException;
import com.danny.store.java.repositories.CategoryRepository;
import com.danny.store.java.services.CategoryService;

@SpringBootTest
public class CategoryServiceTest {
	
	@Autowired
	private CategoryService categoryService;
	
	@MockBean
	private CategoryRepository categoryRepository;
	
	@BeforeEach
	void setUP() {
		Category category = Category.builder()
				.id(1l)
				.title("test category")
				.build();
		
		Mockito.when(categoryRepository.findById(1l))
			.thenReturn(Optional.of(category));
	}
	
	@Test
	public void whenValidIdCategoryShouldBeFound() throws PathNotFoundException {
		Long categoryId= 1l;
		
		Category found = categoryService.getCategoryById(categoryId);
		
		assertEquals(categoryId, found.getId());
	}
}
