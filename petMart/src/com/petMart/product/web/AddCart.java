package com.petMart.product.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petMart.common.DbCommand;
import com.petMart.product.serviceImpl.ProductServiceImpl;

public class AddCart implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 클릭하면 카트 테이블에 한 건 추가( 회원id, 상품정보, 수량은 1 추가 )
		// 그 정보를 찾아와서 세션에 띄워야함
		
		HttpSession session = request.getSession();
		
		
		String id = request.getParameter("id");
		String guestId = request.getParameter("guestId");
		String itemCode = request.getParameter("itemCode");
		int qty = 1;
		
		ProductServiceImpl service = new ProductServiceImpl();
		
		if(guestId != null) {
			service.addGuestCart(guestId, itemCode, qty);
		}
		
		if(id != null) {
			service.addCart(id, itemCode, qty);
		}
		
		session.setAttribute("guestId", guestId);
		
		return "/getCartCount.do";
		
	}

}
