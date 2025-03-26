package com.example.spring.webfrontservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class JoinClientResponseDTO {

    private boolean isSuccess;

    public JoinResponseDTO toJoinResponseDTO() {
        return JoinResponseDTO.builder()
                .isSuccess(isSuccess)
                .url(isSuccess ? "/webs/login" : "/webs/join")
                .build();
    }

}
