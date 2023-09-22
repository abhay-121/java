package com.nagarro.service;

import java.util.List;

import com.nagarro.entity.Product;

public interface ProductService {
	List<Product> getAll();
	Product addProduct(Product product);
	Product getProduct(String productCode);
	void putProductReview(Product product);
	Long countAllProduct();
	
}
