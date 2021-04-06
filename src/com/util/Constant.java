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
	public static final String TEXT_IDENTIFY="��Χ����̡�������֤����#code#";
	public static final String TEXT_LOGIN="��Χ����̡����ڽ��е�¼������������֤����#code#";
	public static final String TEXT_CHANGE_PASS="��Χ����̡��������޸��û�����,��֤��Ϊ#code#,����й©������";
	public static final int MODEL_LOGIN=1;
	public static final int MODEL_REGISTER=2;
	public static final int MODEL_CHANGE_PASS=3;
}