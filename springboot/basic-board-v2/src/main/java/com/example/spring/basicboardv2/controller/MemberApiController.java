package com.example.spring.basicboardv2.controller;

import com.example.spring.basicboardv2.config.jwt.TokenProvider;
import com.example.spring.basicboardv2.config.security.CustomUserDetails;
import com.example.spring.basicboardv2.dto.SignInRequestDTO;
import com.example.spring.basicboardv2.dto.SignInResponseDTO;
import com.example.spring.basicboardv2.dto.SignUpRequestDTO;
import com.example.spring.basicboardv2.dto.SignUpResponseDTO;
import com.example.spring.basicboardv2.model.Member;
import com.example.spring.basicboardv2.service.MemberService;
import com.example.spring.basicboardv2.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @PostMapping("/join")
    public SignUpResponseDTO signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        System.out.println(signUpRequestDTO);
        memberService.signUp(signUpRequestDTO.toMember(bCryptPasswordEncoder));
        return SignUpResponseDTO.builder()
                .successed(true)
                .build();
    }

    @PostMapping("/login")
    public SignInResponseDTO signIn(
            @RequestBody SignInRequestDTO signInRequestDTO,
            HttpServletResponse response
    ) {
        System.out.println(signInRequestDTO);
        // 사용자 인증
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequestDTO.getUsername(),
                        signInRequestDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        Member member = ((CustomUserDetails) authenticate.getPrincipal()).getMember();

        String accessToken = tokenProvider.generateToken(member, Duration.ofHours(2));
        String refreshToken = tokenProvider.generateToken(member, Duration.ofDays(2));

        CookieUtil.addCookie(response, "refreshToken", refreshToken, 7 * 24 * 60 * 60);

        return SignInResponseDTO.builder()
                .isLoggined(true)
                .token(accessToken)
                .userId(member.getUserId())
                .userName(member.getUserName())
                .build();
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.deleteCookie(request, response, "refreshToken");
    }

}
