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
public class AjaxPurchaseCartList extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		PurchaseServiceImpl service = new PurchaseServiceImpl();
		String id = req.getParameter("id");
		String[] purchaseItemCodes = req.getParameterValues("purchaseList[]");
		List<PurchaseVO> list = new ArrayList<>();
		
		int r =0;
		
		 for(int i=0; i< purchaseItemCodes.length; i++) {
			 PurchaseVO vo = new PurchaseVO();
			 vo.setItemCode(purchaseItemCodes[i]);
			 vo.setUserId(id); 
			
			 vo = service.purchaseSelect(vo);
			 r = service.insertPurchase(vo);
			 
			 list.add(vo);
			 r = service.deleteCart(vo);
		 }
		 
		 session.setAttribute("purchaseList", list);
		 resp.getWriter().print(id);
	}

}
