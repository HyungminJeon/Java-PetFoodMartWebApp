package com.petMart.bulletin.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.bulletin.serviceImpl.BulletinServiceImpl;
import com.petMart.bulletin.vo.BulletinVO;
import com.petMart.common.DbCommand;
import com.petMart.common.Paging;

public class BulletinListPaging implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 몇 페이지를 눌렀는지 요청 정보
		String page = request.getParameter("page");
		if (page == null) page = "1"; // 만약 page가 없으면(List페이지 최초 호출인 경우) 기본값 1을 준다
		int intPage = Integer.parseInt(page);
		
		// 전체 글이 몇 개인지를 확인하기 위해 전체 조회
		BulletinServiceImpl service = new BulletinServiceImpl();
		List<BulletinVO> total = service.bulletinSelectList();
		
		// 실제로 보게 될 페이징된 리스트
		service = new BulletinServiceImpl();
		List<BulletinVO> list = service.bulletinSelectListPaging(intPage);
		// 페이징에서 n페이지를 누를 경우 intpage는 n이 된다
		// 최초 요청 시에는 intpage가 1이므로 1~10을 가져오는 쿼리문
		
		
		Paging paging = new Paging();
		paging.setPageSize(10); // 한번에 보여줄 페이지는 10이며
		paging.setPageNo(intPage); // 보고 싶은 페이지 숫자는
		paging.setTotalCount(total.size()); // 전체 조회한 total 객체의 사이즈
		
		request.setAttribute("total", list);
		request.setAttribute("paging", paging);
		
		return "bulletin/bulletinListPaging.tiles";
	}

}
