package com.petMart.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomePage implements DbCommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {

		return "home/homePage.tiles";
	}

}
