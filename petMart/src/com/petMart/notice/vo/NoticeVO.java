package com.petMart.notice.vo;

import java.util.Date;

public class NoticeVO {
	private int id;
	private String title;
	private String content;
	private Date regDate;
	private int hit;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regdate) {
		this.regDate = regdate;
	} 
}
