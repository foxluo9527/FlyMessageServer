package com.service;

import com.po.AdminBean;
import com.po.UserBean;
import com.po.UserPageQuery;

public interface AdminService {
	public String login(String uName,String pass);
	public String addAdmin(AdminBean admin);
	public String delAdmin(AdminBean admin);
	public String updateAdmin(AdminBean admin);
	public String searchAdmin(UserPageQuery query);
	public String getAdmin(UserPageQuery query);
	public String getAdminLog(UserPageQuery query);
	public String searchAdminLog(UserPageQuery query);
	//addAdminLog
	public String getUserCount();
	public String getOnlineUser();
	public String getUserBack(UserPageQuery query);
	public String forbiddenUser(UserBean user);
	public String unForbiddenUser(UserBean user);
	public String getUsers(UserPageQuery query);
	public String searchUser(UserPageQuery query);
	public String getUserMsg(int u_id);
}
