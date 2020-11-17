package com.tcs.ecommerce;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tcs.ecommerce.config.DBConfig;
import com.tcs.ecommerce.model.Product;
import com.tcs.ecommerce.service.ProductService;

public class MainSpring {

	public static void main(String[] args) {
		
		System.out.println("Before object creation");

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class);
		System.out.println("After context source");
		ProductService productService = context.getBean(ProductService.class);
		ProductService productService2 = context.getBean(ProductService.class);
		DataSource dataSource = context.getBean("getMySQLDataSource", DataSource.class);
		DataSource dataSource2 = (DataSource) context.getBean("getMySQLDataSource");
//		DataSource dataSource = context.getBean("mysqlDataSource", DataSource.class);
//		DataSource dataSource2 = (DataSource) context.getBean("mysqlDataSource");
		System.out.println("After data source");
		Product product = new Product(5, "laptop", "i5 1oth gen", 123.0f, "laptop");
		String result = productService.createProduct(product);
		System.out.println(result);
		System.out.println(dataSource.equals(dataSource2));
		System.out.println(productService.equals(productService2));
		context.close();
	}

}
