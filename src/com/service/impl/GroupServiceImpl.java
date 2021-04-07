package com.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.service.GroupService;
import com.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dao.GroupDao;
import com.po.GroupBean;
import com.po.GroupMember;
import com.po.MessageBean;
import com.po.UserBean;
import com.po.UserPageQuery;
import com.util.Constant;
import com.util.MyUtil;

@Service("GroupService")
@Transactional
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupDao groupDao;
	@Autowired 
	private MessageService messageService;
	@Override
	public String createGroup(GroupBean group,UserBean user) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			if (groupDao.addGroup(group)>0) {
				//创建群聊后添加群主
				GroupMember member=new GroupMember();
				member.setG_id(group.getG_id());
				member.setAdd_time(group.getG_create_time());
				member.setG_nick_name(user.getU_nick_name());
				member.setPower(1);
				member.setU_id(user.getU_id());
				groupDao.addGroupMember(member);
				MessageBean message=new MessageBean(0,user.getU_id(),group.getG_id(),1,0,
						group.getG_id(),0,"群聊创建成功，可以开始聊天啦",new Date());
				messageService.sendMessage(message,null);
				result.put("code", 200);
				result.put("g_id", group.getG_id());
				result.put("msg", "创建群聊成功");
			}else {
				result.put("code", 500);
				result.put("msg", "创建群聊失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "创建群聊失败");
		}
		return result.toJSONString();
	}

	@Override
	public String updateGroupMsg(GroupBean group,int u_id) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			if (groupDao.getGroupCreater(group.getG_id()).getU_id()==u_id&&groupDao.updateGroupMsg(group)>0) {
				result.put("code", 200);
				result.put("msg", "修改群聊资料成功");
			}else {
				result.put("code", 500);
				result.put("msg", "修改群聊资料失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "修改群聊资料失败");
		}
		return result.toJSONString();
	}

	@Override
	public String getUserGroups(UserPageQuery query) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			List<GroupBean> groups=groupDao.getUserGroups(query);
			JSONArray groupArray=new JSONArray();
			for (GroupBean group : groups) {
				JSONObject groupJson=(JSONObject) JSONObject.toJSON(group);
				if (groupDao.getGroupCreater(group.getG_id()).getU_id()==query.getU_id()) {
					groupJson.put("isCreater", true);
				}else {
					groupJson.put("isCreater", false);
				}
				groupArray.add(groupJson);
			}
			result.put("code", 200);
			result.put("groups", groupArray);
			result.put("msg", "获取用户群聊信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "获取用户群聊信息失败");
		}
		return result.toJSONString();
	}

	@Override
	public String getGroupMsg(int u_id, int g_id) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			GroupBean group=groupDao.getGroupMsg(g_id);
			if (group!=null) {
				JSONObject groupJson=(JSONObject) JSONObject.toJSON(group);
				GroupMember member=new GroupMember();
				member.setG_id(group.getG_id());
				member.setU_id(u_id);
				if (groupDao.checkGroupMember(member)>0) {
					groupJson.put("isMember", true);
					if (groupDao.getGroupCreater(g_id).getU_id()==u_id) {
						groupJson.put("isCreater", true);
					}else {
						groupJson.put("isCreater", false);
					}
				}else {
					groupJson.put("isMember", false);
					groupJson.put("isCreater", false);
				}
				result.put("code", 200);
				result.put("group", groupJson);
				result.put("msg", "获取群聊信息成功");
			}else {
				result.put("code", 500);
				result.put("msg", "获取群聊信息失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "获取群聊信息失败");
		}
		return result.toJSONString();
	}

	@Override
	public String queryGroup(UserPageQuery query) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			JSONArray groupArray=new JSONArray();
			List<GroupBean> groups=groupDao.queryGroup(query);
			for (GroupBean group : groups) {
				JSONObject groupJson=(JSONObject) JSONObject.toJSON(group);
				GroupMember member=new GroupMember();
				member.setG_id(group.getG_id());
				member.setU_id(query.getU_id());
				if (groupDao.checkGroupMember(member)>0) {
					groupJson.put("isMember", true);
					if (groupDao.getGroupCreater(group.getG_id()).getU_id()==query.getU_id()) {
						groupJson.put("isCreater", true);
					}else {
						groupJson.put("isCreater", false);
					}
				}else {
					groupJson.put("isMember", false);
					groupJson.put("isCreater", false);
				}
				groupArray.add(groupJson);
			}
			result.put("code", 200);
			result.put("groups", groupArray);
			result.put("msg", "搜索群聊信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "搜索群聊信息失败");
		}
		return result.toJSONString();
	}

	@Override
	public String delGroup(int u_id, int g_id) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			if (groupDao.getGroupCreater(g_id).getU_id()==u_id&&groupDao.delGroup(g_id)>0) {
				result.put("code", 200);
				result.put("msg", "删除群聊成功");
			}else {
				result.put("code", 500);
				result.put("msg", "删除群聊失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "删除群聊失败");
		}
		return result.toJSONString();
	}

	@Override
	public String addGroupMember(GroupMember member) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			if (groupDao.addGroupMember(member)>0) {
				result.put("code", 200);
				result.put("msg", "添加群聊成员成功");
				MessageBean message=new MessageBean(0,member.getU_id(),member.getG_id(),1,0,
						member.getG_id(),0,"加入群聊成功，开始和我聊天吧",new Date());
				messageService.sendMessage(message,null);
			}else {
				result.put("code", 500);
				result.put("msg", "添加群聊成员失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "添加群聊成员失败");
		}
		return result.toJSONString();
	}

	@Override
	public String changeGroupMemberRemarkName(GroupMember member) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			if (groupDao.checkGroupMember(member)>0&&groupDao.changeGroupMemberRemarkName(member)>0) {
				result.put("code", 200);
				result.put("msg", "修改群备注成功");
			}else {
				result.put("code", 500);
				result.put("msg", "修改群备注失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "修改群备注失败");
		}
		return result.toJSONString();
	}
	
	@Override
	public String delGroupMember(GroupMember member,int del_u_id) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			GroupMember creater=groupDao.getGroupCreater(member.getG_id());
			if (del_u_id==member.getU_id()||creater.getU_id()==member.getU_id()) {
				if (creater.getU_id()!=del_u_id) {
					member.setU_id(del_u_id);
					if (groupDao.delGroupMember(member)>0) {
						result.put("code", 200);
						result.put("msg", "删除群聊成员成功");
					}else {
						result.put("code", 500);
						result.put("msg", "删除群聊成员失败");
					}
				}else {
					return delGroup(del_u_id, member.getG_id());
				}
			}else {
				result.put("code", 500);
				result.put("msg", "删除群聊成员失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "删除群聊成员失败");
		}
		return result.toJSONString();
	}

	@Override
	public String getGroupMember(UserPageQuery query,int g_id) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			GroupMember member=new GroupMember();
			member.setG_id(g_id);
			member.setU_id(query.getU_id());
			query.setU_id(g_id);
			if (groupDao.checkGroupMember(member)>0) {
				List<GroupMember> groupMembers=groupDao.getGroupMember(query);
				result.put("code", 200);
				result.put("group_members", JSONObject.toJSON(groupMembers));
				result.put("msg", "获取群聊成员成功");
			}else {
				result.put("code", 500);
				result.put("msg", "获取群聊成员失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "获取群聊成员失败");
		}
		return result.toJSONString();
	}
	@Override
	public String getGroupMemberById(UserPageQuery query,int g_id,int object_u_id) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			GroupMember member=new GroupMember();
			member.setG_id(g_id);
			member.setU_id(query.getU_id());
			query.setU_id(g_id);
			if (groupDao.checkGroupMember(member)>0) {
				GroupMember sMember=new GroupMember();
				sMember.setG_id(g_id);
				sMember.setU_id(object_u_id);
				GroupMember groupMember=groupDao.getGroupMemberById(sMember);
				if (groupMember!=null) {
					result.put("code", 200);
					result.put("group_member", JSONObject.toJSON(groupMember));
					result.put("msg", "获取群聊成员信息成功");
				}else {
					result.put("code", 500);
					result.put("msg", "获取群聊成员信息失败");
				}
			}else {
				result.put("code", 500);
				result.put("msg", "获取群聊成员信息失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "获取群聊成员信息失败");
		}
		return result.toJSONString();
	}
	@Override
	public String changeGroupHead(GroupBean group, HttpServletRequest request) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			boolean flag=false;
			String headUrl="";
			String fileName=group.getHeadImage().getOriginalFilename();
			if (group.getHeadImage()!=null) {
				String realpath = request.getSession().getServletContext().getRealPath("/")+"/images/groupHeads";
				//实现文件上传
				String fileType = fileName.substring(fileName.lastIndexOf('.'));
				//防止文件名重名
				fileName = MyUtil.getStringID() + fileType;
				File targetFile = new File(realpath, fileName); 
				if(!targetFile.exists()){  
		            targetFile.mkdirs();  
		        }
				headUrl="http://"+Constant.addr+"/FlyMessage/images/groupHeads/"+fileName;
				//上传 
		        try {   
		        	group.getHeadImage().transferTo(targetFile);
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }  
		        group.setG_head_img(headUrl);
				if (groupDao.updateGroupMsg(group)>0) {
					flag=true;
				}
			}
			if (flag) {
				result.put("code", 200);
				result.put("headUrl", headUrl);
				result.put("msg", "修改群聊头像成功");
			}else {
				result.put("code", 500);
				result.put("msg", "修改群聊头像失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "修改群聊头像失败");
		}
		return result.toJSONString();
	}

	@Override
	public String getGroupCreator(GroupBean group, String loginToken) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			GroupMember groupMember=groupDao.getGroupCreater(group.getG_id());
			if (groupMember!=null) {
				result.put("code", 200);
				result.put("group_creator", JSONObject.toJSON(groupMember));
				result.put("msg", "获取群主信息成功");
			}else {
				result.put("code", 500);
				result.put("msg", "获取群主信息失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "获取群主信息失败");
		}
		return result.toJSONString();
	}

}
