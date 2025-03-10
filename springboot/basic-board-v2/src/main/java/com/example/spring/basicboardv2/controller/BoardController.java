package com.example.spring.basicboardv2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    @GetMapping("/")
    public String boardList() {
        return "board-list";
    }

    @GetMapping("/write")
    public String boardWrite() {
        return "board-write";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "board-detail";
    }
}
