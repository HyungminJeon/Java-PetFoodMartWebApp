package com.petMart.product.web;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petMart.common.DbCommand;
import com.petMart.product.serviceImpl.ProductServiceImpl;

public class GetCartCount implements DbCommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		
		ProductServiceImpl service = new ProductServiceImpl();
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		int cnt;
	
		
		// 세션 아이디가 없으면 쿠키 사용
		if(id == null) {
			Cookie[] cookies = req.getCookies();
			for(Cookie cookie : cookies)
				if(cookie.getName().equals("guestBasketId")) {
					id = cookie.getValue();
				}
			cnt = service.getCountGuestCart(id);
		} else {
			cnt = service.getCountCart(id);
		}
	
		System.out.println(id);
		session.setAttribute("cartCnt", cnt);
		
		return "layout/menu.tiles";
	}

}
