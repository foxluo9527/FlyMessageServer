package com.service;

import com.po.BlackListBean;
import com.po.FriendBean;
import com.po.FriendRequestBean;
import com.po.UserPageQuery;

public interface FriendService {
	public String addBlackList(BlackListBean blackList);
	
	public String getUserBlackList(UserPageQuery query);
	
	public String checkBlackList(BlackListBean blackList);
	
	public String delBlackList(BlackListBean blackList);
	
	public String addFriendRequest(FriendRequestBean friendRequest);
	
	public String delFriendRequest(FriendRequestBean friendRequest);
	
	public String acceptFriendRequest(FriendRequestBean friendRequest);
	
	public String getFriendRequest(UserPageQuery query);
	
	public String delFriend( FriendBean friendBean);
	
	public String changeFriendRemarkName(FriendBean friend);
	
	public String getUserFriends(UserPageQuery query);
}
