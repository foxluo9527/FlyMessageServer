package com.service;

import javax.servlet.http.HttpServletRequest;

import com.po.UserBean;
import com.po.UserPageQuery;
import com.po.UserPrivacyBean;
import com.po.UserSignBean;

public interface UserService {
	String register(UserBean user);
	String login(String username,String pass);
	String exit(String token);
	String getUserByName(int u_id,String u_name);
	String getUserByID(int u_id,int object_u_id);
	String searchUser(UserPageQuery query);
	String updateUserMsg(UserBean user);
	String changeBgImg(UserBean user,HttpServletRequest request);
	String changeHead(UserBean user,HttpServletRequest request);
	String delUserHead(int u_id);
	String changeOldHead(UserBean user,int h_id);
	String getUserHeads(UserPageQuery query);
	String addUserSign(UserSignBean sign);
	String delUserSign(UserSignBean sign);
	String getUserSigns(UserPageQuery query);
	String changeUserPrivacy(UserPrivacyBean privacy);
	String getUserPrivacy(UserBean user);
	String checkUserName(String u_name);
	String checkUserPhone(String phone);
	String changePass(UserBean user);
	String changePhone(UserBean user);
}
