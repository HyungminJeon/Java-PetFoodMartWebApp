package com.petMart.purchase.service;

import java.util.List;

import com.petMart.product.vo.ProductVO;
import com.petMart.purchase.vo.PurchaseVO;


public interface PurchaseService {
	List<PurchaseVO> purchaseSelectList();
	PurchaseVO purchaseSelect(PurchaseVO vo);
	int updatePurchase(PurchaseVO vo);
	int deletePurchase(PurchaseVO vo);
	int insertPurchase(PurchaseVO vo);
}
