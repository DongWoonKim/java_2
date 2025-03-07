package com.example.spring.basicboardv2.controller;

import com.example.spring.basicboardv2.dto.BoardListResponseDTO;
import com.example.spring.basicboardv2.model.Article;
import com.example.spring.basicboardv2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping
    public BoardListResponseDTO getBoards(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        // 게시글 목록 가져오기
        List<Article> articles = boardService.getBoardArticles(page, size);
        // 전체 게시글 수 가져오기
        int totalArticleCnt = boardService.getTotalArticleCnt();

        // 마지막 페이지 여부 계산
        boolean last = (page * size) >= totalArticleCnt;

        return BoardListResponseDTO.builder()
                .articles(articles)
                .last(last)
                .build();
    }

    @PostMapping
    public void saveArticle(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("hiddenUserId") String userId,
            @RequestParam("file") MultipartFile file
    ) {
        boardService.saveArticle(userId, title, content,  file);
    }

}
