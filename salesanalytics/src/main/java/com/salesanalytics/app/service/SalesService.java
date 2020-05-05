package com.salesanalytics.app.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.salesanalytics.app.model.Product;
import com.salesanalytics.app.model.SalesRep;
import com.salesanalytics.app.model.SalesRequest;

@Service
public class SalesService {
	
	
	private List<Product> extractProduct(SalesRequest request){
		return request.getSalesList().stream().flatMap(e -> e.getProducts().stream())
				.collect(Collectors.toList());
	}
	
	private SalesRequest salesJson() throws Exception {
		JsonReader reader = new JsonReader(new FileReader("SampleJson/Sales.Json"));
		SalesRequest request = new Gson().fromJson(reader, SalesRequest.class);
		return request;
	}
	
	
	@SuppressWarnings("rawtypes")
	public Map getSalesByArea() throws Exception{
		SalesRequest request = salesJson();
		Map<Object,Long> codeMap = extractProduct(request).stream().map(e -> e.getProductArea()).collect(Collectors.groupingBy(w ->w, Collectors.counting()));
		codeMap.forEach((k,v) -> System.out.println("Key:"+k+" Value:"+v));
		return codeMap;
	}
	
	@SuppressWarnings("rawtypes")
	public Map getSalesByProductId() throws Exception{
		SalesRequest request = salesJson();
		Map<Object,Long> codeMap = extractProduct(request).stream().map(e -> e.getProductId()).collect(Collectors.groupingBy(w ->w, Collectors.counting()));
		codeMap.forEach((k,v) -> System.out.println("Key:"+k+" Value:"+v));
		return codeMap;
	}
	
	@SuppressWarnings("rawtypes")
	public Map getSalesBySalesPerson() throws Exception{
		SalesRequest request = salesJson();
		Map<Object,Long> codeMap = new HashMap<>();
		request.getSalesList().stream().forEach(e -> codeMap.putIfAbsent(e.getSalesRepId(), (long)e.getProducts().size()));
		codeMap.forEach((k,v) -> System.out.println("Key:"+k+" Value:"+v));
		return codeMap;
	}
	
	public SalesRequest addSalesDetails(SalesRep salesRep) throws Exception {
		SalesRequest request = salesJson();
		List<SalesRep> salesRepList = request.getSalesList();
		Optional<SalesRep> salesOptional = salesRepList.stream()
					.filter(e -> e.getSalesRepId().equals(salesRep.getSalesRepId()))
					.findFirst();
		if(salesOptional.isPresent()) {
			List<Product> productList = salesRep.getProducts();
			salesOptional.get().setProducts(productList);
			salesRepList.add(salesOptional.get());
		}
		else {
			salesRepList.add(salesRep);
		}
		request.setSalesList(salesRepList);
		
		new Gson().toJson(request, new FileWriter("SampleJson/Sales.Json"));
		
		return request;
	}				

}
