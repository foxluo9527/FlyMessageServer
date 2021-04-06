package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.po.BlackListBean;
import com.po.FriendBean;
import com.po.FriendRequestBean;
import com.po.Token;
import com.po.UserPageQuery;
import com.service.FriendService;
import com.util.TokenUtil;

/**
 * 黑名单几个接口测试
 * 明天:接口规范出册
 * 实现群组接口
 * 群:创建，群主修改群详情，用户获取所有隶属的群信息（list），用户查看群详情(判断用户是否为群成员返回 isMember参数)，用户搜索群（List 返回 isMember参数），解散群（检测是否为群主所为）
 * 群成员:用户加入群(无需审核),用户退出群，发送群消息（群转发给所有用户实现），获取群所有成员信息（用户需是群成员）
 * @author ASUS
 *
 */
@Controller
@RequestMapping("/friend")
public class FriendController {
	@Autowired FriendService friendService;
	
	@RequestMapping(value="/addBlackList",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addBlackList(@ModelAttribute BlackListBean blackList,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			blackList.setSource_u_id(token.getUser().getU_id());
		}
		return friendService.addBlackList(blackList);
	}
	
	@RequestMapping(value="/getUserBlackList",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getUserBlackList(@ModelAttribute UserPageQuery query,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			query.setU_id(token.getUser().getU_id());
		}
		return friendService.getUserBlackList(query);
	}
	
	@RequestMapping(value="/checkBlackList",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String checkBlackList(@ModelAttribute BlackListBean blackList,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			blackList.setSource_u_id(token.getUser().getU_id());
		}
		return friendService.checkBlackList(blackList);
	}
	
	
	@RequestMapping(value="/delBlackList",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delBlackList(@ModelAttribute BlackListBean blackList,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			blackList.setSource_u_id(token.getUser().getU_id());
		}
		return friendService.delBlackList(blackList);
	}
	
	@RequestMapping(value="/addFriendRequest",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addFriendRequest(@ModelAttribute FriendRequestBean friendRequest,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			friendRequest.setRq_source_u_id(token.getUser().getU_id());
		}
		return friendService.addFriendRequest(friendRequest);
	}
	
	@RequestMapping(value="/delFriendRequest",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delFriendRequest(@ModelAttribute FriendRequestBean friendRequest,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			friendRequest.setRq_object_u_id(token.getUser().getU_id());
		}
		return friendService.delFriendRequest(friendRequest);
	}
	
	@RequestMapping(value="/acceptFriendRequest",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String acceptFriendRequest(@ModelAttribute FriendRequestBean friendRequest,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			friendRequest.setRq_object_u_id(token.getUser().getU_id());
		}
		return friendService.acceptFriendRequest(friendRequest);
	}
	
	@RequestMapping(value="/getFriendRequest",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getFriendRequest(@ModelAttribute UserPageQuery query,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			query.setU_id(token.getUser().getU_id());
		}
		return friendService.getFriendRequest(query);
	}
	
	@RequestMapping(value="/delFriend",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delFriend(@ModelAttribute FriendBean friend,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			friend.setF_source_u_id(token.getUser().getU_id());
		}
		return friendService.delFriend(friend);
	}
	
	@RequestMapping(value="/changeFriendRemarkName",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String changeFriendRemarkName(@ModelAttribute FriendBean friend,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			friend.setF_source_u_id(token.getUser().getU_id());
		}
		return friendService.changeFriendRemarkName(friend);
	}
	
	@RequestMapping(value="/getUserFriends",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getUserFriends(@ModelAttribute UserPageQuery query,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			query.setU_id(token.getUser().getU_id());
		}
		return friendService.getUserFriends(query);
	}
}
