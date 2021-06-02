package com.petMart.product.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.common.DbCommand;
import com.petMart.product.serviceImpl.ProductServiceImpl;
import com.petMart.product.vo.ProductVO;

public class SearchList implements DbCommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		
		String keyword = req.getParameter("keyword");
		keyword = "%"+keyword+"%"; // sql = "select * from product where item_name like %?%"
		
		ProductServiceImpl service = new ProductServiceImpl();
		List<ProductVO> list = service.searchProductList(keyword);
		int cnt = list.size();
		
		req.setAttribute("searchList", list);
		req.setAttribute("searchedCnt", cnt);
		
		return "product/searchList.tiles";
	}

}
