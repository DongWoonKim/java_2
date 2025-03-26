package com.example.spring.webfrontservice.dto;

import lombok.Getter;

@Getter
public class JoinClientResponseDTO {

    private boolean isSuccess;

    public JoinResponseDTO joinResponseDTO() {
        return JoinResponseDTO.builder()
                .isSuccess(isSuccess)
                .url(isSuccess ? "/webs/login" : "/webs/join")
                .build();
    }

}
