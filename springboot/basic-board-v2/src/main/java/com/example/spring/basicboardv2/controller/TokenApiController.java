package com.example.spring.basicboardv2.controller;

import com.example.spring.basicboardv2.config.jwt.TokenProvider;
import com.example.spring.basicboardv2.dto.SignInResponseDTO;
import com.example.spring.basicboardv2.model.Member;
import com.example.spring.basicboardv2.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
public class TokenApiController {

    private final TokenProvider tokenProvider;

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = CookieUtil.getCookieValue(request, "refreshToken");

        if (refreshToken != null && tokenProvider.validToken(refreshToken) == 1) {
            Member member = tokenProvider.getTokenDetails(refreshToken);

            String newAccessToken  = tokenProvider.generateToken(member, Duration.ofHours(2));
            String newRefreshToken = tokenProvider.generateToken(member, Duration.ofDays(2));

            CookieUtil.addCookie(response, "refreshToken", newRefreshToken, 7 * 24 * 60 * 60);

            response.setHeader(HttpHeaders.AUTHORIZATION, newAccessToken);

            return ResponseEntity.ok(
                    SignInResponseDTO.builder()
                            .token(newAccessToken)
                            .build()
            );
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Refresh Token이 유효하지 않습니다.");
        }
    }

}
