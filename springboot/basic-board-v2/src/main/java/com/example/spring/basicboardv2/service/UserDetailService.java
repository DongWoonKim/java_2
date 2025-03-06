package com.example.spring.basicboardv2.service;

import com.example.spring.basicboardv2.config.security.CustomUserDetails;
import com.example.spring.basicboardv2.mapper.MemberMapper;
import com.example.spring.basicboardv2.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberMapper.findByUserId(username);
        if (member == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        return CustomUserDetails.builder()
                .member(member)
                .roles(List.of(String.valueOf(member.getRole().name())))
                .build();
    }
}
