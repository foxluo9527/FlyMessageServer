package com.service;

import javax.servlet.http.HttpServletRequest;

import com.po.MessageBean;
import com.po.UserPageQuery;

public interface MessageService {
	public String sendMessage(MessageBean message,HttpServletRequest request);
	public String getUserMessage(UserPageQuery query);
	public String getRecordMessage(UserPageQuery query);
	public String delMessage(MessageBean message);
}
