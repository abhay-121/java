package com.nagarro.dbdao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface SearchProductDao extends JpaRepository<Product, String> {
	
	@Query("SELECT p from Product p Where INSTR(Concat(p.productCode,p.productName,p.productBrand),:search)>0 or INSTR(Concat(p.productCode,p.productBrand,p.productName),:search)>0 or INSTR(Concat(p.productName,p.productCode,p.productBrand),:search)>0 or INSTR(Concat(p.productName,p.productBrand,p.productCode),:search)>0 or INSTR(Concat(p.productBrand,p.productName,p.productCode),:search)>0 or INSTR(Concat(p.productBrand,p.productCode,p.productName),:search)>0 ")
	public List<Product> findBySearch(@Param("search") String search);

}
