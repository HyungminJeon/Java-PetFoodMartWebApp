package com.petMart.member.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@WebServlet("/sendSMS")
public class SendSMS extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String targetNum = req.getParameter("tel");
		String textCode = "001001";
		String api_key = "NCS1ZIUBA33Z7UZB";
		String api_secret = "TV6YEYJSUQYTQY7XGE5LVAMU6TT6WOUC";
		Message coolsms = new Message(api_key, api_secret);
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", targetNum);
		params.put("from", "01082017731");
		params.put("type", "SMS");
		params.put("text", textCode);
		params.put("petMart", "petMart v1.0"); // application name and version
		
		System.out.println(params.get("text"));
		
		
		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
		
		resp.getWriter().print(textCode);
		
	}

	
	
	

}

