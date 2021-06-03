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
import com.petMart.product.vo.QuestionVO;

@WebServlet("/showQuestion")
public class ShowQuestion extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		String itemCode = req.getParameter("itemCode");
		System.out.println("ShowQuestion 실행");
		System.out.println(itemCode);
		
		InfoServiceImpl service = new InfoServiceImpl();
		
		List<QuestionVO> questionList = service.selectQuestionList(itemCode); 

		JSONArray questionListJSON = new JSONArray();
		for (int i = 0; i < questionList.size(); i++) {
			JSONObject jobj = new JSONObject();
			jobj.put("questionId", questionList.get(i).getQuestinId());
			jobj.put("itemCode", questionList.get(i).getItemCode());
			jobj.put("content", questionList.get(i).getContent());
			jobj.put("depth", questionList.get(i).getDepth());
			jobj.put("isOpen", questionList.get(i).getIsOpen());
			jobj.put("regDate", questionList.get(i).getRegDate().toString());  // 날짜를 string으로 바꿔줘야 파싱 에러가 안 난다
			jobj.put("title", questionList.get(i).getTitle());
			jobj.put("writer", questionList.get(i).getWriter());
			questionListJSON.add(jobj);
		}
		System.out.println("문의사항 갯수"+ questionListJSON.size());
		resp.getWriter().print(questionListJSON);
	
	}

}




