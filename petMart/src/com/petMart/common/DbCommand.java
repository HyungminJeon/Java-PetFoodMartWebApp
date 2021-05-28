package com.petMart.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DbCommand {

	public String execute(HttpServletRequest req, HttpServletResponse res); 
}
