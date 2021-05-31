package com.petMart.bulletin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.bulletin.service.BulletinService;
import com.petMart.bulletin.serviceImpl.BulletinServiceImpl;
import com.petMart.bulletin.vo.BulletinVO;
import com.petMart.common.DbCommand;

public class BulletinInsert implements DbCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 입력값을 db insert 후 - > bulletinList.do로 return
		String writer = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BulletinVO vo = new BulletinVO();
		vo.setContent(content);
		vo.setWriter(writer);
		vo.setTitle(title);
		
		BulletinService service = new BulletinServiceImpl();
		int cnt = service.insertBulletin(vo);
		System.out.println(cnt+"건 입력됨");
		
		String path = "";
		if(cnt > 0) {
			path = "/bulletinListPaging.do";
		} else {
			path = "/homePage.do";
		}
		
		return path;
	}
	
}
