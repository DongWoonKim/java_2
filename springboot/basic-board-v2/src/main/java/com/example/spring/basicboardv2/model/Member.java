package com.example.spring.basicboardv2.model;

import com.example.spring.basicboardv2.type.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Member {
    private long id;
    private String userId;
    private String password;
    private String userName;
    private Role role;
}
