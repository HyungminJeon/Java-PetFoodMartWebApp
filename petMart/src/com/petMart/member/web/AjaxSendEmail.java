package com.petMart.member.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.velocity.tools.view.WebappUberspector.SetAttributeExecutor;

import com.petMart.email.Gmail;
import com.petMart.email.SHA256;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.*;

@WebServlet("/ajaxSendEmail")
public class AjaxSendEmail extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//사용자에게 보낼 메시지를 기입합니다.
		String host = "http://192.168.0.67/petMart/";
		String from = "wjsgudals6@gmail.com";
		String to = request.getParameter("email");
		String subject = "Pet Mart 회원가입을 위한 이메일 확인 메일입니다.";
		String code = new SHA256().getSHA256(to);
		String content = "다음 코드를 회원가입창에 입력해주세요.\r\n Code: " + code;

// SMTP에 접속하기 위한 정보를 기입합니다.

		Properties p = new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.googlemail.com");
		p.put("mail.smtp.port", "456");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");

		try {
			Authenticator auth = new Gmail();
			Session ses = Session.getInstance(p, auth);
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses);
			msg.setSubject(subject);
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(content, "text/html;charset=UTF8");
			Transport.send(msg);

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("오류가 발생했습니다.");
//   PrintWriter script = response.getWriter();
//   script.println("<script>");
//   script.println("alert('오류가 발생했습니다..');");
//   script.println("history.back();");
//   script.println("</script>");
//   script.close();
			return;
		}
		response.getWriter().print(code);
	}

}
