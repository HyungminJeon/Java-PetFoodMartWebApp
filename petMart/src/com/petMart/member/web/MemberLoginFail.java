package com.petMart.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.common.DbCommand;

public class MemberLoginFail implements DbCommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		return "member/memberLoginFailForm.tiles";
	}

}
