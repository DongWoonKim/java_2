package com.example.spring.authservice.model;

import com.example.spring.authservice.type.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private Long id;
    private String userId;
    private String password;
    private String userName;
    private Role role;
}
