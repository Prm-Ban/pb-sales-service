package com.sunwell.sales.repository;

import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sunwell.sales.model.CartDetail;
import com.sunwell.sales.model.CartDetailPK;


public interface CartDetailRepo extends JpaRepository<CartDetail, CartDetailPK>, JpaSpecificationExecutor<CartDetail> {
	Page<CartDetail> findByIdCustomer(long _ust, Pageable _page);
	List<CartDetail> findByIdCustomer(long _ust);
//	Page<CartDetail> findByCustomer_SystemId(Long _id, Pageable _page);
}
