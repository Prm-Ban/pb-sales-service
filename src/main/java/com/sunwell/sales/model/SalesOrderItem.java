/*
 * SOLines.java
 *
 * Created on September 22, 2007, 7:34 PM
 */
package com.sunwell.sales.model;

import java.sql.*;


import java.util.Arrays;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table( name = "salesorderitem" )
@IdClass(SalesOrderItemPK.class)
public class SalesOrderItem 
{
    @NotNull(message="{error_no_so}")
    @Id
    @ManyToOne
    @JoinColumn( name ="parent")
    private SalesOrder parent;
    
    @NotNull(message="{error_no_item_id}")
    @Id
    @Column(name="id_item")
    private Integer idItem ;
    
    @NotNull(message="{error_no_item_name}")
    @Column(name="item_name")
    private String itemName;
    
    @Column( name = "qty" )
    private Double qty = 0.0;
    
    @Column( name = "disc" )
    private Double discount = 0.0;
    
    @Column( name = "harga_jual" )
    private Double hargaJual = 0.0;
    
    public SalesOrderItem() {
    	
    }
    
    public SalesOrderItem(SalesOrder _so, Integer _idItem, Double _qty) {
    	parent = _so;
    	idItem = _idItem;
    	qty = _qty;
    }
    
    public SalesOrder getParent () { return parent; }

    public void setSalesOrder (SalesOrder _parent)
    {
        this.parent = _parent;
    }
        
    public Integer getIdItem () { return idItem; }

    public void setIdItem (Integer _idItem)
    {
        this.idItem = _idItem;
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
