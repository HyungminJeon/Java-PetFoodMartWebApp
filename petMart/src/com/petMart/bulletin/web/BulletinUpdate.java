package com.petMart.bulletin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.bulletin.service.BulletinService;
import com.petMart.bulletin.serviceImpl.BulletinServiceImpl;
import com.petMart.bulletin.vo.BulletinVO;
import com.petMart.common.DbCommand;

public class BulletinUpdate implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int id = Integer.parseInt(request.getParameter("id"));
		
		BulletinVO vo = new BulletinVO();
		vo.setId(id);
		vo.setTitle(title);
		vo.setContent(content);

		BulletinService service = new BulletinServiceImpl();
		int r = service.updateBulletin(vo);
		System.out.println(r+"건 수정됨");
		
		return "bulletinListPaging.do";
	}

}
