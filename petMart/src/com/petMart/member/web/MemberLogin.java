package com.petMart.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petMart.common.DbCommand;
import com.petMart.member.serviceImpl.MemberServiceImpl;
import com.petMart.member.vo.MemberVO;
import com.petMart.product.serviceImpl.ProductServiceImpl;


public class MemberLogin implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		// id, pwd 확인 결과를 return
		// 정상적인 회원이면 이름을 화면으로 보여주도록
		
		String id = request.getParameter("memberId");
		String pwd = request.getParameter("memberPwd");
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPwd(pwd);
		
		MemberServiceImpl service = new MemberServiceImpl();
		MemberVO rvo = service.loginCheck(vo);
		
		String path = "";
		HttpSession session = request.getSession();
		
		if(rvo != null) { // 회원이 있는 경우
			session.setAttribute("id", rvo.getId());
			ProductServiceImpl service1 = new ProductServiceImpl();
			int cnt = service1.getCountCart(rvo.getId());
			session.setAttribute("cartCnt", cnt);
			request.setAttribute("vo", rvo);
			path = "member/memberLoginSuccess.tiles";
		} else { // 회원이 없는 경우
			path = "member/memberLoginFail.tiles";
		}
		
		return path;
	}
	

}
