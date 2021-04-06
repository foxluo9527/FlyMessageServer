package com.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dao.FriendDao;
import com.dao.UserDao;
import com.po.BlackListBean;
import com.po.FriendBean;
import com.po.FriendRequestBean;
import com.po.MessageBean;
import com.po.UserBean;
import com.po.UserPageQuery;
import com.websocket.MsgScoketHandle;

@Service("FriendService")
@Transactional
public class FriendServiceImpl implements FriendService {
	
	@Autowired
	private FriendDao friendDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private MsgScoketHandle msgScoketHandle;
	@Autowired
	private MessageService messageService;
	
	@Override
	public String addBlackList(BlackListBean blackList) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		blackList.setTime(new Date());
		if (userDao.checkBlackList(blackList)==0&&friendDao.addBlackList(blackList)>0) {
			result.put("code", 200);
			result.put("msg", "���Ӻ������ɹ�");
		}else {
			result.put("code", 500);
			result.put("msg", "���Ӻ�����ʧ��");
		}
		return result.toJSONString();
	}

	@Override
	public String getUserBlackList(UserPageQuery query) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			List<BlackListBean> blackLists=friendDao.getUserBlackList(query);
			result.put("code", 200);
			JSONArray blArray=new JSONArray();
			for (BlackListBean blackListBean : blackLists) {
				JSONObject blJsonObject=(JSONObject) JSONObject.toJSON(blackListBean);
				UserBean userBean=userDao.getUserById(blackListBean.getObject_u_id());
				if (userBean!=null) {
					JSONObject userJson=(JSONObject) JSONObject.toJSON(userBean);
					userJson.remove("u_pass");
					userJson.remove("u_phone");
					userJson.remove("u_forbidden");
					blJsonObject.put("user", userJson);
				}
				blArray.add(blJsonObject);
			}
			result.put("blackLists",blArray);
			result.put("msg", "��ȡ�������ɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "��ȡ������ʧ��");
		}
		return result.toJSONString();
	}
	@Override
	public String checkBlackList(BlackListBean blackList) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			if (friendDao.checkBlackList(blackList)>0) {
				result.put("inBlacklist", true);
			}else {
				result.put("inBlacklist", false);
			}
			result.put("code", 200);
			result.put("msg", "У���������Ϣ�ɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "У���������Ϣʧ��");
		}
		return result.toJSONString();
	}
	@Override
	public String delBlackList(BlackListBean blackList) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			if (friendDao.delBlackList(blackList)>0) {
				result.put("code", 200);
				result.put("msg", "ɾ���������ɹ�");
			}else {
				result.put("code", 500);
				result.put("msg", "ɾ��������ʧ��");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "ɾ��������ʧ��");
		}
		return result.toJSONString();
	}

	@Override
	public String addFriendRequest(FriendRequestBean friendRequest) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		friendRequest.setTime(new Date());
		try {
			FriendBean friend=new FriendBean();
			friend.setF_object_u_id(friendRequest.getRq_object_u_id());
			friend.setF_source_u_id(friendRequest.getRq_source_u_id());
			//���Ǻ��Ѳ������Ӻ���
			if (friendDao.checkFriendRelationship(friend)==0&&friendDao.addFriendRequest(friendRequest)>0) {
				result.put("code", 200);
				result.put("msg", "���Ӻ�������ɹ�");
				msgScoketHandle.sendFrienRequest(friendRequest);
			}else {
				result.put("code", 500);
				result.put("msg", "���Ӻ�������ʧ��");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "���Ӻ�������ʧ��");
		}
		return result.toJSONString();
	}

	@Override
	public String acceptFriendRequest(FriendRequestBean friendRequest) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		int u_id=friendRequest.getRq_object_u_id();
		try {
			friendRequest=friendDao.getFriendRequestById(friendRequest.getRq_id());
			if (u_id==friendRequest.getRq_object_u_id()) {
				FriendBean friend=new FriendBean();
				friend.setTime(new Date());
				friend.setF_object_u_id(friendRequest.getRq_object_u_id());
				friend.setF_source_u_id(friendRequest.getRq_source_u_id());
				if (friendRequest.getRq_remarks_name()==null) {
					friend.setF_remarks_name(userDao.getUserById(u_id).getU_nick_name());
				}else {
					friend.setF_remarks_name(friendRequest.getRq_remarks_name());
				}
				if (friendDao.checkFriendRelationship(friend)>0) {
					friendDao.receiveFriendRequest(friend);
					result.put("code", 500);
					result.put("msg", "ͨ����������ʧ��:�Ѿ��Ǻ���");
				}else {
					if (friendDao.addFriend(friend)>0) {
						friend.setF_object_u_id(friendRequest.getRq_source_u_id());
						friend.setF_source_u_id(friendRequest.getRq_object_u_id());
						friend.setF_remarks_name(userDao.getUserById(friendRequest.getRq_source_u_id()).getU_nick_name());
						friendDao.addFriend(friend);
						friendDao.delFriendRequest(friendRequest);
						result.put("code", 200);
						result.put("msg", "ͨ����������ɹ�");
						MessageBean message=new MessageBean(0,friendRequest.getRq_source_u_id(),0,0,0,
								friendRequest.getRq_object_u_id(),0,"�����Ѿ���Ϊ���ѣ����Կ�ʼ������",new Date());
						messageService.sendMessage(message,null);
					}else {
						result.put("code", 500);
						result.put("msg", "ͨ����������ʧ��");
					}
				}
			}else {
				result.put("code", 500);
				result.put("msg", "ͨ����������ʧ��");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "ͨ����������ʧ��");
		}
		return result.toJSONString();
	}
	
	@Override
	public String delFriendRequest(FriendRequestBean friendRequest) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			if (friendDao.delFriendRequest(friendRequest)>0) {
				result.put("code", 200);
				result.put("msg", "ɾ����������ɹ�");
			}else {
				result.put("code", 500);
				result.put("msg", "ɾ����������ʧ��");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "ɾ����������ʧ��");
		}
		return result.toJSONString();
	}

	@Override
	public String getFriendRequest(UserPageQuery query) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			List<FriendRequestBean> friendRequests=friendDao.getFriendRequest(query);
			JSONArray friendArray=new JSONArray();
			for (FriendRequestBean friendRequestBean : friendRequests) {
				JSONObject friendJson=(JSONObject) JSONObject.toJSON(friendRequestBean);
				FriendBean friend=new FriendBean();
				friend.setF_object_u_id(friendRequestBean.getRq_object_u_id());
				friend.setF_source_u_id(friendRequestBean.getRq_source_u_id());
				if (friendDao.checkFriendRelationship(friend)>0) {
					friendJson.put("isAccept", true);
				}else {
					friendJson.put("isAccept", false);
				}
				UserBean userBean=userDao.getUserById(friendRequestBean.getRq_source_u_id());
				JSONObject userJson=(JSONObject) JSONObject.toJSON(userBean);
				userJson.put("isFriend", true);
				userJson.put("f_id", friend.getF_id());
				userJson.put("isOnline", msgScoketHandle.getUserOnlineState(friend.getF_object_u_id()));
				userJson.remove("u_pass");
				userJson.remove("u_phone");
				userJson.remove("u_forbidden");
				friendJson.put("rqUser", userJson);
				friendArray.add(friendJson);
				friendDao.receiveFriendRequest(friend);
			}
			result.put("code", 200);
			result.put("friendRequests", friendArray);
			result.put("msg", "��ȡ��������ɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "��ȡ��������ʧ��");
		}
		return result.toJSONString();
	}
	
	@Override
	public String changeFriendRemarkName(FriendBean friend) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			FriendBean friendBean=friendDao.getFriendById(friend.getF_id());
			if (friendBean.getF_source_u_id()==friend.getF_source_u_id()
					&&friendDao.changeFriendRemarkName(friend)>0) {
				result.put("code", 200);
				result.put("msg", "�޸ĺ��ѱ�ע�ɹ�");
			}else {
				result.put("code", 500);
				result.put("msg", "�޸ĺ��ѱ�עʧ��");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "�޸ĺ��ѱ�עʧ��");
		}
		return result.toJSONString();
	}
	
	@Override
	public String delFriend(FriendBean friendBean) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		int u_id=friendBean.getF_source_u_id();
		try {
			friendBean=friendDao.getFriendById(friendBean.getF_id());
			if (u_id==friendBean.getF_source_u_id()&&friendDao.delFriend(friendBean)>0) {
				result.put("code", 200);
				result.put("msg", "ɾ�����ѳɹ�");
			}else {
				result.put("code", 500);
				result.put("msg", "ɾ������ʧ��");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "ɾ������ʧ��");
		}
		return result.toJSONString();
	}

	@Override
	public String getUserFriends(UserPageQuery query) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			JSONArray friendArray=new JSONArray();
			List<FriendBean> friends=friendDao.getUserFriends(query);
			for (FriendBean friend : friends) {
				JSONObject friendJson=(JSONObject) JSONObject.toJSON(friend);
				friendJson.put("isOnline", msgScoketHandle.getUserOnlineState(friend.getF_object_u_id()));
				UserBean user=userDao.getUserById(friend.getF_object_u_id());
				JSONObject userJson=(JSONObject) JSONObject.toJSON(user);
				userJson.put("isFriend", true);
				userJson.put("f_id", friend.getF_id());
				userJson.put("isOnline", msgScoketHandle.getUserOnlineState(friend.getF_object_u_id()));
				userJson.remove("u_pass");
				userJson.remove("u_phone");
				userJson.remove("u_forbidden");
				friendJson.put("friendUser", userJson);
				friendArray.add(friendJson);
			}
			result.put("code", 200);
			result.put("friends", JSONObject.toJSON(friendArray));
			result.put("msg", "��ȡ������Ϣ�ɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "��ȡ������Ϣʧ��");
		}
		return result.toJSONString();
	}

}