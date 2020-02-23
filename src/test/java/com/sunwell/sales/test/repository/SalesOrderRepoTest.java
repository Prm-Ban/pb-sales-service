package com.sunwell.sales.test.repository;

import static org.junit.Assert.assertEquals;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.sunwell.sales.model.SalesOrder;
import com.sunwell.sales.model.SalesOrderItem;
import com.sunwell.sales.repository.SalesOrderRepo;
import com.sunwell.sales.test.util.SalesOrderTestUtil;


@RunWith(SpringRunner.class)
@DataJpaTest
//@Sql("/com/sunwell/sales/test/salesorder.sql")
public class SalesOrderRepoTest {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private SalesOrderRepo soRepo;
	
	@Test
	public void persistAndFindSalesOrder() {
		SalesOrder so = SalesOrderTestUtil.getExampleSalesOrder();
		so.setSystemId(null);
		entityManager.persist(so);
				
		SalesOrder retval = soRepo.findById(1l).get();
		
	    assertNotNull(retval);
	    assertTrue(retval.getIdCustomer() == 1);
	}
//	
//	@Ignore
//	@Test
//	public void findByEmail() {
//		Pageable page = new PageRequest(1, 5);
//		Customer cust = custRepo.findByEmail("wacksack@com");
//		assertNotNull(cust);
//	}
}
