package com.petMart.product.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.common.DbCommand;
import com.petMart.product.serviceImpl.ProductServiceImpl;

public class AddCart implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 클릭하면 카트 테이블에 한 건 추가( 회원id, 상품정보, 수량은 1 추가 )
		// 그 정보를 찾아와서 세션에 띄워야함
		
		String id = request.getParameter("id");
		String itemCode = request.getParameter("itemCode");
		int qty = 1;
		
		ProductServiceImpl service = new ProductServiceImpl();
		service.addCart(id, itemCode, qty);
		
		return "/productList.do";
	}

}
