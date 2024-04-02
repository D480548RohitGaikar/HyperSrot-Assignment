package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entities.Product;

public interface InventoryService {
	
	Product addNewProduct(Product product);

	List<Product> getProducts();

	Optional<Product> getProductById(Long productId);

}
