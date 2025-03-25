package com.example.spring.authservice.controller;

import com.example.spring.authservice.dto.*;
import com.example.spring.authservice.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auths")
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/refresh")
    public RefreshTokenResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        log.info("Refresh token request ");
        return tokenService.refreshToken(refreshTokenRequestDTO.getRefreshToken());
    }

    @PostMapping("/validToken")
    public ValidTokenResponseDTO validToken(@RequestBody ValidTokenRequestDTO validTokenRequestDTO) {
        log.info("Validate token request ");
        return tokenService.validateToken(validTokenRequestDTO.getToken());
    }

    @PostMapping("/claims")
    public ClaimsResponseDTO claims(@RequestBody ClaimsRequestDTO claimsRequestDTO) {
        log.info("Claims request ");
        return tokenService.getAuthentication(claimsRequestDTO.getToken());
    }

}
