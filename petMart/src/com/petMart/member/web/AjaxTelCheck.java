package com.petMart.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.member.serviceImpl.MemberServiceImpl;

@WebServlet("/ajaxTelCheck")
public class AjaxTelCheck extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String tel = req.getParameter("tel");
		MemberServiceImpl service = new MemberServiceImpl();
		int cnt = 0;
		if(service.telCheck(tel)) {
			cnt = 1;
		}
		resp.getWriter().print(cnt);
	}
}
