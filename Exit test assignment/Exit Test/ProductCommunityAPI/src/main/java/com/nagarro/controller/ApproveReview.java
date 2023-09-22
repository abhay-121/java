package com.nagarro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.entity.Product;
import com.nagarro.entity.Reviews;
import com.nagarro.service.ProductServiceImpl;
import com.nagarro.service.ReviewServiceImpl;

@RestController
@CrossOrigin
public class ApproveReview {
	
	@Autowired
	private ProductServiceImpl productService;
	
	@Autowired
	private ReviewServiceImpl reviewService;
	
	@PutMapping("/approve/{id}")
	public void approveReviews(@RequestBody List<Reviews> reviews, @PathVariable("id") String id) throws Exception{
		Product product;
		List<Reviews> updateR=new ArrayList<Reviews>();
		try {
			product=productService.getProduct(id);
			for(Reviews review : reviews) {
				review.setProduct(product);	
				updateR.add(review);
			}
			product.setReview(updateR);
			productService.putProductReview(product);
		}catch(Exception e) {
		
			throw new Exception(e.getMessage());
		}
	}
	
	@PutMapping("/reviews/{id}")
	public Reviews putASingleReview(@RequestBody Reviews reviews, @PathVariable("id") String id) {
		Product product=productService.getProduct(id);
		reviews.setProduct(product);
		
		return this.reviewService.putOneReview(reviews);
	}

}
