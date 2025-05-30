// === 6. WebSocketConfig_hs.java ===
// WebSocket 설정 클래스
package com.example.location_app.WebSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig_hs implements WebSocketConfigurer {

    private final BuildingChatHandler_hs chatHandler;

    public WebSocketConfig_hs(BuildingChatHandler_hs chatHandler) {
        this.chatHandler = chatHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler, "/ws/building").setAllowedOrigins("*");
    }
}