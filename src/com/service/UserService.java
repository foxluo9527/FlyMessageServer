package com.service;

import javax.servlet.http.HttpServletRequest;

import com.po.UserBean;
import com.po.UserPageQuery;
import com.po.UserPrivacyBean;
import com.po.UserSignBean;

public interface UserService {
	public String register(UserBean user);
	public String login(String username,String pass);
	public String exit(String token);
	public String getUserByName(int u_id,String u_name);
	public String getUserByID(int u_id,int object_u_id);
	public String searchUser(UserPageQuery query);
	public String updateUserMsg(UserBean user);
	public String changeBgImg(UserBean user,HttpServletRequest request);
	public String changeHead(UserBean user,HttpServletRequest request);
	public String delUserHead(int u_id);
	public String changeOldHead(UserBean user,int h_id);
	public String getUserHeads(UserPageQuery query);
	public String addUserSign(UserSignBean sign);
	public String delUserSign(UserSignBean sign);
	public String getUserSigns(UserPageQuery query);
	public String changeUserPrivacy(UserPrivacyBean privacy);
	public String getUserPrivacy(UserBean user);
	public String checkUserName(String u_name);
	public String checkUserPhone(String phone);
	public String changePass(UserBean user);
	public String changePhone(UserBean user);
}
