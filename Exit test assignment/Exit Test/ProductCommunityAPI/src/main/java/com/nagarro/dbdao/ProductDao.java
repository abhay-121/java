package com.nagarro.dbdao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {
	public Product findByproductCode(String productCode);

}
