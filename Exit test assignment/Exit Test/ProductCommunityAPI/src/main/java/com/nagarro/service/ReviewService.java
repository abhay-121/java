package com.nagarro.service;

import java.util.List;

import com.nagarro.entity.Product;
import com.nagarro.entity.Reviews;

public interface ReviewService {
	
	public List<Reviews> getAllReviews(Product product);
	public List<Reviews> allReviews();
	public Long countAllReviews();
	public void putReviews(List<Reviews> reviews);
	public Reviews putOneReview(Reviews reviews);

}
