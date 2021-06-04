package com.petMart.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.product.serviceImpl.InfoServiceImpl;
import com.petMart.product.vo.ReviewVO;

	@WebServlet("/reviewInsert")
	public class ReviewInsert extends HttpServlet {
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			String itemCode = req.getParameter("itemCode");
			String content = req.getParameter("content");
			String writer = req.getParameter("writer");
			int satisfaction = Integer.parseInt(req.getParameter("satisfaction")); 
			
			ReviewVO vo = new ReviewVO();
			vo.setContent(content);
			vo.setItemCode(itemCode);
			vo.setSatisfaction(satisfaction);
			vo.setWriter(writer);
			
			InfoServiceImpl service = new InfoServiceImpl();
			int r = service.reviewInsert(vo);
			System.out.println(r+"건 입력됨");
			
			
		}
	
	
	}
