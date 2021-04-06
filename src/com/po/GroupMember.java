package com.po;

import java.util.Date;

public class GroupMember {
	private int id;
	private int g_id;
	private int u_id;
	private int power;
	private String g_nick_name;
	private Date add_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getG_id() {
		return g_id;
	}
	public void setG_id(int g_id) {
		this.g_id = g_id;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public String getG_nick_name() {
		return g_nick_name;
	}
	public void setG_nick_name(String g_nick_name) {
		this.g_nick_name = g_nick_name;
	}
	public Date getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
}
