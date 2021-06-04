package com.petMart.purchase.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petMart.common.DbCommand;
import com.petMart.product.serviceImpl.ProductServiceImpl;

public class AfterPurchaseCartCount implements DbCommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		
		ProductServiceImpl service = new ProductServiceImpl();
		HttpSession session = req.getSession();
		String id = req.getParameter("getId");
		int cnt;
	
		
		cnt = service.getCountCart(id);
		
	
		System.out.println(id);
		session.setAttribute("cartCnt", cnt);
		
		return "layout/menu.tiles";
	}

}
