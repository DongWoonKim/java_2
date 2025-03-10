package com.example.spring.basicboardv2.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardDetailResponseDTO {
    private String title;
    private String content;
    private String userId;
    private String filePath;
    private LocalDateTime created;
}
