package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.po.UserBean;
import com.service.SMSService;

@Controller
@RequestMapping("/SMS")
public class SMSController {
	@Autowired
	private SMSService smsService;
	
	@RequestMapping(value="/sendRegisterSMS",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String sendRegisterSMS(String phone) {
		return smsService.sendRegisterSMS(phone);
	}
	
	@RequestMapping(value="/sendLoginSMS",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String sendLoginSMS(String phone) {
		return smsService.sendLoginSMS(phone);
	}
	
	@RequestMapping(value="/sendChangePassSMS",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String sendChangePassSMS(String phone) {
		return smsService.sendChangePassSMS(phone);
	}
	@RequestMapping(value="/noPassLogin",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String noPassLogin(String phone,String code) {
		return smsService.noPassLogin(phone,code);
	}
	@RequestMapping(value="/register",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String register(@ModelAttribute UserBean user,String code) {
		return smsService.register(user, code);
	}
	@RequestMapping(value="/changePass",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String changePass(@ModelAttribute UserBean user,String code) {
		return smsService.changePass(user, code);
	}
}
