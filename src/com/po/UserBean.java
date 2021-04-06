package com.po;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class UserBean {
	private int u_id;
	private String u_name;
	private String u_phone;
	private String u_pass;
	private String u_nick_name;
	private Date u_brithday;
	private String u_position;
	private String u_sex;
	private String u_bg_img;
	private String u_head_img;
	private String u_sign;
	private Date u_create_time;
	private int u_forbidden;
	private MultipartFile headImage;
	private MultipartFile bgImage;
	private Date nowDate=new Date();
	
	/**
	 * @return the bgImage
	 */
	public MultipartFile getBgImage() {
		return bgImage;
	}
	/**
	 * @param bgImage the bgImage to set
	 */
	public void setBgImage(MultipartFile bgImage) {
		this.bgImage = bgImage;
	}
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
	/**
	 * @return the nowDate
	 */
	public Date getNowDate() {
		return nowDate;
	}
	/**
	 * @param nowDate the nowDate to set
	 */
	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_phone() {
		return u_phone;
	}
	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}
	public String getU_pass() {
		return u_pass;
	}
	public void setU_pass(String u_pass) {
		this.u_pass = u_pass;
	}
	public String getU_nick_name() {
		return u_nick_name;
	}
	public void setU_nick_name(String u_nick_name) {
		this.u_nick_name = u_nick_name;
	}
	public Date getU_brithday() {
		return u_brithday;
	}
	public void setU_brithday(Date u_brithday) {
		this.u_brithday = u_brithday;
	}
	public String getU_position() {
		return u_position;
	}
	public void setU_position(String u_position) {
		this.u_position = u_position;
	}
	public String getU_sex() {
		return u_sex;
	}
	public void setU_sex(String u_sex) {
		this.u_sex = u_sex;
	}
	public String getU_bg_img() {
		return u_bg_img;
	}
	public void setU_bg_img(String u_bg_img) {
		this.u_bg_img = u_bg_img;
	}
	public String getU_head_img() {
		return u_head_img;
	}
	public void setU_head_img(String u_head_img) {
		this.u_head_img = u_head_img;
	}
	/**
	 * @return the u_sgin
	 */
	public String getU_sign() {
		return u_sign;
	}
	/**
	 * @param u_sgin the u_sgin to set
	 */
	public void setU_sign(String u_sign) {
		this.u_sign = u_sign;
	}
	public Date getU_create_time() {
		return u_create_time;
	}
	public void setU_create_time(Date u_create_time) {
		this.u_create_time = u_create_time;
	}
	public int getU_forbidden() {
		return u_forbidden;
	}
	public void setU_forbidden(int u_forbidden) {
		this.u_forbidden = u_forbidden;
	}
}
