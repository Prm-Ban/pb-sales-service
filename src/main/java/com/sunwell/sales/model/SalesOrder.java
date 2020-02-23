/*
 * SalesOrder.java
 *
 * Created on September 4, 2007, 5:28 PM
 */
package com.sunwell.sales.model;

import java.io.Serializable;




import java.sql.*;
import java.util.*;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table (name = "salesorder")
public class SalesOrder 
{
    /**
     * Flag Delivery-Status dari Sales Order ini.
     */
    public static final int DS_UNDELIVERED = 0;
    public static final int DS_FULLY_DELIVERED = 1;
    public static final int DS_PARTIALLY_DELIVERED = 2;
    
    /**
     * Flag status reservasi
     */
    public static final int RESERVATION_PARTIAL_OR_NONE = 0;
    public static final int RESERVATION_FULL = 1;
    
    /**
     * Flag Validity-Status dari Sales Order ini.
     */
    public static final int VS_OPEN = 0;
    public static final int VS_CLOSED = 1;
    public static final int VS_CANCELED = 2;
    
    /**
     * Flag status payment dari Sales Order ini.
     */
    public static final int PAYMENT_PAID = 0;
    public static final int PAYMENT_UNPAID = 1;
    
    /** PRIMARY KEY ; auto-increment */
    @Id
    @Column( name = "systemid")
    @SequenceGenerator (name = "salesorder_id_so_seq", sequenceName = "salesorder_id_so_seq", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "salesorder_id_so_seq")
    private Long systemId;
    
    @NotNull(message="{error_no_customer}")
    @Column(name = "id_customer")
    private Long idCustomer;
    
    @NotNull(message="{error_no_issue_date}")
    @Column( name = "issuedate" )
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)    
    private Calendar issueDate = Calendar.getInstance();
        
    @OneToMany( mappedBy = "parent", cascade=CascadeType.ALL, fetch=FetchType.EAGER )
    @Fetch (FetchMode.SELECT)
    private List<SalesOrderItem> items ;    
    
    @Column( name = "status_delivery")
    private Integer deliveryStatus = DS_UNDELIVERED;
    
    @Column( name = "misc_charge")
    private Double miscCharge = 0.0;
    
    @Column( name = "misc_charge_memo" )
    private String miscChargesMemo = "";
    
    @Column( name = "memo" )
    private String memo = "";
    
    @Column( name = "vat" )
    private Double vat = 0.0;
    
    @Column( name = "vat_inclusive" )
    private Boolean vatInclusive = false;
    
    @NotNull
    @Column( name = "status_canceled" )
    private Integer canceledStatus = VS_OPEN;
    
    @Column( name = "status_payment" )
    private Integer paymentStatus = PAYMENT_UNPAID;
    
    @Column( name = "shipping_line" )
    private Integer shippingLine ;
    
    @Column( name = "promocode_used" )
    private Integer promoCodeUsed ;
    
    @Column(name="disc")
    private double discount;
    
    @Column(name="disc_memo")
    private String discountMemo;
        
    /** Creates a new instance of SalesOrder */
    public SalesOrder ()
    {
    }

    public SalesOrder (Long _idSO)
    {
//       resetAttributes ();
       systemId = _idSO;
    }
    
    public Long getSystemId () { return systemId; }
    
    public void setSystemId (Long _id)
    {
        this.systemId = _id;
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

    public String getMiscChargesMemo () { return miscChargesMemo; }

    public void setMiscChargesMemo (String _misc_charges_memo)
    {
        this.miscChargesMemo = (_misc_charges_memo != null) ? _misc_charges_memo : "";
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

    /**
     * @param _vs harus bernilai salah satu dari konstanta {@link #VS_OPEN},
     *  {@link #VS_CLOSED}, atau {@link #VS_CANCELED}. Jika nilai _vs bukan salah
     *  satu dari ketiga konstanta tersebut, maka nilai _vs akan di-override dgn
     *  nilai {@link #VS_CANCELED}.
     */
    public void setCanceledStatus (Integer _vs)
    {
        if (_vs != VS_OPEN && _vs != VS_CLOSED && _vs != VS_CANCELED)
            this.canceledStatus = VS_CANCELED;
        else
            this.canceledStatus = _vs;
    }
                
    public Long getIdCustomer () { return idCustomer; }

    public void setIdCustomer (Long  idCustomer)
    {
        this.idCustomer = idCustomer;
    }
    
    public List<SalesOrderItem> getItems () 
    { 
        return items; 
    }
    
    public void setItems (List<SalesOrderItem> _items)
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

	public Calendar getIssueDate ()
	{
		return issueDate;
	}

	public void setIssueDate (Calendar _issueDate)
	{
		issueDate = _issueDate;
	}

	@Override
	public boolean equals(Object _o) {
		if(! (_o instanceof SalesOrder)) 
			return false;
		
		SalesOrder so = (SalesOrder) _o;
		return so.getSystemId() == systemId ;
	}
	  
}
