package com.example.spring.authservice.dto;

import com.example.spring.authservice.model.User;
import com.example.spring.authservice.type.Role;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@ToString
public class UserJoinRequestDTO {

    private String userId;
    private String password;
    private String userName;
    private Role role;

    public User toUser(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return User.builder()
                .userId(userId)
                .password(bCryptPasswordEncoder.encode(password))
                .userName(userName)
                .role(role)
                .build();
    }

}
