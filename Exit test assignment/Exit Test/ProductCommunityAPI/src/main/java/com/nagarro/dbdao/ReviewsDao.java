package com.nagarro.dbdao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.entity.Product;
import com.nagarro.entity.Reviews;

public interface ReviewsDao extends JpaRepository<Reviews, Integer> {
	public List<Reviews> findByProduct(Product product);

}
