package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.po.BlackListBean;
import com.po.UserBean;
import com.po.UserHeadBean;
import com.po.UserPageQuery;
import com.po.UserPrivacyBean;
import com.po.UserSignBean;
@Repository("UserDao")
@Mapper
public interface UserDao {

	public UserBean getUserById(int u_id);

	public UserBean getUserByName(String u_name);
	
	public UserBean login(UserBean user);

	public int changeHead(UserBean user);

	public List<UserHeadBean> getUserHeads(UserPageQuery query);

	public UserHeadBean getHeadById(int h_id);
	
	public int addUserSign(UserSignBean sign);
	
	public int delUserSign(UserSignBean sign);

	public List<UserSignBean> getUserSigns(UserPageQuery query);

	public List<UserBean> queryUser(UserPageQuery query);

	public int updateMsg(UserBean user);

	public int changeUserPrivacy(UserPrivacyBean privacy);

	public UserPrivacyBean getUserPrivacy(UserBean user);

	public int delUserHead(int h_id);

	public int forbiddenUser(UserBean user);

	public UserBean checkPhone(String phone);

	public int register(UserBean user);

	public int addUserPrivacy(UserBean user);

	public int changePass(UserBean user);
	
	public int checkShowMsgPrivacy(int u_id);
	
	public int checkBlackList(BlackListBean blackList);
	
	public int checkUserName(String u_name);

	public int changePhone(UserBean user);
}
