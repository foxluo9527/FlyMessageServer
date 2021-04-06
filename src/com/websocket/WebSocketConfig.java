package com.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{

    private static final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        System.out.println("==========================注册socket");
        //注册websocket server实现类，"/webSocketServer"访问websocket的地址
        registry.addHandler(msgSocketHandle(),"/webSocketServer").setAllowedOrigins("*").addInterceptors(new WebSocketHandshakeInterceptor());
        //使用socketjs的注册方法
        registry.addHandler(msgSocketHandle(),"/sockjs/webSocketServer").setAllowedOrigins("*").addInterceptors(new WebSocketHandshakeInterceptor()).withSockJS();
    }

     /**
     *
     * @return 消息发送的Bean
     */
    @Bean(name = "msgSocketHandle")
    public WebSocketHandler msgSocketHandle(){
        return new MsgScoketHandle();
    }
}