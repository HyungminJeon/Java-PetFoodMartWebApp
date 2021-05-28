package com.petMart.notice.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.common.DbCommand;
import com.petMart.notice.service.NoticeService;
import com.petMart.notice.serviceImpl.NoticeServiceImpl;
import com.petMart.notice.vo.NoticeVO;

public class NoticeInsert implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		NoticeVO vo = new NoticeVO();
		NoticeService service = new NoticeServiceImpl();
		
		vo.setContent(content);
		vo.setTitle(title);
		int r = service.insertNotice(vo);
		System.out.println(r+"건 입력됨");
		
		return "noticeListPaging.do";
	}

}
