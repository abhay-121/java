package com.nagarro.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
public class ReviewController {
	
	@Autowired
	private ProductServiceImpl productService;
	
	@Autowired
	private ReviewServiceImpl reviewService;
	
	@PutMapping("/postReview/{id}")
	public void postReview(@RequestBody Reviews review, @PathVariable("id") String id) throws Exception{
		List<Reviews> reviews;
		Product product;
		try {
			product=productService.getProduct(id);
			reviews=product.getReview();
			review.setApproved(false);
			review.setProduct(product);
			DateFormat dFormate = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			review.setDate(dFormate.format(date));
			reviews.add(review);
			productService.putProductReview(product);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@GetMapping("/getReviews/{id}")
	public List<Reviews> getReviews(@PathVariable("id") String id) throws Exception{
		List<Reviews> reviews;
		try {
			Product obj = productService.getProduct(id);
			reviews = this.reviewService.getAllReviews(obj);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return reviews;
	}
	
	@GetMapping("/reviews")
	public List<Reviews> getAllReviews(){
		return this.reviewService.allReviews();
	}
	
	
	
	
}
