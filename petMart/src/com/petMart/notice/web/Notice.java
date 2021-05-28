package com.petMart.notice.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.common.DbCommand;
import com.petMart.notice.service.NoticeService;
import com.petMart.notice.serviceImpl.NoticeServiceImpl;
import com.petMart.notice.vo.NoticeVO;


public class Notice implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 한건 조회 -> notice.jsp
		int id = Integer.parseInt(request.getParameter("id"));
		
		NoticeVO nvo = new NoticeVO();
		nvo.setId(id);
		NoticeService service = new NoticeServiceImpl();
		NoticeVO vo = service.noticeSelect(nvo);
		
		request.setAttribute("notice", vo);
		return "notice/notice.tiles";
	}

}
