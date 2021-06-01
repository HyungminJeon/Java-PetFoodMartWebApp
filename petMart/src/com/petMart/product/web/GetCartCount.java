package com.petMart.product.web;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petMart.common.DbCommand;
import com.petMart.product.service.ProductService;
import com.petMart.product.serviceImpl.ProductServiceImpl;
import com.petMart.product.vo.ProductVO;

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
			id = cookies[0].getValue();
			cnt = service.getCountGuestCart(id);
		} else {
			cnt = service.getCountCart(id);
		}
		
		session.setAttribute("cartCnt", cnt);
		
		return "layout/menu.tiles";
	}

}
