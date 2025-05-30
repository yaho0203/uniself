// === 5. UserSelectedLocationRepository_hs.java ===
// 사용자의 선택된 건물 정보를 다루는 레포지토리
package com.example.location_app.repository;

import com.example.location_app.entity.User;
import com.example.location_app.entity.UserSelectedLocation_hs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSelectedLocationRepository_hs extends JpaRepository<UserSelectedLocation_hs, Long> {
    Optional<UserSelectedLocation_hs> findByUser(User user);
}