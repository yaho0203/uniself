// === ChatMessageRepository_hs.java ===
// 채팅 메시지를 저장하고 조회하는 JPA Repository
package com.example.location_app.repository;

import com.example.location_app.entity.ChatMessage_hs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository_hs extends JpaRepository<ChatMessage_hs, Long> {
    // 기본적인 CRUD는 JpaRepository가 자동으로 처리함
}