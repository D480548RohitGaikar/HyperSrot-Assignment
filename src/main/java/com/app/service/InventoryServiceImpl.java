package com.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.ProductDao;
import com.app.entities.Product;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService{
	
	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> getProducts() {
		return productDao.findAll();
	}

	@Override
	public Product addNewProduct(Product product) {	
		return productDao.save(product);
	}
	
	@Override
    public Optional<Product> getProductById(Long productId) {
        return Optional.ofNullable(productDao.findById(productId).orElse(null));
    }

}
