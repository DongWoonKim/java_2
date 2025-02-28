package com.example.spring.basicboardv1.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignUpRequestDTO {
    private String userId;
    private String password;
    private String userName;

}
