// === ChatMessage_hs.java ===
// 채팅 메시지 정보를 저장하는 JPA 엔티티
package com.example.location_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage_hs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 메시지 ID

    @Column(nullable = false)
    private String senderNickname; // 보낸 사람 닉네임

    @Column(nullable = false)
    private String content; // 메시지 내용

    @Column(nullable = false)
    private LocalDateTime sentAt; // 보낸 시간
}
