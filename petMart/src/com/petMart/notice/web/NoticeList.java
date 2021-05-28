package com.petMart.notice.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.common.DbCommand;
import com.petMart.notice.service.NoticeService;
import com.petMart.notice.serviceImpl.NoticeServiceImpl;
import com.petMart.notice.vo.NoticeVO;

public class NoticeList implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 공지사항 조회 결과 -> noticeList.jsp
		NoticeService service = new NoticeServiceImpl();
		List<NoticeVO> list = service.noticeSelectList();
		
		request.setAttribute("noticeList", list);
		
		return "notice/noticeList.tiles";
	}

}
