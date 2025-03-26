package com.example.spring.webfrontservice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDTO {
    private String url;
    private String userName;
    private String userId;
    private String accessToken;
    private String message;
}
