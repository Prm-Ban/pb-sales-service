/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ProdCategoryListDTO.java
 *
 * Created on Oct 18, 2017, 3:27:27 PM
 */

package com.sunwell.sales.dto;

import java.util.LinkedList;




import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.sunwell.sales.model.SalesOrder;


/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesOrderListDTO extends StandardDTO
{
    
    private List<SalesOrderDTO> listSalesOrder;
    
    public SalesOrderListDTO() {
        
    }
    
    public SalesOrderListDTO(List<SalesOrder> _orders) {
        setData (_orders);
    }
    
    public void setData(List<SalesOrder> _salesOrders) {
        if(_salesOrders != null && _salesOrders.size () > 0) {
        	listSalesOrder = new LinkedList<> ();
            for (SalesOrder so : _salesOrders) {
                listSalesOrder.add (new SalesOrderDTO (so));
            }
        }
        else
            listSalesOrder = null;
    }

    /**
     * @return the listUser
     */
    public List<SalesOrderDTO> getListSalesOrder ()
    {
        return listSalesOrder;
    }

    /**
     * @param listCategory the listCategory to set
     */
    public void setListSalesInvoice (List<SalesOrderDTO> _list)
    {
        this.listSalesOrder = _list;
    }
}
