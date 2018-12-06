package com.yx.mydesign.controller.client.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
@Configuration
@EnableWebMvc
@Component("webSocketConfig")
/*
 * @component （把普通pojo实例化到spring容器中，相当于配置文件中的 
<bean id="" class=""/>）

泛指各种组件，就是说当我们的类不属于各种归类的时候（不属于@Controller、@Services等的时候），我们就可以使用@Component来标注这个类。*/
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
	
	//注入处理器
	@Bean
    public WebSocketHandler webSocketHandler(){
        return new WebSocketHandler();
    }
	public WebSocketConfig() {
    }
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// TODO Auto-generated method stub
		//注册具体的服务
		registry.addHandler(webSocketHandler(), "/websocketTest").addInterceptors(new HandshakeInterceptor());
		registry.addHandler(webSocketHandler(), "/websocketTest/sockjs").addInterceptors(new HandshakeInterceptor()).withSockJS();
	}	

}
