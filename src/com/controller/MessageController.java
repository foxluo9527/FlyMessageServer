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
 * ���������ѽӿڡ�������������Ƿ��ڶԷ���������dao����										 ��
 * �û��ӿ�δ��ɵĽӿ�һ����ɣ�ͬʱ���ɼ������û���˽���õ�dao����							 ��
 * ������Ϣ �̣����Ӻ��ѣ������û��̶�Ҫ����������ͬʱ�����̺Ͳ鿴���Ͽ�����Ҫ�������û���˽����    ��
 * ���ѽӿ���Ҫʵ�ֺ�����������������������ѱ��ķ�����										 ��
 * �����������룬�鿴��list����ɾ��															 ��
 * �������룺����,ɾ��(�����ӷ�),ͨ��,�鿴���û���¼���ͺ���������Ϣ							 ��
 * ���ѱ���ɾ������ȡ�û�������Ϣ																 ��
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