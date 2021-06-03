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
		String guestId = (String)session.getAttribute("guestId");
		
		ProductServiceImpl service = new ProductServiceImpl();
		List<CartVO> list;
		
		//장바구니 삭제시 장바구니를 게스트와 유저로 구별하기 위해 새로 만든다
		List<CartVO> guestList =null;
		List<CartVO> userList = null;
		
		// 만일 아이디가 없으면 쿠키를 사용
		if(id == null) {
			guestList = service.guestCartList(guestId);
		} else {
			userList = service.cartList(id);
		}
		
		
		request.setAttribute("userCartList", userList);
		request.setAttribute("guestCartList", guestList);
		request.setAttribute("id", id);
		
		
		return "product/cartList.tiles";
	}

}