package com.po;
//管理员操作日志

import java.util.Date;

public class AdminLog {
	int id;
	int type;
	String content;
	int source_a_id;
	int object_a_id;
	int object_u_id;
	Date time;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the source_a_id
	 */
	public int getSource_a_id() {
		return source_a_id;
	}
	/**
	 * @param source_a_id the source_a_id to set
	 */
	public void setSource_a_id(int source_a_id) {
		this.source_a_id = source_a_id;
	}
	/**
	 * @return the object_a_id
	 */
	public int getObject_a_id() {
		return object_a_id;
	}
	/**
	 * @param object_a_id the object_a_id to set
	 */
	public void setObject_a_id(int object_a_id) {
		this.object_a_id = object_a_id;
	}
	/**
	 * @return the object_u_id
	 */
	public int getObject_u_id() {
		return object_u_id;
	}
	/**
	 * @param object_u_id the object_u_id to set
	 */
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
