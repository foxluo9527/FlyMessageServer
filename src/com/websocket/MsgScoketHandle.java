package com.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;
import com.dao.FriendDao;
import com.dao.MessageDao;
import com.dao.UserDao;
import com.po.FriendBean;
import com.po.FriendRequestBean;
import com.po.MessageBean;
import com.po.UserBean;
import com.util.TokenUtil;

@Component
public class MsgScoketHandle implements WebSocketHandler {
	@Autowired
	public MessageDao messageDao;
	@Autowired 
	public FriendDao friendDao;
	@Autowired
	public UserDao userDao;
    /**�Ѿ����ӵ��û�*/
    private static final ArrayList<WebSocketSession> users;

    static {
        //���浱ǰ�����û�
        users =new ArrayList<>();
    }

    /**
     * ��������
     * @param webSocketSession
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        //���û���Ϣ���ӵ�list��
        System.out.println("=====================�������ӳɹ�==========================");
        UserBean user  = (UserBean) webSocketSession.getAttributes().get("user");
        if(user != null){
            System.out.println("��ǰ�����û�======"+user.getU_name());
        }
        try {
        	for (WebSocketSession session : users) {
    			if (((UserBean) session.getAttributes().get("user")).getU_id()==user.getU_id()) {
    				String loginToken=TokenUtil.getUserToken(((UserBean) session.getAttributes().get("user")));
    				if(!((String)session.getAttributes().get("loginToken")).equals(loginToken)) {
    					//ͬһtoken�ظ���¼�ݲ�ǿ������
    					JSONObject message=new JSONObject();
	                	message.put("msgType", 4);
	                	message.put("content", "�Ƿ���¼����������");
	                	message.put("userId", ((UserBean) session.getAttributes().get("user")).getU_id());
	                	message.put("loginToken", TokenUtil.getUserToken(((UserBean) session.getAttributes().get("user"))));
	                	session.sendMessage(new TextMessage(message.toJSONString()));
					}
                	users.remove(session);
    				session.close();
    				break;
    			}
    		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        users.add(webSocketSession);
        System.out.println("webSocket��������====="+users.size());
        if (messageDao!=null) {
			List<MessageBean> unReceiveMsgs=messageDao.getUserAllMessage(user.getU_id());
			for (MessageBean messageBean : unReceiveMsgs) {
				sendMessageToUser(messageBean);
			}
		}
        try {
        	List<FriendRequestBean> friendRequests=friendDao.getAllFriendRequest(user.getU_id());
            for (FriendRequestBean friendRequest : friendRequests) {
            	if (friendRequest.getRq_receive_state()==0) {
            		sendFrienRequest(friendRequest);
				}
    		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }

    /**
     * ������Ϣ
     * @param webSocketSession
     * @param webSocketMessage
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
    	UserBean user = (UserBean) webSocketSession.getAttributes().get("user");
        System.out.println("�յ��û�:"+user.getU_name()+"����Ϣ");
        System.out.println(webSocketMessage.getPayload().toString());
        System.out.println("===========================================");
    }

    /**
     * �쳣����
     * @param webSocketSession
     * @param throwable
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable){
        if (webSocketSession.isOpen()){
            //�ر�session
            try {
                webSocketSession.close();
            } catch (IOException e) {
            }
        }
        //�Ƴ��û�
        users.remove(webSocketSession);
    }

    /**
     * �Ͽ�����
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        users.remove(webSocketSession);
        UserBean user=(UserBean)webSocketSession.getAttributes().get("user");
        System.out.println(user.getU_name()+"�Ͽ�����");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * ������ͨ��Ϣ
     * ������Ϣ��ָ�����û�
     * @param messageInfo
     */
    public void sendMessageToUser(MessageBean messageInfo){
    	messageInfo.setMessageFile(null);
        for (WebSocketSession session : users) {
        	UserBean sessionUser = (UserBean) session.getAttributes().get("user");
            if(sessionUser.getU_id()==messageInfo.getM_object_id()){
                if (session.isOpen()){
                	JSONObject message=new JSONObject();
                	message.put("msgType", 1);
                	message.put("content", JSONObject.toJSON(messageInfo));
                	try {
	                    session.sendMessage(new TextMessage(message.toJSONString()));
	                    System.out.println("������Ϣ����"+sessionUser.getU_name()+"���ݣ�"+messageInfo.getM_content());
                	} catch (IOException e) {
                        e.printStackTrace();
                    }
                    messageDao.receiveMessage(messageInfo);
                }
                break;
            }
        }
    }
    /**
     * �������Ӻ���������û�
     * @param friendRequest
     */
    public void sendFrienRequest(FriendRequestBean friendRequest) {
    	for (WebSocketSession session : users) {
    		UserBean sessionUser = (UserBean) session.getAttributes().get("user");
    		if(sessionUser.getU_id()==friendRequest.getRq_object_u_id()){
    			JSONObject message=new JSONObject();
            	message.put("msgType", 2);
            	JSONObject friendJson=(JSONObject) JSONObject.toJSON(friendRequest);
				FriendBean friend=new FriendBean();
				friend.setF_object_u_id(friendRequest.getRq_object_u_id());
				friend.setF_source_u_id(friendRequest.getRq_source_u_id());
				if (friendDao.checkFriendRelationship(friend)>0) {
					friendJson.put("isAccept", true);
				}else {
					friendJson.put("isAccept", false);
				}
            	message.put("content", friendJson);
                try {
					session.sendMessage(new TextMessage(message.toJSONString()));
//					FriendBean friendBean=new FriendBean();
//					friendBean.setF_object_u_id(friendRequest.getRq_object_u_id());
//					friendBean.setF_source_u_id(friendRequest.getRq_source_u_id());
//					friendDao.receiveFriendRequest(friendBean);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
	}
    /**
     * ���ͻ����/������û�
     * @param u_id �����û�id
     * @param link ���ӵ�ַ
     * @param linkType ��������
     */
    public void sendLinkMsg(int u_id,String link,int linkType) {
    	for (WebSocketSession session : users) {
    		UserBean sessionUser = (UserBean) session.getAttributes().get("user");
    		if(sessionUser.getU_id()==u_id){
    			JSONObject message=new JSONObject();
            	message.put("msgType", 3);
            	JSONObject linkJson=new JSONObject();
            	linkJson.put("link", link);
            	linkJson.put("linkType", linkType);
            	message.put("content", linkJson);
            	try {
					session.sendMessage(new TextMessage(message.toJSONString()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
	}
    
    public boolean getUserOnlineState(int u_id) {
    	for (WebSocketSession session : users) {
    		try {
    			UserBean sessionUser = (UserBean) session.getAttributes().get("user");
        		if(sessionUser!=null&&sessionUser.getU_id()==u_id&&userDao.getUserPrivacy(sessionUser).getShow_online_state()==0){
        			return true;
        		}
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
    	}
		return false;
	}
}