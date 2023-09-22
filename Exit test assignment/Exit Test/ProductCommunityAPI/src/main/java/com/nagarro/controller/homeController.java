package com.nagarro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.model.Records;
import com.nagarro.service.ProductServiceImpl;
import com.nagarro.service.ReviewServiceImpl;
import com.nagarro.service.UserServiceImpl;

@RestController
@CrossOrigin
public class homeController {
	@Autowired
	private ProductServiceImpl prodService;
	@Autowired
	private ReviewServiceImpl reviewService;
	@Autowired
	private UserServiceImpl userService;

	@GetMapping("/getStats")
	public Records getStats() throws Exception{
		Records record = new Records();
		try {
			record.totalUsers= userService.countAllUser();
			record.totalProducts=prodService.countAllProduct();
			record.totalReviews=reviewService.countAllReviews();
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return record;
		
	}
}
