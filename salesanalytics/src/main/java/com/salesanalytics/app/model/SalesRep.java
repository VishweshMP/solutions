package com.salesanalytics.app.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SalesRep {
	
	private BigDecimal salesRepId;
	private String salesRepName;
	private List<Product> products;
	
	@JsonCreator
	public SalesRep(@JsonProperty(value="salesRepId", required = true)BigDecimal salesRepId, String salesRepName, @JsonProperty(value="products", required = true)List<Product> products) {
		super();
		this.salesRepId = salesRepId;
		this.salesRepName = salesRepName;
		this.products = products;
	}
	public BigDecimal getSalesRepId() {
		return salesRepId;
	}
	public void setSalesRepId(BigDecimal salesRepId) {
		this.salesRepId = salesRepId;
	}
	public String getSalesRepName() {
		return salesRepName;
	}
	public void setSalesRepName(String salesRepName) {
		this.salesRepName = salesRepName;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
