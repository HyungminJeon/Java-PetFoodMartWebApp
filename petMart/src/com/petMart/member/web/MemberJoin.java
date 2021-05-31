package com.petMart.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petMart.common.DbCommand;
import com.petMart.member.serviceImpl.MemberServiceImpl;
import com.petMart.member.vo.MemberVO;
import com.petMart.product.serviceImpl.ProductServiceImpl;


public class MemberJoin implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// DB 저장, main.do로 돌려보내기
		
		
		String id = request.getParameter("memberId");
		String pwd = request.getParameter("memberPwd");
		String name = request.getParameter("memberName");
		String zip = request.getParameter("memberAddressZip");
		String addr1 = request.getParameter("memberAddress");
		String addr2 = request.getParameter("memberAddressDetail");
		
		String addr = zip + " " + addr1 + " " + addr2; 
		
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setName(name);
		vo.setAddr(addr);
		
		MemberServiceImpl service = new MemberServiceImpl();
		service.insertMember(vo);
		
		HttpSession session = request.getSession(); // 페이지 호출 시 세션 객체 생성
		session.setAttribute("id", id);
		session.setAttribute("member", vo);
		
		ProductServiceImpl service1 = new ProductServiceImpl();
		int cnt = service1.getCountCart(id);
		session.setAttribute("cartCnt", cnt);
		
		return "home/homePage.tiles";
	}

}
