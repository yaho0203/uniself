// === 3. UserSelectedLocation_hs.java ===
// 사용자가 선택한 건물 위치 정보를 저장하는 엔티티
package com.example.location_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_selected_locations")
public class UserSelectedLocation_hs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Building_hs building;

    private LocalDateTime selectedAt;
}
