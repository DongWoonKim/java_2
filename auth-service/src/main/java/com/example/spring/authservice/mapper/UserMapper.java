package com.example.spring.authservice.mapper;

import com.example.spring.authservice.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findUserByUserId(String userId);
}
