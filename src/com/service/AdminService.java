package com.service;

import com.po.AdminBean;
import com.po.UserBean;
import com.po.UserPageQuery;

public interface AdminService {
    String login(String uName, String pass);

    String addAdmin(AdminBean admin);

    String delAdmin(AdminBean admin);

    String updateAdmin(AdminBean admin);

    String searchAdmin(UserPageQuery query);

    String getAdmin(UserPageQuery query);

    String getAdminLog(UserPageQuery query);

    String searchAdminLog(UserPageQuery query);

    //addAdminLog
    String getUserCount();

    String getOnlineUser();

    String getUserBack(UserPageQuery query);

    String forbiddenUser(UserBean user);

    String unForbiddenUser(UserBean user);

    String getUsers(UserPageQuery query);

    String searchUser(UserPageQuery query);

    String getUserMsg(int u_id);
}
