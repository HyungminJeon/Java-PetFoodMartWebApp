package com.petMart.notice.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.bulletin.serviceImpl.BulletinServiceImpl;
import com.petMart.bulletin.vo.CommentsVO;

@WebServlet("/addComment")
public class AddComment extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int bid = Integer.parseInt(req.getParameter("bid"));
		int group_id = Integer.parseInt(req.getParameter("gid"));
		String writer = req.getParameter("writer");
		int depth = Integer.parseInt(req.getParameter("depth"));
		String content = req.getParameter("content");
		
		CommentsVO vo = new CommentsVO();
		BulletinServiceImpl service = new BulletinServiceImpl();
		
		vo.setBid(bid);
		vo.setContent(content);
		vo.setDepth(depth);
		vo.setGroup_id(group_id);
		vo.setWriter(writer);
		
		int r = service.replyComment(vo);
		System.out.println(r+"건 코멘트 입력");
		
	}

}
