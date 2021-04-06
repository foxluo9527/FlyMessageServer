package com.po;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class GroupBean {
	private int g_id;
	private String g_name;
	private String g_num;
	private String g_introduce;
	private String g_head_img;
	private Date g_create_time;
	private MultipartFile headImage;
	/**
	 * @return the headImage
	 */
	public MultipartFile getHeadImage() {
		return headImage;
	}
	/**
	 * @param headImage the headImage to set
	 */
	public void setHeadImage(MultipartFile headImage) {
		this.headImage = headImage;
	}
	public int getG_id() {
		return g_id;
	}
	public void setG_id(int g_id) {
		this.g_id = g_id;
	}
	/**
	 * @return the g_num
	 */
	public String getG_num() {
		return g_num;
	}
	/**
	 * @param g_num the g_num to set
	 */
	public void setG_num(String g_num) {
		this.g_num = g_num;
	}
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getG_introduce() {
		return g_introduce;
	}
	public void setG_introduce(String g_introduce) {
		this.g_introduce = g_introduce;
	}
	public String getG_head_img() {
		return g_head_img;
	}
	public void setG_head_img(String g_head_img) {
		this.g_head_img = g_head_img;
	}
	public Date getG_create_time() {
		return g_create_time;
	}
	public void setG_create_time(Date g_create_time) {
		this.g_create_time = g_create_time;
	}
}
