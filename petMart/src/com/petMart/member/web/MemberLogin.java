package com.petMart.member.web;

import javax.servlet.http.Cookie;
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
		ProductServiceImpl service1 = new ProductServiceImpl();
		MemberVO rvo = service.loginCheck(vo);
		
		String path = "";
		HttpSession session = request.getSession();
		
		//새롭게 로그인한 회원의 카트정보 가져오기 (메뉴에 숫자보여주기 위해)
		int cnt = service1.getCountCart(rvo.getId());
		
		
		
		if(rvo != null) { // 회원이 있는 경우
			session.setAttribute("id", rvo.getId());
			
			
			// 쿠키 정보 읽어오기
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("guestBasketId")) {
					String guestId = cookie.getValue();
					service1.mergeCartList(rvo.getId(), guestId);
					// 쿠키 삭체 요청
					cookie.setMaxAge(0); // 쿠키 유효 시간을 0으로 만듦
					response.addCookie(cookie); // 클라이언트의 쿠키를 서버가 마음대로 삭제할 수 없으므로 위의 쿠키를 덮어씌워서 보냄
					break;
				}
			}
			request.setAttribute("vo", rvo);
			session.setAttribute("cartCnt", cnt);
			path = "/homePage.do";
			
			// 회원이 없는 경우
		}
	
		
		return path;
	}
	

}
