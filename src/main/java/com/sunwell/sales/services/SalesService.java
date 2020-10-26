package com.sunwell.sales.services;


import java.lang.reflect.Field;


import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;




import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.sunwell.sales.exception.OperationException;
import com.sunwell.sales.model.CartDetail;
import com.sunwell.sales.model.CartDetailPK;
import com.sunwell.sales.model.SalesOrder;
import com.sunwell.sales.model.SalesOrderItem;
import com.sunwell.sales.model.SalesOrderItemPK;
import com.sunwell.sales.utils.Filters;
import com.sunwell.sales.utils.StandardConstant;


public interface SalesService
{
	public SalesOrder findSalesOrder(@NotNull(message="{error_no_id}") Long _id) throws Exception ;
	public Page<SalesOrder> findAllSalesOrder(Pageable _page) throws Exception ;
	public Page<SalesOrder> findSalesOrdersByCustId(Long _id, Pageable _page) throws Exception;
	public Page<SalesOrder> findSalesOrders(Filters _f, Pageable _page) throws Exception ;
	public List<SalesOrder> findAllSalesOrder() throws Exception ;
	public SalesOrder addSalesOrder(@Valid @NotNull(message="{error_no_so}") SalesOrder _so) throws Exception ;
	public SalesOrder editSalesOrder(@Valid @NotNull(message="{error_no_so}") SalesOrder _so) throws Exception ;
    public SalesOrder deleteSalesOrder(@NotNull(message="{error_no_id}") Long _id) throws Exception ;
	public SalesOrderItem findSOItem(@NotNull(message="{error_no_id}") SalesOrderItemPK _pk) throws Exception ;
	public Page<SalesOrderItem> findSOItemBySalesOrder(SalesOrder _so, Pageable _page) throws Exception ;  
	public CartDetail findCartDetail(@NotNull(message="{error_no_id}")CartDetailPK _pk);
	public Page<CartDetail> findCartDetailByIdCustomer(long _cs, Pageable _page) ;
	public List<CartDetail> findCartDetailByIdCustomer(long _cs) ;
    public void addCartDetails(@Valid @NotNull(message="{error_no_cart_detail}") List<CartDetail> _cartDetails) ;	
    public CartDetail addCartDetail(@Valid @NotNull(message="{error_no_cart_detail}") CartDetail _cartDetail) ;
    public CartDetail editCartDetail(@Valid @NotNull(message="{error_no_cart_detail}") CartDetail _cartDetail) ;
    public CartDetail deleteCartDetail(@NotNull(message="{error_no_id}") Long _custId, @NotNull(message="{error_no_id}") Integer _itemId) ;
    public SalesOrder checkOut(@NotNull(message="{error_no_customer}") long _cust) ;
}
