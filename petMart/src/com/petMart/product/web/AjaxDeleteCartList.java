package com.petMart.product.web;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petMart.product.serviceImpl.ProductServiceImpl;
import com.petMart.product.vo.ProductVO;


@WebServlet("/deleteCart")
public class AjaxDeleteCartList extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*
		 * HttpSession session = req.getSession(); String id = (String)
		 * session.getAttribute("id");
		 */
		String id = req.getParameter("id");
		String guestId = null;
		
		
		if(id.startsWith("0.")) {
			guestId = id;
		}
		int r= 0;
		
		
		
		String[] deleteItemCode = req.getParameterValues("deleteList[]");
		
		
		if(guestId == null) {
		
		 for(int i=0; i< deleteItemCode.length; i++) {
			 ProductVO vo = new ProductVO();
			 vo.setItemCode(deleteItemCode[i]);
			 vo.setUserId(id); 
			 ProductServiceImpl service = new ProductServiceImpl();
			 r += service.deleteProduct(vo);
		 }
		 resp.getWriter().print(id);
		} else {
			for(int i=0; i< deleteItemCode.length; i++) {
				 ProductVO vo = new ProductVO();
				 vo.setItemCode(deleteItemCode[i]);
				 vo.setUserId(id); 
				 ProductServiceImpl service = new ProductServiceImpl();
				 r += service.deleteGuestProduct(vo);
			 }
			resp.getWriter().print(id);
		}
		
		
	}

}
