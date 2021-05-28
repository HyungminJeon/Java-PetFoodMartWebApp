package com.petMart.common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet{
	
	private HashMap<String, DbCommand> map = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		// ex: map.put("/~.do", new ~~());
		
		
		// 초기 실행
		
		// 로그인, 회원 가입
		
		// 장바구니
		
		// 자유 게시판
		
		// 공지사항
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String uri = req.getRequestURI();
		String cpath = req.getContextPath();
		String path = uri.substring(cpath.length());
		System.out.println(path);
		
		DbCommand dbCommand = map.get(path);
		String viewPage = dbCommand.execute(req, resp);
		
		RequestDispatcher rd = req.getRequestDispatcher(viewPage);
		rd.forward(req, resp);
		
		
	}
}
