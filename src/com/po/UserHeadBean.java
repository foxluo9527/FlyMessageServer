package com.po;

import java.sql.Date;

public class UserHeadBean {
	private int h_id;
	private int u_id;
	private String head_img_link;
	private Date time;
	public int getH_id() {
		return h_id;
	}
	public void setH_id(int h_id) {
		this.h_id = h_id;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getHead_img_link() {
		return head_img_link;
	}
	public void setHead_img_link(String head_img_link) {
		this.head_img_link = head_img_link;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

}
