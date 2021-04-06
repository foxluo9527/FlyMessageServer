package com.po;

import java.util.Date;

public class BlackListBean {
	
	private int bl_id;
	private int source_u_id;
	private int object_u_id;
	private Date time;
	public int getBl_id() {
		return bl_id;
	}
	public void setBl_id(int bl_id) {
		this.bl_id = bl_id;
	}
	public int getSource_u_id() {
		return source_u_id;
	}
	public void setSource_u_id(int source_u_id) {
		this.source_u_id = source_u_id;
	}
	public int getObject_u_id() {
		return object_u_id;
	}
	public void setObject_u_id(int object_u_id) {
		this.object_u_id = object_u_id;
	}
	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

}
