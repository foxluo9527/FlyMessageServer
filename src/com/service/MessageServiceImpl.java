package com.service;

import java.io.File;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.dao.GroupDao;
import com.dao.MessageDao;
import com.dao.UserDao;
import com.po.BlackListBean;
import com.po.GroupMember;
import com.po.MessageBean;
import com.po.UserPageQuery;
import com.util.Constant;
import com.util.MyUtil;
import com.websocket.MsgScoketHandle;

@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private MsgScoketHandle msgScoketHandle;
	@Autowired
	private GroupDao groupDao;
	
	BlackListBean checkBlackList=new BlackListBean();
	@Override
	public String sendMessage(MessageBean message,HttpServletRequest request) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		if (message.getMessageFile()!=null&&message.getM_content_type()>0) {
			try {
				String fileUrl="";
				String fileName=URLDecoder.decode(message.getMessageFile().getOriginalFilename(), "utf-8");
				String realpath = "C:\\upload/file/messageFile";
				//ʵ���ļ��ϴ�
				String fileType = fileName.substring(fileName.lastIndexOf('.'));
				//��ֹ�ļ�������
				fileName = MyUtil.getStringID() + fileType;
				File targetFile = new File(realpath, fileName); 
				if(!targetFile.exists()){  
		            targetFile.mkdirs();  
		        }
				fileUrl="http://"+Constant.addr+"/FlyMessage/file/messageFile/"+fileName;
				//�ϴ� 
		        try {   
		        	message.getMessageFile().transferTo(targetFile);
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        } 
				String fileContent="";
				if (fileType!=null) {
					String baseName=message.getMessageFile().getOriginalFilename();
					if (message.getM_content_type()==3) {
						fileContent="[voice:"+baseName+",link:"+fileUrl+"]";
					}else {
						if (fileType.equalsIgnoreCase(".doc")||fileType.equalsIgnoreCase(".docx")) {
							fileContent="[word:"+baseName+",link:"+fileUrl+"]";
						}else if (fileType.equalsIgnoreCase(".pptx")) {
							fileContent="[ppt:"+baseName+",link:"+fileUrl+"]";
						}else if (fileType.equalsIgnoreCase(".xls")||fileType.equalsIgnoreCase(".xlsx")) {
							fileContent="[excel:"+baseName+",link:"+fileUrl+"]";
						}else if (fileType.equalsIgnoreCase(".pdf")) {
							fileContent="[pdf:"+baseName+",link:"+fileUrl+"]";
						}else if (fileType.equalsIgnoreCase(".mp3")||fileType.equalsIgnoreCase(".flac")||fileType.equalsIgnoreCase(".acc")||fileType.equalsIgnoreCase(".aac")) {
							fileContent="[music:"+baseName+",link:"+fileUrl+"]";
						}else if (fileType.equalsIgnoreCase(".mp4")) {
							fileContent="[video:"+baseName+",link:"+fileUrl+"]";
						}else if (fileType.equalsIgnoreCase(".JPG")||fileType.equalsIgnoreCase(".PNG")||fileType.equalsIgnoreCase(".JPEG")) {
							fileContent="[picture:"+baseName+",link:"+fileUrl+"]";
						}else if (fileType.equalsIgnoreCase(".GIF")||fileType.equalsIgnoreCase(".gif")){
							fileContent="[gif:"+baseName+",link:"+fileUrl+"]";
						}else {
							fileContent="[file:"+baseName+",link:"+fileUrl+"]";
						}
					}
					message.setM_content(message.getM_content()+fileContent);
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		message.setMessageFile(null);
		if (message.getM_type()==0) {
			checkBlackList.setSource_u_id(message.getM_object_id());
			checkBlackList.setObject_u_id(message.getM_source_id());
			//�û����ܸ��ڶԷ����������û�������Ϣ
			if (userDao.checkBlackList(checkBlackList)==0) {
				if (messageDao.sendMessage(message)>0) {
					result.put("code", 200);
					result.put("mId", message.getM_id());
					result.put("time", message.getM_send_time());
					result.put("msg", "���ͳɹ�");
					msgScoketHandle.sendMessageToUser(message);
				}else {
					result.put("code", 500);
					result.put("msg", "����ʧ��");
				}
			}else {
				result.put("code", 500);
				result.put("msg", "����ʧ��");
			}
		}else if (message.getM_type()==1){
			try {
				GroupMember member=new GroupMember();
				member.setG_id(message.getM_source_g_id());
				member.setU_id(message.getM_source_id());
				if (groupDao.checkGroupMember(member)>0) {
					//Ⱥ����Ϣ ��ȡȺ�����û����η��͸���Ϣ
					List<GroupMember> members=groupDao.getGroupAllMember(message.getM_source_g_id());
					messageDao.sendMessage(message);
					int mId=message.getM_id();
					Date time=message.getM_send_time();
					if (members.size()>0) {
						int send_id=message.getM_source_id();
						for (GroupMember groupMember : members) {
							//�����͸�������
							if (groupMember.getU_id()!=send_id) {
								message.setM_object_id(groupMember.getU_id());
								messageDao.sendMessage(message);
								msgScoketHandle.sendMessageToUser(message);
							}
						}
						result.put("code", 200);
						result.put("mId", mId);
						result.put("time", time);
						result.put("msg", "���ͳɹ�");
					}else {
						result.put("code", 500);
						result.put("msg", "����ʧ��");
					}
				}else {
					result.put("code", 500);
					result.put("msg", "����ʧ��:���Ǳ�Ⱥ�û�");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result.put("code", 500);
				result.put("msg", "����ʧ��");
			}
		}else {
			result.put("code", 500);
			result.put("msg", "����ʧ��");
		}
		return result.toJSONString();
	}

	@Override
	public String getUserMessage(UserPageQuery query) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			List<MessageBean> messages=messageDao.getUserMessage(query);
			for (MessageBean messageBean : messages) {
				messageDao.receiveMessage(messageBean);
			}
			result.put("code", 200);
			result.put("messages", JSONObject.toJSON(messages));
			result.put("msg", "��ȡ�û���Ϣ�ɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "��ȡ�û���Ϣʧ��");
		}
		return result.toJSONString();
	}
	@Override
	public String getRecordMessage(UserPageQuery query) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		try {
			List<MessageBean> messages=messageDao.getRecordMessage(query);
			result.put("code", 200);
			result.put("messages", JSONObject.toJSON(messages));
			result.put("msg", "��ȡ��Ϣ��¼�ɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "��ȡ��Ϣ��¼ʧ��");
		}
		return result.toJSONString();
	}
	@Override
	public String delMessage(MessageBean message) {
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		if (messageDao.delMessage(message)>0) {
			result.put("code", 200);
			result.put("msg", "ɾ����Ϣ�ɹ�");
		}else {
			result.put("code", 500);
			result.put("msg", "ɾ����Ϣʧ��");
		}
		return result.toJSONString();
	}


}