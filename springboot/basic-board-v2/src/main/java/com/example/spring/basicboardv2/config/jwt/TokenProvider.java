package com.example.spring.basicboardv2.config.jwt;

import com.example.spring.basicboardv2.model.Member;
import com.example.spring.basicboardv2.type.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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

    public Member getTokenDetails(String token) {
        Claims claims = getClaims(token);

        return Member.builder()
                .id(claims.get("id", Long.class))
                .userId(claims.getSubject())
                .userName(claims.get("userName", String.class))
                .role(
                        Role.valueOf(claims.get("role", String.class))
                )
                .build();
    }
    
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);

        // Claims에서 역할을 추출하고, GrantedAuthority로 변환
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(claims.get("role", String.class))
        );

        // UserDetails 객체 생성
        UserDetails userDetails = new User(claims.getSubject(), "", authorities);

        // UsernamePasswordAuthenticationToken 생성
        return new UsernamePasswordAuthenticationToken(userDetails, token, authorities);
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

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Base64.getDecoder().decode(jwtProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
