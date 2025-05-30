// ===  MapController_hs.java ===
// 건물 목록을 제공하고, 사용자가 선택한 건물을 저장하고, 건물 기반 채팅 뷰를 렌더링하는 컨트롤러.
package com.example.location_app.controller;

import com.example.location_app.entity.*;
import com.example.location_app.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class MapController_hs {

    private final BuildingRepository_hs buildingRepository;
    private final UserSelectedLocationRepository_hs locationRepository;
    private final UserRepository userRepository; // 기존 클래스

    // 건물 선택 페이지 렌더링
    @GetMapping("/map")
    public String showMap(Model model) {
        model.addAttribute("buildings", buildingRepository.findAll());
        return "map"; // map.html
    }

    // 사용자가 건물을 선택하면 DB에 저장
    @PostMapping("/map/select-building")
    public String selectBuilding(@RequestParam Long buildingId, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        Building_hs building = buildingRepository.findById(buildingId).orElseThrow();

        UserSelectedLocation_hs location = locationRepository.findByUser(user)
                .orElse(new UserSelectedLocation_hs());
        location.setUser(user);
        location.setBuilding(building);
        location.setSelectedAt(LocalDateTime.now());
        locationRepository.save(location);

        return "redirect:/chat/building";
    }

    // 사용자가 선택한 건물을 기반으로 채팅 뷰 렌더링
    @GetMapping("/chat/building")
    public String buildingChatPage(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        UserSelectedLocation_hs loc = locationRepository.findByUser(user).orElse(null);
        if (loc == null) {
            return "redirect:/map";
        }
        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("buildingName", loc.getBuilding().getName());
        return "chat_room_hs"; // chat_room_hs.html
    }
}
