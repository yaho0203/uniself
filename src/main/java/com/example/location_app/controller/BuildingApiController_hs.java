// === BuildingApiController_hs.java ===
// REST 방식으로 건물 목록을 JSON으로 제공하는 컨트롤러
package com.example.location_app.controller;

// 건물 Entity
import com.example.location_app.entity.Building_hs;
// 건물 Repository
import com.example.location_app.repository.BuildingRepository_hs;
// 의존성 주입을 위한 어노테이션
import lombok.RequiredArgsConstructor;
// REST 컨트롤러 관련 어노테이션
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BuildingApiController_hs {

    private final BuildingRepository_hs buildingRepository;

    // 모든 건물 목록을 JSON 형태로 반환
    @GetMapping("/buildings")
    public List<Building_hs> getAllBuildings() {
        return buildingRepository.findAll();
    }
}
