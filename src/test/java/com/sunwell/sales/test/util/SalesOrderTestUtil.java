package com.sunwell.sales.test.util;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.sunwell.sales.dto.SalesOrderDTO;
import com.sunwell.sales.dto.SalesOrderItemDTO;
import com.sunwell.sales.model.CartDetail;
import com.sunwell.sales.model.SalesOrder;
import com.sunwell.sales.model.SalesOrderItem;

public class SalesOrderTestUtil {
	
	public static SalesOrder getExampleSalesOrder() {
		SalesOrder so = new SalesOrder();
		List<SalesOrderItem> listSOItem = new LinkedList<>();
		
		SalesOrderItem soItem = new SalesOrderItem();
		soItem.setIdItem(1);
		soItem.setItemName("Roket");
		soItem.setQty(10.0);
		soItem.setHargaJual(20000.0);
		soItem.setSalesOrder(so);
		listSOItem.add(soItem);
		
		soItem = new SalesOrderItem();
		soItem.setIdItem(2);
		soItem.setItemName("Brolly");
		soItem.setQty(9.0);
		soItem.setHargaJual(3000.0);
		soItem.setSalesOrder(so);
		listSOItem.add(soItem);
		
		so.setSystemId(1l);
		so.setIdCustomer(1l);
		so.setItems(listSOItem);
		
		return so;
	}
	
	public static CartDetail getExampleCartDetail() {
		CartDetail cd = new CartDetail();
		cd.setIdCustomer(1L);
		cd.setIdItem(1);
		cd.setItemName("Roket");
		cd.setQty(10);
		cd.setReqNote("Req Note");
		
		return cd;
	}
	
	public static List<CartDetail> getExampleCartDetails() {
		List<CartDetail> cartDetails = new LinkedList<>();
		CartDetail cd = new CartDetail();
		cd.setIdCustomer(1L);
		cd.setIdItem(1);
		cd.setItemName("Roket");
		cd.setQty(10);
		cd.setReqNote("Req Note");
		cartDetails.add(cd);
		
		cd = new CartDetail();
		cd.setIdCustomer(2L);
		cd.setIdItem(2);
		cd.setItemName("Brolly");
		cd.setQty(11);
		cd.setReqNote("Req Note 2");
		cartDetails.add(cd);
		
		cd = new CartDetail();
		cd.setIdCustomer(3L);
		cd.setIdItem(3);
		cd.setItemName("Comb");
		cd.setQty(22);
		cd.setReqNote("Req Note 3");
		cartDetails.add(cd);
		
		return cartDetails;
	}
	
	public static List<SalesOrder> getExampleSalesOrders() {
		List<SalesOrder> listSO = new LinkedList<>();
		List<SalesOrderItem> listSOItem = new LinkedList<>();
		
		SalesOrder so = new SalesOrder();
		SalesOrderItem soItem = new SalesOrderItem();
		soItem.setIdItem(1);
		soItem.setItemName("Roket");
		soItem.setQty(10.0);
		soItem.setHargaJual(20000.0);
		soItem.setSalesOrder(so);
		listSOItem.add(soItem);
		
		soItem = new SalesOrderItem();
		soItem.setIdItem(2);
		soItem.setItemName("Brolly");
		soItem.setQty(9.0);
		soItem.setHargaJual(3000.0);
		soItem.setSalesOrder(so);
		listSOItem.add(soItem);
		
		so.setSystemId(2l);
		so.setIdCustomer(1l);
		so.setItems(listSOItem);
		listSO.add(so);
		
		so = new SalesOrder();
		soItem = new SalesOrderItem();
		soItem.setIdItem(1);
		soItem.setItemName("Roket");
		soItem.setQty(20.0);
		soItem.setHargaJual(12000.0);
		soItem.setSalesOrder(so);
		listSOItem.add(soItem);
		
		soItem = new SalesOrderItem();
		soItem.setIdItem(2);
		soItem.setItemName("Brolly");
		soItem.setQty(10.0);
		soItem.setHargaJual(2000.0);
		soItem.setSalesOrder(so);
		listSOItem.add(soItem);
		
		so.setSystemId(3l);
		so.setIdCustomer(2l);
		so.setItems(listSOItem);
		listSO.add(so);
		
		so = new SalesOrder();
		soItem = new SalesOrderItem();
		soItem.setIdItem(1);
		soItem.setItemName("Roket");
		soItem.setQty(110.0);
		soItem.setHargaJual(4000.0);
		soItem.setSalesOrder(so);
		listSOItem.add(soItem);
		
		soItem = new SalesOrderItem();
		soItem.setIdItem(3);
		soItem.setItemName("Comb");
		soItem.setQty(3.0);
		soItem.setHargaJual(3000.0);
		soItem.setSalesOrder(so);
		listSOItem.add(soItem);
		
		so.setIdCustomer(2l);
		so.setItems(listSOItem);
		listSO.add(so);
		
		return listSO;
	}
	
	public static SalesOrderDTO getExampleSalesOrderDTO() {
		SalesOrderDTO soDTO = new SalesOrderDTO();
		List<SalesOrderItemDTO> listItems = new LinkedList<>();
		
		SalesOrderItemDTO item = new SalesOrderItemDTO();
		item.setIdItem(1);
		item.setItemName("Roket");
		item.setHargaJual(1000.0);
		item.setQty(20.0);
		listItems.add(item);
		
		item = new SalesOrderItemDTO();
		item.setIdItem(2);
		item.setItemName("Brolly");
		item.setHargaJual(2000.0);
		item.setQty(10.0);
		listItems.add(item);
		
		item = new SalesOrderItemDTO();
		item.setIdItem(3);
		item.setItemName("Comb");
		item.setHargaJual(200.0);
		item.setQty(110.0);
		listItems.add(item);
		
		soDTO.setSystemId(1l);
		soDTO.setIdCustomer(1l);
		soDTO.setCanceledStatus(0);
		soDTO.setCompanyName("company");
		soDTO.setDeliveryStatus(0);
		soDTO.setDiscount(0.0);
		soDTO.setDiscountMemo("Discount Memo");
		soDTO.setIssueDatetime(Calendar.getInstance());
		soDTO.setItems(listItems);
		soDTO.setMemo("Memo");
		soDTO.setMiscCharges(0.0);
		soDTO.setMiscChargesMemo("Misc. Charges Memo");
		soDTO.setPaymentStatus(0);
		soDTO.setPromoCodeUsed(null);
		soDTO.setShippingLine(0);
		soDTO.setVAT(0.0);
		soDTO.setVATInclusive(true);
		
		return soDTO;
	}
	
	public static List<SalesOrderDTO> getExampleSalesOrderDTOs() {
		List<SalesOrderDTO> listSoDTO = new LinkedList<>();
		List<SalesOrderItemDTO> listItems = new LinkedList<>();
		
		SalesOrderDTO soDTO = new SalesOrderDTO();
		SalesOrderItemDTO item = new SalesOrderItemDTO();
		item.setIdItem(1);
		item.setItemName("Roket");
		item.setHargaJual(1000.0);
		item.setQty(20.0);
		listItems.add(item);
		
		item = new SalesOrderItemDTO();
		item.setIdItem(2);
		item.setItemName("Brolly");
		item.setHargaJual(2000.0);
		item.setQty(10.0);
		listItems.add(item);
		
		item = new SalesOrderItemDTO();
		item.setIdItem(3);
		item.setItemName("Comb");
		item.setHargaJual(200.0);
		item.setQty(110.0);
		listItems.add(item);
		
		soDTO.setSystemId(1l);
		soDTO.setIdCustomer(1l);
		soDTO.setCanceledStatus(0);
		soDTO.setCompanyName("company");
		soDTO.setDeliveryStatus(0);
		soDTO.setDiscount(0.0);
		soDTO.setDiscountMemo("Discount Memo");
		soDTO.setIssueDatetime(Calendar.getInstance());
		soDTO.setItems(listItems);
		soDTO.setMemo("Memo");
		soDTO.setMiscCharges(0.0);
		soDTO.setMiscChargesMemo("Misc. Charges Memo");
		soDTO.setPaymentStatus(0);
		soDTO.setPromoCodeUsed(null);
		soDTO.setShippingLine(0);
		soDTO.setVAT(0.0);
		soDTO.setVATInclusive(true);
		soDTO.setItems(listItems);
		listSoDTO.add(soDTO);
		
		soDTO = new SalesOrderDTO();
		item = new SalesOrderItemDTO();
		item.setIdItem(1);
		item.setItemName("Roket");
		item.setHargaJual(100.0);
		item.setQty(200.0);
		listItems.add(item);
		
		item = new SalesOrderItemDTO();
		item.setIdItem(2);
		item.setItemName("Brolly");
		item.setHargaJual(300.0);
		item.setQty(130.0);
		listItems.add(item);
		
		item = new SalesOrderItemDTO();
		item.setIdItem(3);
		item.setItemName("Comb");
		item.setHargaJual(400.0);
		item.setQty(15.0);
		listItems.add(item);
		
		soDTO.setSystemId(1l);
		soDTO.setIdCustomer(1l);
		soDTO.setCanceledStatus(0);
		soDTO.setCompanyName("company2");
		soDTO.setDeliveryStatus(0);
		soDTO.setDiscount(0.0);
		soDTO.setDiscountMemo("Discount Memo2");
		soDTO.setIssueDatetime(Calendar.getInstance());
		soDTO.setItems(listItems);
		soDTO.setMemo("Memo2");
		soDTO.setMiscCharges(0.0);
		soDTO.setMiscChargesMemo("Misc. Charges Memo2");
		soDTO.setPaymentStatus(0);
		soDTO.setPromoCodeUsed(null);
		soDTO.setShippingLine(0);
		soDTO.setVAT(0.0);
		soDTO.setVATInclusive(true);
		soDTO.setItems(listItems);
		listSoDTO.add(soDTO);
		
		soDTO = new SalesOrderDTO();
		item = new SalesOrderItemDTO();
		item.setIdItem(1);
		item.setItemName("Roket");
		item.setHargaJual(100.0);
		item.setQty(200.0);
		listItems.add(item);
		
		item = new SalesOrderItemDTO();
		item.setIdItem(2);
		item.setItemName("Brolly");
		item.setHargaJual(300.0);
		item.setQty(130.0);
		listItems.add(item);
		
		item = new SalesOrderItemDTO();
		item.setIdItem(3);
		item.setItemName("Comb");
		item.setHargaJual(400.0);
		item.setQty(15.0);
		listItems.add(item);
		
		soDTO.setSystemId(3l);
		soDTO.setIdCustomer(3l);
		soDTO.setCanceledStatus(0);
		soDTO.setCompanyName("company3");
		soDTO.setDeliveryStatus(0);
		soDTO.setDiscount(0.0);
		soDTO.setDiscountMemo("Discount Memo3");
		soDTO.setIssueDatetime(Calendar.getInstance());
		soDTO.setItems(listItems);
		soDTO.setMemo("Memo3");
		soDTO.setMiscCharges(0.0);
		soDTO.setMiscChargesMemo("Misc. Charges Memo3");
		soDTO.setPaymentStatus(0);
		soDTO.setPromoCodeUsed(null);
		soDTO.setShippingLine(0);
		soDTO.setVAT(0.0);
		soDTO.setVATInclusive(true);
		soDTO.setItems(listItems);
		listSoDTO.add(soDTO);
		
		return listSoDTO;
	}
	
	public static SalesOrderDTO getInexistentSalesOrderDTO() {
		SalesOrderDTO so = getExampleSalesOrderDTO();
		so.setSystemId(-1l);
		return so;
	}
	
	public static SalesOrderDTO getSalesOrderDTO() {
		SalesOrderDTO so = getExampleSalesOrderDTO();
		so.setSystemId(null);
		return so;
	}

}
