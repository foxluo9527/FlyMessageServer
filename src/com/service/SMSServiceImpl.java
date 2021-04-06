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
						//���ͳɹ�
						result.put("code", 200);
						result.put("msg", "��֤�뷢�ͳɹ�,��ע�����");
					}else {
						//����ʧ��
						result.put("code", 503);
						result.put("msg", "��֤�뷢��ʧ��,������������");
					}
				}else {
					result.put("code", 504);
					result.put("msg", "��֤�뷢��ʧ��:һ�����ڲ����ظ�����");
				}
			}else {
				//һСʱ�ڷ��Ͷ��Ŵ�����������
				result.put("code", 505);
				result.put("msg", "��֤�뷢��ʧ��:һСʱ�ڷ��Ͳ��ܳ�������");
			}
		}else {
			result.put("code", 500);
			result.put("msg", "ע��ʧ��:���ֻ�����ע��");
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
						//���ͳɹ�
						result.put("code", 200);
						result.put("msg", "��֤�뷢�ͳɹ�,��ע�����");
					}else {
						//����ʧ��
						result.put("code", 500);
						result.put("msg", "��֤�뷢��ʧ��,������������");
					}
				}else {
					result.put("code", 500);
					result.put("msg", "��֤�뷢��ʧ��:һ�����ڲ����ظ�����");
				}
			}else {
				//һСʱ�ڷ��Ͷ��Ŵ�����������
				result.put("code", 500);
				result.put("msg", "��֤�뷢��ʧ��:һСʱ�ڷ��Ͳ��ܳ�������");
			}
		}else {
			result.put("code", 500);
			result.put("msg", "��֤�뷢��ʧ��:���˺���δע��");
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
						//���ͳɹ�
						result.put("code", 200);
						result.put("msg", "��֤�뷢�ͳɹ�,��ע�����");
					}else {
						//����ʧ��
						result.put("code", 500);
						result.put("msg", "��֤�뷢��ʧ��,������������");
					}
				}else {
					result.put("code", 500);
					result.put("msg", "��֤�뷢��ʧ��:һ�����ڲ����ظ�����");
				}
			}else {
				//һСʱ�ڷ��Ͷ��Ŵ�����������
				result.put("code", 500);
				result.put("msg", "��֤�뷢��ʧ��:һСʱ�ڷ��Ͳ��ܳ�������");
			}
		}else {
			result.put("code", 500);
			result.put("msg", "��֤�뷢��ʧ��:���˺���δע��");
		}
		return result.toJSONString();
	}

	@Override
	public String noPassLogin(String phone, String code) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		if (code.equals(SMSUtil.getCode(phone))&&SMSUtil.getModel(phone)==Constant.MODEL_LOGIN) {
			if (new Date().getTime()<SMSUtil.getUsefulTime(phone)) {
				SMSUtil.removeSMS(phone);			//��֤�ɹ��Ƴ���֤�����
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
					result.put("msg", "��¼�ɹ�");
				}else {
					result.put("code", 502);
					result.put("msg", "��¼ʧ��:�˺Ų�����");
				}
			}else {
				result.put("code", 508);
				result.put("msg", "��¼ʧ��:��֤���ѹ���");
			}
		}else {
			result.put("code", 509);
			result.put("msg", "��¼ʧ��:��֤�����");
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
				SMSUtil.removeSMS(phone);			//��֤�ɹ��Ƴ���֤�����
				user.setU_create_time(new Date());
				user.setU_name(UUID.randomUUID().toString().substring(9));
				user.setU_pass(Base64Util.encode(user.getU_pass().getBytes()));
				if (userDao.register(user)>0) {
					user.setU_head_img(Constant.defaultHead);
					userDao.changeHead(user);
					userDao.addUserPrivacy(user);
					result.put("code", 200);
					result.put("account",user.getU_name());
					result.put("msg", "ע��ɹ�");
				}else {
					result.put("code", 500);
					result.put("msg", "ע��ʧ��");
				}
			}else {
				result.put("code", 500);
				result.put("msg", "ע��ʧ��:��֤���ѹ���");
			}
		}else {
			result.put("code", 500);
			result.put("msg", "ע��ʧ��:��֤�����");
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
					SMSUtil.removeSMS(phone);			//��֤�ɹ��Ƴ���֤�����
					result.put("code", 200);
					result.put("msg", "�޸�����ɹ�");
				}else {
					result.put("code", 500);
					result.put("msg", "�޸�����ʧ��:���Ժ�����");
				}
			}else {
				result.put("code", 500);
				result.put("msg", "�޸�����ʧ��:��֤���ѹ���");
			}
		}else {
			result.put("code", 500);
			result.put("msg", "�޸�����ʧ��:��֤�����");
		}
		return result.toJSONString();
	}

}