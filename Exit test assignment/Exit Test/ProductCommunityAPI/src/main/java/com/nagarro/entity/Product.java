package com.nagarro.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
	@Id
	private String productCode;
	private String productBrand;
	private String productName;
	
	@OneToMany(mappedBy="product", cascade = CascadeType.ALL)
	private List<Reviews> reviews = new ArrayList<Reviews>();
	
	public Product() {
		super();
	}

	
	public Product(String productCode, String productBrand, String productName) {
		super();
		this.productCode = productCode;
		this.productBrand = productBrand;
		this.productName = productName;
	}


	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<Reviews> getReview() {
		return reviews;
	}

	public void setReview(List<Reviews> review) {
		this.reviews = review;
	}
	
	

}
