// === 4. BuildingRepository_hs.java ===
// 건물 정보를 다루는 Spring Data JPA 레포지토리
package com.example.location_app.repository;

import com.example.location_app.entity.Building_hs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository_hs extends JpaRepository<Building_hs, Long> {
}
