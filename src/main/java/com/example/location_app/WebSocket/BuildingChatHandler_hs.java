// === 7. BuildingChatHandler_hs.java ===
// 사용자의 위치(건물) 기반으로 채팅방을 분리하고 메시지를 주고받는 WebSocket 핸들러.
package com.example.location_app.WebSocket;

import com.example.location_app.entity.*;
import com.example.location_app.repository.ChatMessageRepository_hs;
import com.example.location_app.repository.UserSelectedLocationRepository_hs;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class BuildingChatHandler_hs extends TextWebSocketHandler {

    private final ChatMessageRepository_hs chatMessageRepository;
    private final UserSelectedLocationRepository_hs locationRepository;
    private final Map<Long, Set<WebSocketSession>> buildingSessions = new HashMap<>(); // 건물 ID별 세션 관리

    // WebSocket 연결이 열릴 때 사용자 위치에 따라 세션 그룹핑
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long buildingId = locationRepository.findByUser(user)
                .map(loc -> loc.getBuilding().getId())
                .orElse(null);

        if (buildingId != null) {
            buildingSessions.putIfAbsent(buildingId, new HashSet<>());
            buildingSessions.get(buildingId).add(session);
            session.getAttributes().put("buildingId", buildingId);
            session.getAttributes().put("nickname", user.getNickname());
        }
    }

    // 메시지 수신 시 같은 건물 사용자들에게만 전송 및 DB 저장
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Long buildingId = (Long) session.getAttributes().get("buildingId");
        String nickname = (String) session.getAttributes().get("nickname");
        String content = message.getPayload();

        if (buildingId != null && nickname != null) {
            ChatMessage_hs saved = chatMessageRepository.save(
                    new ChatMessage_hs(null, nickname, content, LocalDateTime.now()));

            for (WebSocketSession s : buildingSessions.getOrDefault(buildingId, Set.of())) {
                if (s.isOpen()) {
                    try {
                        s.sendMessage(new TextMessage(nickname + ": " + content));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // 연결 종료 시 해당 세션 제거
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Long buildingId = (Long) session.getAttributes().get("buildingId");
        if (buildingId != null) {
            buildingSessions.getOrDefault(buildingId, Set.of()).remove(session);
        }
    }
}
