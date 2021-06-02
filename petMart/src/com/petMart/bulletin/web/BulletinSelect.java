package com.petMart.bulletin.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.bulletin.service.BulletinService;
import com.petMart.bulletin.serviceImpl.BulletinServiceImpl;
import com.petMart.bulletin.vo.BulletinVO;
import com.petMart.bulletin.vo.CommentsVO;
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
		
		//댓글 조회
		BulletinServiceImpl service2 = new BulletinServiceImpl();
		List<CommentsVO> list = new ArrayList<>();
		list = service2.commentsSelectList(vo.getId());
		request.setAttribute("commentsList", list);
		return "bulletin/bulletin.tiles";
	}

}
