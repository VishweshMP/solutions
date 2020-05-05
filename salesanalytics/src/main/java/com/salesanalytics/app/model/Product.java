package com.salesanalytics.app.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
	
	private BigDecimal productId;
	private String productName;
	private String productType;
	private Double productAmount;
	private String productArea;	
	
	@JsonCreator
	public Product(@JsonProperty(value="productId", required = true)BigDecimal productId, String productName, String productType, @JsonProperty(value="productAmount", required = true)Double productAmount,
			@JsonProperty(value="productArea", required = true)String productArea) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productType = productType;
		this.productAmount = productAmount;
		this.productArea = productArea;
	}
	public String getProductArea() {
		return productArea;
	}
	public void setProductArea(String productArea) {
		this.productArea = productArea;
	}
	public BigDecimal getProductId() {
		return productId;
	}
	public void setProductId(BigDecimal productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Double getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(Double productAmount) {
		this.productAmount = productAmount;
	}

}
