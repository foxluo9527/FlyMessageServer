package com.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dao.UserDao;
import com.po.UserBean;
import com.util.SMSUtil;
import com.util.TokenUtil;
import com.util.Base64Util;
import com.util.Constant;

@Service("sMSService")
@Transactional
public class SMSServiceImpl implements SMSService {
	@Autowired
	private UserDao userDao;
	@Override
	public String sendRegisterSMS(String phone) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		UserBean userBean=userDao.checkPhone(phone);
		if (userBean==null) {
			if (SMSUtil.getCount(phone)<3) {
				if (SMSUtil.getTimeout(phone)) {
					if (SMSUtil.sendSMS(phone, Constant.MODEL_REGISTER)) {
						//发送成功
						result.put("code", 200);
						result.put("msg", "验证码发送成功,请注意查收");
					}else {
						//发送失败
						result.put("code", 503);
						result.put("msg", "验证码发送失败,请检查号码后重试");
					}
				}else {
					result.put("code", 504);
					result.put("msg", "验证码发送失败:一分钟内不能重复发送");
				}
			}else {
				//一小时内发送短信次数超过三次
				result.put("code", 505);
				result.put("msg", "验证码发送失败:一小时内发送不能超过三次");
			}
		}else {
			result.put("code", 500);
			result.put("msg", "注册失败:该手机号已注册");
		}
		return result.toJSONString();
	}

	@Override
	public String sendLoginSMS(String phone) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		if (userDao.checkPhone(phone)!=null) {
			if (SMSUtil.getCount(phone)<3) {
				if (SMSUtil.getTimeout(phone)) {
					if (SMSUtil.sendSMS(phone, Constant.MODEL_LOGIN)) {
						//发送成功
						result.put("code", 200);
						result.put("msg", "验证码发送成功,请注意查收");
					}else {
						//发送失败
						result.put("code", 500);
						result.put("msg", "验证码发送失败,请检查号码后重试");
					}
				}else {
					result.put("code", 500);
					result.put("msg", "验证码发送失败:一分钟内不能重复发送");
				}
			}else {
				//一小时内发送短信次数超过三次
				result.put("code", 500);
				result.put("msg", "验证码发送失败:一小时内发送不能超过三次");
			}
		}else {
			result.put("code", 500);
			result.put("msg", "验证码发送失败:该账号暂未注册");
		}
		return result.toJSONString();
	}

	@Override
	public String sendChangePassSMS(String phone) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		if (userDao.checkPhone(phone)!=null) {
			if (SMSUtil.getCount(phone)<3) {
				if (SMSUtil.getTimeout(phone)) {
					if (SMSUtil.sendSMS(phone, Constant.MODEL_CHANGE_PASS)) {
						//发送成功
						result.put("code", 200);
						result.put("msg", "验证码发送成功,请注意查收");
					}else {
						//发送失败
						result.put("code", 500);
						result.put("msg", "验证码发送失败,请检查号码后重试");
					}
				}else {
					result.put("code", 500);
					result.put("msg", "验证码发送失败:一分钟内不能重复发送");
				}
			}else {
				//一小时内发送短信次数超过三次
				result.put("code", 500);
				result.put("msg", "验证码发送失败:一小时内发送不能超过三次");
			}
		}else {
			result.put("code", 500);
			result.put("msg", "验证码发送失败:该账号暂未注册");
		}
		return result.toJSONString();
	}

	@Override
	public String noPassLogin(String phone, String code) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		if (code.equals(SMSUtil.getCode(phone))&&SMSUtil.getModel(phone)==Constant.MODEL_LOGIN) {
			if (new Date().getTime()<SMSUtil.getUsefulTime(phone)) {
				SMSUtil.removeSMS(phone);			//验证成功移除验证码队列
				UserBean loginUser = userDao.checkPhone(phone);
				if(loginUser != null) {
					String userToken=UUID.randomUUID().toString();
					TokenUtil.addToken(userToken, loginUser, 6*60);
					String phoneText=loginUser.getU_phone();
					char[] phoneChars=phoneText.toCharArray();
					for(int i=0;i<phoneText.length();i++) {
						if (i>=3&&i<=6) {
							phoneChars[i]='*';
						}
					}
					phoneText=String.valueOf(phoneChars);
					loginUser.setU_phone(phoneText);
					result.put("code", 200);
					result.put("loginToken", userToken);
					result.put("loginUser",(JSONObject) JSON.toJSON(loginUser));
					result.put("msg", "登录成功");
				}else {
					result.put("code", 502);
					result.put("msg", "登录失败:账号不存在");
				}
			}else {
				result.put("code", 508);
				result.put("msg", "登录失败:验证码已过期");
			}
		}else {
			result.put("code", 509);
			result.put("msg", "登录失败:验证码错误");
		}
		return result.toJSONString();
	}

	@Override
	public String register(UserBean user, String code) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		String phone=user.getU_phone();
		if (code.equals(SMSUtil.getCode(phone))&&SMSUtil.getModel(phone)==Constant.MODEL_REGISTER) {
			if (new Date().getTime()<SMSUtil.getUsefulTime(phone)) {
				SMSUtil.removeSMS(phone);			//验证成功移除验证码队列
				user.setU_create_time(new Date());
				user.setU_name(UUID.randomUUID().toString().substring(9));
				user.setU_pass(Base64Util.encode(user.getU_pass().getBytes()));
				if (userDao.register(user)>0) {
					user.setU_head_img(Constant.defaultHead);
					userDao.changeHead(user);
					userDao.addUserPrivacy(user);
					result.put("code", 200);
					result.put("account",user.getU_name());
					result.put("msg", "注册成功");
				}else {
					result.put("code", 500);
					result.put("msg", "注册失败");
				}
			}else {
				result.put("code", 500);
				result.put("msg", "注册失败:验证码已过期");
			}
		}else {
			result.put("code", 500);
			result.put("msg", "注册失败:验证码错误");
		}
		
		return result.toJSONString();
	}

	@Override
	public String changePass(UserBean user, String code) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		String phone=user.getU_phone();
		if (code.equals(SMSUtil.getCode(phone))&&SMSUtil.getModel(phone)==Constant.MODEL_CHANGE_PASS) {
			if (new Date().getTime()<SMSUtil.getUsefulTime(phone)) {
				user.setU_pass(Base64Util.encode(user.getU_pass().getBytes()));
				if (userDao.changePass(user)>0) {
					SMSUtil.removeSMS(phone);			//验证成功移除验证码队列
					result.put("code", 200);
					result.put("msg", "修改密码成功");
				}else {
					result.put("code", 500);
					result.put("msg", "修改密码失败:请稍后重试");
				}
			}else {
				result.put("code", 500);
				result.put("msg", "修改密码失败:验证码已过期");
			}
		}else {
			result.put("code", 500);
			result.put("msg", "修改密码失败:验证码错误");
		}
		return result.toJSONString();
	}

}
