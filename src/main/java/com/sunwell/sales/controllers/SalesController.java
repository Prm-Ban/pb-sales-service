package com.sunwell.sales.controllers;

import java.util.Arrays;






import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunwell.sales.clients.StockClient;
import com.sunwell.sales.dto.CartDetailDTO;
import com.sunwell.sales.dto.SalesOrderDTO;
import com.sunwell.sales.exception.OperationException;
import com.sunwell.sales.model.CartDetail;
import com.sunwell.sales.model.SalesOrder;
import com.sunwell.sales.services.SalesService;
import com.sunwell.sales.utils.Filters;
import com.sunwell.sales.utils.ServiceUtil;
import com.sunwell.sales.utils.StandardConstant;



@RestController
public class SalesController
{
	@Autowired
	SalesService salesSvc ;
	
	@Autowired
	ServiceUtil svcUtil;
	
	@Autowired
	StockClient stockClient;
	
	
	@RequestMapping(value = "resources/salesorders", method = RequestMethod.GET,
			produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> getSalesOrders(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestParam(value="systemId", required = false) Long _systemId,
//    		@RequestParam(value="soNo", required = false) String _soNo,
    		@RequestParam(value="customerId", required = false) Long _custId,
    		Pageable _page
    		) throws Exception 
    {
		Map<String,Object> retData = null;
		try {
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_SALES_ORDER, null);
			Object mainData = null;
    		Page<SalesOrder> pageSO = null ;
    		SalesOrder so = null;
			int totalPages = 0;
			long totalItems = 0;
						 
			if(_systemId != null) {
				so = salesSvc.findSalesOrder(_systemId);;
			}
            else {
            	if(_custId != null) {
            		pageSO = salesSvc.findSalesOrdersByCustId(_custId, _page);
            	}
            	else {
            		pageSO = salesSvc.findAllSalesOrder(_page);
            	}
            }
            
            if(so != null) {
            	totalPages = 1;
            	totalItems = 1;
            	mainData = new SalesOrderDTO(so);
            }
            else if(pageSO != null && pageSO.getContent().size() > 0 ) {
            	totalPages = pageSO.getTotalPages();
            	totalItems = pageSO.getTotalElements();
            	List<SalesOrder> salesOrders = pageSO.getContent();
            	List<SalesOrderDTO> listSODTO = new LinkedList<>();
            	for(SalesOrder s : salesOrders) {
            		listSODTO.add(new SalesOrderDTO(s));
            	}
            	mainData = listSODTO;
        	}
        
            retData = svcUtil.returnSuccessfulData(mainData, totalPages, totalItems);
		}
		catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
    }
	
	@RequestMapping(value = "resources/salesorders", method = RequestMethod.GET,
			produces = "application/json", params="criteria"
	)
    public ResponseEntity<Map<String,Object>> getSalesOrders(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestParam(value="criteria") List<String> _filters,
    		Pageable _page
    		) throws Exception 
    {
		Map<String,Object> retData = null;
	
		try {
//			TokenInfo ti = svcUtil.authenticate(_auth, UserCredential.TASK_VIEW_SALES_ORDERS, -1);
//    		retData = svcUtil.getErrorFromToken(ti, false);
//    		if(retData != null)
//    			return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
			
			Object mainData = null;
    		Page<SalesOrder> pageSO = null ;
			int totalPages = 0;
			long totalItems = 0;
			Filters filters =  svcUtil.convertToFilters(_filters, SalesOrder.class);
			
			pageSO = salesSvc.findSalesOrders(filters, _page);
			if(pageSO != null && pageSO.getNumberOfElements() > 0) {
            	totalPages = pageSO.getTotalPages();
            	totalItems = pageSO.getTotalElements();
            	List<SalesOrder> salesOrders = pageSO.getContent();
            	
            	// autthorization        		
//            	for(SalesOrder so : salesOrders) {
//            		if(ti.access == ServiceUtil.ACCESS_LIMITED && so.getCustomer().getSystemId() != ti.id)
//            			return new ResponseEntity<Map<String,Object>>(svcUtil.returnErrorData(ServiceUtil.ERROR_NOT_PERMITTED),null, HttpStatus.OK);
//            	}
            	
            	List<SalesOrderDTO> listSODTO = new LinkedList<>();
            	for(SalesOrder s : salesOrders) {
            		listSODTO.add(new SalesOrderDTO(s));
            	}
            	mainData = listSODTO;
        	}
            
            retData = svcUtil.returnSuccessfulData(mainData, totalPages, totalItems);
		}
		catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
				
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
    }
	
	@RequestMapping(value = "resources/salesorders", method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> addSalesOrder(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestBody SalesOrderDTO _dto) throws Exception  
	{     
		Map<String,Object> retData;

		try {
//			Customer cust = null;
//			
//			TokenInfo ti = svcUtil.authenticate(_auth, UserCredential.TASK_CREATE_SALES_ORDERS, 
//					_dto.getCustomer() != null ? _dto.getCustomer() : -1);
//    		retData = svcUtil.getErrorFromToken(ti, false);
//    		if(retData != null)
//    			return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
//    		if(ti.access == ServiceUtil.ACCESS_LIMITED)
//    			_dto.setIdCustomer(ti.id);
//    		
            SalesOrder data = _dto.getData();
//            data.setIdCustomer(new Customer(_dto.getCustomer()));            
            
            data = salesSvc.addSalesOrder(data);
            retData = svcUtil.returnSuccessfulData(new SalesOrderDTO(data), 1, 1);
		}
		catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.CREATED);
    }
	
	
	
	@RequestMapping(value = "resources/salesorders", method = RequestMethod.PUT,
			consumes = "application/json", produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> editSalesOrder(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestBody SalesOrderDTO _dto) throws Exception  
	{     
		Map<String,Object> retData;

		try {		
			
//			TokenInfo ti = svcUtil.authenticate(_auth, UserCredential.TASK_UPDATE_SALES_ORDERS, 
//					_dto.getCustomer() != null ? _dto.getCustomer() : -1);
//    		retData = svcUtil.getErrorFromToken(ti, false);
//    		if(retData != null)
//    			return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
//    		if(ti.access == ServiceUtil.ACCESS_LIMITED)
//    			_dto.setIdCustomer(ti.id);
			
            SalesOrder data = _dto.getData();
//            System.out.println("canceled status: " + data.getCanceledStatus());
//            data.setIdCustomer(new Customer(_dto.getCustomer()));
            data = salesSvc.editSalesOrder(data);
            retData = svcUtil.returnSuccessfulData(new SalesOrderDTO(data), 1, 1);
		}
		catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "resources/salesorders", method = RequestMethod.DELETE,
			produces = "application/json"
	)
	public ResponseEntity<Map<String,Object>> deleteSalesOrder(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestParam("systemId") Long _systemId) throws Exception 
	{
		Map<String,Object> retData;

    	try {
//    		SalesOrder so = salesSvc.findSalesOrder(_systemId);
//    		Customer cust = null;
//    		if(so != null)
//    			cust = so.getCustomer();
//    		
//    		TokenInfo ti = svcUtil.authenticate(_auth, UserCredential.TASK_DELETE_SALES_ORDERS, cust != null ? cust.getSystemId() : -2);
//    		retData = svcUtil.getErrorFromToken(ti, true);
//    		if(retData != null)
//    			return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
    		
            SalesOrder data = salesSvc.deleteSalesOrder(_systemId);
            retData = svcUtil.returnSuccessfulData(new SalesOrderDTO(data), 1, 1);
    	}
    	catch(Exception e) {
    		retData = svcUtil.handleException(e);
    	}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
    }  
	
	@RequestMapping(value = "resources/cartdetails", method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> addCartDetail(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestBody CartDetailDTO _dto) throws Exception 
    {
		Map<String,Object> retData = null;
		try {
			
//			TokenInfo ti = svcUtil.authenticate(_auth, UserCredential.TASK_CREATE_CART_DETAILS, _dto.getIdCustomer() != null ? _dto.getIdCustomer() : -1);
//    		retData = svcUtil.getErrorFromToken(ti, false);
//    		if(retData != null)
//    			return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
//    		if(ti.access == ServiceUtil.ACCESS_LIMITED)
//    			_dto.setIdCustomer(ti.id);
    		
			CartDetail data = _dto.getData();
			data = salesSvc.addCartDetail(data);
			retData = svcUtil.returnSuccessfulData(new CartDetailDTO(data), 1, 1);
		}
		catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "resources/cartdetails", method = RequestMethod.PUT,
			consumes = "application/json", produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> editCartDetail(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestBody CartDetailDTO _dto) throws Exception 
    {
		Map<String,Object> retData = null;
		try {
			
//			TokenInfo ti = svcUtil.authenticate(_auth, UserCredential.TASK_UPDATE_CART_DETAILS, _dto.getCustomer() != null ? _dto.getCustomer() : -1);
//    		retData = svcUtil.getErrorFromToken(ti, false);
//    		if(retData != null)
//    			return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
//    		if(ti.access == ServiceUtil.ACCESS_LIMITED)
//    			_dto.setCustomer(ti.id);
    		
			CartDetail data = _dto.getData();
			data = salesSvc.editCartDetail(data);
			retData = svcUtil.returnSuccessfulData(new CartDetailDTO(data), 1, 1);
		}
		catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "resources/cartdetails", method = RequestMethod.DELETE,
			produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> deleteCartDetail(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestParam(value="customerId", required=false) Long _customerId, @RequestParam("itemId") Integer _itemId) throws Exception 
    {
		Map<String,Object> retData = null;
    	try {
    		
//    		TokenInfo ti = svcUtil.authenticate(_auth, UserCredential.TASK_DELETE_CART_DETAILS, _customerId != null ? _customerId : -1);
//    		retData = svcUtil.getErrorFromToken(ti, false);
//    		if(retData != null)
//    			return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
//    		if(ti.access == ServiceUtil.ACCESS_LIMITED) 
//    			_customerId = ti.id;
    		
//    		Customer cust = userCredSvc.findCustomer(_customerId);
    		CartDetail cd = salesSvc.deleteCartDetail(_customerId, _itemId);
            retData = svcUtil.returnSuccessfulData(new CartDetailDTO(cd), 1, 1);
    	}
    	catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
    }
    
    @RequestMapping(value = "resources/cartdetails", method = RequestMethod.GET,
			produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> getCartDetails(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestParam(value="customerId") Long _custId,
    		Pageable _page ) throws Exception 
    {
		Map<String,Object> retData = null;
		try {
//			TokenInfo ti = svcUtil.authenticate(_auth, UserCredential.TASK_VIEW_CART_DETAILS, _custId != null ? _custId : -1);
//    		retData = svcUtil.getErrorFromToken(ti, false);
//    		if(retData != null)
//    			return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
//    		if(ti.access == ServiceUtil.ACCESS_LIMITED)
//    			_custId = ti.id ;
    	
    		Page<CartDetail> pageCartDetail = null ;
    		int totalPages = 0;
			long totalItems = 0;
			
        	pageCartDetail = salesSvc.findCartDetailByIdCustomer(_custId, _page);
            
            if(pageCartDetail != null && pageCartDetail.getNumberOfElements() > 0) {
    			List<CartDetail> cartDetails = pageCartDetail.getContent();
    			List<CartDetailDTO> cartDetailsData = new LinkedList<>();
    			for(CartDetail cd : cartDetails) 
    				cartDetailsData.add(new CartDetailDTO(cd));
    			totalPages = pageCartDetail.getTotalPages();
    			totalItems = pageCartDetail.getTotalElements();
    			
    			retData = svcUtil.returnSuccessfulData(cartDetailsData, totalPages, totalItems);
    		}
		}
		catch(Exception e) {
			svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
    }
    
    @RequestMapping(value = "resources/checkout", method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> checkOut(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestBody SalesOrderDTO _so,
    		Pageable _page) throws Exception 
    {
		Map<String,Object> retData = null;
    	try {
    		
//    		TokenInfo ti = svcUtil.authenticate(_auth, UserCredential.TASK_CHECK_OUT, 
//    				_dto.getSystemId() != null ? _dto.getSystemId() : -1);
//    		retData = svcUtil.getErrorFromToken(ti, false);
//    		if(retData != null)
//    			return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
//    		if(ti.access == ServiceUtil.ACCESS_LIMITED)
//    			_dto.setSystemId(ti.id);
//    		
//    		Customer cust = null;
//			if(_dto.getSystemId() != null)
//				cust = userCredSvc.findCustomer(_dto.getSystemId());
			
			List<CartDetail> carts = salesSvc.findCartDetailByIdCustomer(_so.getIdCustomer());
			List<String> stockErrorMessages = new LinkedList<>();
			List<Integer> notEnoughtStocks = new LinkedList<>();
			List<Integer> idItems = new LinkedList<>();
			if(carts == null || carts.size() <= 0)
				return null;
			
			for(CartDetail cd : carts) {
				idItems.add(cd.getIdItem());
			}
			
			Map<Integer,Double> stocks = stockClient.getStocks(idItems);
			
			for(CartDetail cd : carts) {
				double ohQty = stocks.get(cd.getIdItem());
				System.out.println("OH QTY: " + ohQty + " name: " + cd.getIdItem());
				if(ohQty < cd.getQty()) 
					notEnoughtStocks.add(cd.getIdItem());  
			}
			
			if(notEnoughtStocks.size() > 0) {
				
				String errorMessage = "";
				List<Integer> itemIds = new LinkedList<>();
				
				for(Integer i : notEnoughtStocks) {
					stockErrorMessages.add(svcUtil.getErrorMessageFromCode(
							StandardConstant.ERROR_NOT_ENOUGH_STOCK_FOR_ITEM, new Object[] {i}));
					
//					itemIds.add(i.getSystemId());
				}
				for(String errMess : stockErrorMessages) {
					errorMessage += errMess + "\n";
				}
				
				errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
				retData = new HashMap<>();
				retData.put("status", "error");
				retData.put("errorCode", StandardConstant.ERROR_NOT_ENOUGH_STOCK);
				retData.put("errorMessage", errorMessage);
				retData.put("items", idItems);
			
				return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.BAD_REQUEST);
			}
			
    		SalesOrder so = salesSvc.checkOut(_so.getIdCustomer());
    		retData = svcUtil.returnSuccessfulData(new SalesOrderDTO(so), 1, 1);
    		return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.CREATED);
    	}
    	catch(Exception _e) {
    		retData = svcUtil.handleException(_e);
    		return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
}
