package com.util;

import java.util.ArrayList;
import java.util.Date;

import com.po.Token;
import com.po.UserBean;

public class TokenUtil {
    private static final ArrayList<Token> tokens = new ArrayList<>();

    public static Token getTokenUser(String tokenString) {
		for (Token token : tokens) {
			if (token.getTokenString().equals(tokenString)) {
				return token;
			}
		}
        return null;
    }

    public static String getUserToken(UserBean user) {
		for (Token token : tokens) {
			if (token.getUser().getU_id() == user.getU_id()) {
				return token.getTokenString();
			}
		}
        return null;
    }

    public static Token getUserTokenBean(UserBean user) {
		for (Token token : tokens) {
			if (token.getUser().getU_id() == user.getU_id()) {
				return token;
			}
		}
        return null;
    }

    public static void addToken(String tokenString, UserBean user, int min) {
        boolean flag = false;
		for (Token token : tokens) {
			if (token.getUser().getU_id() == user.getU_id()) {
				token.setTokenString(tokenString);
				token.setEndTime(new Date().getTime() + min * 60 * 1000);
				flag = true;
			}
		}
        if (!flag) {
            tokens.add(new Token(tokenString, user, new Date().getTime() + min * 60 * 1000));
        }
    }

    public static void setLoginUser(String tokenString, UserBean user) {
		for (Token token : tokens) {
			if (token.getTokenString().equals(tokenString)) {
				token.setUser(user);
			}
		}
    }

    public static boolean removeToken(String tokenString) {
        for (int i = 0; i < tokens.size(); i++) {
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
		for (Token token : tokens) {
			if (token.getTokenString().equals(tokenString)) {
				Date date = new Date();
				long nowTime = date.getTime();
				if (nowTime < token.getEndTime()) {
					System.out.println("当前时间:" + nowTime + ",token过期时间:" + token.getEndTime());
					return true;
				}
			}
		}
        return false;
    }

    public static void resetTimeOut(String tokenString) {
		for (Token token : tokens) {
			if (token.getTokenString().equals(tokenString)) {
				token.setEndTime(new Date().getTime() + 15 * 60 * 1000);
				System.out.println("重置token过期时间:" + token.getEndTime());
			}
		}
    }

    public static void resetTimeOut(String tokenString, int min) {
		for (Token token : tokens) {
			if (token.getTokenString().equals(tokenString)) {
				token.setEndTime(new Date().getTime() + min * 60 * 1000);
				System.out.println("重置token过期时间:" + token.getEndTime());
			}
		}
    }
}

