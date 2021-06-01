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
		
		ProductServiceImpl service = new ProductServiceImpl();
		List<CartVO> list;
		
		// 만일 아이디가 없으면 쿠키를 사용
		if(id == null) {
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("guestBasketId")) {
					id = cookie.getValue();
					break;
				}
			}
			list = service.guestCartList(id);
		} else {
			list = service.cartList(id);
		}
		
		request.setAttribute("cartList", list);
		
		return "product/cartList.tiles";
	}

}