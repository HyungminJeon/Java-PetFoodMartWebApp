package com.petMart.product.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petMart.common.DbCommand;
import com.petMart.product.service.ProductService;
import com.petMart.product.serviceImpl.ProductServiceImpl;
import com.petMart.product.vo.ProductVO;


public class CartList implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		ProductService service = new ProductServiceImpl();
		List<ProductVO> list = service.productSelectList();

		ProductServiceImpl service1 = new ProductServiceImpl();
		HttpSession session = request.getSession();
		int cnt = service1.getCountCart((String)session.getAttribute("id"));
		session.setAttribute("cartCnt", cnt);
		request.setAttribute("list", list);
		
		return "product/cartList.tiles";
	}

}
