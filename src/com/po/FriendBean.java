package com.po;

import java.util.Date;

public class FriendBean {
	private int f_id;
	private int f_source_u_id;
	private int f_object_u_id;
	private String f_remarks_name;
	private Date time;
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public int getF_source_u_id() {
		return f_source_u_id;
	}
	public void setF_source_u_id(int f_source_u_id) {
		this.f_source_u_id = f_source_u_id;
	}
	public int getF_object_u_id() {
		return f_object_u_id;
	}
	public void setF_object_u_id(int f_object_u_id) {
		this.f_object_u_id = f_object_u_id;
	}
	public String getF_remarks_name() {
		return f_remarks_name;
	}
	public void setF_remarks_name(String f_remarks_name) {
		this.f_remarks_name = f_remarks_name;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
