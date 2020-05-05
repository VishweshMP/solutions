package com.salesanalytics.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.salesanalytics.app.model.SalesRep;
import com.salesanalytics.app.model.SalesRequest;
import com.salesanalytics.app.service.SalesService;

@RestController
public class SalesController {
	
	@Autowired
	private SalesService service;
	
	@GetMapping(value="/hello")
	public String helloWorld() {
		return "Hello";
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value="/salesByArea",produces = {"application/json"})
	public ResponseEntity salesByArea() {
		try {
			return new ResponseEntity<>(service.getSalesByArea(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error Detected",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value="/salesByProduct",produces = {"application/json"})
	public ResponseEntity salesByProduct() {
		try {
			return new ResponseEntity<>(service.getSalesByProductId(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error Detected",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value="/salesBySales",produces = {"application/json"})
	public ResponseEntity salesBySales() {
		try {
			return new ResponseEntity<>(service.getSalesBySalesPerson(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error Detected",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="addSalesDetails",consumes = {"application/json"})
	public SalesRequest addSalesDetails(@RequestBody SalesRep salesRep) {
		
		try {
			return service.addSalesDetails(salesRep);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
