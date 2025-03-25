package com.example.spring.authservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserLoginResponseDTO {
    private boolean loggedIn;
    private String userName;
    private String userId;
    private String accessToken;
    private String refreshToken;
}
