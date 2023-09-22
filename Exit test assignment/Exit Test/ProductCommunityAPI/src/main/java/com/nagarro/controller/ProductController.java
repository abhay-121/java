package com.nagarro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.entity.Product;
import com.nagarro.entity.User;
import com.nagarro.service.ProductServiceImpl;

@RestController
@CrossOrigin
public class ProductController {

	private ProductServiceImpl productService;
	
	public ProductController(ProductServiceImpl productService) {
		this.productService = productService;
	}
	
	@PostMapping("/product")
	public ResponseEntity<Product> addNewProduct(@RequestBody Product product){
		Product createdProduct = null;
		try {
			createdProduct = productService.addProduct(product);
			return ResponseEntity.of(Optional.of(createdProduct));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/product")
	public List<Product> getProduct(){
		return this.productService.getAll();
	}
	
	@GetMapping("/getProductById/{id}")
	public Product getProductById(@PathVariable("id") String productCode) throws Exception{
		try {
			return this.productService.getProduct(productCode);
		}
		catch(Exception e) {
			throw new Exception("Some error occur");
		}
		
	}
	
	@PostMapping("/askforReview")
	public boolean askForReview(@RequestBody Product product) throws Exception {
		String productCode = product.getProductCode();
		Product productObj = this.productService.getProduct(productCode);
		
		if(productObj != null) {
			throw new Exception("Product with this " + productCode +" is alrady Exist");
		}
	return false;
		
	}
}
