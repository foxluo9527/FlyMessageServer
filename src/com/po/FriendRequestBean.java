package com.po;

import java.util.Date;

public class FriendRequestBean {
	private int rq_source_u_id;
	private int rq_object_u_id;
	private int rq_id;
	private String rq_content;
	private String rq_remarks_name;
	private int rq_receive_state;
	private Date time;
	public int getRq_source_u_id() {
		return rq_source_u_id;
	}
	public void setRq_source_u_id(int rq_source_u_id) {
		this.rq_source_u_id = rq_source_u_id;
	}
	public int getRq_object_u_id() {
		return rq_object_u_id;
	}
	public void setRq_object_u_id(int rq_object_u_id) {
		this.rq_object_u_id = rq_object_u_id;
	}
	public int getRq_id() {
		return rq_id;
	}
	public void setRq_id(int rq_id) {
		this.rq_id = rq_id;
	}
	public String getRq_content() {
		return rq_content;
	}
	public void setRq_content(String rq_content) {
		this.rq_content = rq_content;
	}
	public String getRq_remarks_name() {
		return rq_remarks_name;
	}
	public void setRq_remarks_name(String rq_remarks_name) {
		this.rq_remarks_name = rq_remarks_name;
	}
	
	/**
	 * @return the rq_receive_state
	 */
	public int getRq_receive_state() {
		return rq_receive_state;
	}
	/**
	 * @param rq_receive_state the rq_receive_state to set
	 */
	public void setRq_receive_state(int rq_receive_state) {
		this.rq_receive_state = rq_receive_state;
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
