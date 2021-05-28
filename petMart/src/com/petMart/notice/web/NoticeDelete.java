package com.petMart.notice.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.common.DbCommand;
import com.petMart.notice.service.NoticeService;
import com.petMart.notice.serviceImpl.NoticeServiceImpl;
import com.petMart.notice.vo.NoticeVO;


public class NoticeDelete implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		NoticeService service = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();		
		vo.setId(id);
		int r = service.deleteNotice(vo);
		System.out.println(r+"건 삭제됨");
		
		return "noticeListPaging.do";
	}

}
