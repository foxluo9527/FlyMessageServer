package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Constant {
	static {
        InputStream inputStream = Constant.class.getResourceAsStream("/ip.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            String ipConfig = properties.getProperty("ipconfig");
            Constant.addr=ipConfig;
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
	public static String addr;
	public static String defaultHead="http://"+addr+"FlyMessage/images/defaultHead.png";
	public static final String API_KEY="c2b3ecaa39bb1ef293b45b603fb3ef41";
	public static final String SEND_URL="https://sms.yunpian.com/v2/sms/single_send.json";
	public static final String TEXT_IDENTIFY="【围博编程】您的验证码是#code#";
	public static final String TEXT_LOGIN="【围博编程】正在进行登录操作，您的验证码是#code#";
	public static final String TEXT_CHANGE_PASS="【围博编程】您正在修改用户密码,验证码为#code#,请勿泄漏给他人";
	public static final int MODEL_LOGIN=1;
	public static final int MODEL_REGISTER=2;
	public static final int MODEL_CHANGE_PASS=3;
}
