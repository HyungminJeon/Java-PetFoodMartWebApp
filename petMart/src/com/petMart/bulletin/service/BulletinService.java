package com.petMart.bulletin.service;

import java.util.List;

import com.petMart.bulletin.vo.BulletinVO;

public interface BulletinService {
	List<BulletinVO> bulletinSelectList();
	BulletinVO bulletinselect(BulletinVO vo);
	int insertBulletin(BulletinVO vo);
	int updateBulletin(BulletinVO vo);
	int deleteBulletin(BulletinVO vo);
}
