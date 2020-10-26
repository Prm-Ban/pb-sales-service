package com.sunwell.sales.dto;

import com.sunwell.sales.model.CartDetail;

public class CartDetailDTO extends StandardDTO
{
	private Long idCustomer;
	private Integer idItem;
	private String itemName;
	private Double qty;
//	private Double discount;
	private String reqNote;
	
	public CartDetailDTO() {
		
	}
	
	public CartDetailDTO(CartDetail _cd) {
		setData(_cd);
	}
	
	public void setData(CartDetail _cd) {
		idCustomer = _cd.getIdCustomer() ;
		idItem = _cd.getIdItem();
		qty = _cd.getQty();
		reqNote = _cd.getReqNote();
		itemName = _cd.getItemName();
	}
	
	public CartDetail getData() {
		CartDetail cd = new CartDetail();
		cd.setReqNote(reqNote);
		if(idCustomer != null)
			cd.setIdCustomer(idCustomer);
		if(idItem != null)
			cd.setIdItem(idItem);
		if(qty != null)
			cd.setQty(qty);;
		if(itemName != null)
			cd.setItemName(itemName);
//		if(discount != null)
//			cd.setDiscount(discount);
		return cd;
	}
	
	public Long getCustomer ()
	{
		return idCustomer;
	}
	public void setCustomer (Long _customer)
	{
		idCustomer = _customer;
	}
	
	public Integer getIdItem ()
	{
		return idItem;
	}
	public void setIdItem (Integer _item)
	{
		idItem = _item;
	}
	public Double getQty ()
	{
		return qty;
	}
	public void setQty (Double _qty)
	{
		qty = _qty;
	}
	public String getReqNote ()
	{
		return reqNote;
	}
	public void setReqNote (String _memo)
	{
		reqNote = _memo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

//	public Double getDiscount ()
//	{
//		return discount;
//	}
//
//	public void setDiscount (Double _discount)
//	{
//		discount = _discount;
//	}

}
