package com.service;

import com.po.BlackListBean;
import com.po.FriendBean;
import com.po.FriendRequestBean;
import com.po.UserPageQuery;

public interface FriendService {
    String addBlackList(BlackListBean blackList);

    String getUserBlackList(UserPageQuery query);

    String checkBlackList(BlackListBean blackList);

    String delBlackList(BlackListBean blackList);

    String addFriendRequest(FriendRequestBean friendRequest);

    String delFriendRequest(FriendRequestBean friendRequest);

    String acceptFriendRequest(FriendRequestBean friendRequest);

    String getFriendRequest(UserPageQuery query);

    String delFriend(FriendBean friendBean);

    String changeFriendRemarkName(FriendBean friend);

    String getUserFriends(UserPageQuery query);
}
