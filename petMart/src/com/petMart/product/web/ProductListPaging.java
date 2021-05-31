package com.petMart.product.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.common.DbCommand;
import com.petMart.common.Paging;
import com.petMart.product.serviceImpl.ProductServiceImpl;
import com.petMart.product.vo.ProductVO;

public class ProductListPaging implements DbCommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		
		String page = req.getParameter("page");
		if(page == null) page = "1";
		int intPage = Integer.parseInt(page);
		
		ProductServiceImpl service = new ProductServiceImpl();
		List<ProductVO> total = service.productSelectList();
		
		ProductServiceImpl service2 = new ProductServiceImpl();
		List<ProductVO> list = service2.productSelectListPaging(intPage);
		
		Paging paging = new Paging();
		paging.setPageNo(intPage);
		paging.setTotalCount(total.size());
		paging.setPageSize(10);
		
		req.setAttribute("productList", list);
		req.setAttribute("paging", paging);
		
		return "product/productListPaging.tiles";
	}

}
