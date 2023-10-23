package com.danny.store.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danny.store.java.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
