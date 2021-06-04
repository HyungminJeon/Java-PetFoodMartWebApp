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

import com.petMart.common.DbCommand;
import com.petMart.product.serviceImpl.InfoServiceImpl;
import com.petMart.product.vo.ReviewVO;

public class ShowReview implements DbCommand {
	

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		
		String itemCode = req.getParameter("itemCode");
		
		InfoServiceImpl service = new InfoServiceImpl();
		
		List<ReviewVO> reviewList = service.selectReviewList(itemCode);
		
		req.setAttribute("reviewList", reviewList);
		
		// foot, head, menu가 필요 없는 경우(view)의 호출 방법과 경로는 아래와 같다. view.jsp와 tiles.xml 참고
		return "view/ajax/reviewList.tiles";
		
		
//		JSONArray reviewListJSON = new JSONArray();
//		for (int i = 0; i < reviewList.size(); i++) {
//			JSONObject jobj = new JSONObject();
//			jobj.put("itemCode", reviewList.get(i).getItemCode());
//			jobj.put("content", reviewList.get(i).getContent());
//			jobj.put("regDate", reviewList.get(i).getRegDate().toString()); // 날짜를 string으로 바꿔줘야 파싱 에러가 안 난다
//			jobj.put("writer", reviewList.get(i).getWriter());
//			jobj.put("satisfaction", reviewList.get(i).getSatisfaction());
//			jobj.put("reviewId", reviewList.get(i).getReviewId());
//			reviewListJSON.add(jobj);
//		}
//
//		System.out.println("리뷰 갯수"+ reviewListJSON.size());
//		resp.getWriter().print(reviewListJSON);
		
	}

}




