package com.danny.store.java.models;

import java.util.List;

import com.danny.store.java.entities.Category;
import com.danny.store.java.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductModel {
	
	private Long id;

	private String name;
	private String price;
	private String description;
	private int amountInStock;
	private Long categoryId;
	private List<String> images;
	
	public Product toProduct(Category category) {
		return Product.builder()
			.id(id)
			.name(name)
			.price(price)
			.description(description)
			.amountInStock(amountInStock)
			.category(category)
			.images(images)
			.build();
	}
	
	public  ProductModel(Product product) {
				this.id =product.getId();
				this.name = product.getName();
				this.price = product.getPrice();
				this.description = product.getDescription();
				this.amountInStock = product.getAmountInStock();
				this.categoryId = product.getCategory().getId();		
	}
	
}
