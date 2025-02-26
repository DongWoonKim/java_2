package com.example.spring.feigndata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DataResponseDTO {
    private Long id;
    private String name;
    private int value;
}
