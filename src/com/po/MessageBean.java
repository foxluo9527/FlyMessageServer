package com.po;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class MessageBean {
	private int m_id;
	private int m_source_id;
	private int m_source_g_id;
	private int m_type;
	private int m_content_type;
	private int m_object_id;
	private int m_receive_state;
	private String m_content;
	private Date m_send_time;
	private MultipartFile messageFile;
	
	public MessageBean() {
		super();
	}

	public MessageBean(int m_id, int m_source_id, int m_source_g_id, int m_type, int m_content_type, int m_object_id,
			int m_receive_state, String m_content, Date m_send_time) {
		super();
		this.m_id = m_id;
		this.m_source_id = m_source_id;
		this.m_source_g_id = m_source_g_id;
		this.m_type = m_type;
		this.m_content_type = m_content_type;
		this.m_object_id = m_object_id;
		this.m_receive_state = m_receive_state;
		this.m_content = m_content;
		this.m_send_time = m_send_time;
	}
	
	/**
	 * @return the messageFile
	 */
	public MultipartFile getMessageFile() {
		return messageFile;
	}

	/**
	 * @param messageFile the messageFile to set
	 */
	public void setMessageFile(MultipartFile messageFile) {
		this.messageFile = messageFile;
	}

	/**
	 * @return the m_source_g_id
	 */
	public int getM_source_g_id() {
		return m_source_g_id;
	}

	/**
	 * @param m_source_g_id the m_source_g_id to set
	 */
	public void setM_source_g_id(int m_source_g_id) {
		this.m_source_g_id = m_source_g_id;
	}

	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public int getM_source_id() {
		return m_source_id;
	}
	public void setM_source_id(int m_source_id) {
		this.m_source_id = m_source_id;
	}
	public int getM_type() {
		return m_type;
	}
	public void setM_type(int m_type) {
		this.m_type = m_type;
	}
	public int getM_content_type() {
		return m_content_type;
	}
	public void setM_content_type(int m_content_type) {
		this.m_content_type = m_content_type;
	}
	public int getM_object_id() {
		return m_object_id;
	}
	public void setM_object_id(int m_object_id) {
		this.m_object_id = m_object_id;
	}
	public int getM_receive_state() {
		return m_receive_state;
	}
	public void setM_receive_state(int m_receive_state) {
		this.m_receive_state = m_receive_state;
	}
	public String getM_content() {
		return m_content;
	}
	public void setM_content(String m_content) {
		this.m_content = m_content;
	}
	public Date getM_send_time() {
		return m_send_time;
	}
	public void setM_send_time(Date m_send_time) {
		this.m_send_time = m_send_time;
	}
	
}
