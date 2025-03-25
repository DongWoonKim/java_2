package com.example.spring.authservice.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ClaimsResponseDTO {
    private String userId;
    private List<String> roles;
}
