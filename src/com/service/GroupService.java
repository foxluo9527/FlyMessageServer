package com.service;

import javax.servlet.http.HttpServletRequest;

import com.po.GroupBean;
import com.po.GroupMember;
import com.po.UserBean;
import com.po.UserPageQuery;

public interface GroupService {
	
	public String createGroup(GroupBean group,UserBean user);
	
	public String changeGroupHead(GroupBean group,HttpServletRequest request);
	
	public String updateGroupMsg(GroupBean group,int u_id);
	
	public String getUserGroups(UserPageQuery query);
	
	public String getGroupMsg(int u_id,int g_id);
	
	public String queryGroup(UserPageQuery query);
	
	public String delGroup(int u_id,int g_id);
	
	public String addGroupMember(GroupMember member);
	
	public String changeGroupMemberRemarkName(GroupMember member);
	
	public String delGroupMember(GroupMember member,int del_u_id);
	
	public String getGroupMember(UserPageQuery query,int g_id);
	
	public String getGroupCreator(GroupBean group,String loginToken);
	
	public String getGroupMemberById(UserPageQuery query,int g_id,int object_u_id);
}
