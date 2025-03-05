package com.example.spring.basicboardv2.config.jwt;

import com.example.spring.basicboardv2.model.Member;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenProvider {

    private final JwtProperties jwtProperties;

    public String generateToken(Member member, Duration expiredAt) {
        Date now = new Date();
        return makeToken(
                member,
                new Date( now.getTime() + expiredAt.toMillis() )
        );
    }

    private String makeToken(Member member, Date expired) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expired)
                .setSubject(member.getUserId())
                .claim("id", member.getId())
                .claim("role", member.getRole().name())
                .claim("userName", member.getUserName())
                .compact();
    }

}
