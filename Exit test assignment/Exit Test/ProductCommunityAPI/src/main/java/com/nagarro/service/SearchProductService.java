package com.nagarro.service;

import java.util.List;

import com.nagarro.entity.Product;

public interface SearchProductService {
	public List<Product> getProductBySearch(String search);

}
