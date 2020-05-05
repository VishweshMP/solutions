package com.salesanalytics.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.salesanalytics.app.service.SalesService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SalesServiceTest {
	
	
	@Autowired
	private SalesService serviceTest;
	
	@Test
	public void testSalesByArea() throws Exception {
		Map testMap = serviceTest.getSalesByArea();
		assertThat(testMap).hasSizeGreaterThan(1);
	}
	
	@Test
	public void testSalesByProduct() throws Exception {
		Map testMap = serviceTest.getSalesByProductId();
		assertThat(testMap).hasSizeGreaterThan(1);
	}
	
	@Test
	public void testSalesBySales() throws Exception {
		Map testMap = serviceTest.getSalesBySalesPerson();
		assertThat(testMap).hasSizeGreaterThan(1);
	}
	
	
	
}
