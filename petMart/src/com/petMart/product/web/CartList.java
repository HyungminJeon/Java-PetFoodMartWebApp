package com.petMart.product.web;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petMart.common.DbCommand;
import com.petMart.product.serviceImpl.ProductServiceImpl;
import com.petMart.product.vo.CartVO;

public class CartList implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 세션, id 획득
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		 
		if(id == null) {
			Cookie[] cookies = request.getCookies();
			id = cookies[0].getValue();
		}
		
		// 장바구니 목록
		ProductServiceImpl service = new ProductServiceImpl();
		List<CartVO> list = service.guestCartList(id);
		
		request.setAttribute("cartList", list);
		
		return "product/cartList.tiles";
	}

}