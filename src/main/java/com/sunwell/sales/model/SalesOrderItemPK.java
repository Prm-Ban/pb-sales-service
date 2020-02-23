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

public class SalesOrderItemPK implements Serializable
{
    private Long parent ;
    private Integer idItem ;
    
    public Long getParent ()
    {
        return parent;
    }

    public void setParent (Long _so)
    {
        this.parent = _so;
    } 
    
    public Integer getIdItem ()
    {
        return idItem;
    }

    public void setIdItem (Integer _itemid)
    {
        this.idItem = _itemid;
    }

       
    
    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += parent ;
        hash += idItem ;
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
    	if(object == null)
    		return false;
        if (!(object instanceof SalesOrderItemPK)) {
            return false;
        }
        SalesOrderItemPK other = (SalesOrderItemPK) object;
        if(parent.equals(other.parent))
        	return false;
        
        if(idItem.equals(other.idItem))
        	return false;
       
        return true;
    }
}