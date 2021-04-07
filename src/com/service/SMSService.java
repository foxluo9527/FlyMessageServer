package com.service;

import com.po.UserBean;

public interface SMSService {
    String sendRegisterSMS(String phone);

    String sendLoginSMS(String phone);

    String sendChangePassSMS(String phone);

    String noPassLogin(String phone, String code);

    String register(UserBean user, String code);

    String changePass(UserBean user, String code);
}
