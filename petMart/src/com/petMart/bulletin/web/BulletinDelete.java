package com.petMart.bulletin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.bulletin.service.BulletinService;
import com.petMart.bulletin.serviceImpl.BulletinServiceImpl;
import com.petMart.bulletin.vo.BulletinVO;
import com.petMart.common.DbCommand;


public class BulletinDelete implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		BulletinVO vo = new BulletinVO();
		vo.setId(id);
		
		BulletinService service = new BulletinServiceImpl();
		int r = service.deleteBulletin(vo);
		System.out.println(r+"건 입력됨");

		return "bulletinListPaging.do";
	}

}
