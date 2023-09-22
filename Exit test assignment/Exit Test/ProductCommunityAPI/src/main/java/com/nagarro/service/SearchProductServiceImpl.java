package com.nagarro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.dbdao.SearchProductDao;
import com.nagarro.entity.Product;

@Service
public class SearchProductServiceImpl  implements SearchProductService{

	@Autowired
	private SearchProductDao repo;

	@Override
	public List<Product> getProductBySearch(String search) {
		
		return repo.findBySearch(search);
	}

}
