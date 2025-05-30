// === AdminInitializer.java ===
// 서버 시작 시 관리자 계정을 자동 생성하는 초기화 클래스
package com.example.location_app.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.location_app.entity.User;
import com.example.location_app.entity.UserRole;
import com.example.location_app.entity.VerificationStatus;
import com.example.location_app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@DependsOn("passwordEncoder") // 🔑 passwordEncoder 빈이 먼저 초기화되도록 보장
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 서버 실행 시 관리자 계정이 없으면 생성
    @Override
    public void run(String... args) {
        if (!userRepository.existsByUsername("admin")) {
            User admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin")) // 기본 비밀번호는 "admin"
                .nickname("관리자")
                .role(UserRole.ADMIN)
                .status(VerificationStatus.APPROVED)
                .build();

            userRepository.save(admin);
        }
    }
}
