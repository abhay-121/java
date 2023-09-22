package com.nagarro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.dbdao.ProductDao;
import com.nagarro.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao; 
	
	@Override
	public List<Product> getAll() {
		return this.productDao.findAll();
	}

	@Override
	public Product addProduct(Product product) {
		return this.productDao.save(product);
	}

	@Override
	public Product getProduct(String productCode) {
		return this.productDao.findByproductCode(productCode);
	}

	@Override
	public void putProductReview(Product product) {
		this.productDao.save(product);
	}

	@Override
	public Long countAllProduct() {
		return this.productDao.count();
	}

}
