package com.example.spring.webfrontservice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JoinResponseDTO {
    private boolean isSuccess;
    private String url;
}
