package com.example.spring.authservice.service;

import com.example.spring.authservice.config.jwt.JwtProperties;
import com.example.spring.authservice.model.User;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

import static io.jsonwebtoken.Header.*;
import static io.jsonwebtoken.SignatureAlgorithm.*;

@Service
@RequiredArgsConstructor
public class TokenProviderService {

    private final JwtProperties jwtProperties;

    private String makeToken(Date expire, User user) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(TYPE, JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expire)
                .setSubject(user.getUserId())
                .claim("id", user.getId())
                .claim("role", user.getRole())
                .claim("userName", user.getUserName())
                .signWith(getSecretKey(), HS512)
                .compact();
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Base64.getDecoder().decode(jwtProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
