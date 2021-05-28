package com.petMart.bulletin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.bulletin.service.BulletinService;
import com.petMart.bulletin.serviceImpl.BulletinServiceImpl;
import com.petMart.bulletin.vo.BulletinVO;
import com.petMart.common.DbCommand;

public class BulletinSelect implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 한 건 조회
		int id = Integer.parseInt(request.getParameter("id"));
		
		BulletinVO vo = new BulletinVO();
		vo.setId(id);
		
		BulletinService service = new BulletinServiceImpl();
		vo = service.bulletinselect(vo);
		
		request.setAttribute("bulletin", vo);
		
		return "bulletin/bulletin.tiles";
	}

}
