package com.sunwell.sales.test.service;

import static org.junit.Assert.assertEquals;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.sunwell.sales.clients.StockClient;
import com.sunwell.sales.model.CartDetail;
import com.sunwell.sales.model.CartDetailPK;
import com.sunwell.sales.model.SalesOrder;
import com.sunwell.sales.repository.CartDetailRepo;
import com.sunwell.sales.repository.SalesOrderItemRepo;
import com.sunwell.sales.repository.SalesOrderRepo;
import com.sunwell.sales.services.SalesService;
import com.sunwell.sales.services.SalesSvc;
import com.sunwell.sales.test.util.SalesOrderTestUtil;

import junit.framework.Assert;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SalesSvc.class})
public class SalesOrderServiceTest {
	
	@MockBean
	SalesOrderRepo soRepo;
	
	@MockBean
	SalesOrderItemRepo soiRepo;
	
	@MockBean
	StockClient stockClient;
	
	@MockBean
	CartDetailRepo cdRepo;
	
	@Autowired
	SalesService salesSvc ;
	
	
	@Test
	public void findSalesOrder() throws Exception {
		SalesOrder so = SalesOrderTestUtil.getExampleSalesOrder();
		Optional<SalesOrder> optSo = Optional.of(so);
		Mockito.when(soRepo.findById(Mockito.anyLong())).thenReturn(optSo);
		
		SalesOrder retval = salesSvc.findSalesOrder(1l);
		
		Assert.assertTrue(so.equals(retval));
	}
	
	@Test
	public void findCartDetail() throws Exception {
		CartDetail cs = SalesOrderTestUtil.getExampleCartDetail();
		Optional<CartDetail> optCD = Optional.of(cs);
		Mockito.when(cdRepo.findById(Mockito.any(CartDetailPK.class))).thenReturn(optCD);
		
		CartDetail retval = salesSvc.findCartDetail(new CartDetailPK(1l, 1));
		
		Assert.assertTrue(cs.equals(retval));
	}
	
//	@Test 
//	public void cantFindEntitiesWhenAddingOnHand() {
//		OnHandStock oh = new OnHandStock(new Item(1), new Gudang(1), 1000);
//		boolean throwException = false;
//		
//		try {
//			invSvc.addOnHand(oh);
//		}
//		catch(OperationException e) {
//			assertEquals(e.getErrorCode(), StandardConstant.ERROR_CANT_FIND_PRODUCT);
//			throwException = true;
//		}
//		
//		assertTrue(throwException);
//		throwException = false;
//		
//		Mockito.when(prodSvc.findItem(Mockito.anyInt())).thenReturn(new Item(1));
//		
//		try {
//			invSvc.addOnHand(oh);
//		}
//		catch(OperationException e) {
//			assertEquals(e.getErrorCode(), StandardConstant.ERROR_CANT_FIND_WAREHOUSE);
//			throwException = true;
//		}
//		
//		assertTrue(throwException);
//		throwException = false;
//		
//		Mockito.when(wrRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(new Gudang(1)));
//		
//		try {
//			invSvc.addOnHand(oh);
//		}
//		catch(OperationException e) {
//			assertEquals(e.getErrorCode(), StandardConstant.ERROR_CANT_FIND_STOCK);
//			throwException = true;
//		}
//		
//		assertTrue(throwException);
//	}
//	
//	@Ignore
//	@Test
//	public void findAllOnHands() {
//		List<OnHandStock> listOHS = new LinkedList<>();
//		listOHS.add(new OnHandStock(new Item(1), new Gudang(1), 100));
//		listOHS.add(new OnHandStock(new Item(2), new Gudang(1), 100));
//		listOHS.add(new OnHandStock(new Item(3), new Gudang(1), 100));
//		
//		Pageable pageable = new PageRequest(1,10);
//		Page<OnHandStock> page = new PageImpl<>(listOHS, pageable, 3);
//						
//		Mockito.when(ohRepo.findAll(Mockito.any(Pageable.class))).thenReturn(page);
//		Page<OnHandStock> retval = invSvc.findAllOnHandStock(pageable);
//		assertEquals(page, retval);
//	}
//	
//	@Ignore
//	@Test
//	public void findOnHand() {
//		OnHandStock ohs = new OnHandStock(new Item(1), new Gudang(1), 100);
//		
//		Mockito.when(ohRepo.findById(Mockito.any())).thenReturn(Optional.of(ohs));
//		
//		OnHandStock retval = invSvc.findOnHandStock(new OnHandStockPK(6, 1));
//		
//		assertNotNull(ohs);
//	}
//	
//	@Ignore
//	@Test 
//	public void addOnHand() {
//		OnHandStock ohs = new OnHandStock(new Item(1), new Gudang(1), 100);
//		
//		Mockito.when(ohRepo.findById(Mockito.any())).thenReturn(Optional.of(ohs));
//		Mockito.when(prodSvc.findItem(Mockito.any())).thenReturn(new Item(1));
//		Mockito.when(wrRepo.findById(Mockito.any())).thenReturn(Optional.of(new Gudang(1)));
//		
//		OnHandStock retval = invSvc.addOnHand(ohs);
//		
//		assertEquals(ohs, retval);
//	}
//	
//	@Ignore
//	@Test 
//	public void editOnHand() {
//		OnHandStock ohs = new OnHandStock(new Item(1), new Gudang(1), 100);
//		
//		Mockito.when(ohRepo.findById(Mockito.any())).thenReturn(Optional.of(ohs));
//		Mockito.when(prodSvc.findItem(Mockito.any())).thenReturn(new Item(1));
//		Mockito.when(wrRepo.findById(Mockito.any())).thenReturn(Optional.of(new Gudang(1)));
//		
//		OnHandStock retval = invSvc.editOnHand(ohs);
//		
//		assertEquals(ohs, retval);
//	}
//	
//	@Ignore
//	@Test(expected=OperationException.class)
//	public void cantFindOnHandWhenEditing() {
//		OnHandStock ohs = new OnHandStock(new Item(1), new Gudang(1), 100);		
//		invSvc.editOnHand(ohs);
//	}
//	
//	@Ignore
//	@Test(expected=OperationException.class)
//	public void cantFindItemWhenEditing() {
//		OnHandStock ohs = new OnHandStock(new Item(1), new Gudang(1), 100);
//		
//		Mockito.when(ohRepo.findById(Mockito.any())).thenReturn(Optional.of(ohs));
//		
//		invSvc.editOnHand(ohs);
//	}
//	
//	@Ignore
//	@Test(expected=OperationException.class)
//	public void cantFindWarehouseWhenEditing() {
//		OnHandStock ohs = new OnHandStock(new Item(1), new Gudang(1), 100);
//		
//		Mockito.when(ohRepo.findById(Mockito.any())).thenReturn(Optional.of(ohs));
//		Mockito.when(prodSvc.findItem(Mockito.any())).thenReturn(new Item(1));
//		
//		invSvc.editOnHand(ohs);
//	}
	
}
