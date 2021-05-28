package com.petMart.member.service;

import java.util.List;

import com.petMart.member.vo.MemberVO;

public interface MemberService {
	// 전체 조회, 한 건 조회, 입력, 수정, 삭제
	List<MemberVO> selectMemberList();
	MemberVO selectMember();
	public int insertMember(MemberVO vo);
	public int updateMember(MemberVO vo);
	public int deleteMember(MemberVO vo);

}
