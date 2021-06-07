package com.petMart.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.product.serviceImpl.InfoServiceImpl;
import com.petMart.product.vo.QuestionVO;

@WebServlet("/questionInsert")
public class QuestionInsert extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String itemCode = req.getParameter("itemCode");
		String writer = req.getParameter("writer");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String isOpen = req.getParameter("isOpen");
		
		
		
		QuestionVO vo = new QuestionVO();
		vo.setContent(content);
		vo.setItemCode(itemCode);
		vo.setTitle(title);
		vo.setWriter(writer);
		vo.setIsOpen(isOpen);
		
		InfoServiceImpl service = new InfoServiceImpl();
		int r = service.questionInsert(vo);
		System.out.println(r+"건 입력됨");
		
	}


}
