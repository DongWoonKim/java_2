package com.example.spring.basicboardv2.mapper;

import com.example.spring.basicboardv2.model.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void saved(Member member);
    Member findByUserId(String userId);
}
