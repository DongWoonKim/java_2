package com.example.spring.authservice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidTokenResponseDTO {
    private int statusNum;
}
