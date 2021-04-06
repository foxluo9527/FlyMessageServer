package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.po.BlackListBean;
import com.po.FriendBean;
import com.po.FriendRequestBean;
import com.po.UserPageQuery;

@Repository("FriendDao")
@Mapper
public interface FriendDao {
	public int checkFriendRelationship(FriendBean friend);

	public int addBlackList(BlackListBean blackList);

	public List<BlackListBean> getUserBlackList(UserPageQuery query);
	
	public int checkBlackList(BlackListBean blackListBean);
	
	public int delBlackList(BlackListBean blackList);
	
	public int addFriendRequest(FriendRequestBean friendRequest);
	
	public int receiveFriendRequest(FriendBean friendBean);
	
	public int delFriendRequest(FriendRequestBean friendRequest);
	
	public List<FriendRequestBean> getFriendRequest(UserPageQuery query);
	
	public FriendRequestBean getFriendRequestById(int rq_id);
	
	public List<FriendRequestBean> getAllFriendRequest(int u_id);
	
	public FriendBean getFriendById(int f_id);
	
	public int addFriend(FriendBean friend);
	
	public int changeFriendRemarkName(FriendBean friend);
	
	public int delFriend(FriendBean friendBean);
	
	public List<FriendBean> getUserFriends(UserPageQuery query);
}
