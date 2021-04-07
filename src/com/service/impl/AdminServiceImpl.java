package com.service.impl;

import com.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.dao.UserDao;
import com.po.AdminBean;
import com.po.UserBean;
import com.po.UserPageQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AdminService")
@Transactional
public class AdminServiceImpl implements AdminService {
	@Autowired
	private UserDao userDao;
	@Override
	public String forbiddenUser(UserBean user) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		if (userDao.forbiddenUser(user)>0) {
			result.put("code", "200");
			result.put("msg", "禁用用户成功");
		}else {
			result.put("code", "500");
			result.put("msg", "禁用用户失败");
		}
		return result.toJSONString();
	}
	@Override
	public String unForbiddenUser(UserBean user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String login(String uName, String pass) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String addAdmin(AdminBean admin) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUsers(UserPageQuery query) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String searchUser(UserPageQuery query) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUserMsg(int u_id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String delAdmin(AdminBean admin) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String updateAdmin(AdminBean admin) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String searchAdmin(UserPageQuery query) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getAdmin(UserPageQuery query) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getAdminLog(UserPageQuery query) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String searchAdminLog(UserPageQuery query) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUserCount() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getOnlineUser() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUserBack(UserPageQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

}
