package com.petMart.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.notice.serviceImpl.NoticeServiceImpl;
import com.petMart.notice.vo.NoticeVO;

public class HomePage implements DbCommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {

		NoticeServiceImpl service = new NoticeServiceImpl();
		List<NoticeVO> list = service.homePageNoticeList();
		
		req.setAttribute("noticeList", list);
		 
		return "home/homePage.tiles";
	}

}
