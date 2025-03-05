package com.example.spring.basicboardv2.config.jwt;

import com.example.spring.basicboardv2.model.Member;
import com.example.spring.basicboardv2.type.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class TokenProviderTest {

    @Autowired
    TokenProvider tokenProvider;
    
    @Test
    void 토큰생성_테스트() {
        // given
        Member member = Member.builder()
                .id(0L)
                .userId("test")
                .password("123456")
                .userName("test")
                .role(Role.ROLE_USER)
                .build();

        Duration duration = Duration.ofHours(1);

        // when
        String token = tokenProvider.generateToken(member, duration);

        // then
        System.out.println(token);
        assertNotNull(token);
    }

}