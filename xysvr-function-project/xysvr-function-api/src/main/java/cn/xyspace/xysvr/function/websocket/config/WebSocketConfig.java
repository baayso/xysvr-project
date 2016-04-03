package cn.xyspace.xysvr.function.websocket.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import cn.xyspace.xysvr.function.websocket.handler.MsgWebsocketEndPoint;
import cn.xyspace.xysvr.function.websocket.interceptor.UserHandshakeInterceptor;

@Configuration
// @EnableWebMvc
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Inject
    private MsgWebsocketEndPoint msgEndPoint;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(this.msgEndPoint, "/websocket/api/v1/msg").addInterceptors(new UserHandshakeInterceptor()).setAllowedOrigins("*");
        // registry.addHandler(this.myHandler, "/myHandler/socketjs").addInterceptors(new HandshakeInterceptor()).withSockJS();
    }

}
