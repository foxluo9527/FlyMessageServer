package com.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.po.GroupBean;
import com.po.GroupMember;
import com.po.Token;
import com.po.UserBean;
import com.po.UserPageQuery;
import com.service.GroupService;
import com.util.TokenUtil;

@Controller
@RequestMapping("/group")
public class GroupController {
	@Autowired
	private GroupService groupService;
	
	/**
	 * GET: g_name,g_introduce,loginToken
	 * @param group
	 * @param loginToken
	 * @return
	 */
	@RequestMapping(value="/createGroup",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String createGroup(@ModelAttribute GroupBean group,String loginToken) {
		UserBean user=null;
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			user=token.getUser();
		}
		group.setG_num(UUID.randomUUID().toString());
		group.setG_create_time(new Date());
		return groupService.createGroup(group,user);
	}
	/**
	 * GET:g_id,g_name,g_introduce,loginToken
	 * @param group
	 * @param loginToken
	 * @return
	 */
	@RequestMapping(value="/updateGroupMsg",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String updateGroupMsg(@ModelAttribute GroupBean group,String loginToken) {
		int u_id=0;
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			u_id=token.getUser().getU_id();
		}
		return groupService.updateGroupMsg(group,u_id);
	}
	
	/**
	 * GET:loginToken,pageSize,pageIndex
	 * @param loginToken 
	 * @return
	 */
	@RequestMapping(value="/getUserGroups",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getUserGroups(@ModelAttribute UserPageQuery query,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			query.setU_id(token.getUser().getU_id());
		}
		return groupService.getUserGroups(query);
	}
	
	/**
	 * GET:g_id,loginToken
	 * @param g_id
	 * @param loginToken
	 * @return
	 */
	@RequestMapping(value="/getGroupMsg",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getGroupMsg(int g_id,String loginToken) {
		UserBean user=null;
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			user=token.getUser();
		}
		return groupService.getGroupMsg(user.getU_id(),g_id);
	}
	
	/**
	 * GET:pageSize,pageIndex,queryString,loginToken
	 * @param loginToken
	 * @return
	 */
	@RequestMapping(value="/queryGroup",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String queryGroup(@ModelAttribute UserPageQuery query,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			query.setU_id(token.getUser().getU_id());
		}
		return groupService.queryGroup(query);
	}
	
	/**
	 * GET:g_id,loginToken
	 * @param group
	 * @param loginToken
	 * @return
	 */
	@RequestMapping(value="/delGroup",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delGroup(int g_id,String loginToken) {
		UserBean user=null;
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			user=token.getUser();
		}
		return groupService.delGroup(user.getU_id(),g_id);
	}
	
	/**
	 * GET:g_id,loginToken
	 * @param group
	 * @param loginToken
	 * @return
	 */
	@RequestMapping(value="/addGroupMember",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addGroupMember(@ModelAttribute GroupMember member,String loginToken) {
		UserBean user=null;
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			user=token.getUser();
		}
		member.setU_id(user.getU_id());
		member.setPower(0);
		member.setG_nick_name(user.getU_nick_name());
		member.setAdd_time(new Date());
		return groupService.addGroupMember(member);
	}
	
	/**
	 * GET:g_id,g_nick_name,loginToken
	 * @param member
	 * @param loginToken
	 * @return
	 */
	@RequestMapping(value="/changeGroupMemberRemarkName",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String changeGroupMemberRemarkName(@ModelAttribute GroupMember member,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			member.setU_id(token.getUser().getU_id());
		}
		return groupService.changeGroupMemberRemarkName(member);
	}
	
	/**
	 * GET:g_id,del_u_id,loginToken
	 * @param group
	 * @param loginToken
	 * @return
	 */
	@RequestMapping(value="/delGroupMember",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delGroupMember(@ModelAttribute GroupMember member,int del_u_id,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			member.setU_id(token.getUser().getU_id());
		}
		return groupService.delGroupMember(member,del_u_id);
	}
	
	/**
	 * GET:g_id,loginToken,pageSize,pageIndex
	 * @param g_id
	 * @return
	 */
	@RequestMapping(value="/getGroupMember",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getGroupMember(@ModelAttribute UserPageQuery query,int g_id,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			query.setU_id(token.getUser().getU_id());
		}
		return groupService.getGroupMember(query,g_id);
	}
	
	/**
	 * GET:g_id,object_u_id,loginToken,pageSize,pageIndex
	 * @param g_id
	 * @return
	 */
	@RequestMapping(value="/getGroupMemberById",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getGroupMemberById(@ModelAttribute UserPageQuery query,int g_id,int object_u_id,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			query.setU_id(token.getUser().getU_id());
		}
		return groupService.getGroupMemberById(query,g_id,object_u_id);
	}
	/**
	 * GET:g_id,loginToken
	 * POST:file
	 * @param group
	 * @param loginToken
	 * @return
	 */
	@RequestMapping(value="/getGroupCreator",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getGroupCreator(@ModelAttribute GroupBean group,String loginToken) {
		return groupService.getGroupCreator(group, loginToken);
	}
	/**
	 * GET:g_id,loginToken
	 * POST:file
	 * @param group
	 * @param loginToken
	 * @return
	 */
	@RequestMapping(value="/changeGroupHead",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String changeGroupHead(@ModelAttribute GroupBean group,String loginToken,@Autowired HttpServletRequest request) {
		group.setG_create_time(new Date());
		return groupService.changeGroupHead(group, request);
	}

}
