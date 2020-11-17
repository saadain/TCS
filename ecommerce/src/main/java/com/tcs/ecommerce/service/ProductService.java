package com.tcs.ecommerce.service;

import java.util.Optional;

import com.tcs.ecommerce.model.Product;

public interface ProductService {
	
	public String createProduct(Product product);
	public Optional<Product> getProductById(int id);

}
