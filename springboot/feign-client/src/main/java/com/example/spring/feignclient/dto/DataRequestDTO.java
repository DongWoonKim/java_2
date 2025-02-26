package com.example.spring.feignclient.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DataRequestDTO {
    private String name;
    private int value;
}
