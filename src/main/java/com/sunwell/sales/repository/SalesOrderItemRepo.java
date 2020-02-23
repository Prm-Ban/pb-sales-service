package com.sunwell.sales.repository;

import java.util.List;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sunwell.sales.model.SalesOrder;
import com.sunwell.sales.model.SalesOrderItem;
import com.sunwell.sales.model.SalesOrderItemPK;

public interface SalesOrderItemRepo extends JpaRepository<SalesOrderItem, SalesOrderItemPK> {
	Page<SalesOrderItem> findBySalesOrder(SalesOrder _so, Pageable _page);
}
