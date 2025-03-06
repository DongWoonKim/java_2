package com.example.spring.basicboardv2.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignInRequestDTO {
    private String username;
    private String password;
}
