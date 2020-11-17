package com.tcs.ecommerce;

import java.util.Optional;

import com.tcs.ecommerce.dao.ProductDAO;
import com.tcs.ecommerce.dao.ProductDAOImpl;
import com.tcs.ecommerce.model.Product;
import com.tcs.ecommerce.service.ProductService;
import com.tcs.ecommerce.service.ProductServiceImpl;

public class Main {

	public static void main(String[] args) {
		
		Product product = new Product(2, "laptop", "i5 1oth gen", 123.0f, "laptop");
		
		ProductService productService =  ProductServiceImpl.getInstance();
		
		String result = productService.createProduct(product);
		
		
		if("success".equals(result)) {
			System.out.println("recored added successfully");
		}
		else {
			System.out.println("problem");
		}
		
		Optional<Product> optional= productService.getProductById(1);
		
		if(optional.isPresent()) {
			Product product2 = optional.get();
			System.out.println(product2);
		}
		else {
			System.out.println("product is not available");
		}
		
	}
}
