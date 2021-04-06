package com.websocket;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.dao.UserDao;
import com.po.UserBean;
import com.util.TokenUtil;

public class WebSocketHandshakeInterceptor extends HttpSessionHandshakeInterceptor  {
    @Autowired
    public UserDao UserDao;
    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandshakeInterceptor.class);

    /**
     * 握手前
     * @param request
     * @param response
     * @param webSocketHandler
     * @param attributes
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
        logger.info("握手操作");
        if (request instanceof ServletServerHttpRequest){
           String loginToken=((ServletServerHttpRequest) request).getServletRequest().getParameter("loginToken");
           UserBean user=TokenUtil.getTokenUser(loginToken).getUser();
    	   if (user!=null) {
			   attributes.put("user",user);
			   attributes.put("loginToken", loginToken);
		   }else {
			   return false;
		   }
       }
        return true;
    }

    /**
     * 握手后
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @param webSocketHandler
     * @param e
     */
    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
    	 super.afterHandshake(serverHttpRequest, serverHttpResponse, webSocketHandler, e);
    }
}