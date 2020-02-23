package com.sunwell.sales.test;

import org.junit.Before;


import org.junit.Ignore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sunwell.sales.dto.SalesOrderDTO;
import com.sunwell.sales.services.SalesService;
import com.sunwell.sales.test.util.SalesOrderTestUtil;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;


//@RunWith(SpringRunner.class)
//@SpringBootTest(
//  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
public class ContainerTest {
	
	@Autowired
    private MockMvc mvc;
	
	@Autowired
	SalesService salesSvc;
	
	private ObjectMapper objectMapper;
	
	@Before
	public void setUp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(sdf);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		objectMapper.setVisibilityChecker(objectMapper.getSerializationConfig().getDefaultVisibilityChecker()
				.withFieldVisibility(JsonAutoDetect.Visibility.ANY)
				.withGetterVisibility(JsonAutoDetect.Visibility.NONE)
				.withSetterVisibility(JsonAutoDetect.Visibility.NONE));
	}
	
	@Ignore
	@Test
	public void addSalesOrder() throws Exception {		

		SalesOrderDTO soDTO = SalesOrderTestUtil.getSalesOrderDTO();
		
		MockHttpServletRequestBuilder reqBld = post("/resources/salesorders").content(objectMapper.writeValueAsString(soDTO))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		mvc.perform(reqBld)					
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.data.systemId").exists());
	};
	
//	@Ignore
//	@Test
//	public void editSalesOrder() throws Exception {		
//
//		SalesOrderDTO soDTO = new SalesOrderDTO();
//		soDTO.setSystemId(3l);
//		soDTO.setCustomer(2l);
//		soDTO.setIssueDatetime(Calendar.getInstance());
//		soDTO.setDiscount(10000.0);
//		soDTO.setMiscCharges(2000.0);
//		soDTO.setDiscountMemo("Potongan");
//		soDTO.setMiscChargesMemo("Biaya");
//		soDTO.setCanceledStatus(1);
//		soDTO.setDeliveryStatus(1);
//		soDTO.setPaymentStatus(1);
//		
//		Mockito.when(salesSvc.editSalesOrder(any(SalesOrder.class))).thenReturn(soDTO.getData());
//		
//		MockHttpServletRequestBuilder reqBld = put("resources/salesorders").content(objectMapper.writeValueAsString(soDTO))
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		mvc.perform(reqBld)					
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$.paymentStatus", is(1)));
//	}
//	
//	@Ignore
//	@Test
//	public void deleteSalesOrder() throws Exception {		
//
//		SalesOrderDTO soDTO = new SalesOrderDTO();
//		soDTO.setSystemId(3l);
//		soDTO.setCustomer(2l);
//		soDTO.setIssueDatetime(Calendar.getInstance());
//		soDTO.setDiscount(10000.0);
//		soDTO.setMiscCharges(2000.0);
//		soDTO.setDiscountMemo("Potongan");
//		soDTO.setMiscChargesMemo("Biaya");
//		soDTO.setCanceledStatus(1);
//		soDTO.setDeliveryStatus(1);
//		soDTO.setPaymentStatus(1);
//		
//		Mockito.when(salesSvc.deleteSalesOrder(any())).thenReturn(soDTO.getData());
//		
//		MockHttpServletRequestBuilder reqBld = delete("resources/salesorders?systemId=3")
//				.accept(MediaType.APPLICATION_JSON);
//
//		mvc.perform(reqBld)					
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$.paymentStatus", is(1)));
//	}
//	
//	@Ignore
//	@Test
//	public void addCustomer() throws Exception {		
//		CustomerDTO custDTO = new CustomerDTO();
//		custDTO.setFirstName("Johan");
//		custDTO.setLastName("Mulya");
//		custDTO.setAddress("Jalan, Ketapang 9");
//		custDTO.setCreditLimit(0);
//		
//		MockHttpServletRequestBuilder reqBld = post("/resources/customers").content(objectMapper.writeValueAsString(custDTO))
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		
//		mvc.perform(reqBld)					
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$[0].name", is("Vargas")));
//	}
//	
//	@Ignore
//	@Test
//	public void editCustomer() throws Exception {		
//		CustomerDTO custDTO = new CustomerDTO();
//		custDTO.setSystemId(1l);
//		custDTO.setFirstName("Johan");
//		custDTO.setLastName("Mulya");
//		custDTO.setAddress("Jalan, Ketapang 9");
//		custDTO.setCreditLimit(0);
//		
//		Mockito.when(userCredSvc.editCustomer(any(Customer.class), any(Boolean.class))).thenReturn(custDTO.getData());
//		
//		MockHttpServletRequestBuilder reqBld = post("resources/customers").content(objectMapper.writeValueAsString(custDTO))
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		
//		mvc.perform(reqBld)					
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$.data.firstName", is("Johan")));
//	}
//	
//	@Ignore
//	@Test
//	public void deleteCustomer() throws Exception {
//		
//		CustomerDTO custDTO = new CustomerDTO();
//		custDTO.setSystemId(1l);
//		custDTO.setFirstName("Johan");
//		custDTO.setLastName("Mulya");
//		custDTO.setAddress("Jalan, Ketapang 9");
//		custDTO.setCreditLimit(0);
//		
//		Mockito.when(userCredSvc.deleteCustomer(any())).thenReturn(custDTO.getData());
//		
//		MockHttpServletRequestBuilder reqBld = delete("/resources/customers?systemId=1")
//				.accept(MediaType.APPLICATION_JSON);
//				
//		mvc.perform(reqBld)					
//		.andExpect(status().is2xxSuccessful())
//		.andExpect(jsonPath("$.data.firstName", is("Johan")));
//	}
//	
//	@Ignore
//	@Test
//	public void addMerk() throws Exception {
//		
//		MerkDTO mrkDTO = new MerkDTO();
//		mrkDTO.setName("Vargas");
//		mrkDTO.setManufacturer("Bintang");
//		
//		System.out.println("MERK JSON: " + objectMapper.writeValueAsString(mrkDTO));
//		
//		MockHttpServletRequestBuilder reqBld = post("/resources/merks").content(objectMapper.writeValueAsString(mrkDTO))
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		Mockito.when(productService.addMerk(any(Merk.class))).thenReturn(mrkDTO.getData());
//		
//		mvc.perform(reqBld)					
//			.andExpect(status().is2xxSuccessful())
//			.andExpect(jsonPath("$.data.name", is("Vargas")));
//	}
//	
//	@Ignore
//	@Test
//	public void editMerk() throws Exception {
//		MerkDTO mrkDTO = new MerkDTO();
//		mrkDTO.setSystemId(1);
//		mrkDTO.setName("Vargas");
//		mrkDTO.setManufacturer("Bintang");
//		
//		System.out.println("MERK JSON: " + objectMapper.writeValueAsString(mrkDTO));
//		
//		MockHttpServletRequestBuilder reqBld = put("/resources/merks").content(objectMapper.writeValueAsString(mrkDTO))
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		Mockito.when(productService.addMerk(any(Merk.class))).thenReturn(mrkDTO.getData());
//		
//		mvc.perform(reqBld)					
//			.andExpect(status().is2xxSuccessful())
//			.andExpect(jsonPath("$.data.name", is("Vargas")));
//	}
//	
//	@Ignore
//	@Test
//	public void deleteMerk() throws Exception {
//		
//		MerkDTO mrkDTO = new MerkDTO();
//		mrkDTO.setSystemId(1);
//		mrkDTO.setName("Vargas");
//		mrkDTO.setManufacturer("Bintang");
//		
//		MockHttpServletRequestBuilder reqBld = delete("/resources/merks?systemId=1")
//				.accept(MediaType.APPLICATION_JSON);
//		
//		Mockito.when(productService.deleteMerk(any())).thenReturn(mrkDTO.getData());
//		
//		mvc.perform(reqBld)					
//		.andExpect(status().is2xxSuccessful())
//		.andExpect(jsonPath("$.data.name", is("Vargas")));
//	}
//	
//	
//	@Ignore
//	@Test
//	public void getMerks() throws Exception {
//		mvc.perform(get("/resources/merks")
//			      .contentType(MediaType.APPLICATION_JSON))
//			      .andExpect(status().isOk())
//			      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//			      .andExpect(jsonPath("$").exists());
//	}
//	
//	@Ignore
//	@Test
//	public void getCustomers() throws Exception {
//		mvc.perform(get("/resources/customers")
//			      .contentType(MediaType.APPLICATION_JSON))
//			      .andExpect(status().isOk())
//			      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//			      .andExpect(jsonPath("$").exists());
//	}
//	
//	@Ignore
//	@Test
//	public void testItems() throws Exception {
//		mvc.perform(get("/resources/items")
//			      .contentType(MediaType.APPLICATION_JSON))
//			      .andExpect(status().isOk())
//			      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//			      .andExpect(jsonPath("$").exists());
//	}
//	
//	@Ignore
//	@Test
//	public void testStocks() throws Exception {
//		mvc.perform(get("/resources/stocks")
//			      .contentType(MediaType.APPLICATION_JSON))
//			      .andExpect(status().isOk())
//			      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//			      .andExpect(jsonPath("$").exists());
//	}
}
