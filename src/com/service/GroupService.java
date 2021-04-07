package com.service;

import javax.servlet.http.HttpServletRequest;

import com.po.GroupBean;
import com.po.GroupMember;
import com.po.UserBean;
import com.po.UserPageQuery;

public interface GroupService {
	
	String createGroup(GroupBean group,UserBean user);
	
	String changeGroupHead(GroupBean group,HttpServletRequest request);
	
	String updateGroupMsg(GroupBean group,int u_id);
	
	String getUserGroups(UserPageQuery query);
	
	String getGroupMsg(int u_id,int g_id);
	
	String queryGroup(UserPageQuery query);
	
	String delGroup(int u_id,int g_id);
	
	String addGroupMember(GroupMember member);
	
	String changeGroupMemberRemarkName(GroupMember member);
	
	String delGroupMember(GroupMember member,int del_u_id);
	
	String getGroupMember(UserPageQuery query,int g_id);
	
	String getGroupCreator(GroupBean group,String loginToken);
	
	String getGroupMemberById(UserPageQuery query,int g_id,int object_u_id);
}
