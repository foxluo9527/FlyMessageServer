package com.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.service.MessageService;
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

    BlackListBean checkBlackList = new BlackListBean();

    @Override
    public String sendMessage(MessageBean message, HttpServletRequest request) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        if (message.getMessageFile() != null && message.getM_content_type() > 0) {
            try {
                String fileUrl = "";
                String fileName = URLDecoder.decode(message.getMessageFile().getOriginalFilename(), "utf-8");
                String realpath = "C:\\upload/file/messageFile";
                //实现文件上传
                String fileType = fileName.substring(fileName.lastIndexOf('.'));
                //防止文件名重名
                fileName = MyUtil.getStringID() + fileType;
                File targetFile = new File(realpath, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                fileUrl = "http://" + Constant.addr + "/FlyMessage/file/messageFile/" + fileName;
                //上传
                try {
                    message.getMessageFile().transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String fileContent = "";
                if (fileType != null) {
                    String baseName = message.getMessageFile().getOriginalFilename();
                    if (message.getM_content_type() == 3) {
                        fileContent = "[voice:" + baseName + ",link:" + fileUrl + "]";
                    } else {
                        if (fileType.equalsIgnoreCase(".doc") || fileType.equalsIgnoreCase(".docx")) {
                            fileContent = "[word:" + baseName + ",link:" + fileUrl + "]";
                        } else if (fileType.equalsIgnoreCase(".pptx")) {
                            fileContent = "[ppt:" + baseName + ",link:" + fileUrl + "]";
                        } else if (fileType.equalsIgnoreCase(".xls") || fileType.equalsIgnoreCase(".xlsx")) {
                            fileContent = "[excel:" + baseName + ",link:" + fileUrl + "]";
                        } else if (fileType.equalsIgnoreCase(".pdf")) {
                            fileContent = "[pdf:" + baseName + ",link:" + fileUrl + "]";
                        } else if (fileType.equalsIgnoreCase(".mp3") || fileType.equalsIgnoreCase(".flac") || fileType.equalsIgnoreCase(".acc") || fileType.equalsIgnoreCase(".aac")) {
                            fileContent = "[music:" + baseName + ",link:" + fileUrl + "]";
                        } else if (fileType.equalsIgnoreCase(".mp4")) {
                            fileContent = "[video:" + baseName + ",link:" + fileUrl + "]";
                        } else if (fileType.equalsIgnoreCase(".JPG") || fileType.equalsIgnoreCase(".PNG") || fileType.equalsIgnoreCase(".JPEG")) {
                            fileContent = "[picture:" + baseName + ",link:" + fileUrl + "]";
                        } else if (fileType.equalsIgnoreCase(".GIF") || fileType.equalsIgnoreCase(".gif")) {
                            fileContent = "[gif:" + baseName + ",link:" + fileUrl + "]";
                        } else {
                            fileContent = "[file:" + baseName + ",link:" + fileUrl + "]";
                        }
                    }
                    message.setM_content(message.getM_content() + fileContent);
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        message.setMessageFile(null);
        if (message.getM_type() == 0) {
            checkBlackList.setSource_u_id(message.getM_object_id());
            checkBlackList.setObject_u_id(message.getM_source_id());
            //用户不能给在对方黑名单的用户发送信息
            if (userDao.checkBlackList(checkBlackList) == 0) {
                if (messageDao.sendMessage(message) > 0) {
                    result.put("code", 200);
                    result.put("mId", message.getM_id());
                    result.put("time", message.getM_send_time());
                    result.put("msg", "发送成功");
                    msgScoketHandle.sendMessageToUser(message);
                } else {
                    result.put("code", 500);
                    result.put("msg", "发送失败");
                }
            } else {
                result.put("code", 500);
                result.put("msg", "发送失败");
            }
        } else if (message.getM_type() == 1) {
            try {
                GroupMember member = new GroupMember();
                member.setG_id(message.getM_source_g_id());
                member.setU_id(message.getM_source_id());
                if (groupDao.checkGroupMember(member) > 0) {
                    //群聊消息 获取群所有用户依次发送该消息
                    List<GroupMember> members = groupDao.getGroupAllMember(message.getM_source_g_id());
                    messageDao.sendMessage(message);
                    int mId = message.getM_id();
                    Date time = message.getM_send_time();
                    if (members.size() > 0) {
                        int send_id = message.getM_source_id();
                        for (GroupMember groupMember : members) {
                            //不发送给发送人
                            if (groupMember.getU_id() != send_id) {
                                message.setM_object_id(groupMember.getU_id());
                                messageDao.sendMessage(message);
                                msgScoketHandle.sendMessageToUser(message);
                            }
                        }
                        result.put("code", 200);
                        result.put("mId", mId);
                        result.put("time", time);
                        result.put("msg", "发送成功");
                    } else {
                        result.put("code", 500);
                        result.put("msg", "发送失败");
                    }
                } else {
                    result.put("code", 500);
                    result.put("msg", "发送失败:您非本群用户");
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                result.put("code", 500);
                result.put("msg", "发送失败");
            }
        } else {
            result.put("code", 500);
            result.put("msg", "发送失败");
        }
        return result.toJSONString();
    }

    @Override
    public String getUserMessage(UserPageQuery query) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        try {
            List<MessageBean> messages = messageDao.getUserMessage(query);
            for (MessageBean messageBean : messages) {
                messageDao.receiveMessage(messageBean);
            }
            result.put("code", 200);
            result.put("messages", JSONObject.toJSON(messages));
            result.put("msg", "获取用户消息成功");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", "获取用户消息失败");
        }
        return result.toJSONString();
    }

    @Override
    public String getRecordMessage(UserPageQuery query) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        try {
            List<MessageBean> messages = messageDao.getRecordMessage(query);
            result.put("code", 200);
            result.put("messages", JSONObject.toJSON(messages));
            result.put("msg", "获取消息记录成功");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", "获取消息记录失败");
        }
        return result.toJSONString();
    }

    @Override
    public String delMessage(MessageBean message) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        if (messageDao.delMessage(message) > 0) {
            if (message.getM_content_type() > 0) {
                //文件类型删除源文件
                String realpath = "C:\\upload/file/messageFile" +
                        getMsgFile(message.getM_content()).
                                substring("http://www.foxluo.cn/FlyMessage/file/messageFile".length());
                File file=new File(realpath);
                file.delete();
            }
            result.put("code", 200);
            result.put("msg", "删除消息成功");
        } else {
            result.put("code", 500);
            result.put("msg", "删除消息失败");
        }
        return result.toJSONString();
    }

    public static String getMsgFile(String content) {
        if (content.contains("[") && content.contains("]")) {
            String fileContent = content.substring(content.indexOf("[") + 1, content.indexOf("]"));
            if (fileContent.contains(",")) {
                String[] params = fileContent.split(",", 2);
                if (params.length == 2) {
                    if (params[0].contains(":")) {
                        String[] fileParams = params[0].split(":", 2);
                        if (fileParams[1].length() > 24) {
                            String[] fileNameParams = fileParams[1].split("\\.");
                            String fileType = fileNameParams[fileNameParams.length - 1];
                            String fileName = "";
                            for (int i = 0; i < fileNameParams.length - 1; i++) {
                                fileName += fileNameParams[i];
                            }
                            String tmp1, tmp2;
                            if (fileName.length() > 20) {
                                tmp1 = fileName.substring(0, 10);
                                tmp2 = fileName.substring(fileName.length() - 10, fileName.length());
                                fileName = tmp1 + "..." + tmp2;
                            }
                            fileParams[1] = fileName + "." + fileType;
                        }
                        String messageContent = content.substring(0, content.indexOf("["));
                        if (params[1].contains(":")) {
                            String[] linkParams = params[1].split(":", 2);
                            if (linkParams[0].equals("link")) {
                                return linkParams[1];
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
