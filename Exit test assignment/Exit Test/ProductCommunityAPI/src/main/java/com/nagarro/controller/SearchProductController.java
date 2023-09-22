package com.nagarro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.entity.Product;
import com.nagarro.service.SearchProductServiceImpl;

@RestController
@CrossOrigin
public class SearchProductController {
	
	@Autowired
	private SearchProductServiceImpl service;
	
	@GetMapping("/getSearchedProduct/{productSearch}")
	public List<Product> getProductBySearch(@PathVariable("productSearch") String searchProduct) throws Exception{
		searchProduct = searchProduct.replaceAll(" ", "");
		List<Product> productList;
		try {
			productList=service.getProductBySearch(searchProduct);
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}
		return productList;
	}

}
