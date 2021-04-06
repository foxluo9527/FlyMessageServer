package com.util;

import java.util.ArrayList;
import java.util.Date;
import com.po.Token;
import com.po.UserBean;

public class TokenUtil {
	private static ArrayList<Token> tokens=new ArrayList<>();
	public static Token getTokenUser(String tokenString) {
		for(int i=0;i<tokens.size();i++) {
			if (tokens.get(i).getTokenString().equals(tokenString)) {
				return tokens.get(i);
			}
		}
		return null;
	}
	public static String getUserToken(UserBean user) {
		for(int i=0;i<tokens.size();i++) {
			if (tokens.get(i).getUser().getU_id()==user.getU_id()) {
				return tokens.get(i).getTokenString();
			}
		}
		return null;
	}
	public static Token getUserTokenBean(UserBean user) {
		for(int i=0;i<tokens.size();i++) {
			if (tokens.get(i).getUser().getU_id()==user.getU_id()) {
				return tokens.get(i);
			}
		}
		return null;
	}
	public static void addToken(String tokenString,UserBean user,int min) {
		boolean flag=false;
		for(int i=0;i<tokens.size();i++) {
			if (tokens.get(i).getUser().getU_id()==user.getU_id()) {
				tokens.get(i).setTokenString(tokenString);
				tokens.get(i).setEndTime(new Date().getTime()+min*60*1000);
				flag=true;
			}
		}if (!flag) {
			tokens.add(new Token(tokenString,user,new Date().getTime()+min*60*1000));
		}
	}
	public static void setLoginUser(String tokenString,UserBean user) {
		for(int i=0;i<tokens.size();i++) {
			if (tokens.get(i).getTokenString().equals(tokenString)) {
				tokens.get(i).setUser(user);
			}
		}
	}
	public static boolean removeToken(String tokenString) {
		for(int i=0;i<tokens.size();i++) {
			if (tokens.get(i).getTokenString().equals(tokenString)) {
				tokens.remove(i);
				return true;
			}
		}
		return false;
	}
	public static void removeAllToken() {
		tokens.clear();
	}
	public static boolean checkTimeOut(String tokenString) {
		for(int i=0;i<tokens.size();i++) {
			if (tokens.get(i).getTokenString().equals(tokenString)) {
				Date date = new Date();
				long nowTime=date.getTime();
				if (nowTime<tokens.get(i).getEndTime()) {
					System.out.println("当前时间:"+nowTime+",token过期时间:"+tokens.get(i).getEndTime());
					return true;
				}
			}
		}
		return false;
	}
	public static void resetTimeOut(String tokenString) {
		for(int i=0;i<tokens.size();i++) {
			if (tokens.get(i).getTokenString().equals(tokenString)) {
				tokens.get(i).setEndTime(new Date().getTime()+15*60*1000);
				System.out.println("重置token过期时间:"+tokens.get(i).getEndTime());
			}
		}
	}
	public static void resetTimeOut(String tokenString,int min) {
		for(int i=0;i<tokens.size();i++) {
			if (tokens.get(i).getTokenString().equals(tokenString)) {
				tokens.get(i).setEndTime(new Date().getTime()+min*60*1000);
				System.out.println("重置token过期时间:"+tokens.get(i).getEndTime());
			}
		}
	}
}

