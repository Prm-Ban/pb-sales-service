/*
 * SOLines.java
 *
 * Created on September 22, 2007, 7:34 PM
 */
package com.sunwell.sales.dto;

import java.sql.*;

import java.util.Arrays;
import java.util.List;

import com.sunwell.sales.model.SalesOrderItem;

public class SalesOrderItemDTO extends StandardDTO
{
    private Integer idItem;
	private String itemName ;
    private Double qty ;
    private Double discount ;
    private Double hargaJual ;
//    private List<AppliedSOItemDiscounts> discounts ;
    
    
    /** Creates a new instance of SOItem */
    public SalesOrderItemDTO ()
    {
    }
    
    public SalesOrderItemDTO (SalesOrderItem _item)
    {
    	setData(_item);
    }
    
    public void setData(SalesOrderItem _item) {
    	if(_item.getIdItem() != null)
    		idItem = _item.getIdItem();
    	
    	if(_item.getIdItem() != null)
    		itemName = _item.getItemName();
    	
    	qty = _item.getQty();
    	hargaJual = _item.getHargaJual();
    	discount = _item.getDiscount();
    }
    
    public SalesOrderItem getData() {
    	SalesOrderItem sItem = new SalesOrderItem();
//    	sItem.setReqNote(reqNote);
    	if(idItem != null)
    		sItem.setIdItem(idItem);
    	if(itemName != null)
    		sItem.setItemName(itemName);
    	if(qty != null)
    		sItem.setQty(qty);
    	if(hargaJual != null)
    		sItem.setHargaJual(hargaJual);
    	if(discount != null)
    		sItem.setDiscount(discount);
    	return sItem;
    }
         
    public String getItemName () { return itemName; }

    public void setItemName (String _item)
    {
        this.itemName = _item;
    }

    public Double getQty () { return qty; }

    /**
     * Penggunaan method ini akan meng-override nilai qty yang sebelumnya diset
     * melalui {@link #setQtyInSalesMetric(double, sunwell.xrp.inventory.Metrics)}.
     * 
     * @param m_qty 
     */
    public void setQty (Double _qty)
    {
        this.qty = _qty;
    }
     
    public Double getHargaJual () { return hargaJual; }

    public void setHargaJual (Double m_harga_jual)
    {
        this.hargaJual = m_harga_jual;
    }

	public Double getDiscount ()
	{
		return discount;
	}

	public void setDiscount (Double _discount)
	{
		discount = _discount;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

}
