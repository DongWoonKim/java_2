package com.example.spring.basicboardv2.service;

import com.example.spring.basicboardv2.mapper.MemberMapper;
import com.example.spring.basicboardv2.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public void signUp(Member member) {
        memberMapper.saved(member);
    }

}
