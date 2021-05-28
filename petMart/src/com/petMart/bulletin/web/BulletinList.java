package com.petMart.bulletin.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.bulletin.service.BulletinService;
import com.petMart.bulletin.serviceImpl.BulletinServiceImpl;
import com.petMart.bulletin.vo.BulletinVO;
import com.petMart.common.DbCommand;

public class BulletinList implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		BulletinService service = new BulletinServiceImpl();
		List<BulletinVO> list = service.bulletinSelectList();
		
		request.setAttribute("bulletinList", list);
		
		return "bulletin/bulletinList.tiles";
	}

}
