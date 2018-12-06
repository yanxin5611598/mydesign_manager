package com.yx.mydesign.controller.dynamic;

import java.util.Map;


import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
//拦截器类：对握手操作增强处理
@Component
public class DynamicHandshakeInterceptor extends HttpSessionHandshakeInterceptor {
  
    //会话开始
	@Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler,
            Map<String, Object> attributes) throws Exception {
		System.out.println("before dynamic handshake");
        //attributes是websocket的session
        return true;
    }
	//会话结束
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
            Exception ex) {
        System.out.println("after dynamic handshake");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}