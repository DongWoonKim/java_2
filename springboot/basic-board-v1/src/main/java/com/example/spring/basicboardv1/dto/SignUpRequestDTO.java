package com.example.spring.basicboardv1.dto;

import com.example.spring.basicboardv1.model.Member;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignUpRequestDTO {
    private String userId;
    private String password;
    private String userName;

    public Member toMember() {
        return Member.builder()
                .userId(userId)
                .password(password)
                .userName(userName)
                .build();
    }

}
