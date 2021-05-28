package com.petMart.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.common.DbCommand;

public class MemberJoinForm implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return "member/memberJoinForm.tiles";
	}

}
