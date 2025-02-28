package com.example.spring.basicboardv1.controller;

import com.example.spring.basicboardv1.config.WebSecurityConfig;
import com.example.spring.basicboardv1.dto.SignInRequestDTO;
import com.example.spring.basicboardv1.dto.SignInResponseDTO;
import com.example.spring.basicboardv1.dto.SignUpRequestDTO;
import com.example.spring.basicboardv1.dto.SignUpResponseDTO;
import com.example.spring.basicboardv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/join")
    public SignUpResponseDTO join(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        System.out.println("SignUpRequestDTO :: " + signUpRequestDTO);
        memberService.signUp(signUpRequestDTO.toMember(bCryptPasswordEncoder));
        return SignUpResponseDTO.builder()
                .build();
    }

//    @PostMapping("/login")
//    public SignInResponseDTO join(@RequestBody SignInRequestDTO signInRequestDTO) {
//        System.out.println("signInRequestDTO :: " + signInRequestDTO);
//        return SignInResponseDTO.builder()
//                .build();
//    }


}
