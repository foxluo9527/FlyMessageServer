package com.service;

import javax.servlet.http.HttpServletRequest;

import com.po.MessageBean;
import com.po.UserPageQuery;

public interface MessageService {
	String sendMessage(MessageBean message,HttpServletRequest request);
	String getUserMessage(UserPageQuery query);
	String getRecordMessage(UserPageQuery query);
	String delMessage(MessageBean message);
}
