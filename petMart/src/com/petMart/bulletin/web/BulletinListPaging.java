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
		
		String page = request.getParameter("page");
		if (page == null) page = "1";
		int intPage = Integer.parseInt(page);
		
		// 일단은 전체 조회 가져오고
		BulletinServiceImpl service = new BulletinServiceImpl();
		List<BulletinVO> total = service.bulletinSelectList();
		
		service = new BulletinServiceImpl();
		List<BulletinVO> list = service.bulletinSelectListPaging(intPage);
		
		Paging paging = new Paging();
		paging.setPageSize(10);
		paging.setPageNo(intPage);
		paging.setTotalCount(total.size());
		
		request.setAttribute("total", list);
		request.setAttribute("paging", paging);
		
		return "bulletin/bulletinListPaging.tiles";
	}

}
