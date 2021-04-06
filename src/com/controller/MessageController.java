package com.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.po.MessageBean;
import com.po.Token;
import com.po.UserPageQuery;
import com.service.MessageService;
import com.util.TokenUtil;


/**
 * 明日做好友接口×，并做出检测是否在对方黑名单的dao方法										 √
 * 用户接口未完成的接口一并完成，同时做成检测对象用户隐私设置的dao方法							 √
 * 发送消息 √，添加好友，搜索用户√都要检测黑名单；同时搜索√和查看资料卡√需要检测对象用户隐私设置    √
 * 好友接口主要实现黑名单表，好友申请表，好友表的方法；										 √
 * 黑名单：加入，查看（list），删除															 √
 * 好友申请：添加,删除(被添加方),通过,查看，用户登录推送好友添加信息							 √
 * 好友表：删除，获取用户好友信息																 √
 * @author ASUS
 *
 */
@Controller
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="/sendMessage",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String sendMessage(@ModelAttribute MessageBean message, HttpServletRequest request,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			message.setM_source_id(token.getUser().getU_id());
		}
		message.setM_send_time(new Date());
		return messageService.sendMessage(message,request);
	}
	@RequestMapping(value="/getUserMessage",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getUserMessage(@ModelAttribute UserPageQuery query,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			query.setU_id(token.getUser().getU_id());
		}
		return messageService.getUserMessage(query);
	}
	@RequestMapping(value="/getRecordMessage",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getRecordMessage(@ModelAttribute UserPageQuery query,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			query.setU_id(token.getUser().getU_id());
		}
		return messageService.getRecordMessage(query);
	}
	@RequestMapping(value="/delMessage",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delMessage(@ModelAttribute MessageBean message,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			message.setM_source_id(token.getUser().getU_id());
		}
		return messageService.delMessage(message);
	}
}
