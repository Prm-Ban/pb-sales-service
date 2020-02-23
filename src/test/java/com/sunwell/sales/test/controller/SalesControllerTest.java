package com.sunwell.sales.test.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sunwell.sales.clients.StockClient;
import com.sunwell.sales.controllers.SalesController;
import com.sunwell.sales.dto.SalesOrderDTO;
import com.sunwell.sales.dto.SalesOrderItemDTO;
import com.sunwell.sales.model.CartDetail;
import com.sunwell.sales.model.SalesOrder;
import com.sunwell.sales.services.SalesService;
import com.sunwell.sales.test.util.SalesOrderTestUtil;
import com.sunwell.sales.utils.ServiceUtil;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers= {SalesController.class}, secure=false)
@EnableSpringDataWebSupport
public class SalesControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	SalesService salesSvc;
	
	@MockBean
	StockClient stockClient;
    
	@MockBean
    ServletContext sCtx;
    
	@MockBean
    MessageSource messageSource;
	
//	@Autowired
	ObjectMapper objectMapper;
	
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
	
//	@Ignore
	@Test
	public void addSalesOrder() throws Exception {		
		SalesOrderDTO soDTO = SalesOrderTestUtil.getExampleSalesOrderDTO();
		Mockito.when(salesSvc.addSalesOrder(any(SalesOrder.class))).thenReturn(soDTO.getData());
		
		MockHttpServletRequestBuilder reqBld = post("/resources/salesorders").
				content(objectMapper.writeValueAsString(soDTO))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
				
		mockMvc.perform(reqBld)					
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.data.systemId", is(1)));		
	}
	
	@Test
	public void editSalesOrder() throws Exception {		
		SalesOrderDTO soDTO = SalesOrderTestUtil.getExampleSalesOrderDTO();
		Mockito.when(salesSvc.editSalesOrder(any(SalesOrder.class))).thenReturn(soDTO.getData());
		
		MockHttpServletRequestBuilder reqBld = put("/resources/salesorders").
				content(objectMapper.writeValueAsString(soDTO))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
				
		mockMvc.perform(reqBld)					
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.data.systemId", is(1)));		
	}
	
	@Test
	public void getSalesOrder() throws Exception {		
		SalesOrderDTO soDTO = SalesOrderTestUtil.getExampleSalesOrderDTO();
		Mockito.when(salesSvc.findSalesOrder(any(Long.class))).thenReturn(soDTO.getData());
		
		MockHttpServletRequestBuilder reqBld = get("/resources/salesorders")
				.param("systemId", "1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
				
		mockMvc.perform(reqBld)					
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.data.systemId", is(1)));		
	}
	
	@Test
	public void getCartDetails() throws Exception {		
		List<CartDetail> listCD = SalesOrderTestUtil.getExampleCartDetails();
		Pageable pageable = new PageRequest(1, 10);
		Page<CartDetail> pageCD = new PageImpl<CartDetail>(listCD, pageable, 3);
		Mockito.when(salesSvc.findCartDetailByIdCustomer(any(Long.class), any(Pageable.class))).
				thenReturn(pageCD);
		
		MockHttpServletRequestBuilder reqBld = get("/resources/cartdetails")
				.param("customerId", "1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
				
		mockMvc.perform(reqBld)					
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.data", Matchers.hasSize(3)));		
	}
	
	@Test
	public void getSalesOrders() throws Exception {		
		List<SalesOrder> listSO = SalesOrderTestUtil.getExampleSalesOrders();
		Pageable pageable = new PageRequest(1, 10);
		Page<SalesOrder> pageSO = new PageImpl<SalesOrder>(listSO, pageable, 3);
		Mockito.when(salesSvc.findAllSalesOrder(any(Pageable.class))).thenReturn(pageSO);
		
		MockHttpServletRequestBuilder reqBld = get("/resources/salesorders")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
				
		ResultActions ra = mockMvc.perform(reqBld);
		MvcResult res = ra.andReturn();
		System.out.println("RESPONSE: \n" + res.getResponse().getContentAsString());
		
			ra.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.data", Matchers.hasSize(3)));		
	}
	
	@Test
	public void deleteSalesOrder() throws Exception {		
		SalesOrderDTO soDTO = SalesOrderTestUtil.getExampleSalesOrderDTO();
		Mockito.when(salesSvc.deleteSalesOrder(any(Long.class))).thenReturn(soDTO.getData());
		
		MockHttpServletRequestBuilder reqBld = delete("/resources/salesorders")
				.param("systemId", "1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
				
		mockMvc.perform(reqBld)					
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.data.systemId", is(1)));		
	}
	
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
//		MockHttpServletRequestBuilder reqBld = put("resources/customers").content(objectMapper.writeValueAsString(custDTO))
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		
//		mockMvc.perform(reqBld)					
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
//		mockMvc.perform(reqBld)					
//		.andExpect(status().is2xxSuccessful())
//		.andExpect(jsonPath("$.data.firstName", is("Johan")));
//	}
//    
//	
//	@Ignore
//	@Test
//	public void getAllCustomers() throws Exception {
//		
//		assertNotNull(mockMvc);
//		
//		System.out.println(mockMvc);
//		
//		List<Customer> listCustomers = new LinkedList<>();
//		
//		Customer cust = new Customer() ;
//		cust.setSystemId(1);
//		cust.setFirstName("Samson");
//		cust.setLastName("Sonsam");
//		
//		listCustomers.add(cust);
//		
//		cust = new Customer() ;
//		cust.setSystemId(2);
//		cust.setFirstName("Nick");
//		cust.setLastName("Huda");
//		
//		listCustomers.add(cust);
//		
//		cust = new Customer() ;
//		cust.setSystemId(3);
//		cust.setFirstName("Joe");
//		cust.setLastName("Taslim");
//		
//		listCustomers.add(cust);
//		
//		Pageable pageable = new PageRequest(1,10);
//		Page<Customer> pages = new PageImpl<>(listCustomers, pageable, 2);
//				
//		given(userCredSvc.findAllCustomers(any())).willReturn(pages);
//		
//		mockMvc.perform(get("/resources/customers")
//			      .contentType(MediaType.APPLICATION_JSON))
//			      .andExpect(status().isOk())
//			      .andExpect(jsonPath("$.data[0].firstname", is("Samson")));
//	}
	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfiguration {
  
        @Bean
        public ServiceUtil serviceUtil() {
            return new ServiceUtil();
        }
        
        @Bean
    	public MessageSource messageSource ()
    	{
    		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    		messageSource.setCacheSeconds(-1);
    		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
    		messageSource.setBasenames("/WEB-INF/i18n/titles", "/WEB-INF/i18n/messages", "/WEB-INF/i18n/errors",
    				"/WEB-INF/i18n/validation", "classpath:errors");
    		return messageSource;
    	}
        
    }
}
