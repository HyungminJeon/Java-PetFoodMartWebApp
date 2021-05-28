package com.petMart.notice.service;

import java.util.List;

import com.petMart.notice.vo.NoticeVO;


public interface NoticeService {
	// 리스트, 조건조회, 입력, 수정, 삭제
	List<NoticeVO> noticeSelectList();
	NoticeVO noticeSelect(NoticeVO vo);
	int insertNotice(NoticeVO vo);
	int updateNotice(NoticeVO vo);
	int deleteNotice(NoticeVO vo);
}
