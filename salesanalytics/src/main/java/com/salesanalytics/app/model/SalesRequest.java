package com.salesanalytics.app.model;

import java.util.List;

public class SalesRequest {
	
	private List<SalesRep> salesList;

	public List<SalesRep> getSalesList() {
		return salesList;
	}

	public void setSalesList(List<SalesRep> salesList) {
		this.salesList = salesList;
	}

}
