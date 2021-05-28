package com.petMart.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainPage implements DbCommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {

		return "main/main.tiles";
	}

}
