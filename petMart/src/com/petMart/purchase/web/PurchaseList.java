package com.petMart.purchase.web;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petMart.common.DbCommand;
import com.petMart.product.vo.CartVO;
import com.petMart.purchase.serviceImpl.PurchaseServiceImpl;
import com.petMart.purchase.vo.PurchaseVO;

public class PurchaseList implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 세션, id 획득
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		PurchaseServiceImpl service = new PurchaseServiceImpl();
		
		//장바구니 삭제시 장바구니를 게스트와 유저로 구별하기 위해 새로 만든다
		List<PurchaseVO> list =null;
		
		
			list = service.purchaseList(id);
		
		request.setAttribute("userCartList", list);
		request.setAttribute("id", id);
		
		
		return "purchase/purchaseList.tiles";
	}

}