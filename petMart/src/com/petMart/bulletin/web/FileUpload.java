package com.petMart.bulletin.web;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/fileUpload")
public class FileUpload extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		String path = "c:/tmp";
		ServletContext sc = this.getServletContext();
		path = sc.getRealPath("upload"); // 파일이 저장될 서버의 경로 
		
		MultipartRequest multi = new MultipartRequest(req, path, 8 * 1024 * 1024, "UTF-8", new DefaultFileRenamePolicy());
													// request 객체, 저장될 서버 경로, 파일 최대 크기, 인코딩 방식, 같은 이름의 파일명 방지 처리
		Enumeration en = multi.getFileNames(); // form 요소 중 input에서 file 속성을 가진 파라미터의 이름을 반환 ex)name="uploadfile"> uploadfile을 저장한 en 반환
		String fileN = null; // 실제 파일 명
		while(en.hasMoreElements()) {
			String name = (String) en.nextElement();
			// en에 다음 요소가 없을 때까지, en의 다음 요소 이름을 name으로 넣고..시발
			String fileName = multi.getFilesystemName(name); // DefaultFileRenamePolicy() 때문에 실제 서버에 저장되었을 때의 파일명 반환 
			fileN = fileName;
		}
		JsonObject json = new JsonObject();
		json.addProperty("uploaded", 1);
		json.addProperty("fileName", fileN);
		json.addProperty("url", req.getContextPath()+"/upload/"+fileN);
		resp.getWriter().print(json);
	}
	
}
