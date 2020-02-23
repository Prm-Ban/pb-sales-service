/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ItemPOPK.java
 *
 * Created on Apr 17, 2015, 11:50:33 AM
 */

package com.sunwell.sales.model;

import java.io.Serializable;

/**
 *
 * @author Benny
 */

public class CartDetailPK implements Serializable
{
    long idCustomer ;
    int idItem ;
    
    public CartDetailPK() {
    	
    }
    
    public CartDetailPK(long _cust, int _item) {
    	idCustomer = _cust;
    	idItem = _item;
    }
    
    public Integer getIdItem ()
    {
        return idItem;
    }

    public void setIdItem (int _itemid)
    {
        this.idItem = _itemid;
    }

    public long getIdCustomer ()
    {
        return idCustomer;
    }

    public void setIdCustomer (long _si)
    {
        this.idCustomer = _si;
    }
    
    @Override
    public int hashCode ()
    {
        int hash = 0;
//        hash += salesInvoice != null ? salesInvoice.hashCode() : 0;
//        hash += item != null ? item.hashCode() : 0 ;
        hash += idCustomer ;
        hash += idItem ;
//        long c = customer != null ? customer.getSystemId() : 0;
//		int i = item != null ? item.getSystemId() : 0;
//		hash += customer != null ? customer.getSystemId() : 0;
//		hash += item != null ? item.getSystemId() : 0;
		System.out.println("HASH CODE c: " + idCustomer + " i: " + idItem + " hash" + hash);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CartDetailPK)) {
            return false;
        }
        CartDetailPK other = (CartDetailPK) object;
        if(idCustomer != other.idCustomer) {
        	System.out.println("RETURN FALSE, customer = " + idCustomer);
        	return false;
        }
        
        if(idItem != other.idItem) {
        	System.out.println("RETURN TRUE,  item = " + idItem );
        	return false;
        }
        
        System.out.println("RETURN TRUE, customer = " + idCustomer + " item = " + idItem );
        return true;
    }
}