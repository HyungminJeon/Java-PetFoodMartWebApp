package com.petMart.notice.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.common.DbCommand;
import com.petMart.common.Paging;
import com.petMart.notice.serviceImpl.NoticeServiceImpl;
import com.petMart.notice.vo.NoticeVO;

public class NoticeListPaging implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page"); // 사용자가 클릭한 페이지 번호
		
		if(page == null) {
			page = "1";
		}
		
		int pageCnt = Integer.parseInt(page); 
				
		// 전체 건수를 위해 실행
		NoticeServiceImpl service = new NoticeServiceImpl();
		List<NoticeVO> total = service.noticeSelectList();
		
		// 현재 페이지 리스트를 위해 실행
		service = new NoticeServiceImpl();
		List<NoticeVO> list = service.noticeListPaging(pageCnt);
		
        Paging paging = new Paging();
        paging.setPageNo(pageCnt);
        paging.setPageSize(10);
        paging.setTotalCount(total.size());

        request.setAttribute("noticeList", list);
        request.setAttribute("paging", paging);
        
		return "notice/noticeListPaging.tiles";
	}

}
