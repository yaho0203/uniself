// === 1. DummyBuildingDataInitializer_hs.java ===
// 서버 시작 시 buildings 테이블에 더미 건물 데이터를 삽입하는 클래스

package com.example.location_app.dev;

import com.example.location_app.entity.Building_hs;
import com.example.location_app.repository.BuildingRepository_hs;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DummyBuildingDataInitializer_hs {

    // 건물 엔티티를 DB에 저장하기 위한 Repository 주입
    private final BuildingRepository_hs buildingRepository;

    // 애플리케이션 시작 시 자동 실행되는 초기화 메서드
    @PostConstruct
    public void init() {
        // 테이블에 아무 데이터도 없을 때만 더미 데이터 삽입
        if (buildingRepository.count() == 0) {
            buildingRepository.save(new Building_hs(null, "제1공학관", 37.123456, 127.123456));
            buildingRepository.save(new Building_hs(null, "제2공학관", 37.123789, 127.123789));
            buildingRepository.save(new Building_hs(null, "도서관", 37.124000, 127.124000));
            buildingRepository.save(new Building_hs(null, "학생회관", 37.125000, 127.125000));
        }
    }
}
