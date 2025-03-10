package com.example.spring.basicboardv2.model;

import com.example.spring.basicboardv2.dto.BoardDetailResponseDTO;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Article {
    private Long id;
    private String title;
    private String content;
    private String userId;
    private String filePath;
    private LocalDateTime created;
    private LocalDateTime updated;

    public BoardDetailResponseDTO toBoardDetailResponseDTO() {
        return BoardDetailResponseDTO.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .filePath(filePath)
                .created(created)
                .build();
    }
}
