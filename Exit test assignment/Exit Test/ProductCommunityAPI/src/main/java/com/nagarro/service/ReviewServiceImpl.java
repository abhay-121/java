package com.nagarro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.dbdao.ReviewsDao;
import com.nagarro.entity.Product;
import com.nagarro.entity.Reviews;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewsDao repo;
	@Override
	public List<Reviews> getAllReviews(Product product) {
		return repo.findByProduct(product);
	}

	@Override
	public Long countAllReviews() {
		return repo.count();
	}

	@Override
	public List<Reviews> allReviews() {
		return repo.findAll();
	}

	@Override
	public void putReviews(List<Reviews> reviews) {
		this.repo.saveAll(reviews);
		
	}

	@Override
	public Reviews putOneReview(Reviews reviews) {
		return this.repo.save(reviews);
	}

}
