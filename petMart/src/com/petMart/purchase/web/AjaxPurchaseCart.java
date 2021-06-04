package com.petMart.purchase.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petMart.product.serviceImpl.ProductServiceImpl;
import com.petMart.product.vo.CartVO;
import com.petMart.product.vo.ProductVO;
import com.petMart.purchase.serviceImpl.PurchaseServiceImpl;
import com.petMart.purchase.vo.PurchaseVO;


@WebServlet("/purchaseCart")
public class AjaxPurchaseCart extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		PurchaseServiceImpl service = new PurchaseServiceImpl();
		PurchaseServiceImpl service2 = new PurchaseServiceImpl();
		PurchaseServiceImpl service3 = new PurchaseServiceImpl();
		String id = req.getParameter("id");
		String[] purchaseItemCodes = req.getParameterValues("purchaseList[]");
		
		List<PurchaseVO> list = new ArrayList<>();
		PurchaseVO vo = new PurchaseVO();
		PurchaseVO vo1 = new PurchaseVO();
		int r =0;
		int r1 =0;
		
		 for(int i=0; i< purchaseItemCodes.length; i++) {
			 
			 vo.setItemCode(purchaseItemCodes[i]);
			 vo.setUserId(id); 
			
			 vo1 = service.purchaseSelect(vo);
			 r = service2.insertPurchase(vo1);
			 
			 list.add(vo);
		 }
		 r1 = service3.deleteCart(vo1);
		 
		 session.setAttribute("purchaseList", list);
		 resp.getWriter().print(id);
	}

}
