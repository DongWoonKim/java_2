package com.example.spring.basicboardv1.dto;

import com.example.spring.basicboardv1.model.Member;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@ToString
public class SignInRequestDTO {
    private String userId;
    private String password;
}
