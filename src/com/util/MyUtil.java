package com.util;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {
	/**
	 * 获得时间字符串
	 */
	public static String getStringID(){
		String id;
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
		id=sdf.format(date);
		return id;
	}
}
