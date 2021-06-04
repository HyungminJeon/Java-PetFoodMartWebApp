package com.petMart.product.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.petMart.common.DbCommand;
import com.petMart.product.serviceImpl.InfoServiceImpl;
import com.petMart.product.vo.QuestionVO;

public class ShowQuestion implements DbCommand{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		
		res.setCharacterEncoding("UTF-8");
		
		String itemCode = req.getParameter("itemCode");
		
		InfoServiceImpl service = new InfoServiceImpl();
		
		List<QuestionVO> questionList = service.selectQuestionList(itemCode); 
		req.setAttribute("questionList", questionList);
		
		// foot, head, menu가 필요 없는 경우(view)의 호출 방법과 경로는 아래와 같다. view.jsp와 tiles.xml 참고
		return "view/ajax/questionList.tiles";
	}
	

}




