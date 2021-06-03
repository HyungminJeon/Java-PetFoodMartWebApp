package com.petMart.product.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.petMart.product.serviceImpl.InfoServiceImpl;
import com.petMart.product.vo.ReviewVO;

@WebServlet("/showReview")
public class ShowReview extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String itemCode = req.getParameter("itemCode");
		System.out.println("ShowReview 실행");
		System.out.println(itemCode);
		
		InfoServiceImpl service = new InfoServiceImpl();
		
		List<ReviewVO> reviewList = service.selectReviewList(itemCode);
		
		JSONArray reviewListJSON = new JSONArray();
		for (int i = 0; i < reviewList.size(); i++) {
			JSONObject jobj = new JSONObject();
			jobj.put("itemCode", reviewList.get(i).getItemCode());
			jobj.put("content", reviewList.get(i).getContent());
			jobj.put("regDate", reviewList.get(i).getRegDate().toString()); // 날짜를 string으로 바꿔줘야 파싱 에러가 안 난다
			jobj.put("writer", reviewList.get(i).getWriter());
			jobj.put("satisfaction", reviewList.get(i).getSatisfaction());
			jobj.put("reviewId", reviewList.get(i).getReviewId());
			reviewListJSON.add(jobj);
		}

		System.out.println("리뷰 갯수"+ reviewListJSON.size());
		resp.getWriter().print(reviewListJSON);
	
	}

}




