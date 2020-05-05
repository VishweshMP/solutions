package com.salesanalytics.app.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.salesanalytics.app.model.Product;
import com.salesanalytics.app.model.SalesRep;
import com.salesanalytics.app.model.SalesRequest;

@Service
public class SalesService {
	
	private static final Logger logger=LoggerFactory.getLogger(SalesService.class);
	
	
	private List<Product> extractProduct(SalesRequest request){
		return request.getSalesList().stream().flatMap(e -> e.getProducts().stream())
				.collect(Collectors.toList());
	}
	
	private SalesRequest salesJson() throws Exception {
		Instant start = Instant.now();
		JsonReader reader = new JsonReader(new FileReader("SampleJson/Sales.Json"));
		SalesRequest request = new Gson().fromJson(reader, SalesRequest.class);
		Instant stop = Instant.now();
		logger.info("Time to Red file from Json"+Duration.between(start, stop).toMillis());
		return request;
	}
	
	
	@SuppressWarnings("rawtypes")
	public Map getSalesByArea() throws Exception{
		Instant start = Instant.now();
		SalesRequest request = salesJson();
		Map<Object,Long> codeMap = extractProduct(request).stream().map(e -> e.getProductArea()).collect(Collectors.groupingBy(w ->w, Collectors.counting()));
		codeMap.forEach((k,v) -> logger.info("Key:"+k+" Value:"+v));
		Instant stop = Instant.now();
		logger.info("Time to Red file from Json"+Duration.between(start, stop).toMillis());
		return codeMap;
	}
	
	@SuppressWarnings("rawtypes")
	public Map getSalesByProductId() throws Exception{
		Instant start = Instant.now();
		SalesRequest request = salesJson();
		Map<Object,Long> codeMap = extractProduct(request).stream().map(e -> e.getProductId()).collect(Collectors.groupingBy(w ->w, Collectors.counting()));
		codeMap.forEach((k,v) -> logger.info("Key:"+k+" Value:"+v));
		Instant stop = Instant.now();
		logger.info("Time to Red file from Json"+Duration.between(start, stop).toMillis());
		return codeMap;
	}
	
	@SuppressWarnings("rawtypes")
	public Map getSalesBySalesPerson() throws Exception{
		Instant start = Instant.now();
		SalesRequest request = salesJson();
		Map<Object,Long> codeMap = new HashMap<>();
		request.getSalesList().stream().forEach(e -> codeMap.putIfAbsent(e.getSalesRepId(), (long)e.getProducts().size()));
		codeMap.forEach((k,v) -> logger.info("Key:"+k+" Value:"+v));
		Instant stop = Instant.now();
		logger.info("Time to Red file from Json"+Duration.between(start, stop).toMillis());
		return codeMap;
	}
	
	public SalesRequest addSalesDetails(SalesRep salesRep) throws Exception {
		Instant start = Instant.now();
		SalesRequest request = salesJson();
		List<SalesRep> salesRepList = request.getSalesList();
		Optional<SalesRep> salesOptional = salesRepList.stream()
					.filter(e -> e.getSalesRepId().equals(salesRep.getSalesRepId()))
					.findFirst();
		logger.info("Before adding a Json File");
		if(salesOptional.isPresent()) {
			List<Product> productList = salesRep.getProducts();
			salesOptional.get().setProducts(productList);
			salesRepList.add(salesOptional.get());
			logger.info("Insside Optional Loop");
		}
		else {
			salesRepList.add(salesRep);
		}
		request.setSalesList(salesRepList);
		
		new Gson().toJson(request, new FileWriter("SampleJson/Sales.Json"));
		Instant stop = Instant.now();
		logger.info("Time to Red file from Json"+Duration.between(start, stop).toMillis());
		
		return request;
	}				

}
