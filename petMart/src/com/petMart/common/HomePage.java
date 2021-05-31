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

		NoticeServiceImpl noservice = new NoticeServiceImpl();
		List<NoticeVO> list1 = noservice.homePageNoticeList();
		
		req.setAttribute("homepageNoticeList", list1);
		 
		BulletinServiceImpl buservice = new BulletinServiceImpl();
		List<BulletinVO> list2 = buservice.homePageBulletinList();
		
		req.setAttribute("homepageBulletinList", list2);
		
		ProductServiceImpl prservice = new ProductServiceImpl();
		List<ProductVO> list3 = prservice.homePageProductList();
		
		req.setAttribute("homepageProductList", list3);
		
		
		return "home/homePage.tiles";
	}

}
