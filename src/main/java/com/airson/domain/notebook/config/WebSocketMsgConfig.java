package com.airson.domain.notebook.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 *
 */
/*@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMsgConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(Const.WS_MSG_TOPIC_PREFIX);
        config.setApplicationDestinationPrefixes(Const.WS_MSG_APP_PREFIX);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(Const.WS_MSG_ENDPOINT).setAllowedOrigins("*");
        registry.addEndpoint(Const.WS_MSG_ENDPOINT).setAllowedOrigins("*").withSockJS();
    }

}*/
