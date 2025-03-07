package com.example.spring.basicboardv2.mapper;

import com.example.spring.basicboardv2.model.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    void saveArticle(Article article);
}
