package com.example.spring.webfrontservice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshTokenResponseDTO {
    private int status;
    private String accessToken;
}
