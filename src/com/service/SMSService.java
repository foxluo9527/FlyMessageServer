package com.service;

import com.po.UserBean;

public interface SMSService {
	public String sendRegisterSMS(String phone);
	public String sendLoginSMS(String phone);
	public String sendChangePassSMS(String phone);
	public String noPassLogin(String phone,String code);
	public String register(UserBean user,String code);
	public String changePass(UserBean user,String code);
}
