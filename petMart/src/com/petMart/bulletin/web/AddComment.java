package com.petMart.bulletin.web;

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
		
		// 공통으로 사용할 변수들
		String want = req.getParameter("want");
		
		CommentsVO vo = new CommentsVO();
		BulletinServiceImpl service = new BulletinServiceImpl();
		
		String writer = req.getParameter("writer");
		String content = req.getParameter("content");
		int bid = Integer.parseInt(req.getParameter("bid"));
		
		System.out.println(content);
		
		int r = 0;
		
		vo.setBid(bid);
		vo.setContent(content);
		vo.setWriter(writer);
		
		// 넘어온 String want가 '기존 댓글에 답장'인가
		if(want.equals("add")) {
			
			int group_id = Integer.parseInt(req.getParameter("gid"));
			int depth = Integer.parseInt(req.getParameter("depth"));
			int pid = Integer.parseInt(req.getParameter("pid"));
			
			vo.setDepth(depth);
			vo.setGroup_id(group_id);
			vo.setParent_id(pid);
			
			r = service.replyComment(vo);
			
		} else if (want.equals("new")){ // 넘어온 String want가 '새로 추가'인가
			
			r = service.newComment(vo);
		}
		
		System.out.println(r+"건 코멘트 입력");

	}

}
