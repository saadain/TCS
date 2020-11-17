package com.tcs.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//It will inform jpa that this class is used for jpa repository.(ORM mapping purpose)
@Table(name = "product_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	private int productId;
	@Column(name="product_name")
	private String productName;
	private String description;
	private float price;
	private String category;
	//If we are not providing any annotation like @column then it will take
	//the field name as the column name and it will have the default size
	
}
