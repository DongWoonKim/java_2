package com.example.spring.basicboardv1.controller;

import com.example.spring.basicboardv1.dto.SignUpRequestDTO;
import com.example.spring.basicboardv1.dto.SignUpResponseDTO;
import com.example.spring.basicboardv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/join")
    public SignUpResponseDTO join(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        System.out.println("SignUpRequestDTO :: " + signUpRequestDTO);
        memberService.signUp(signUpRequestDTO.toMember());
        return SignUpResponseDTO.builder()
                .build();
    }


}
