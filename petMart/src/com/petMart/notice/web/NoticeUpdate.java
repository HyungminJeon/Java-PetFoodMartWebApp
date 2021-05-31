package com.petMart.notice.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.common.DbCommand;
import com.petMart.notice.service.NoticeService;
import com.petMart.notice.serviceImpl.NoticeServiceImpl;
import com.petMart.notice.vo.NoticeVO;

public class NoticeUpdate implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		NoticeVO vo = new NoticeVO();
		vo.setId(id);
		vo.setTitle(title);
		vo.setContent(content);
		
		NoticeService service = new NoticeServiceImpl();
		int r = service.updateNotice(vo);
		System.out.println(r+"건 수정됨");

		return "noticeListPaging.do";
	}
}
