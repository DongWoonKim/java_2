package com.example.spring.authservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RefreshTokenResponseDTO {
    private int status;
    private String accessToken;
    private String refreshToken;
}
