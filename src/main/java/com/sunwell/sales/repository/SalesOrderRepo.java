package com.sunwell.sales.repository;

import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sunwell.sales.model.SalesOrder;

public interface SalesOrderRepo extends JpaRepository<SalesOrder, Long>, JpaSpecificationExecutor<SalesOrder> {
	
	Page<SalesOrder> findByIdCustomer(long _id, Pageable _page);
//	Page<SalesOrder> findByCustomer_FirstName(String _custName, Pageable _page);
}
