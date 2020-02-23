/*
 * SalesOrder.java
 *
 * Created on September 4, 2007, 5:28 PM
 */
package com.sunwell.sales.dto;

import java.io.Serializable;








import java.sql.*;
import java.util.*;
import javax.validation.Validation;
import javax.validation.constraints.NotNull;

import com.sunwell.sales.model.SalesOrder;
import com.sunwell.sales.model.SalesOrderItem;

public class SalesOrderDTO extends StandardDTO
{
    private Long systemId;
    private Long idCustomer;
    private List<SalesOrderItemDTO> items ;
    private Calendar issueDate = Calendar.getInstance();
    private Integer deliveryStatus ;
    private Double miscCharge = 0.0;
    private String miscChargeMemo = "";
    private String memo = "";
    private Double vat = 0.0;
    private Boolean vatInclusive = false;
    private Integer canceledStatus = 0;
    private Integer paymentStatus ;
    private Integer shippingLine ;
    private Integer promoCodeUsed ;
    private Double discount;
    private String discountMemo;
        
    /** Creates a new instance of SalesOrder */
    public SalesOrderDTO ()
    {
    }
    
    public SalesOrderDTO(SalesOrder _so) {
    	setData(_so);
    }
    
    public void setData(SalesOrder _so) {
    	systemId = _so.getSystemId();
    	issueDate = _so.getIssueDate();
    	deliveryStatus = _so.getDeliveryStatus();
    	miscCharge = _so.getMiscCharges();
    	miscChargeMemo = _so.getMiscChargesMemo();
    	memo = _so.getMemo();
    	vat = _so.getVAT();
    	vatInclusive = _so.isVATInclusive();
    	canceledStatus = _so.getCanceledStatus();
    	paymentStatus = _so.getPaymentStatus();
    	shippingLine = _so.getShippingLine();
    	promoCodeUsed = _so.getPromoCodeUsed();
    	discount = _so.getDiscount();
    	discountMemo = _so.getDiscountMemo();
    	if(_so.getIdCustomer() != null)
    		idCustomer = _so.getIdCustomer();
    	if(_so.getItems() != null && _so.getItems().size() > 0) {
    		items = new LinkedList<>();
    		for(SalesOrderItem sItem: _so.getItems()) {
    			items.add(new SalesOrderItemDTO(sItem));
    		}
    	}
    }
    
    public SalesOrder getData() {
    	SalesOrder so = new SalesOrder();    	
    	so.setIssueDate(issueDate);
    	so.setMiscChargesMemo(miscChargeMemo);
    	so.setMemo(memo);
    	so.setDiscountMemo(discountMemo);
    	so.setShippingLine(shippingLine);
    	
    	if(systemId != null)
    		so.setSystemId(systemId);
    	if(idCustomer != null)
    		so.setIdCustomer(idCustomer);
    	if(miscCharge != null)
    		so.setMiscCharges(miscCharge);
    	if(deliveryStatus != null)
    		so.setDeliveryStatus(deliveryStatus);
    	if(paymentStatus != null)
    		so.setPaymentStatus(paymentStatus);
    	if(shippingLine != null)
    		so.setShippingLine(shippingLine);
    	if(promoCodeUsed != null)
    		so.setPromoCodeUsed(promoCodeUsed);
    	if(discount != null)
    		so.setDiscount(discount);
    	if(vat != null)
    		so.setVAT(vat);
    	if(vatInclusive != null)
    		so.setVATInclusive(vatInclusive);
    	if(canceledStatus != null)
    		so.setCanceledStatus(canceledStatus);
    	if(idCustomer != null)
    		so.setIdCustomer(idCustomer);
    	if(items != null && items.size() > 0) {
    		List<SalesOrderItem> listItem = new LinkedList<>();
    		for(SalesOrderItemDTO sItem : items) {
    			SalesOrderItem data = sItem.getData();
    			data.setSalesOrder(so);
    			listItem.add(data);
    		}
    		so.setItems(listItem);
    	}
        System.out.println("canceled status get data: " + so.getCanceledStatus());

    	return so;
    }

    
    public Long getSystemId () { return systemId; }
    
    public void setSystemId (Long _id)
    {
        this.systemId = _id;
    }
    
    public Calendar getIssueDate () { return issueDate; }

    public void setIssueDatetime (Calendar _date)
    {
        this.issueDate = _date;
    }

    public Integer getDeliveryStatus () { return deliveryStatus; }

    public void setDeliveryStatus (Integer _delivered)
    {
        this.deliveryStatus = _delivered;
    }

    public Double getMiscCharges () { return miscCharge; }

    public void setMiscCharges (Double m_misc_charges)
    {
        this.miscCharge = m_misc_charges;
    }

    public String getMiscChargesMemo () { return miscChargeMemo; }

    public void setMiscChargesMemo (String _misc_charges_memo)
    {
        this.miscChargeMemo = (_misc_charges_memo != null) ? _misc_charges_memo : "";
    }

    public String getMemo () { return memo; }

    public void setMemo (String _memo)
    {
        this.memo = (_memo != null) ? _memo : "";
    }

    public Double getVAT () { return vat; }

    public void setVAT (Double _vat)
    {
        this.vat = _vat;
    }

    public Boolean isVATInclusive () { return vatInclusive; }

    public void setVATInclusive (Boolean m_vat_inclusive)
    {
        this.vatInclusive = m_vat_inclusive;
    }

    public Integer getCanceledStatus () { return canceledStatus; }

    public void setCanceledStatus (Integer _vs)
    {
        canceledStatus = _vs;
    }
    
    public Long getIdCustomer () { return idCustomer; }

    public void setIdCustomer (Long _cust)
    {
        this.idCustomer = _cust;
    }
    
    
    public List<SalesOrderItemDTO> getItems () 
    { 
        return items; 
    }
    
    public void setItems (List<SalesOrderItemDTO> _items)
    {
        items = _items;
    }

	public Integer getPaymentStatus ()
	{
		return paymentStatus;
	}

	public void setPaymentStatus (Integer _paymentStatus)
	{
		paymentStatus = _paymentStatus;
	}

	public Integer getShippingLine ()
	{
		return shippingLine;
	}

	public void setShippingLine (Integer _shippingLine)
	{
		shippingLine = _shippingLine;
	}

	public Integer getPromoCodeUsed ()
	{
		return promoCodeUsed;
	}

	public void setPromoCodeUsed (Integer _promoCodeUsed)
	{
		promoCodeUsed = _promoCodeUsed;
	}

	public Double getDiscount ()
	{
		return discount;
	}

	public void setDiscount (Double _discount)
	{
		discount = _discount;
	}

	public String getDiscountMemo ()
	{
		return discountMemo;
	}

	public void setDiscountMemo (String _discountMemo)
	{
		discountMemo = _discountMemo;
	}
    
}
