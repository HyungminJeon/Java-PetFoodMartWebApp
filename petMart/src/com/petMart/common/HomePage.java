package com.petMart.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.bulletin.serviceImpl.BulletinServiceImpl;
import com.petMart.bulletin.vo.BulletinVO;
import com.petMart.notice.serviceImpl.NoticeServiceImpl;
import com.petMart.notice.vo.NoticeVO;
import com.petMart.product.serviceImpl.ProductServiceImpl;
import com.petMart.product.vo.ProductVO;

public class HomePage implements DbCommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {

		// 공지사항 최신 순 상위 7건
		NoticeServiceImpl noservice = new NoticeServiceImpl();
		List<NoticeVO> list1 = noservice.homePageNoticeList();
		req.setAttribute("homepageNoticeList", list1);
		
		// 자유게시판 최신 순 상위 7건
		BulletinServiceImpl buservice = new BulletinServiceImpl();
		List<BulletinVO> list2 = buservice.homePageBulletinList();
		req.setAttribute("homepageBulletinList", list2);
		
		// 상품 좋아요 순 상위 5건
		ProductServiceImpl prservice = new ProductServiceImpl();
		List<ProductVO> list3 = prservice.homePageProductList();
		req.setAttribute("homepageProductList", list3);
		
		return "home/homePage.tiles";
	}

}
