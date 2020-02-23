package com.sunwell.sales.services;


import java.lang.reflect.Field;



import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

import org.omg.CORBA._IDLTypeStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sunwell.sales.clients.StockClient;
import com.sunwell.sales.exception.OperationException;
import com.sunwell.sales.model.CartDetail;
import com.sunwell.sales.model.CartDetailPK;
import com.sunwell.sales.model.SalesOrder;
import com.sunwell.sales.model.SalesOrderItem;
import com.sunwell.sales.model.SalesOrderItemPK;
import com.sunwell.sales.repository.CartDetailRepo;
import com.sunwell.sales.repository.SalesOrderItemRepo;
import com.sunwell.sales.repository.SalesOrderRepo;
import com.sunwell.sales.utils.Filters;
import com.sunwell.sales.utils.SalesOrderSpecification;
import com.sunwell.sales.utils.StandardConstant;


@Service
@Transactional
@Validated
public class SalesSvc implements SalesService
{
	@Autowired
	SalesOrderRepo soRepo;
	
	@Autowired
	SalesOrderItemRepo soItemRepo;
	
	@Autowired
	CartDetailRepo cdRepo;
	
	@Autowired
	StockClient stockClient;
	
	
	public SalesOrder findSalesOrder(@NotNull(message="{error_no_id}")Long _id) {
		return soRepo.findById(_id).orElse(null); 
	}
	
	@HystrixCommand(fallbackMethod = "logFailedGetSalesOrders",
            threadPoolKey = "soThreadPool",
            threadPoolProperties =
                    {@HystrixProperty(name = "coreSize",value="30"),
                     @HystrixProperty(name="maxQueueSize", value="10")},
            commandProperties={
            		 @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="3000"),
                     @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
                     @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
                     @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"),
                     @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
                     @HystrixProperty(name="metrics.rollingStats.numBuckets", value="3")}
    )
	
	public Page<SalesOrder> findAllSalesOrder(Pageable _page) {
		randomlyRunLong();
		return soRepo.findAll(_page);
	}
	
	public Page<SalesOrder> findSalesOrdersByCustId(Long _id, Pageable _page) {
		return soRepo.findByIdCustomer(_id, _page);
	}
	
	public Page<SalesOrder> findSalesOrders(Filters _f, Pageable _page) throws Exception {
		return soRepo.findAll(new SalesOrderSpecification(_f), _page);
	}
	
	@HystrixCommand(fallbackMethod = "logFailedGetSalesOrders",
            threadPoolKey = "soThreadPool",
            threadPoolProperties =
                    {@HystrixProperty(name = "coreSize",value="30"),
                     @HystrixProperty(name="maxQueueSize", value="10")},
            commandProperties={
            		 @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="3000"),
                     @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
                     @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
                     @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"),
                     @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
                     @HystrixProperty(name="metrics.rollingStats.numBuckets", value="3")}
    )
	public List<SalesOrder> findAllSalesOrder() {
		randomlyRunLong();
		return soRepo.findAll();
	}
	
	public SalesOrder addSalesOrder(
    		@Valid @NotNull(message="{error_no_so}") SalesOrder _so) 
    {
//		prepareSalesOrder(_so);
		return soRepo.save(_so);
    }
    
	public SalesOrder editSalesOrder(
    		@Valid @NotNull(message="{error_no_so}") SalesOrder _so) 
    {
		SalesOrder so = soRepo.findById(_so.getSystemId()).orElse(null);
		if(so == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_SALES_ORDER, null);
//		prepareSalesOrder(_so);
		
		if(_so.getItems() != null && _so.getItems().size () > 0) {
            for (SalesOrderItem sItem : _so.getItems()) {
                sItem.setSalesOrder(so);
            }
        }
		
		if(so.getItems() != null) {
			soItemRepo.deleteAll(so.getItems()); // mesti didelete dulu kalau objek ug diambil pakai spring data jpa
			so.setItems(null);
			soItemRepo.flush();
		}
		
		so.setIdCustomer(_so.getIdCustomer());
		so.setItems(_so.getItems());
		so.setIssueDate(_so.getIssueDate());
		so.setDeliveryStatus(_so.getDeliveryStatus());
		so.setDiscount(_so.getDiscount());
		so.setDiscountMemo(_so.getDiscountMemo());
		so.setMiscCharges(_so.getMiscCharges());
		so.setMiscChargesMemo(_so.getMiscChargesMemo());
		so.setMemo(_so.getMemo());
		so.setVAT(_so.getVAT());
		so.setVATInclusive(_so.isVATInclusive());
		so.setCanceledStatus(_so.getCanceledStatus());
		so.setPaymentStatus(_so.getPaymentStatus());
		so.setPromoCodeUsed(_so.getPromoCodeUsed());
		so.setShippingLine(_so.getShippingLine());
		
		return so;
		
    }
    
    public SalesOrder deleteSalesOrder(@NotNull(message="{error_no_id}") Long _id) {
    	SalesOrder so = soRepo.findById(_id).orElse(null);
		if(so == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_SALES_ORDER, null);
	    soRepo.delete(so);
	    return so;
    }
	
	public SalesOrderItem findSOItem(@NotNull(message="{error_no_id}")SalesOrderItemPK _pk) {
		return soItemRepo.findById(_pk).orElse(null);
	}
	
	public Page<SalesOrderItem> findSOItemBySalesOrder(SalesOrder _so, Pageable _page) {
		return soItemRepo.findBySalesOrder(_so, _page);
	}
	
	public CartDetail findCartDetail(@NotNull(message="{error_no_id}")CartDetailPK _pk) {
		return cdRepo.findById(_pk).orElse(null);
	}	
	
	public Page<CartDetail> findCartDetailByIdCustomer(long _cs, Pageable _page) {
		return cdRepo.findByIdCustomer(_cs, _page);
	}
	
	public List<CartDetail> findCartDetailByIdCustomer(long _cs) {
		return cdRepo.findByIdCustomer(_cs);
	}
//	
//	public Page<CartDetail> findCartDetailByCustomerId(Long _cs, Pageable _page) {
//		return cdRepo.findByCustomer_SystemId(_cs, _page);
//	}
	
//	public Page<CartDetail> findCartDetails(Filters _f, Pageable _page) throws Exception {
//		return cdRepo.findAll(new GenericSpecification<CartDetail>(_f, CartDetail.class), _page);
//	}
	
    public void addCartDetails(
    		@Valid @NotNull(message="{error_no_cart_detail}") List<CartDetail> _cartDetails) 
    {
		for(CartDetail cd : _cartDetails) {
			addCartDetail(cd);
		}
    }
	
	// note fungsi ini bisa juga untuk mengedit cart detail yang sudah ada
    public CartDetail addCartDetail(
    		@Valid @NotNull(message="{error_no_cart_detail}") CartDetail _cartDetail) 
    {
//    	prepareCartDetail(_cartDetail);
		return cdRepo.save(_cartDetail);
//    	// sama seperti relasi dari relasi one to many yang harus sudah managed
//    	// kemungkinan karena ini adalah id maka juga harus sudah managed
//    	Customer cust = userCredSvc.findCustomer(_cartDetail.getCustomer().getSystemId());
//		if(cust == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_CUSTOMER, null);
//		
//		Item item = productSvc.findItem(_cartDetail.getItem().getSystemId());
//		if(item == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT, null);
//		
//		_cartDetail.setCustomer(cust);
//		_cartDetail.setItem(item);
    	
    }
    
    public CartDetail editCartDetail(
    		@Valid @NotNull(message="{error_no_cart_detail}") CartDetail _cartDetail) 
    {
//    	Item item = null;
//    	if(_cartDetail.getItem().getSystemId() > 0)
//    		item = productSvc.findItem(_cartDetail.getItem().getSystemId());
//    	else if(_cartDetail.getItem().getName() != null)
//    		item = productSvc.findItemByName(_cartDetail.getItem().getName());
//    	if(item == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT, null);
    	CartDetail cd = cdRepo.findById(new CartDetailPK(_cartDetail.getIdCustomer(), _cartDetail.getIdItem()))
    			.orElse(null);
		if(cd == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_CART_DETAIL, null);
		
//		prepareCartDetail(_cartDetail);
		return cdRepo.save(_cartDetail);
		
//		// sama seperti relasi dari relasi one to many yang harus sudah managed
//    	// kemungkinan karena ini adalah id maka juga harus sudah managed
//		
//		Customer cust = userCredSvc.findCustomer(_cartDetail.getCustomer().getSystemId());
//		if(cust == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_CUSTOMER, null);
//		
//		Item item = productSvc.findItem(_cartDetail.getItem().getSystemId());
//		if(item == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT, null);
//		
//		_cartDetail.setCustomer(cust);
//		_cartDetail.setItem(item);
//		
//		return cdRepo.save(_cartDetail);
    }
    
    public CartDetail deleteCartDetail(
    		@NotNull(message="{error_no_id}") Long _custId,
    		@NotNull(message="{error_no_id}") Integer _itemId) 
    {
    	CartDetail cd = cdRepo.findById(new CartDetailPK(_custId, _itemId)).orElse(null);
//    	System.out.println("CustId: " + _custId + " itemId: " + _itemId + " cd: " + cd.getCustomer().getFirstName());
		if(cd == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_CART_DETAIL, null);
//		List<CartDetail> cds = cd.getCustomer().getCartDetails();
//		cds.remove(cd);
	    cdRepo.delete(cd);
	    return cd;
    }
    
    public Page<CartDetail> findCartDetailByCustomerId(Long _cs, Pageable _page) {
		return cdRepo.findByIdCustomer(_cs, _page);
	}
    
    public SalesOrder checkOut(@NotNull(message="{error_no_customer}") long _cust) {
//    	if(_cust.getCartDetails() == null || _cust.getCartDetails().size() <= 0 ) {
//    		throw new OperationException(StandardConstant.ERROR_NO_ITEM_IN_THE_CART, null);
//    	}
//    	// mesti diambil ulang customernya karena ada kemungkinan belum dimanage
//    	Customer cust = userCredSvc.findCustomer(_cust.getSystemId());
//    	if(cust == null)
//    		throw new OperationException(StandardConstant.ERROR_CANT_FIND_CUSTOMER, null);
//    	
//    	Gudang defWarehouse = invSvc.findDefaultWarehouse();
//    	if(defWarehouse == null)
//    		throw new OperationException(StandardConstant.ERROR_CANT_FIND_WAREHOUSE, null);
    	
//    	
    	SalesOrder so = new SalesOrder();
    	Calendar now = Calendar.getInstance();
    	List<SalesOrderItem> items = new LinkedList<>();
//    	List<OnHandStock> removedStocks = new LinkedList<>();
    	List<CartDetail> cds = findCartDetailByIdCustomer(_cust);
    	System.out.println("CDS SIZE: " + cds.size());
    	for(CartDetail cd : cds) {
    		System.out.println("CD ITEM : " + cd.getItemName() + " systemId: " + cd.getIdItem());
//    		cdRepo.delete(cd);
    	}
    	
    	deleteStocks(cds);
//    	stockClient.deleteStocks(cds);
    	
    	cdRepo.deleteAll(cds);
    	
    	so.setIdCustomer(_cust);
    	for(CartDetail cd : cds) {
    		SalesOrderItem soItem = new SalesOrderItem();
    		// mesti managed entity untuk relasi dari relasi yang dicascade persist
//    		Item item = cd.getItem(); 
//    		ProductSellPrice psp = item.getDefaultSellPrice();
    		soItem.setSalesOrder(so);
    		soItem.setIdItem(cd.getIdItem());
    		soItem.setQty(cd.getQty());
    		soItem.setItemName(cd.getItemName());
//    		soItem.setHargaJual(psp != null ? psp.getPrice() : 0);
    		items.add(soItem);
    		
//    		OnHandStock oh = new OnHandStock(item, defWarehouse, null, "", "", cd.getQty());
//    		OnHandStock oh = new OnHandStock(item, defWarehouse, cd.getQty());
//    		removedStocks.add(oh);
    	}
    	so.setItems(items);
//    	stockClient.deleteStocks(cds);
    	deleteStocks(cds);
    	soRepo.save(so);
//    	invSvc.removeOnHand(removedStocks);
    	
    	return so;
    }
    
    @HystrixCommand(fallbackMethod = "logFailedDelete",
            threadPoolKey = "stockClientThreadPool",
            threadPoolProperties =
                    {@HystrixProperty(name = "coreSize",value="30"),
                     @HystrixProperty(name="maxQueueSize", value="10")},
            commandProperties={
            		@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="3000"),
                     @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
                     @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
                     @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"),
                     @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
                     @HystrixProperty(name="metrics.rollingStats.numBuckets", value="3")}
    )
    private void deleteStocks(List<CartDetail> cartDetails) {
    	randomlyRunLong();
    	stockClient.deleteStocks(cartDetails);
    }
    
    private void randomlyRunLong(){
        Random rand = new Random();

        int randomNum = rand.nextInt((3 - 1) + 1) + 1;

        if (randomNum==3) {
      	  System.out.println("Randomnum: " + randomNum + " Sleeping");
      	  sleep();
        }
        else {
      	  System.out.println("Randomnum: " + randomNum + " Proceeding");
        }
      }

      private void sleep(){
          try {
              Thread.sleep(11000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
      
      private Page<SalesOrder> logFailedGetSalesOrders(Pageable _page) throws Exception {
    	  System.out.println("Failed to get sales orders, timeout reached");
    	  throw new Exception("Failed to get sales orders, timeout reached");
      }

}
