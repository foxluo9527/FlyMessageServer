package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.po.GroupBean;
import com.po.GroupMember;
import com.po.UserPageQuery;

@Repository("GroupDao")
@Mapper
public interface GroupDao {
	public int addGroup(GroupBean group);
	
	public int updateGroupMsg(GroupBean group);
	
	public List<GroupBean> getUserGroups(UserPageQuery query);
	
	public List<GroupBean> getGroups();
	
	public GroupBean getGroupMsg(int g_id);
	
	public List<GroupBean> queryGroup(UserPageQuery query);
	
	public GroupMember getGroupCreater(int g_id);
	
	public int delGroup(int g_id);
	
	public int addGroupMember(GroupMember member);
	
	public int changeGroupMemberRemarkName(GroupMember member);
	
	public int delGroupMember(GroupMember member);
	
	public int getGroupMCount(int g_id);
	
	public int checkGroupMember(GroupMember member);
	
	public List<GroupMember> getGroupMember(UserPageQuery query);
	
	public List<GroupMember> getGroupAllMember(int g_id);
	
	public GroupMember getGroupMemberById(GroupMember groupMember);
}
