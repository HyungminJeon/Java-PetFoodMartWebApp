package com.petMart.member.web;

import javax.servlet.http.Cookie;
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
		String pwd = request.getParameter("memberPwd1");
		String name = request.getParameter("memberName");
		String zip = request.getParameter("memberAddressZip");
		String addr1 = request.getParameter("memberAddress");
		String addr2 = request.getParameter("memberAddressDetail");
		String tel = request.getParameter("tel");
		
		String addr = zip + " " + addr1 + " " + addr2; 
		
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setName(name);
		vo.setAddr(addr);
		vo.setTel(tel);
		
		MemberServiceImpl service = new MemberServiceImpl();
		service.insertMember(vo);
		
		HttpSession session = request.getSession(); // 페이지 호출 시 세션 객체 생성
		session.setAttribute("id", id);
		session.setAttribute("member", vo);
		
		// 쿠키 정보 읽어오기
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("guestBasketId")) {
				String guestId = cookie.getValue();
				ProductServiceImpl service1 = new ProductServiceImpl();
				service1.mergeCartList(vo.getId(), guestId);
				// 쿠키 삭체 요청
				cookie.setMaxAge(0); // 쿠키 유효 시간을 0으로 만듦
				response.addCookie(cookie); // 클라이언트의 쿠키를 서버가 마음대로 삭제할 수 없으므로 위의 쿠키를 덮어씌워서 보냄
				break;
			}
		}
		
		return "/homePage.do";
	}

}
