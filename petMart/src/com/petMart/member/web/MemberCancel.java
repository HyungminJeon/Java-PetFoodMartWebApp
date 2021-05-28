package com.petMart.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petMart.common.DbCommand;
import com.petMart.member.serviceImpl.MemberServiceImpl;
import com.petMart.member.vo.MemberVO;


public class MemberCancel implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("memberId");
		String pwd = request.getParameter("memberPwd");
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPwd(pwd);
		
		
		MemberServiceImpl service = new MemberServiceImpl();
		service.deleteMember(vo);
		
		HttpSession session = request.getSession();
		session.invalidate(); 
		
		return "home/homePage.tiles";
	}

}