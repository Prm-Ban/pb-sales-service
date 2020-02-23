//package com.thoughtmechanix.licenses;
//
//import static org.hamcrest.CoreMatchers.is;
//
//
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.LinkedList;
//import java.util.List;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.config.EnableSpringDataWebSupport;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.sunwell.sales.config.ServiceConfig;
//import com.sunwell.sales.controllers.LicenseServiceController;
//import com.sunwell.sales.model.License;
//import com.sunwell.sales.services.LicenseService;
//
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(controllers= {LicenseServiceController.class}, secure=false)
//@EnableSpringDataWebSupport
//@ActiveProfiles("production")
//public class LicenseServiceControllerTest {
//	
//	@Autowired
//	MockMvc mockMvc;
//
//	@MockBean
//    private LicenseService licenseService;
//
//    @MockBean
//    private ServiceConfig serviceConfig;
//
////    @MockBean
////    private HttpServletRequest request;
//	
//	@Test
//	public void testPath() throws Exception {
//		
//		List<License> listLicenses = new LinkedList<>();
//		
//		License license = new License() ;
//		license.setLicenseId("12345");
//		listLicenses.add(license);
//		
//		license = new License();
//		license.setLicenseId("23456");
//		listLicenses.add(license);
//		
//		license = new License();
//		license.setLicenseId("34567");
//		listLicenses.add(license);
//				
//		given(licenseService.getLicensesByOrg(any())).willReturn(listLicenses); 
//		
//		mockMvc.perform(get("/v1/organizations/e254f8c-c442-4ebe-a82a-e2fc1d1ff78a/licenses/")
////		mockMvc.perform(get("/v1/e254f8c-c442-4ebe-a82a-e2fc1d1ff78a")
//			      .contentType(MediaType.APPLICATION_JSON))
//			      .andExpect(status().isOk())
//			      .andExpect(jsonPath("$[0].licenseId", is("12345")));
//	}      
//  }
//	